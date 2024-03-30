<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.MovieDTO"%>
<%@ page import="examples.dao.MovieDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	Integer Mid=null;
	
	if(request.getParameter("id")!=null){
		Mid= Integer.parseInt(request.getParameter("id"));
	}
	if(Mid==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	MovieDAO movie=new MovieDAO();
	int result=movie.deleteMovie(Mid);
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Delete Success!')");
		script.println("location.href='Movie.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>