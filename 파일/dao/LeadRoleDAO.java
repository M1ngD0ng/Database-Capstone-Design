package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.LeadRoleDTO;

public class LeadRoleDAO {
	private static String dburl="jdbc:mysql://172.17.0.2:3306/testdbC";
	private static String dbUser="root";
	private static String dbpasswd="root";
	
	public LeadRoleDTO getLeadRole(String actor_Assn1, Integer movie_Mid) {
		LeadRoleDTO leadRole=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="SELECT actor_Assn1, movie_Mid, actor_assn2 FROM lead_role WHERE actor_Assn1 = ? and movie_Mid = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
				
				ps.setString(1, actor_Assn1);
				ps.setInt(2, movie_Mid);
				
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						String Assn1=rs.getString(1);
						int Mid=rs.getInt(2);
						String Assn2=rs.getString(3);
						leadRole=new LeadRoleDTO(Assn1, Mid, Assn2);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return leadRole;
	}
	
	public int addLeadRole(LeadRoleDTO leadRole) {
		int insertCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO lead_role (actor_Assn1, movie_Mid, actor_assn2) VALUES ( ?, ?, ? )";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, leadRole.getActor_Assn1());
			ps.setInt(2, leadRole.getMovie_Mid());
			ps.setString(3, leadRole.getActor_assn2());
			
			insertCount=ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public int deleteLeadRole(String actor_Assn1, Integer movie_Mid) {
		int deleteCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="DELETE FROM lead_role WHERE actor_Assn1 = ? and movie_Mid = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, actor_Assn1);
			ps.setInt(2, movie_Mid);
			deleteCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}
	
	public int updateLeadRole(LeadRoleDTO leadRole) {
		int updateCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update lead_role set actor_assn2=? where actor_Assn1 = ? and movie_Mid = ?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, leadRole.getActor_assn2());
			ps.setString(2, leadRole.getActor_Assn1());
			ps.setInt(3, leadRole.getMovie_Mid());
			
			updateCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}

	public List<LeadRoleDTO> getLeadRoles(){
		List<LeadRoleDTO> list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT actor_Assn1, movie_Mid, actor_assn2 FROM lead_role order by movie_Mid desc";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery()){
				while (rs.next()) {
					String Assn1=rs.getString(1);
					int Mid=rs.getInt(2);
					String Assn2=rs.getString(3);
					
					LeadRoleDTO leadRole=new LeadRoleDTO(Assn1, Mid, Assn2);
					
					list.add(leadRole);
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