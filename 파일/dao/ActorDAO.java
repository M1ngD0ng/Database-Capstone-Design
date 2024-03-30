package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.ActorDTO;


public class ActorDAO {
	private static String dburl="jdbc:mysql://localhost/mydb?serverTimezone=Asia/Seoul";
	private static String dbUser="root";
	private static String dbpasswd="gkgk369";
	
	public ActorDTO getActor(String Assn) {
		ActorDTO actor=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="SELECT Assn, Aname, Director_Dssn, Producer_Pssn FROM Actor WHERE Assn = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
				
				ps.setString(1, Assn);
				
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						String A_ssn=rs.getString(1);
						String Aname=rs.getString(2);
						String Director_Dssn=rs.getString(3);
						String Producer_Pssn=rs.getString(4);
						actor=new ActorDTO(A_ssn, Aname, Director_Dssn, Producer_Pssn);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return actor;
	}
	
	public int addActor(ActorDTO actor) {
		int insertCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO Actor (Assn, Aname, Director_Dssn, Producer_Pssn) VALUES ( ?, ? ,?, ? )";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, actor.getAssn());
			ps.setString(2, actor.getAname());
			ps.setString(3, actor.getDirector_Dssn());
			ps.setString(4, actor.getProducer_Pssn());
			
			insertCount=ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	
	public int deleteActor(String Assn) {
		int deleteCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="DELETE FROM Actor WHERE Assn = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, Assn);
			deleteCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}
	
	public int updateActor(ActorDTO actor) {
		int updateCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update Actor set Aname=?, Director_Dssn=?, Producer_Pssn=? where Assn = ?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, actor.getAname());
			ps.setString(2, actor.getDirector_Dssn());
			ps.setString(3, actor.getProducer_Pssn());
			ps.setString(4, actor.getAssn());
			
			updateCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return updateCount;
	}
	
	public List<ActorDTO> getActors(){
		List<ActorDTO> list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT Assn, Aname, Director_Dssn, Producer_Pssn FROM Actor order by Assn desc";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery()){
				while (rs.next()) {
					String A_ssn=rs.getString(1);
					String Aname=rs.getString(2);
					String Director_Dssn=rs.getString(3);
					String Producer_Pssn=rs.getString(4);
					
					ActorDTO actor=new ActorDTO(A_ssn, Aname, Director_Dssn, Producer_Pssn);
					
					list.add(actor);
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
