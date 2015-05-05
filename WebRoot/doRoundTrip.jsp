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
  String toDate="";
  String backDate="";
  String ticketType="";
 
  request.setCharacterEncoding("utf-8");

  start = request.getParameter("startTwo");
  destination = request.getParameter("destinationTwo");
  toDate=request.getParameter("beginDate");
  backDate=request.getParameter("returnDate");
  ticketType=request.getParameter("twoticketType");
 
  session.setAttribute("start", start);
  session.setAttribute("toDate",toDate);
  session.setAttribute("backDate",backDate);
  session.setAttribute("ticketType", ticketType);
  session.setAttribute("destination", destination);
  //get formatted date
  
   MyDate todate=new MyDate(toDate);
   String finalToDate=todate.dateGet();
   MyDate backdate=new MyDate(backDate);
   String finalBackDate=backdate.dateGet();
  
 TwowayCustomer customer = new TwowayCustomer(start, destination,finalToDate, finalBackDate, ticketType);
		ArrayList<TwowayTicketPlan> searchResult = customer.searchFlight();
		request.setAttribute("list", searchResult);
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

//response.sendRedirect("showRoundTickets.jsp");
request.getRequestDispatcher("showRoundTickets.jsp").forward(request, response);
}

 %>
 