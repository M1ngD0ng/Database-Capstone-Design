package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.MovieDTO;

public class MovieDAO {
	private static String dburl="jdbc:mysql://localhost/mydb?serverTimezone=Asia/Seoul";
	private static String dbUser="root";
	private static String dbpasswd="gkgk369";
	
	public MovieDTO getMovie(Integer Mid) {
		MovieDTO movie=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="SELECT Mid, Mtitle, Director_Dssn FROM Movie WHERE Mid = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
				
				ps.setInt(1, Mid);
				
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						int M_id=rs.getInt(1);
						String Mtitle=rs.getString(2);
						String Director_Dssn=rs.getString(3);
						movie=new MovieDTO(M_id, Mtitle, Director_Dssn);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return movie;
	}
	
	public int addMovie(MovieDTO movie) {
		int insertCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO Movie (Mid, Mtitle, Director_Dssn) VALUES ( ?, ? ,? )";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setInt(1, movie.getMid());
			ps.setString(2, movie.getMtitle());
			ps.setString(3, movie.getDirector_Dssn());
			
			insertCount=ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public int deleteMovie(Integer Mid) {
		int deleteCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="DELETE FROM Movie WHERE Mid = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setInt(1, Mid);
			deleteCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}
	
	public int updateMovie(MovieDTO movie) {
		int updateCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update Movie set Mtitle=?, Director_Dssn=? where Mid = ?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, movie.getMtitle());
			ps.setString(2, movie.getDirector_Dssn());
			ps.setInt(3, movie.getMid());
			
			updateCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return updateCount;
	}
	
	public List<MovieDTO> getMovies(){
		List<MovieDTO> list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT Mid, Mtitle, Director_Dssn FROM Movie order by Mid desc";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery()){
				while (rs.next()) {
					int M_id=rs.getInt(1);
					String Mtitle=rs.getString(2);
					String Director_Dssn=rs.getString(3);
					
					MovieDTO movie=new MovieDTO(M_id, Mtitle, Director_Dssn);
					
					list.add(movie);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}
