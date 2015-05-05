<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import = "com.team2.model.*" %>
<%@ page import = "com.team2.model.customer.*" %>
<%@ page import = "com.team2.model.ticket.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String dfrom =request.getParameter("dfrom");
String dto =request.getParameter("dto");
String afrom =request.getParameter("afrom");
String ato =request.getParameter("ato");

String dbfrom =request.getParameter("dbfrom");
String dbto =request.getParameter("dbto");
String abfrom =request.getParameter("abfrom");
String abto =request.getParameter("abto");

int dfromTime=Integer.valueOf(dfrom);
int dtoTime=Integer.valueOf(dto);
int afromTime=Integer.valueOf(afrom);
int atoTime=Integer.valueOf(ato);

int dbfromTime=Integer.valueOf(dbfrom);
int dbtoTime=Integer.valueOf(dbto);
int abfromTime=Integer.valueOf(abfrom);
int abtoTime=Integer.valueOf(abto);

   ArrayList<TwowayTicketPlan> result=new ArrayList<TwowayTicketPlan>(); 
   ArrayList<TwowayTicketPlan> searchResult= (ArrayList)session.getAttribute("List");
   ArrayList<TwowayTicketPlan> searchRoundSortResult= (ArrayList)request.getAttribute("sortlist"); 
  
   if(searchRoundSortResult!=null){
   result=searchRoundSortResult;}
   else{
   result=searchResult;}
   request.setCharacterEncoding("utf-8");
   
   searchRoundSortResult =TwowaySort.sortByTimeWindow(result, dfromTime, dtoTime,afromTime,atoTime,dbfromTime, dbtoTime,abfromTime,abtoTime);
   request.setAttribute("sortlist",searchRoundSortResult);
   request.getRequestDispatcher("showRoundSortTickets.jsp").forward(request, response); 
  
%>


