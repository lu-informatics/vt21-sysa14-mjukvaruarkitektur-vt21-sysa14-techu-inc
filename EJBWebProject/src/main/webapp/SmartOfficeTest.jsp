<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css"><meta charset="ISO-8859-1">
<title>SmartOffice Test Page</title>
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
			<div class="h1hometext" style="top: 45px;">
				<h1>Testing</h1>
				<p>
					Please choose one of the following tests:<br>
				</p>
			</div>

		</div>

		<div class="FormTest">
	<form action="TestServlet" method="get" id="youPickItForm">
		<select name="suite" size="3" style="font-size: 28px;padding-left: 70px;padding-right: 70px;padding-top: 20px;padding-bottom: 20px; 
    background: rgba(0, 0, 0, 0.3); border: 0px; border-radius: 3px 3px 3px 3px;  font-family:  'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; color: white; font-style: italic;"
    multiple>
			<option value="org.ics.test.AllTests"> 
				All Tests</option>
			<option value="org.ics.test.TestBuildingEJB_JUnitEE">
				Test Building</option>
			<option value="org.ics.test.TestOfficeEJB_JUnitEE">
				Test Offices</option>
		</select> <input type="submit" value="Test" id="btnTest">
	</form>
	</div>
	</section>
	<footer class="footer">
       <ul>
             <li class="one">We are a small IT company that wants to make life easier for you</li>
            <li class="two">For more information please contact us at: TechnologicalUnemployment@live.se</li>
       <li class="three">&copy;All Right Reserved to Technological Unemployment 2021</li>
       </ul>
    </footer>
</body>
</html>