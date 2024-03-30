<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.PerformsInDTO"%>
<%@ page import="examples.dao.PerformsInDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Actor_Assn=null;
	Integer Movie_Mid=null;
		
	if(request.getParameter("ssn1")!=null){
		Actor_Assn=(String) request.getParameter("ssn1");
	}
	if(request.getParameter("id")!=null){
		Movie_Mid= Integer.parseInt(request.getParameter("id"));
	}
	if(Actor_Assn==null||Movie_Mid==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	PerformsInDAO pf=new PerformsInDAO();
	int result=pf.deletePerformsIn(Actor_Assn, Movie_Mid);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Delete Success!')");
		script.println("location.href='PerformsIn.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>