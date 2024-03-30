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
	MovieDTO movieT=movie.getMovie(Mid);
	
	if(movieT==null)
	       out.println("<h3> Movie is null </h3>");
	    else {
	    	out.println("<h3> Movie id :  "+ movieT.getMid()+"</h3><h3> Movie title : "+
	    			movieT.getMtitle()+"</h3><h3> Director_Dssn : "+ movieT.getDirector_Dssn()+"</h3><br>");
	    }
	
%>