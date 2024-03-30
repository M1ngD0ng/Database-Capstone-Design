package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.ProducesDTO;

public class ProducesDAO {
	private static String dburl="jdbc:mysql://localhost/mydb?serverTimezone=Asia/Seoul";
	private static String dbUser="root";
	private static String dbpasswd="root";

	public int addProduces(ProducesDTO produces) {
		int insertCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO produces (Producer_Pssn, Movie_Mid) VALUES ( ?, ? )";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, produces.getProducer_Pssn());
			ps.setInt(2, produces.getMovie_Mid());
			
			insertCount=ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public int deleteProduces(String Producer_Pssn, Integer Movie_Mid) {
		int deleteCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="DELETE FROM produces WHERE Producer_Pssn = ? and Movie_Mid = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, Producer_Pssn);
			ps.setInt(2, Movie_Mid);
			deleteCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}
	
	public int updateProduces(ProducesDTO produces) {
		int updateCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update produces set Producer_Pssn=? where Movie_Mid = ?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, produces.getProducer_Pssn());
			ps.setInt(2, produces.getMovie_Mid());
			
			updateCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}
	
	public List<ProducesDTO> getProducess(){
		List<ProducesDTO> list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT Producer_Pssn, Movie_Mid FROM produces";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery()){
				while (rs.next()) {
					String Pssn=rs.getString(1);
					int Mid=rs.getInt(2);
					
					ProducesDTO produces=new ProducesDTO(Pssn, Mid);
					
					list.add(produces);
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
