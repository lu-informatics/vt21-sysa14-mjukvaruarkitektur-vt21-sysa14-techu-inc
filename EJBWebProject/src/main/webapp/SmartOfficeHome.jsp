<%@page import="org.ics.ejb.Office"%>
<%@page import="org.ics.ejb.Building"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="sv">
<head>
<title>Home Page building</title>

<link rel="stylesheet" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/smartOffice.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body>
	<img src="img/CompanyLogo.png" class="logo">


	<section id="topPic">

	
		<div class="textHeader">

			<ul>
				<li><a href="SmartOfficeServlet?operation=viewOffices">Home</a></li>
				<li><a href="SmartOfficeAbout.jsp">About</a></li>
				<li><a href="SmartOfficeTest.jsp">Testing</a></li>
				<li><a href="SmartOfficeIndex.jsp">Start</a></li>
			</ul>


		</div>
		<div class="h1hometext" style="top: 45px;">
			<h1>Buildings</h1>
		</div>

		<nav class="weathernav">
			<table id="asideTable">

				<tr>
					<th><span id="city"></span></th>
					<th><span id="ipNbr"></span></th>
				</tr>

				<tr>
					<td><span id="degree"></span></td>
					<td><span id="weather"></span></td>
				</tr>
				<tr>
					<td colspan="4"><span id="sunrise"></span></td>
				</tr>
				<tr>
					<td colspan="4"><span id="sunset"></span></td>
				</tr>
			</table>
		</nav>
		
		<% List<Building> buildingList = (List<Building>) request.getAttribute("buildings"); %>
		
		<table class="content-table" style="left: 35%; top: -85px">
			<thead id="tableHeadBuilding">
				<tr>
					<td style="text-align: center;">Address</td>
				</tr>
			</thead>
			<tbody id="tableBodyBuilding">
				<%
					for (int i = 0; i < buildingList.size(); i += 1) {
				%>
				<tr onclick="myFunction(this)">
					<td><%=buildingList.get(i).getAddress()%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</section>
	
	<div id="sidenav" style="display: none;">
		<nav>
			<ul>
				<li><a href="SmartOfficeServlet?operation=viewOffices">Offices</a></li>
				<li><a href="SmartOfficeServlet?operation=viewBuildings">Buildings</a></li>

			</ul>
		</nav>
	</div>
	<div id="menuBtn">
		<img src="img/white-menu-icon-0.jpg" id="menu">
	</div>
	
	<footer class="footer">
       <ul>
             <li class="one">We are a small IT company that wants to make life easier for you</li>
            <li class="two">For more information please contact us at: TechnologicalUnemployment@live.se</li>
       <li class="three">&copy;All Right Reserved to Technological Unemployment 2021</li>
       </ul>
    </footer>
	<script>
	var menuBtn = document.getElementById("menuBtn")
	var sidenav = document.getElementById("sidenav")
	var menu = document.getElementById("menu")
	menuBtn.onclick = function() {
		sidenav.style.display = "block";
		if (sidenav.style.right == "-250px") {
			sidenav.style.right = "0";
		} else {
			sidenav.style.right = "-250px";
		}
	}
	</script>
</body>

</html>

