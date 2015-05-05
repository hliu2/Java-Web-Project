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
   ArrayList<OnewayTicketPlan> result=new ArrayList<OnewayTicketPlan>();
   ArrayList<OnewayTicketPlan> searchSortResult= (ArrayList)request.getAttribute("sortlist"); 
   ArrayList<OnewayTicketPlan> searchResult= (ArrayList)session.getAttribute("List");
   if(searchSortResult!=null){
   result=searchSortResult;
   System.out.println("searchSortResult bytotalprice!=null");
   }
   else{
   result=searchResult;
      System.out.println("searchSortResult bytotalprice=null");
   }
   request.setCharacterEncoding("utf-8");
 %>

  <%
  searchSortResult =OnewaySort.sortByTotalPrice(result);
  request.setAttribute("sortlist",searchSortResult);
  request.getRequestDispatcher("showSortTickets.jsp").forward(request, response); 
  %>
  

