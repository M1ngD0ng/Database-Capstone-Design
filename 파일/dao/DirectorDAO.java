package examples.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import examples.dto.DirectorDTO;

public class DirectorDAO {
	private static String dburl="jdbc:mysql://localhost/mydb?serverTimezone=Asia/Seoul";
	private static String dbUser="root";
	private static String dbpasswd="gkgk369";
	
	public DirectorDTO getDirector(String dssn) {
		DirectorDTO director=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="SELECT Dssn, Dname FROM Director WHERE dssn = ?";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
				
				ps.setString(1, dssn);
				
				try(ResultSet rs=ps.executeQuery()){
					if(rs.next()) {
						String D_ssn=rs.getString(1);
						String Dname=rs.getString(2);
						director=new DirectorDTO(D_ssn, Dname);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		return director;
	}
	
	public int addDirector(DirectorDTO director) {
		int insertCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("연결 성공!!~");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="INSERT INTO Director (Dssn, Dname) VALUES ( ?, ? )";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, director.getDssn());
			ps.setString(2, director.getDname());
			
			insertCount=ps.executeUpdate();
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public int deleteDirector(String dssn) {
		int deleteCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="DELETE FROM Director WHERE dssn = ?";
	
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, dssn);
			deleteCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return deleteCount;
	}
	
	public int updateDirector(DirectorDTO director) {
		int updateCount=0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql="update Director set Dname=? where Dssn = ?;";

		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){

			ps.setString(1, director.getDname());
			ps.setString(2, director.getDssn());
			
			updateCount=ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return updateCount;
	}
	
	public List<DirectorDTO> getDirectors(){
		List<DirectorDTO> list=new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql="SELECT Dssn, Dname FROM Director order by Dssn desc";
		
		try(Connection conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
				PreparedStatement ps=conn.prepareStatement(sql)){
			try(ResultSet rs=ps.executeQuery()){
				while (rs.next()) {
					String D_ssn=rs.getString(1);
					String Dname=rs.getString(2);
					
					DirectorDTO director=new DirectorDTO(D_ssn, Dname);
					
					list.add(director);
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
