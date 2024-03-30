<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.PerformsInDTO"%>
<%@ page import="examples.dao.PerformsInDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List"%>

<% 
	request.setCharacterEncoding("UTF-8");
	
	PerformsInDAO direc=new PerformsInDAO();
	List<PerformsInDTO> PList = direc.getPerformsIns();
	for(PerformsInDTO P : PList) { 
		 out.println("Actor ssn : "+P.getActor_Assn()); 
		 out.println("<br>Movie ID : "+P.getMovie_Mid()); 
		 out.println("<br><br>"); 
	}

%>