package setvlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PutScoreServlet
 */
@WebServlet("/putScore.do")
public class PutScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutScoreServlet() {
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
		HttpSession session = request.getSession();
		String data = (String)session.getAttribute("LOGIN");
		if(data == null) {
			response.sendRedirect("login.jsp?MSG=Y");
		}else {
		String id = request.getParameter("ID");
		String name = request.getParameter("NAME");
		String major = request.getParameter("MAJOR");
		String gender = request.getParameter("GENDER");
		String subject = request.getParameter("SUBJECT");
		String grade = request.getParameter("GRADE");
		//작성한 연월일시분초를 생성하기 위해 달력객체(Calendar)를 생성
		Calendar c = Calendar.getInstance();//달력 객체 생성
		//달력객체가 가지고 있는 메서드 호출
		long times = c.getTimeInMillis();//1970년 1월1일부터 오늘까지의 초를 획득
		Timestamp ts = new Timestamp(times);//위에서 획득한 초를 이용해 객체생성
		String ymdhms = String.valueOf(ts);//위의 객체를 문자열로 변환
		String insert = "insert into std_score values(?,?,?,?,?,?,?,?)";
		int result = -1;
		Connection con = null; PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
			pstmt = con.prepareStatement(insert);
			pstmt.setInt(1, Integer.parseInt(id));
			pstmt.setString(2, name);
			pstmt.setString(3, major);
			pstmt.setString(4, gender);
			pstmt.setString(5, subject);
			pstmt.setString(6, grade);
			pstmt.setString(7, data);
			pstmt.setString(8, ymdhms);
			result = pstmt.executeUpdate();//pstmt로 데이터를 넣은후 삽입을 시행
			response.sendRedirect("putScoreResult.jsp?R="+result);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally {
			try {
				if(con != null)con.close(); if(pstmt != null)pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	}	

}
