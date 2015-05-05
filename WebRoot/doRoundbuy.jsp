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
String dnum1=request.getParameter("dnum1");
String dnum2=request.getParameter("dnum2");
String dnum3=request.getParameter("dnum3");
String anum1=request.getParameter("anum1");
String anum2=request.getParameter("anum2");
String anum3=request.getParameter("anum3");
String numOfTicket=request.getParameter("numbers");

int numOfT=Integer.valueOf(numOfTicket);

Boolean dn1=dnum1.equals("null");
Boolean dn2=dnum2.equals("null");
Boolean dn3=dnum3.equals("null");
Boolean an1=anum1.equals("null");
Boolean an2=anum2.equals("null");
Boolean an3=anum3.equals("null");

Boolean dm1=true;
Boolean dm2=true;
Boolean dm3=true;
Boolean am1=true;
Boolean am2=true;
Boolean am3=true;


FlightData data=new FlightData();
DatabaseManagement db=DatabaseManagement.getInstance();
String ticketType=(String)session.getAttribute("ticketType");
if(!dn1)
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
String dfnumber1=data.generatorFactory(dnum1, Type);
dm1=db.buyTickets(dfnumber1);
}
}

if(dm1)
{
if(!dn2)
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
String dfnumber2=data.generatorFactory(dnum2,Type);
dm2=db.buyTickets(dfnumber2);
}
}

if(!dn3)
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
String dfnumber3=data.generatorFactory(dnum3, Type);
dm3=db.buyTickets(dfnumber3);
}
}

if(!an1)
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
String afnumber1=data.generatorFactory(anum1, Type);
am1=db.buyTickets(afnumber1);
}
}

if(!an2)
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
String afnumber2=data.generatorFactory(anum2,Type);
am2=db.buyTickets(afnumber2);
}
}

if(!an3)
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
String afnumber3=data.generatorFactory(anum3, Type);
am3=db.buyTickets(afnumber3);
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
    
    <title>My JSP 'doRoundbuy.jsp' starting page</title>
    
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
