<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.DirectorDTO"%>
<%@ page import="examples.dao.DirectorDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Dssn=null;
	String Dname=null;
	
	if(request.getParameter("ssn")!=null){
		Dssn=(String) request.getParameter("ssn");
	}
	if(request.getParameter("name")!=null){
		Dname=(String) request.getParameter("name");
	}
	if(Dssn==null||Dname==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	DirectorDAO direc=new DirectorDAO();
	DirectorDTO direcT=new DirectorDTO(Dssn, Dname);
	int result=direc.updateDirector(direcT);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Update Success!')");
		script.println("location.href='Director.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>