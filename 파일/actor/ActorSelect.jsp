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
	ActorDTO actorT=actor.getActor(Assn);
	
	if(actorT==null)
	       out.println("<h3> Actor is null </h3>");
	    else {
	    	out.println("<h3> Actor ssn :  "+ actorT.getAssn()+"</h3><h3> Actor name : "+
	    			actorT.getAname()+"</h3><h3> Director_Dssn : "+ actorT.getDirector_Dssn()+
	    "</h3><h3> Producer_Pssn : "+actorT.getProducer_Pssn()+"</h3><br>");
	    }
	
%>