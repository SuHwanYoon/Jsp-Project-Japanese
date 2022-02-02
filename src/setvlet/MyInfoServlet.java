package setvlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyInfoServlet
 */
@WebServlet("/myInfo.do")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String data = (String)session.getAttribute("LOGIN");
		String infoid = null;
		if(data == null) {
			response.sendRedirect("login.jsp?MSG=NOLOGIN");
		}else {
			String select = "select mid from member where mid=?";
			Connection con = null; PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
				pstmt = con.prepareStatement(select);
				pstmt.setString(1, data);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					infoid = rs.getString(1);

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
			request.setAttribute("INFOID", infoid);
			RequestDispatcher rd = request.getRequestDispatcher("myInfo.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
