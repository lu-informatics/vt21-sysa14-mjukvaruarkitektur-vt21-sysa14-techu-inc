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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.contextMenu.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-contextmenu/2.7.1/jquery.ui.position.js"></script>
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
				<li><a href="SmartOfficeServlet?operation=viewOffices">Home</a></li>
				<li><a href="SmartOfficeAbout.jsp">About</a></li>
				<li><a href="SmartOfficeTest.jsp">Testing</a></li>
				<li><a href="SmartOfficeIndex.jsp">Start</a></li>
			</ul>


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
		List<Building> buildingList = (List<Building>) request.getAttribute("buildings");
		String[] ventilationSettingList = new String[]{"V1", "V2", "V3", "V4", "V5"};
		%>
		
		
		<table id="tableOffice" class="content-table" style="top: -85px;">
			<thead id="tableHeadOffice">
				<tr>
					<td>OfficeNumber</td>
					<td>BuildingAddress</td>
					<td>TemperatureSetting</td>
					<td>VentilationSetting</td>
				</tr>
			</thead>
			<tbody id="tableBodyOffice">
				<%
				for (int i = 0; i < list.size(); i += 1) {
				%>
				<tr>
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
		
		<div id="popup">
		<div id="updateWindow">
			<label id="office">Office Number:</label>
			<label id="idLabel"></label>
			<form id="updateOfficeForm">
			<label for="building">Building: </label> 
					<select id="building">
					<%
						for (int i = 0; i < buildingList.size(); i += 1) {
					%>
						<option><%=buildingList.get(i).getAddress()%></option>
					<%
						}
					%>
					</select>
					<br>
				<label for="temperatureSetting">Temperature Setting:</label> 
				<label id="temperatureSettingLabel"></label>
				<input type="range" min="16" max="25" class="slider" id="temperatureSetting">
				<br>
				<label for="ventilationSetting">Ventilation Setting:</label>
				<select
					id="ventilationSetting">
					<%
					for (int i = 0; i < ventilationSettingList.length; i += 1) {
					%>
					<option><%=ventilationSettingList[i]%></option>
					<%
					}
					%>
				</select>
				<br>
				<input id="updateOffice" type="submit" value="Update Office"></input>
			</form>
			<input type="button" id="closeUpdate" value="Close">
		</div>
	</div>
	
	<div id="flip">Add an office.</div>
		<div id="panel">
			<form id="addOfficeForm">
				<label for="buildingAdd">Building Address: </label>
				<select
					id="buildingAdd">
					<%
					for (int i = 0; i < buildingList.size(); i += 1) {
					%>
					<option><%=buildingList.get(i).getAddress()%></option>
					<%
					}
					%>
				</select>
				<br>
				<label for="temperatureSettingAdd">Temperature Setting:</label> 
				<label id="temperatureSettingAddLabel"></label>
				<input type="range" min="16" max="25" value="20" class="slider" id="temperatureSettingAdd">
				<br>
				<label for="ventilationSettingAdd">Ventilation Setting:</label>
				<select
					id="ventilationSettingAdd">
					<%
					for (int i = 0; i < ventilationSettingList.length; i += 1) {
					%>
					<option><%=ventilationSettingList[i]%></option>
					<%
					}
					%>
				</select>
				<br>
				<input id="addOffice" class="button" type="submit" value="Add Office"></input>
			</form>
		</div>
		
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
			<li class="one">We are a small IT company that wants to make
				life easier for you</li>
			<li class="two">For more information please contact us at:
				TechnologicalUnemployment@live.se</li>
			<li class="three">&copy;All Right Reserved to Technological
				Unemployment 2021</li>
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
