<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import = "com.team2.model.*" %>
<%@ page import = "com.team2.model.customer.*" %>
<%@ page import = "com.team2.model.ticket.*" %>
<%@ page import = "naihui.MyDate"%>

<%@ page import="java.text.*" %>

<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

 //define and get parameter
  String start ="";
  String destination ="";
  String date="";
  String ticketType="";
 
  request.setCharacterEncoding("utf-8");

  start = request.getParameter("start");
  destination = request.getParameter("destination");
  date=request.getParameter("startDate");
  ticketType=request.getParameter("ticketType");
  session.setAttribute("start", start);
  session.setAttribute("date", date);
  session.setAttribute("ticketType", ticketType);
  session.setAttribute("destination", destination);
  //get formatted date
  
   MyDate mydate=new MyDate(date);
   String finalDate=mydate.dateGet();
  
  
  OnewayCustomer customer = new OnewayCustomer(start ,destination, finalDate, ticketType);
  
  ArrayList<OnewayTicketPlan> searchResult= customer.searchFlight();

  request.setAttribute("list",searchResult);
  //request.getRequestDispatcher("showTickets.jsp").forward(request, response);
  

//do not find the flight customer requested
if(searchResult.isEmpty())
{
response.sendRedirect("emptyResult.jsp");
//request.getRequestDispatcher("emptyResult.jsp").forward(request, response);
}
//find the flight

else
{
//response.sendRedirect("emptyResult.jsp");
request.getRequestDispatcher("showTickets.jsp").forward(request, response);
}

 %>
 