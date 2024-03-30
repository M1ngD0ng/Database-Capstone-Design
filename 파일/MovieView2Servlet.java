package examples;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieView2Servlet
 */
@WebServlet("/MovieView2Servlet")
public class MovieView2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieView2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출!");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/mydb?serverTimezone=Asia/Seoul";
			
			conn = DriverManager.getConnection(url, "root", "gkgk369");
			System.out.println("연결 성공!!~");
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * from view2;";
			
			rs = stmt.executeQuery(sql);

			out.println("<h3>View 2</h3>");
			while(rs.next()) {
				String name=rs.getString(1);
				String title=rs.getString(2);
				out.println("Actor Name : "+ name +"<br>Movie_Title : "+ title+"<br><br>");
			}
		}
		catch( ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		}
		catch( SQLException e) {
			System.out.println("에러" + e);
		}
		finally {
			try {
				if( conn != null && !conn.isClosed()) {
					conn.close();
				}
				if( stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if( rs != null && !rs.isClosed()) {
					rs.close();
				}
			}
			catch( SQLException e) {
				e.printStackTrace();
			}
		}
		out.close();
	}
}
