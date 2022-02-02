package setvlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		String pwd = request.getParameter("PWD");
		String select ="select pwd from member where mid=?";
		String result = null;
		Connection con = null; PreparedStatement pstmt = null; 
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String password = rs.getString(1);
				if(pwd.equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("LOGIN", id);
					result = "OK";
				}else {
					result = "NOTPWD";//입력한 아이디와 일치하는 패스워드가 없음
										//즉 아이디는 있지만 패스워드가 없음
				}
			}else {
				result = "NOID";//입력한 아이디에 해당하는 패스워드가 없음
								//즉 아이디가 없는것
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(con != null) con.close(); if(pstmt != null)pstmt.close();
				if(rs != null)rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		response.sendRedirect("loginResult.jsp?R="+result);
	}

}
