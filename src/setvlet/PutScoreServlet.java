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
		//�ۼ��� �����Ͻú��ʸ� �����ϱ� ���� �޷°�ü(Calendar)�� ����
		Calendar c = Calendar.getInstance();//�޷� ��ü ����
		//�޷°�ü�� ������ �ִ� �޼��� ȣ��
		long times = c.getTimeInMillis();//1970�� 1��1�Ϻ��� ���ñ����� �ʸ� ȹ��
		Timestamp ts = new Timestamp(times);//������ ȹ���� �ʸ� �̿��� ��ü����
		String ymdhms = String.valueOf(ts);//���� ��ü�� ���ڿ��� ��ȯ
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
			result = pstmt.executeUpdate();//pstmt�� �����͸� ������ ������ ����
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
