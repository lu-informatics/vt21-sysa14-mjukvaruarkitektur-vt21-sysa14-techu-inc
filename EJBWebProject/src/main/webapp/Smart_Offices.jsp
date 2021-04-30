<%@page import="org.ics.ejb.Office"%>
<%@page import="org.ics.ejb.Building"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="sv">
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/smartOffice.js"></script>
<title>Home Page</title>
<style>
div {
	width: auto;
	text-align: center;
	padding: 15px solid white;
	border: 3px;
	background-color: solid white;
}
</style>
<link rel="stylesheet" href="css/style.css">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial scale=1">
</head>
<body>
	<img src="img/CompanyLogo.png" class="logo">

	<section id="topPic">

		
		<div class="textHeader">

			<ul>
				<li><a href="Smart_Offices.jsp">Home</a></li>
				<li><a href="SmartOfficeAbout.jsp">About</a></li>
				<li><a href="SmartOfficeTest.jsp">Testing</a></li>
				<li><a href="SmartOfficeIndex.jsp">Start</a></li>
			</ul>


		</div>
		</div>
		<div class="h1hometext" style="top: 45px;">
			<h1>Offices</h1>
			<p>Choose between create, read, update or delete</p>
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

		<%
		List<Office> list = (List<Office>) request.getAttribute("offices");
		%>

		<table class="content-table" style="top: -85px;">
			<thead>
				<tr>
					<th>OfficeNumber</th>
					<th>BuildingAddress</th>
					<th>TemperatureSetting</th>
					<th>VentilationSetting</th>
				</tr>
			</thead>
			<tbody id="tableBodyOffice">
				<%
				for (int i = 0; i < list.size(); i += 1) {
				%>
				<tr onclick="myFunction(this)">
					<td><%=list.get(i).getOfficeNumber()%></td>
					<td><%=list.get(i).getBuilding().getAddress()%></td>
					<td><%=list.get(i).getTemperatureSetting()%></td>
					<td><%=list.get(i).getVentilationSetting()%></td>
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
				<li><a href="Smart_Offices.jsp">Offices</a></li>
				<li><a href="SmartOfficeHome.jsp">Buildings</a></li>

			</ul>
		</nav>
	</div>
	<div id="menuBtn">
		<img src="img/white-menu-icon-0.jpg" id="menu">
	</div>
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
