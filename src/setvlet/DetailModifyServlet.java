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
 * Servlet implementation class DetailModifyServlet
 */
@WebServlet("/detailModify.do")
public class DetailModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailModifyServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String btn = request.getParameter("BTN");
		String id = request.getParameter("ID");//기준으로 삭제변경할 요소가 필요하다
		String userid = request.getParameter("USERID");
		HttpSession session = request.getSession();
		String data = (String)session.getAttribute("LOGIN");
		int result = -1;
		if(data == null) {
			response.sendRedirect("login.jsp?MSG=DU");
		}else {
			
			if(data.equals(userid)) {//접속 아이디와 작성자가 같을때
				if(btn.equals("DELETE")) {
					
					String delete ="delete from std_score where id=?";
					Connection con = null; PreparedStatement pstmt = null;
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con = DriverManager.getConnection(
								"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
						pstmt = con.prepareStatement(delete);
						pstmt.setInt(1, Integer.parseInt(id));
						result = pstmt.executeUpdate();
						response.sendRedirect("detailDeleteResult.jsp?R="+result);
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
				}else if(btn.equals("CHANGE")) {
					String name = request.getParameter("NAME");
					String major = request.getParameter("MAJOR");
					String gender = request.getParameter("GENDER");
					String subject= request.getParameter("SUBJECT");
					String grade = request.getParameter("GRADE");
					String update ="update std_score set name=?,major=?,"
							+ "gender=?,subject=?,grade=? where id=?";
					Connection con = null; PreparedStatement pstmt = null;
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						con = DriverManager.getConnection(
								"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
						pstmt = con.prepareStatement(update);
						pstmt.setString(1, name);
						pstmt.setString(2, major);
						pstmt.setString(3, gender);
						pstmt.setString(4, subject);
						pstmt.setString(5, grade);
						pstmt.setInt(6, Integer.parseInt(id));
						result = pstmt.executeUpdate();
						response.sendRedirect("detailUpdateResult.jsp?R="+result);
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}finally {
						try {
							if(con != null)con.close();if(pstmt != null)pstmt.close();
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
					}
				}
			}else {//다를때
				response.sendRedirect("nowriter.jsp");
			}
		
		}
	}

}
