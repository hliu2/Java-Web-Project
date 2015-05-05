<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>System Home</title>
	
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    

	<script src="js/index.js" type="text/javascript"></script>
<link href="css/index.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
body,td,th {
	color: #F90;
	text-decoration: none;
	background-color: #FFF;
	border-color: #F90;
	margin: auto;
		/* 文字等内容居中 */
}
body {
	background-color: #FFF;
	background-image: url(images/spring.jpg);
	background-size:cover;
}

</style>
  </head>
  
<body>
<div >
<br>
<p align="center" style="font-size:36px; align-content:center">Fly by Night Airlines
</p>
</div>


<br>
<br>
<div class="content" id="content" style="filter:alpha(opacity=80);
-moz-opacity:0.8;
-khtml-opacity: 0.8;
opacity: 0.8;">
<div id="TabbedPanels1" class="TabbedPanels">
  <ul class="TabbedPanelsTabGroup">
    <li class="TabbedPanelsTab" >One-way</li>
    <li class="TabbedPanelsTab" >Round-trip</li>
  </ul>
  <div class="TabbedPanelsContentGroup" >
    <div class="TabbedPanelsContent" style="display: none; font-size: large;">
      <form id="search" name="oneWay" method="post" action="doOneWay.jsp" onsubmit=" return oneVali()">
 
        <p>
          <label for="start"> From:</label> 
          <select name="start" id="city">
            <option>ANC</option>
            <option>ATL</option>
            <option>AUS</option>
            <option>BWI</option>
            <option>BOS</option>
            <option>CLT</option>
            <option>ORD</option>
            <option>MDW</option>
            <option>CVG</option>
            <option>CLE</option>
            <option>CMH</option>
            <option>DEN</option>
            <option>DTW</option>
            <option>FLL</option>
            <option>RSW</option>
            <option>BDL</option>
            <option>HNL</option>
            <option>HOU</option>
            <option>IAH</option>
            <option>IND</option>
            <option>MCI</option>
            <option>LAS</option>
            <option>LAX</option>
            <option>MEM</option>
            <option>MIA</option>
            <option>MSP</option>
            <option>BNA</option>
            <option>MSY</option>
            <option>LGA</option>
            <option>JFK</option>
            <option>EWR</option>
            <option>OAK</option>
            <option>ONT</option>
            <option>MCO</option>
            <option>PHL</option>
            <option>PHX</option>
            <option>PIT</option>
            <option>PDX</option>
            <option>RDU</option>
            <option>SMF</option>
            <option>SAT</option>
            <option>SAN</option>
            <option>SFO</option>
            <option>SJC</option>
            <option>SNA</option>
            <option>SEA</option>
            <option>STL</option>
            <option>IAD</option>
            <option>DCA</option>
          </select>
        </p>
  
       <p>
      <label for="destination">To:</label>
     
      <select name="destination" id="city" >
            <option>ANC</option>
            <option>ATL</option>
            <option>AUS</option>
            <option>BWI</option>
            <option>BOS</option>
            <option>CLT</option>
            <option>ORD</option>
            <option>MDW</option>
            <option>CVG</option>
            <option>CLE</option>
            <option>CMH</option>
            <option>DEN</option>
            <option>DTW</option>
            <option>FLL</option>
            <option>RSW</option>
            <option>BDL</option>
            <option>HNL</option>
            <option>HOU</option>
            <option>IAH</option>
            <option>IND</option>
            <option>MCI</option>
            <option>LAS</option>
            <option>LAX</option>
            <option>MEM</option>
            <option>MIA</option>
            <option>MSP</option>
            <option>BNA</option>
            <option>MSY</option>
            <option>LGA</option>
            <option>JFK</option>
            <option>EWR</option>
            <option>OAK</option>
            <option>ONT</option>
            <option>MCO</option>
            <option>PHL</option>
            <option>PHX</option>
            <option>PIT</option>
            <option>PDX</option>
            <option>RDU</option>
            <option>SMF</option>
            <option>SAT</option>
            <option>SAN</option>
            <option>SFO</option>
            <option>SJC</option>
            <option>SNA</option>
            <option>SEA</option>
            <option>STL</option>
            <option>IAD</option>
            <option>DCA</option>
          </select>
       </p>   
    
       <p>         
                  
        
          <label for="startDate">Date:</label>
          <%
          Date now = new Date(); 
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
          String nowDate = dateFormat.format( now ); 
           %>
          <input type="date" min="<%=nowDate%>" name="startDate" id="date"  />
       </p>  
       <p>
        <label for="ticketType">Ticket Type:</label>
        <select name="ticketType" id="ticketType">
          <option>coach</option>
          <option>firstClass</option>
        </select>
       </p>
       <p>
          <input name="handOut" type="submit" class="TabbedPanelsTabGroup" id="handOut" value="Search" />
       </p>
   
      </form>
    </div>
    <div class="TabbedPanelsContent">
<form id="search" name="roundTrip" method="post" action="doRoundTrip.jsp" onsubmit=" return twoVali()">
      <p>
     
         <label for="start"> From:</label> 
          <select name="startTwo" id="city">
            <option>ANC</option>
            <option>ATL</option>
            <option>AUS</option>
            <option>BWI</option>
            <option>BOS</option>
            <option>CLT</option>
            <option>ORD</option>
            <option>MDW</option>
            <option>CVG</option>
            <option>CLE</option>
            <option>CMH</option>
            <option>DEN</option>
            <option>DTW</option>
            <option>FLL</option>
            <option>RSW</option>
            <option>BDL</option>
            <option>HNL</option>
            <option>HOU</option>
            <option>IAH</option>
            <option>IND</option>
            <option>MCI</option>
            <option>LAS</option>
            <option>LAX</option>
            <option>MEM</option>
            <option>MIA</option>
            <option>MSP</option>
            <option>BNA</option>
            <option>MSY</option>
            <option>LGA</option>
            <option>JFK</option>
            <option>EWR</option>
            <option>OAK</option>
            <option>ONT</option>
            <option>MCO</option>
            <option>PHL</option>
            <option>PHX</option>
            <option>PIT</option>
            <option>PDX</option>
            <option>RDU</option>
            <option>SMF</option>
            <option>SAT</option>
            <option>SAN</option>
            <option>SFO</option>
            <option>SJC</option>
            <option>SNA</option>
            <option>SEA</option>
            <option>STL</option>
            <option>IAD</option>
            <option>DCA</option>
          </select>
      </p>  
         
      <p>
         <label for="start"> To :</label>
         <select name="destinationTwo" id="city" >
            <option>ANC</option>
            <option>ATL</option>
            <option>AUS</option>
            <option>BWI</option>
            <option>BOS</option>
            <option>CLT</option>
            <option>ORD</option>
            <option>MDW</option>
            <option>CVG</option>
            <option>CLE</option>
            <option>CMH</option>
            <option>DEN</option>
            <option>DTW</option>
            <option>FLL</option>
            <option>RSW</option>
            <option>BDL</option>
            <option>HNL</option>
            <option>HOU</option>
            <option>IAH</option>
            <option>IND</option>
            <option>MCI</option>
            <option>LAS</option>
            <option>LAX</option>
            <option>MEM</option>
            <option>MIA</option>
            <option>MSP</option>
            <option>BNA</option>
            <option>MSY</option>
            <option>LGA</option>
            <option>JFK</option>
            <option>EWR</option>
            <option>OAK</option>
            <option>ONT</option>
            <option>MCO</option>
            <option>PHL</option>
            <option>PHX</option>
            <option>PIT</option>
            <option>PDX</option>
            <option>RDU</option>
            <option>SMF</option>
            <option>SAT</option>
            <option>SAN</option>
            <option>SFO</option>
            <option>SJC</option>
            <option>SNA</option>
            <option>SEA</option>
            <option>STL</option>
            <option>IAD</option>
            <option>DCA</option>
          </select>
      <p>
   
          <label for="startDate"> Departure Date:</label>
          <input type="date" min="<%=nowDate%>" name="beginDate" id="date"/>
    
     <p>
          <label for="returnDate">Return Date:</label>
      
          <input type="date" min="<%=nowDate%>" name="returnDate" id="date" />
         
     </p>
     <p>
        
        <label for="twoticketType">Ticket Type:</label>
        <select name="twoticketType" id="ticketType">
          <option>coach</option>
          <option>firstClass</option>
        </select>
       
      </p>
      <p>
 
          <input name="handOut" type="submit" class="TabbedPanelsTabGroup" id="handOut" value="Search"  />
      </p>
    
      </form>
    </div>
  </div>
</div>
<script type="text/javascript">
var TabbedPanels1 = new Spry.Widget.TabbedPanels("TabbedPanels1");
</script>
</div>

</body>
</html>
