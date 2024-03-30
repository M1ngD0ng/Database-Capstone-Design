<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.ProducerDTO"%>
<%@ page import="examples.dao.ProducerDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Pssn=null;
	String Pname=null;
	
	if(request.getParameter("ssn")!=null){
		Pssn=(String) request.getParameter("ssn");
	}
	if(request.getParameter("name")!=null){
		Pname=(String) request.getParameter("name");
	}
	if(Pssn==null||Pname==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	ProducerDAO prod=new ProducerDAO();
	ProducerDTO prodT=new ProducerDTO(Pssn, Pname);
	int result=prod.addProducer(prodT);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Insert Success!')");
		script.println("location.href='Producer.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>