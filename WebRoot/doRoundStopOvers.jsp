<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import = "com.team2.model.*" %>
<%@ page import = "com.team2.model.customer.*" %>
<%@ page import = "com.team2.model.ticket.*" %>
<%@ page import = "naihui.MyDate"%>
<%@ page import="java.lang.*" %>
<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
//define and get parameter
   ArrayList<TwowayTicketPlan> result=new ArrayList<TwowayTicketPlan>();
   ArrayList<TwowayTicketPlan> searchRoundSortResult= (ArrayList)request.getAttribute("sortlist"); 
   ArrayList<TwowayTicketPlan> searchResult= (ArrayList)session.getAttribute("List");
   if(searchRoundSortResult!=null){
   result=searchRoundSortResult;}
   else{
   result=searchResult;}
   request.setCharacterEncoding("utf-8");
 %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'doselectStopovers.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  String toStop="";
  String backStop="";
  toStop=request.getParameter("toStopOver");
  int toStopOvers=Integer.valueOf(toStop); 
  backStop=request.getParameter("backStopOver");
  int backStopOvers=Integer.valueOf(backStop);
  searchRoundSortResult =TwowaySort.selectStopovers(result,toStopOvers,backStopOvers);
  request.setAttribute("sortlist",searchRoundSortResult);
  request.getRequestDispatcher("showRoundSortTickets.jsp").forward(request, response); 
  %>
  


    <br>
  </body>
</html>
