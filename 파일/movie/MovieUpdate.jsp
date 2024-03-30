<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.MovieDTO"%>
<%@ page import="examples.dao.MovieDAO"%>
<%@ page import="java.io.PrintWriter"%>

<% 
	request.setCharacterEncoding("UTF-8");
	Integer Mid=null;
	String Mtitle=null;
	String Director_Dssn=null;
	
	if(request.getParameter("id")!=null){
		Mid= Integer.parseInt(request.getParameter("id"));
	}
	if(request.getParameter("title")!=null){
		Mtitle=(String) request.getParameter("title");
	}
	if(request.getParameter("dssn")!=null){
		Director_Dssn=(String) request.getParameter("dssn");
	}
	if(Mid==null||Mtitle==null||Director_Dssn==null){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	MovieDAO movie=new MovieDAO();
	MovieDTO movieT=new MovieDTO(Mid, Mtitle, Director_Dssn);
	int result=movie.updateMovie(movieT);
	
	
	if(result>=1){
		PrintWriter script=response.getWriter();
		script.println("<script>");
		script.println("alert('Update Success!')");
		script.println("location.href='Movie.html';");
		script.println("</script>");
		script.close();
		return;
	}
%>