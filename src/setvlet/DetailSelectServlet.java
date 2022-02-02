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

import student.Score;

/**
 * Servlet implementation class DetailSelectServlet
 */
@WebServlet("/detailSelect.do")
public class DetailSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		HttpSession session = request.getSession();
		String data = (String)session.getAttribute("LOGIN");
		String admin = "admin";
		Score score = new Score();
		Connection con = null; PreparedStatement pstmt = null;
		ResultSet rs = null;
		String select = "select * from std_score where id=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				score.setId(rs.getInt(1));
				score.setName(rs.getString(2));
				score.setMajor(rs.getString(3));
				score.setGender(rs.getString(4));
				score.setSubject(rs.getString(5));
				score.setGrade(rs.getString(6));
				score.setUserid(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(con != null)con.close(); if(pstmt != null)pstmt.close();
				if(rs != null)rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		
		
		if(data.equals(admin)) {
			request.setAttribute("DATA", data);
			request.setAttribute("DETAIL", score);
			request.setAttribute("ADMIN", admin);
			RequestDispatcher rdd = request.getRequestDispatcher("selectAdminDetail.jsp");
			rdd.forward(request, response);
		}else if(data.equals("")) {
			request.setAttribute("DATA", data);
			request.setAttribute("DETAIL", score);
			RequestDispatcher rd = request.getRequestDispatcher("selectDetail.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("DATA", data);
			request.setAttribute("DETAIL", score);
			RequestDispatcher rd = request.getRequestDispatcher("selectDetail.jsp");
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
