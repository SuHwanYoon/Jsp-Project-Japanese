package setvlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyInfoModify
 */
@WebServlet("/myInfoModify.do")
public class MyInfoModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoModify() {
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
		request.setCharacterEncoding("euc-kr");
		String minfoid = request.getParameter("MINFOID");
		String btn = request.getParameter("BTN");
		if(btn.equals("DROP")) {
			String delete ="delete from member where mid=?";
			Connection con = null; PreparedStatement pstmt = null;
			int result = -1;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
				pstmt = con.prepareStatement(delete);
				pstmt.setString(1, minfoid);
				result = pstmt.executeUpdate();
				response.sendRedirect("myinfoDelete.jsp?R="+result);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				try {
					if(con != null) con.close(); if(pstmt != null)pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}else if(btn.equals("PWDCHANGE")) {
			String minfopwd = request.getParameter("MINFOPWD");
			String update ="update member set pwd=? where mid=?";
			Connection con = null; PreparedStatement pstmt = null;
			int result = -1;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
				pstmt = con.prepareStatement(update);
				pstmt.setString(1, minfopwd);
				pstmt.setString(2, minfoid);
				result = pstmt.executeUpdate();
				response.sendRedirect("myInfoUpdate.jsp?R="+result);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				try {
					if(con != null) con.close(); if(pstmt != null)pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}

}
