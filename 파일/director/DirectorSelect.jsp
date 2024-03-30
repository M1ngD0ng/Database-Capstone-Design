<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.DirectorDTO"%>
<%@ page import="examples.dao.DirectorDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Dssn=null;
		
	if(request.getParameter("ssn")!=null){
		Dssn=(String) request.getParameter("ssn");
	}
	if(Dssn==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	DirectorDAO direc=new DirectorDAO();
	DirectorDTO direcT=direc.getDirector(Dssn);
	
	if(direcT==null)
	       out.println("<h3> Director is null </h3>");
	    else {
	    	out.println("<h3> Director ssn :  "+ direcT.getDssn()+"</h3><h3> Director name : "+
	    			direcT.getDname()+"</h3><br>");
	    }
	
%>