<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import = "com.team2.model.*" %>
<%@ page import = "com.team2.model.customer.*" %>
<%@ page import = "com.team2.model.ticket.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String from =request.getParameter("from");
String to =request.getParameter("to");
String afrom =request.getParameter("afrom");
String ato =request.getParameter("ato");

int fromTime=Integer.valueOf(from);
int toTime=Integer.valueOf(to);
int afromTime=Integer.valueOf(afrom);
int atoTime=Integer.valueOf(ato);

   ArrayList<OnewayTicketPlan> result=new ArrayList<OnewayTicketPlan>();
   ArrayList<OnewayTicketPlan> timeResult= (ArrayList)request.getAttribute("sortlist"); 
   ArrayList<OnewayTicketPlan> searchResult= (ArrayList)session.getAttribute("List");
   if(timeResult!=null){
   result=timeResult;
   
   }
   else{
   result=searchResult;
  
   }
   request.setCharacterEncoding("utf-8");
   
   timeResult =OnewaySort.sortByTimeWindow(result, fromTime, toTime,afromTime, atoTime);
   request.setAttribute("sortlist",timeResult);
   request.getRequestDispatcher("showSortTickets.jsp").forward(request, response); 
  
%>


