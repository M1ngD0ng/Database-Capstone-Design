<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.ActorDTO"%>
<%@ page import="examples.dao.ActorDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Assn=null;
	String Aname=null;
	String Director_Dssn=null;
	String Producer_Pssn=null;
	
	if(request.getParameter("assn")!=null){
		Assn=(String) request.getParameter("assn");
	}
	if(request.getParameter("aname")!=null){
		Aname=(String) request.getParameter("aname");
	}
	if(request.getParameter("dssn")!=null){
		Director_Dssn=(String) request.getParameter("dssn");
	}
	if(request.getParameter("pssn")!=null){
		Producer_Pssn=(String) request.getParameter("pssn");
	}
	if(Assn==null||Aname==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	ActorDAO actor=new ActorDAO();
	ActorDTO actorT=new ActorDTO(Assn, Aname, Director_Dssn, Producer_Pssn);
	int result=actor.updateActor(actorT);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Update Success!')");
		script.println("location.href='Actor.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>