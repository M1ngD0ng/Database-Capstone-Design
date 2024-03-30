<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="examples.dto.ProducesDTO"%>
<%@ page import="examples.dao.ProducesDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.util.List"%>

<% 
	request.setCharacterEncoding("UTF-8");
	
	ProducesDAO pd=new ProducesDAO();
	List<ProducesDTO> PList = pd.getProducess();
	for(ProducesDTO P : PList) { 
	 out.println("Producer ssn : "+P.getProducer_Pssn()); 
	 out.println("<br>Movie ID : "+P.getMovie_Mid()); 
	 out.println("<br><br>"); 
}

%>