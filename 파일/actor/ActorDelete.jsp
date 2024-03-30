<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.ActorDTO"%>
<%@ page import="examples.dao.ActorDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Assn=null;
	
	if(request.getParameter("assn")!=null){
		Assn=(String) request.getParameter("assn");
	}
	if(Assn==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	ActorDAO actor=new ActorDAO();
	int result=actor.deleteActor(Assn);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Delete Success!')");
		script.println("location.href='Actor.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>