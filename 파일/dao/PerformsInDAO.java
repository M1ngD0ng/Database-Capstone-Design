package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.PerformsInDTO;

public class PerformsInDAO {
	private static String dburl="jdbc:mysql://localhost/mydb?serverTimezone=Asia/Seoul";
	private static String dbUser="root";
	private static String dbpasswd="gkgk369";
	
	public int addPerformsIn(PerformsInDTO perform) {
		int insertCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO performs_in (Actor_Assn, Movie_Mid) VALUES ( ?, ? )";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, perform.getActor_Assn());
			ps.setInt(2, perform.getMovie_Mid());
			
			insertCount=ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public int deletePerformsIn(String actor_Assn, Integer movie_Mid) {
		int deleteCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="DELETE FROM performs_in WHERE actor_Assn = ? and movie_Mid = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, actor_Assn);
			ps.setInt(2, movie_Mid);
			deleteCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}
	
	public int updatePerformsIn(PerformsInDTO perform) {
		int updateCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update performs_in set actor_Assn=? where movie_Mid = ?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, perform.getActor_Assn());
			ps.setInt(2, perform.getMovie_Mid());
			
			updateCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}
	
	public List<PerformsInDTO> getPerformsIns(){
		List<PerformsInDTO> list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT DISTINCT Actor_Assn, Movie_Mid FROM performs_in";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery()){
				while (rs.next()) {
					String Assn=rs.getString(1);
					int Mid=rs.getInt(2);
					
					PerformsInDTO perform=new PerformsInDTO(Assn, Mid);
					
					list.add(perform);
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
