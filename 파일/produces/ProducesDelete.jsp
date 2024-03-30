<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.ProducesDTO"%>
<%@ page import="examples.dao.ProducesDAO"%>
<%@ page import="java.io.PrintWriter"%>


<% 
	request.setCharacterEncoding("UTF-8");
	String Producer_Pssn=null;
	Integer Movie_Mid=null;
		
	if(request.getParameter("ssn1")!=null){
		Producer_Pssn=(String) request.getParameter("ssn1");
	}
	if(request.getParameter("id")!=null){
		Movie_Mid= Integer.parseInt(request.getParameter("id"));
	}
	if(Producer_Pssn==null||Movie_Mid==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	ProducesDAO lr=new ProducesDAO();
	int result=lr.deleteProduces(Producer_Pssn, Movie_Mid);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Delete Success!')");
		script.println("location.href='Produces.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>