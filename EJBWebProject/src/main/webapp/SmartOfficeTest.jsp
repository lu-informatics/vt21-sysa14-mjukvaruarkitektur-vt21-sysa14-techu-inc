<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css"><meta charset="ISO-8859-1">
<title>SmartOffice Test Page</title>
</head>
<body>
	<p>
		Please choose one of the following tests:<br>
	</p>
	<form action="TestServlet" method="get" name="youPickItForm">
		<select name="suite" size="3" multiple>
			<option value="org.ics.test.AllTests">
				org.ics.test.AllTests</option>
			<option value="org.ics.test.TestFacadeEJB_JUnitEE_Building">
				org.ics.test.TestFacadeEJB_JUnitEE_Building</option>
			<option value="org.ics.test.TestFacadeEJB_JUnitEE_Office">
				org.ics.test.TestFacadeEJB_JUnitEE_Office</option>
		</select> <input type="submit" value="Run" />
	</form>
</body>
</html>