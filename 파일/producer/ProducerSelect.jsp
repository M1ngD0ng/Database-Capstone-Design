<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.ProducerDTO"%>
<%@ page import="examples.dao.ProducerDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	String Pssn=null;
		
	if(request.getParameter("ssn")!=null){
		Pssn=(String) request.getParameter("ssn");
	}
	if(Pssn==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	ProducerDAO prod=new ProducerDAO();
	ProducerDTO prodT=prod.getProducer(Pssn);
	
	if(prodT==null)
	       out.println("<h3> Producer is null </h3>");
	    else {
	    	out.println("<h3> Producer ssn :  "+ prodT.getPssn()+"</h3><h3> Producer name : "+
	    			prodT.getPname()+"</h3><br>");
	    }
	
%>