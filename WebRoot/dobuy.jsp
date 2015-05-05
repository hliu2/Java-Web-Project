<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import = "com.team2.model.*" %>
<%@ page import = "com.team2.model.customer.*" %>
<%@ page import = "com.team2.model.ticket.*" %>
<%@ page import = "naihui.MyDate"%>

<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
String minseat=request.getParameter("minseat");

String num1=request.getParameter("num1");
String num2=request.getParameter("num2");
String num3=request.getParameter("num3");
String numOfTicket=request.getParameter("numbers");

int numOfT=Integer.valueOf(numOfTicket);



Boolean n1=num1.equals("null");
Boolean n2=num2.equals("null");
Boolean n3=num3.equals("null");

Boolean m1=true;
Boolean m2=true;
Boolean m3=true;

FlightData data=new FlightData();
DatabaseManagement db=DatabaseManagement.getInstance();
String ticketType=(String)session.getAttribute("ticketType");
if(!n1)
{
for(int k=0;k<numOfT;k++)
{
StringBuffer ticketT=new StringBuffer(ticketType);
if(ticketT.charAt(0)=='f')
{
ticketT.setCharAt(0, 'F');
}
if(ticketT.charAt(0)=='c')
{
ticketT.setCharAt(0, 'C');
}

String Type=ticketT.toString();
String fnumber1=data.generatorFactory(num1, Type);
m1=db.buyTickets(fnumber1);
}
}

if(m1)
{
if(!n2)
{
for(int k=0;k<numOfT;k++)
{
StringBuffer ticketT=new StringBuffer(ticketType);
if(ticketT.charAt(0)=='f')
{
ticketT.setCharAt(0, 'F');
}
if(ticketT.charAt(0)=='c')
{
ticketT.setCharAt(0, 'C');
}

String Type=ticketT.toString();
String fnumber2=data.generatorFactory(num2,Type);
m2=db.buyTickets(fnumber2);
}
}
if(!n3)
{
for(int k=0;k<numOfT;k++)
{
StringBuffer ticketT=new StringBuffer(ticketType);
if(ticketT.charAt(0)=='f')
{
ticketT.setCharAt(0, 'F');
}
if(ticketT.charAt(0)=='c')
{
ticketT.setCharAt(0, 'C');
}

String Type=ticketT.toString();
String fnumber3=data.generatorFactory(num3, Type);
m3=db.buyTickets(fnumber3);
}
}
response.sendRedirect("successBuy.jsp");
}
else
{
response.sendRedirect("failureBuy.jsp");
}
//StringBuffer finalnum =new StringBuffer(fnumber);


//String a=flightnumber[2];
//System.out.println(fnumber);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dobuy.jsp' starting page</title>
    
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
 

 <br>Congratulations!You have successfully bought the ticket.
 <br>
 <br>Enjoy your trip!
 <br>
<a href="index.jsp">Buy another tickets!</a>


  </body>
</html>
