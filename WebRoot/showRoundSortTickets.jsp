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
   ArrayList<TwowayTicketPlan> result=new ArrayList<TwowayTicketPlan>();
   ArrayList<TwowayTicketPlan> searchRoundSortResult= (ArrayList)request.getAttribute("sortlist"); 
   ArrayList<TwowayTicketPlan> searchResult= (ArrayList)session.getAttribute("List");
   if(searchRoundSortResult!=null){
   result=searchRoundSortResult;}
   else{
   result=searchResult;}
   request.setAttribute("sortlist",result);
   request.setCharacterEncoding("utf-8");
 %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 
<head>
     <meta charset="UTF-8">
     <title>Two way Information Page
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
 #dtime .ui-slider-range { background: #f6931f; }
  #atime .ui-slider-range { background: #f6931f; }
  #dbtime .ui-slider-range { background: #f6931f; }
  #abtime .ui-slider-range { background: #f6931f; }
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
  <script src="js/showRoundTrip.js"></script>

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
 <div id="upleft" >
 <br> <br>
 <ul id="MenuBar1" class="MenuBarHorizontal">
<li><a class="MenuBarItemSubmenu" href="#"> sortBy</a>
  <ul>
   <li><a href="doRoundSorttotalprice.jsp">total price</a></li>
    <li><a href="doRoundSorttotaltime.jsp">total time</a></li>
      <li><a href="doRoundSortflighttime.jsp">flight time</a></li>
    </ul>
  </li>
</ul>


   <p>&nbsp;</p>
    <br> 
  <ul id="MenuBar2" class="MenuBarHorizontal">
<li><a class="MenuBarItemSubmenu" href="#"> stopOvers</a>
  <ul>
   <li><a href="doRoundStopOvers.jsp?toStopOver=0&backStopOver=0">00</a></li>
    <li><a href="doRoundStopOvers.jsp?toStopOver=0&backStopOver=1">01</a></li>
      <li><a href="doRoundStopOvers.jsp?toStopOver=0&backStopOver=2">02</a></li>
      <li><a href="doRoundStopOvers.jsp?toStopOver=1&backStopOver=0">10</a></li>
    <li><a href="doRoundStopOvers.jsp?toStopOver=1&backStopOver=1">11</a></li>
      <li><a href="doRoundStopOvers.jsp?toStopOver=1&backStopOver=2">12</a></li>
      <li><a href="doRoundStopOvers.jsp?toStopOver=2&backStopOver=0">20</a></li>
    <li><a href="doRoundStopOvers.jsp?toStopOver=2&backStopOver=1">21</a></li>
      <li><a href="doRoundStopOvers.jsp?toStopOver=2&backStopOver=2">22</a></li>
    </ul>
  </li>
</ul>


   <p>&nbsp;</p>
      <br> 
 </div>

 <div id=upright>
 <br><br>
 <table width="95%"  height="50%" border="1" >
  <tbody>
    <tr>
      <td style="text-align:center;vertical-align:middle;">
      <% 
      //define and get parameter
  String start ="";
  String destination ="";
  String toDate="";
  String backDate="";
  String ticketType="";
 
  request.setCharacterEncoding("utf-8");

  start =(String)session.getAttribute("start");
  destination = (String)session.getAttribute("destination");
  toDate=(String)session.getAttribute("toDate");
  backDate=(String)session.getAttribute("backDate");
  ticketType=(String)session.getAttribute("ticketType");
    %>
 <form id="form" name="form" method="post" action="index.jsp"> 
  <%=start%>
   -->
  <%=destination %>
  &nbsp;
  <%=toDate %>
  -->
  <%=backDate %>
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


<form id="form1" name="form1" method="post"action="doTwoTimeWin.jsp" >
 &nbsp;&nbsp; To Departure ;&nbsp;Time Range<br>
<p> 
 &nbsp;&nbsp;
  <label for="dfrom"  >From:</label>
  <input type="text" name="dfrom" id="dfrom" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
  <label for="dto">To:</label>
  <input type="text" name="dto" id="dto" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
</p>
<div id="dtime" style="width:100px; margin:15px;border-color: #f6931f; "></div>
  
<br>
 
&nbsp;&nbsp;To Arrival ;&nbsp;Time Range<br>
  <p>
&nbsp;&nbsp;
  <label for="afrom"  >From:</label>
  <input type="text" name="afrom" id="afrom" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
  <label for="ato">To:</label>
  <input type="text" name="ato" id="ato" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
</p>
<div id="atime" style="width:100px; margin:15px;border-color: #f6931f; "></div>

&nbsp;&nbsp;Back Departure ;&nbsp;Time Range<br>
  <p>
&nbsp;&nbsp;
  <label for="dbfrom"  >From:</label>
  <input type="text" name="dbfrom" id="dbfrom" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
  <label for="dbto">To:</label>
  <input type="text" name="dbto" id="dbto" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
</p>
<div id="dbtime" style="width:100px; margin:15px;border-color: #f6931f; "></div>
  
<br>
 
&nbsp;&nbsp;Back Arrival ;&nbsp;Time Range<br>
  <p>
&nbsp;&nbsp;
  <label for="abfrom"  >From:</label>
  <input type="text" name="abfrom" id="abfrom" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
  <label for="abto">To:</label>
  <input type="text" name="abto" id="abto" readonly style="border:0; color:#f6931f; font-weight:bold;width:30px;">
</p>
<div id="abtime" style="width:100px; margin:15px;border-color: #f6931f; "></div>
&nbsp;&nbsp;
   <input type="submit" value="search" >
</form>
</div>

<div id="right">

<%//for 循环输出arraylist %>
       <% int a=1;
          int indexPlan=-1;
          for(TwowayTicketPlan i:result)
          {
          indexPlan++;
          float price=i.getTotalPrice();
          ArrayList<Flight> departureplan=i.getDeparturePlan();
          ArrayList<Flight> arrivalplan=i.getArrivalPlan();
          int departuretotaltime=i.getDepartureTotalTime();
          int arrivaltotaltime=i.getArrivalTotalTime();
          int departuretotalflighttime=i.getDepartureFlightTime();
          int arrivaltotalflighttime=i.getArrivalFlightTime();
          int departurenumofstopovers=i.getDepartureNumOfStopovers();
          int arrivalnumofstopovers=i.getArrivalNumOfStopovers();
          ArrayList<Integer> departurelayover=i.getDepartureLayoverTimes();
          ArrayList<Integer> arrivallayover=i.getArrivalLayoverTimes();
          String tickettype=i.getTicketType();
          String[] departureflightnumber=new String[3];
          int departurecount=0;
          String[] arrivalflightnumber=new String[3];
          int arrivalcount=0;
           int[] seat=new int[6];
          %>
          
  <table width="95%" border="1">
  <tbody>
    <tr>
    <td style="text-align:center;vertical-align:middle;">
        <br>
    $ <%=price %>&nbsp;&nbsp;
 <% for(Flight j:departureplan)
          {
          Airport ddepatureairport=j.getDepatureAirport();
          String ddepaturecode=ddepatureairport.getCode();
          Date ddeparturetime1=j.getDepartureTime();
          Date ddeparturelocaltime=TimeManagement.gmtLocal(ddepaturecode,ddeparturetime1);
          String ddeparturetime=format.format(ddeparturelocaltime);
          Airport darrivalairport=j.getArrivalAirport();
          String darrivalcode=darrivalairport.getCode();
          Date darrivaltime1=j.getArrivalTime();
          Date darrivallocaltime=TimeManagement.gmtLocal(darrivalcode,darrivaltime1);
          String darrivaltime=format.format(darrivallocaltime);
          int dflighttime=j.getFlightTime();
          int dseats=0;
       
          if(tickettype.equals("firstClass")){dseats=j.getFirstClassSeats();}
          if(tickettype.equals("coach")){dseats=j.getCoachSeats();}
          departureflightnumber[departurecount]=j.getFlightNumber();
          seat[departurecount]=dseats;
          
          departurecount=departurecount+1;
          %>
        <%=ddeparturetime%>&nbsp;&nbsp;
        <%=ddepaturecode %>
        -->
        <%=darrivaltime %>&nbsp;&nbsp;
        <%=darrivalcode%>&nbsp;
        leftSeats:<%=dseats%>&nbsp;
         <%
     int dflighthour=(int)dflighttime/60;
     int dflightmin=(int)dflighttime-dflighthour*60;
      %> 
      <%=dflighthour %>Hours<%=dflightmin %>Mins
        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%} %>
     <br>&nbsp;
     <%
     int dtotalhour=(int)departuretotaltime/60;
     int dtotalmin=(int)departuretotaltime-dtotalhour*60;
      %> 
      <%
     int dtotalflighthour=(int)departuretotalflighttime/60;
     int dtotalflightmin=(int)departuretotalflighttime-dtotalflighthour*60;
      %> &nbsp;&nbsp; &nbsp;&nbsp;
       LayoverTime:
    
      <%for(int l=0;l<departurelayover.size();l++){ %>
      <%
     int departurelayoverhour=(int)departurelayover.get(l)/60;
     int departurelayovermin=(int)departurelayover.get(l)-departurelayoverhour*60;
      %>  
      <%=departurelayoverhour %>Hours<%=departurelayovermin %>Mins&nbsp;<%} %>

     
      <br><br>

      totalTime:<%=dtotalhour %>Hours<%=dtotalmin %>Mins
      &nbsp;&nbsp;
      totalFlightTime:<%=dtotalflighthour %>Hours<%=dtotalflightmin %>Mins
      &nbsp;&nbsp;
      <%=departurenumofstopovers %>Stops
      <br><br><br> &nbsp;&nbsp;
      
      <% for(Flight m:arrivalplan)
          {
          Airport adepatureairport=m.getDepatureAirport();
          String adepaturecode=adepatureairport.getCode();
          Date adeparturetime1=m.getDepartureTime();
          Date adeparturelocaltime=TimeManagement.gmtLocal(adepaturecode,adeparturetime1);
          String adeparturetime=format.format(adeparturelocaltime);
          Airport aarrivalairport=m.getArrivalAirport();
          String aarrivalcode=aarrivalairport.getCode();         
          Date aarrivaltime1=m.getArrivalTime();
          Date aarrivallocaltime=TimeManagement.gmtLocal(aarrivalcode,aarrivaltime1);
          String aarrivaltime=format.format(aarrivallocaltime); 
          int aflighttime=m.getFlightTime();
          int aseats=0;
          if(tickettype.equals("coach")){aseats=m.getCoachSeats();}
          if(tickettype.equals("firstClass")){aseats=m.getFirstClassSeats();}
          arrivalflightnumber[arrivalcount]=m.getFlightNumber();
          
          int acountSeat=arrivalcount+3;
          seat[acountSeat]=aseats;
          arrivalcount=arrivalcount+1;
          %>
        <%=adeparturetime%>&nbsp;&nbsp;
        <%=adepaturecode %>
        -->
        <%=aarrivaltime %>&nbsp;&nbsp;
        <%=aarrivalcode%>&nbsp;
         leftSeats:<%=aseats%>
         <%
     int aflighthour=(int)aflighttime/60;
     int aflightmin=(int)aflighttime-aflighthour*60;
      %> 
      <%=aflighthour %>Hours<%=aflightmin %>Mins
        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%} %>
     <br>&nbsp;
     <%
     int arrivaltotalhour=(int)arrivaltotaltime/60;
     int arrivaltotalmin=(int)arrivaltotaltime-arrivaltotalhour*60;
      %> 
       <%
     int arrivaltotalflighthour=(int)arrivaltotalflighttime/60;
     int arrivaltotalflightmin=(int)arrivaltotalflighttime-arrivaltotalflighthour*60;
      %> 
      &nbsp;&nbsp; &nbsp;&nbsp;
      
      
    
      LayoverTime:
      <%for(int s=0;s<arrivallayover.size();s++){ %>
      <%
     int arrivallayoverhour=(int)arrivallayover.get(s)/60;
     int arrivallayovermin=(int)arrivallayover.get(s)-arrivallayoverhour*60;
      %> 
      <%=arrivallayoverhour %>Hours<%=arrivallayovermin %>Mins&nbsp;<%} %> 
  
      <br><br>
      totalTime:<%=arrivaltotalhour %>Hours<%=arrivaltotalmin %>Mins
      &nbsp;&nbsp;
      totalFlightTime:<%=arrivaltotalflighthour %>Hours<%=arrivaltotalflightmin %>Mins
      &nbsp;&nbsp;
      <%=arrivalnumofstopovers %>Stops
      
      
       <%
       String dnum1=departureflightnumber[0];
       String dnum2=departureflightnumber[1];
       String dnum3=departureflightnumber[2];
       String anum1=arrivalflightnumber[0];
       String anum2=arrivalflightnumber[1];
       String anum3=arrivalflightnumber[2];
       
        Arrays.sort(seat);
       int minseat=seat[0];
       for(int u=0;u<6;u++)
       {if(seat[u]!=0)
        {
       minseat=seat[u];
       break;
        }
       }
       System.out.println("minseats is"+minseat);
     
       %>
       <br><br>
       <form id="fnumber" name="fnumber" method="post" action="doRoundbuy.jsp" onsubmit="return seatVali(<%=indexPlan%>)">
       <input type="hidden" name="dnum1" id="dnum1" value="<%=dnum1%>">
       <input type="hidden" name="dnum2" id="dnum2" value="<%=dnum2%>">
       <input type="hidden" name="dnum3" id="dnum3" value="<%=dnum3%>">
       <input type="hidden" name="anum1" id="anum1" value="<%=anum1%>">
       <input type="hidden" name="anum2" id="anum2" value="<%=anum2%>">
       <input type="hidden" name="anum3" id="anum3" value="<%=anum3%>">
         <input type="hidden" name="minseat" id="minseat" value="<%=minseat%>">
       <label for="points">Number of tickets:</label>
       <input type="number" name="numbers" min="1" max="10" value="1" style="width:30px;"/>
       <input type="submit" name="submit" id="submit" value="Buy Ticket">
      </form>


      <br>
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
  


  

