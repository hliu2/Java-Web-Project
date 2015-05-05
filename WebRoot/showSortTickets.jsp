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
SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<% 
//define and get parameter
   ArrayList<OnewayTicketPlan> result=new ArrayList<OnewayTicketPlan>();
   ArrayList<OnewayTicketPlan> searchSortResult= (ArrayList)request.getAttribute("sortlist"); 
   ArrayList<OnewayTicketPlan> searchResult= (ArrayList)session.getAttribute("List");
   if(searchSortResult!=null){
   result=searchSortResult;
   System.out.println("searchSortResult!=null");
   }
   else{
   result=searchResult;
   System.out.println("searchSortResult=null");
   }
  request.setAttribute("sortlist",result);
   request.setCharacterEncoding("utf-8");
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
<head>
     <meta charset="UTF-8">
     <title>One way Information Page
     </title>
     <style type="text/css">
#mainContainer{
	height:100%;
	width:100%; 
}
#down{
	display:flex;
}
#up{
    display:flex;
}
#head{
    display:flex; 
    height:5%;
    width:100%;
}
#upleft{
	height:15%;
	width:20%;
}
#upright{
    height:15%;
    width:80%
}

#left{
	height:80%;
	width:20%;
}
#right{
	height:80%;
	width:80%;
}
 #time .ui-slider-range { background: #f6931f; }
  #atime .ui-slider-range { background: #f6931f; }
</style>

<link type="text/css" rel="stylesheet" href="onewayInformation.css"></link>
<link href="SpryAssets/SpryMenuBarHorizontal.css" rel="stylesheet" type="text/css"></link>
<script src="SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script src="js/showTickets.js"></script>

<style>
 #time .ui-slider-range { background: #f6931f; }
  #time .ui-slider-handle { border-color: #f6931f; }
</style>
<style type="text/css">
a:link,a:visited{
 text-decoration:none;  
}
</style>
</head>

<body style="background-color:#FFFFFF;background-size:cover">
<!-- Fixed navbar -->

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          
          <a class="navbar-brand" href="index.jsp">Fly by Night Airlines</a>
        </div>
       
      </div>
    </nav>
<div id="container">
<div>
<br>
<br>
</div>

<div id="up">
 <div id="upleft">
 <br> <br>
 
 <ul id="MenuBar1" class="MenuBarHorizontal">
<li><a class="MenuBarItemSubmenu" href="#"> sortBy</a>
  <ul>
   <li><a href="doSortBytotalprice.jsp" >Total Price</a></li>
    <li><a href="doSortBytotaltime.jsp">Total Time</a></li>
      <li><a href="doSortByflighttime.jsp">Flight Time</a></li>
    </ul>
  </li>
</ul>

   <p>&nbsp;</p>
   <br>
   <ul id="MenuBar2" class="MenuBarHorizontal">
<li><a class="MenuBarItemSubmenu" href="#"> stopOvers</a>
  <ul>
   <li><a href="doSelectStopOvers.jsp?stop=0">0</a></li>
    <li><a href="doSelectStopOvers.jsp?stop=1">1</a></li>
      <li><a href="doSelectStopOvers.jsp?stop=2">2</a></li>
    </ul>
  </li>
</ul>

   <p>&nbsp;</p>  
<br>
   
 </div> 

 <div id=upright>
 <br><br>
 <table width="95%"  height="60%" border="1" >
  <tbody>
    <tr>
      <td style="text-align:center;vertical-align:middle;">
      <% //define and get parameter
  String start ="";
  String destination ="";
  String date="";
  String ticketType="";
 
  request.setCharacterEncoding("utf-8");

  start = (String)session.getAttribute("start");
  destination = (String)session.getAttribute("destination");
  date=(String)session.getAttribute("date");
  ticketType=(String)session.getAttribute("ticketType");
    %>
<form id="form" name="form" method="post" action="index.jsp"> 
  <%=start%>
  -->
  <%=destination %>
  &nbsp;
  <%=date %>
  &nbsp;
  <%=ticketType %>
  &nbsp;
  1 Traveler
   &nbsp;&nbsp;
 <input type="submit" value="SearchAgain">
</form>

  </td>
    </tr>
  </tbody>
</table>
</div>
 </div>
 <br>
<br>

<div id="down">
<div id="left">


<form id="form1" name="form1" method="post"action="doOneTimeWin.jsp" >

  <p>
<br>
  <label for="from"  >Departure From:</label>
  <input type="text" name="from" id="from" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
  <label for="to">To:</label>
  <input type="text" name="to" id="to" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">

<div id="time" style="width:100px; margin:15px;border-color: #f6931f; "></div>

<label for="afrom"  >Arrival From:</label>
  <input type="text" name="afrom" id="afrom" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
  <label for="ato">To:</label>
  <input type="text" name="ato" id="ato" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">

<div id="atime" style="width:100px; margin:15px;border-color: #f6931f; "></div>
&nbsp;&nbsp;
   <input type="submit" value="search" >
</form>
</div>




<div id="right">

<%//for 循环输出arraylist %>
       <% int a=1;
          int indexPlan=-1;
          for(OnewayTicketPlan i:result)
          {
          indexPlan++;
          float price=i.getTotalPrice();
          ArrayList<Flight> plan=i.getDeparturePlan();
          int totaltime=i.getDepartureTotalTime();
          int totalflighttime=i.getDepartureFlightTime();
          int numofstopovers=i.getDepartureNumOfStopovers();
          ArrayList<Integer> layover=i.getDepartureLayoverTimes();
          String tickettype=i.getTicketType();
          String[] flightnumber=new String[3];
          int[] seat=new int[3];
          int count=0;
          %>
          
  <table width="95%" border="1">
  <tbody>
    <tr>
    <td style="text-align:center;vertical-align:middle;">
        <br>
    $ <%=price %>&nbsp;&nbsp;
 <% 
   
 for(Flight j:plan)
          { 
          Airport depatureairport=j.getDepatureAirport();
          String depaturecode=depatureairport.getCode();
          Date departuretime1=j.getDepartureTime();
          Date departurelocaltime=TimeManagement.gmtLocal(depaturecode,departuretime1);
          String departuretime=format.format(departurelocaltime);
          Airport arrivalairport=j.getArrivalAirport();
          String arrivalcode=arrivalairport.getCode();
          Date arrivaltime1=j.getArrivalTime();
          Date arrivallocaltime=TimeManagement.gmtLocal(arrivalcode,arrivaltime1);
          String arrivaltime=format.format(arrivallocaltime);
          int flighttime=j.getFlightTime();
          int seats=0;
        
          if(tickettype.equals("firstClass")){seats=j.getFirstClassSeats();}
          if(tickettype.equals("coach")){seats=j.getCoachSeats();}
          
          int coachseats=j.getCoachSeats();
          int firstclassseats=j.getFirstClassSeats();
          flightnumber[count]=j.getFlightNumber();
         seat[count]=seats;
          count=count+1;
          %>
        <%=departuretime%>&nbsp;&nbsp;
        <%=depaturecode %>
        -->
        <%=arrivaltime %>&nbsp;&nbsp;
        <%=arrivalcode%>&nbsp;
         <%
     int flighthour=(int)flighttime/60;
     int flightmin=(int)flighttime-flighthour*60;
      %> 
      leftSeats:<%=seats%>&nbsp;
    <%=flighthour %>Hours<%=flightmin %>Mins
        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

       <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <%} %>
     <br>&nbsp;
     <%
     int totalhour=(int)totaltime/60;
     int totalmin=(int)totaltime-totalhour*60;
      %> 
       <%
     int totalflighthour=(int)totalflighttime/60;
     int totalflightmin=(int)totalflighttime-totalflighthour*60;
      %>&nbsp;&nbsp; &nbsp;&nbsp;
      LayoverTime:
      <%for(int l=0;l<layover.size();l++){ %>
      <%
     int layoverhour=(int)layover.get(l)/60;
     int layovermin=(int)layover.get(l)-layoverhour*60;
      %>  
      <%=layoverhour %>Hours<%=layovermin %>Mins&nbsp;<%} %>    
      <br><br>
      totalTime:<%=totalhour %>Hours<%=totalmin %>Mins
      &nbsp;&nbsp;
      totalFlightTime:<%=totalflighthour %>Hours<%=totalflightmin %>Mins
      &nbsp;&nbsp;
      <%=numofstopovers %>Stops
       
      <%
        Arrays.sort(seat);
       int minseat=seat[0];
       for(int u=0;u<3;u++)
       {if(seat[u]!=0)
        {
       minseat=seat[u];
       break;
        }
       }
       System.out.println("minseats is"+minseat);
     
       String num1=flightnumber[0];
       String num2=flightnumber[1];
       String num3=flightnumber[2];
       session.setAttribute("sortticketList",plan);
       %>
       <br><br>
       <form id="fnumber" name="fnumber" method="post" action="dobuy.jsp" onsubmit="return seatVali(<%=indexPlan%>)">
       <input type="hidden" name="num1" id="num1" value="<%=num1%>">
       <input type="hidden" name="num2" id="num2" value="<%=num2%>">
       <input type="hidden" name="num3" id="num3" value="<%=num3%>">
        <input type="hidden" name="minseat" id="minseat" value="<%=minseat%>">
       <label for="points">Number of tickets:</label>
       <input type="number" name="numbers" min="1" max="10" value="1" style="width:30px;"/>
       <input type="submit" name="submit" id="submit" value="Buy Ticket">
      </form>
  
    
    

      <br>
           
    </td>
    </tr>
  </tbody>
</table>
      <%a++;} %>
<br> 
        
</div>
</div>
</div>

<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
var MenuBar2 = new Spry.Widget.MenuBar("MenuBar2", {imgDown:"SpryAssets/SpryMenuBarDownHover.gif", imgRight:"SpryAssets/SpryMenuBarRightHover.gif"});
</script>


</body>
</html>
  


  

 