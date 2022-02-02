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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdDeleteServlet
 */
@WebServlet("/addelete.doo")
public class AdDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String id = request.getParameter("ID");
		String add = request.getParameter("ADD");
		HttpSession session = request.getSession();
		String data = (String)session.getAttribute("LOGIN");
		int result = -1;
		if(data.equals("admin")) {
			if(add.equals("관리자삭제")) {
				
				String delete ="delete from std_score where id=?";
				Connection con = null; PreparedStatement pstmt = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
					pstmt = con.prepareStatement(delete);
					pstmt.setInt(1, Integer.parseInt(id));
					result = pstmt.executeUpdate();
					response.sendRedirect("adminDeleteResult.jsp?R="+result);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}finally {
					try {
						if(con != null) con.close(); if(pstmt != null)pstmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
						// TODO: handle exception
					}
				}
			}
		}
	}

}
