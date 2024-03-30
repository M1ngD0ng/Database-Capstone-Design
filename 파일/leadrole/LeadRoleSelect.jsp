<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.LeadRoleDTO"%>
<%@ page import="examples.dao.LeadRoleDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List"%>

<% 
	request.setCharacterEncoding("UTF-8");
	
	LeadRoleDAO direc=new LeadRoleDAO();
	List<LeadRoleDTO> LList = direc.getLeadRoles();
	for(LeadRoleDTO L : LList) { 
	 out.println("Actor ssn1 : "+L.getActor_Assn1()); 
	 out.println("<br>Actor ssn2 : "+L.getActor_assn2()); 
	 out.println("<br>Movie ID : "+L.getMovie_Mid()); 
	 out.println("<br><br>"); 
}

%>