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

import student.Score;

/**
 * Servlet implementation class SelectGradeServlet
 */
@WebServlet("/selectGrade.do")
public class SelectGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectGradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("PAGE_NUM");
		if(page == null) page="1";//문자열 "1"을 넣어준다
		String getCount = "select count(*) from std_score";
		int pageCount = 0;//페이지 갯수
		int totalCount = 0;//총 데이터 행의 갯수
		String select ="select id,name,major,subject,grade,userid,std_date "
				+ "from (select rownum rn,id,name,major,subject,grade,userid,std_date "
				+ " from (select id,name,major,subject,grade,userid,std_date from std_score order by id)) "
				+ " where rn > (?-1)*5 and rn <((?-1)*5)+6";
		Connection con = null; PreparedStatement pstmt = null;
		ResultSet rs = null;
		Score[] score = null;//검색된 데이터행들이 담겨질 배열 선언
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/XE","hr","hr");
			pstmt = con.prepareStatement(select);
			pstmt.setInt(1, Integer.parseInt(page));//계산을 위해서 페이지를 숫자로 넣어준다
			pstmt.setInt(2, Integer.parseInt(page));//계산을 위해서 페이지를 숫자로 넣어준다
			rs = pstmt.executeQuery();//데이터행건수찾기위한 조회
			int count = 0;
			while(rs.next()) {
				count++;
			}
			score = new Score[count];//조회된 데이터행 갯수만큼의 빈배열을 생성
			rs = pstmt.executeQuery();//빈배열에 저장하기위한 데이터조회
			int idx = 0;
			while(rs.next()) {
				Score sco = new Score();//배열에 저장할 데이터를 저장할 DTO 객체
				sco.setId(rs.getInt(1));
				sco.setName(rs.getString(2));
				sco.setMajor(rs.getString(3));
				sco.setSubject(rs.getString(4));
				sco.setGrade(rs.getString(5));
				sco.setUserid(rs.getString(6));
				sco.setStd_date(rs.getString(7));
				score[idx] = sco;
				idx++;
			}
			//페이지 작성
			pstmt = con.prepareStatement(getCount);//데이터행 갯수
			rs = pstmt.executeQuery();//데이터행갯수로 페이지 수 구하기위한 조회
			if(rs.next()) {
				totalCount = rs.getInt(1);
				pageCount = totalCount/5;
				if(totalCount%5 != 0)pageCount++;
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
		request.setAttribute("SCORE", score);
		request.setAttribute("PAGE", pageCount);
		RequestDispatcher rd = request.getRequestDispatcher("selectSudentResult.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
