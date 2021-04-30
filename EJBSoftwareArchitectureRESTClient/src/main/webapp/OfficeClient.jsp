<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js">
 </script>
 <meta charset="ISO-8859-1">
  <link rel="stylesheet" type="text/css" href="css/smartOffice.css">
  <script src="js/office.js"></script>
 <title>Rest Client</title>
</head>
<body>
 <header>
<p><b>RestClient - SmartOffice 2.0</b></p>
 </header>
 <section id="row">
 <nav>
 <ul>
<li><a>Buildings</a></li>
<li class="active"><a>Offices</a></li>
 </ul>
 <img src = "img/CompanyLogo.png"/>
</nav>
<aside>
</aside>
 <section id="main">
 <section id="content">
<article>
 <fieldset id="PersonalFS">
 <legend>Office:</legend>
 Office number:<br>
<input type="text" name="officeNumber" id="officeNumber" value=""><br>
 Building address:<br>
<input type="text" name="buildingAddress" id="buildingAddress" value=""><br>
Temperature Setting:<br>
<input type="text" name="temperatureSetting" id="temperatureSetting" value=""><br>
Ventilation Setting:<br>
<input type="text" name="ventilationSetting" id="ventilationSetting" value=""><br>
 <br><br>
 <input type="button" name="submitBtn" value="Find" id="FindBtn">
 <input type="button" name="submitBtn" value="Add" id="AddBtn">
 <input type="button" name="submitBtn" value="Delete" id="DeleteBtn">
 <input type="button" name="submitBtn" value="Update" id="UpdateBtn">
 </fieldset>
</article>
 </section>
 </section>
 </section>
 <footer>
 <p>&copy; Technological Unemployment Inc.</p>
 </footer>
</body>
</html>