<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.LeadRoleDTO"%>
<%@ page import="examples.dao.LeadRoleDAO"%>
<%@ page import="java.io.PrintWriter"%>


<% 
	request.setCharacterEncoding("UTF-8");
	String actor_Assn1=null;
	Integer movie_Mid=null;
	String actor_Assn2=null;
	
	if(request.getParameter("ssn1")!=null){
		actor_Assn1=(String) request.getParameter("ssn1");
	}
	if(request.getParameter("id")!=null){
		movie_Mid= Integer.parseInt(request.getParameter("id"));
	}
	if(request.getParameter("ssn2")!=null){
		actor_Assn2=(String) request.getParameter("ssn2");
	}
	if(actor_Assn1==null||movie_Mid==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	LeadRoleDAO lr=new LeadRoleDAO();
	LeadRoleDTO lrT=new LeadRoleDTO(actor_Assn1, movie_Mid, actor_Assn2);
	int result=lr.updateLeadRole(lrT);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Update Success!')");
		script.println("location.href='LeadRole.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>