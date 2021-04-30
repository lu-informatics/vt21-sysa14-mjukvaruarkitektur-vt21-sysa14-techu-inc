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
 <script src="js/building.js"></script>
 <title>Rest Client</title>
</head>
<body>
 <header>
<p><b>RestClient - SmartOffice 2.0</b></p>
 </header>
 <section id="row">
 <nav>
 <ul>
<li class="active"><a>Buildings</a></li>
<li><a>Offices</a></li>
 </ul>
 <img src = "img/CompanyLogo.png"/>
</nav>
<aside>
</aside>
 <section id="main">
 <section id="content">
<article>
 <fieldset id="PersonalFS">
 <legend>Building:</legend>
 Address:<br>
 <input type="text" name="address" id="address" value=""><br>
 <br><br>
 <input type="button" name="submitBtn" value="Find" id="FindBtn">
 <input type="button" name="submitBtn" value="Add" id="AddBtn">
 <input type="button" name="submitBtn" value="Delete" id="DeleteBtn">
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