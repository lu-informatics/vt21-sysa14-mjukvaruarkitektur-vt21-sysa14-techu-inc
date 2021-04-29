<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang ="sv">
    <head>
    <title>Home Page building</title>
    <style>
         div{
            width: auto;  
            text-align: center;  
            padding: 15px solid white;  
            border: 3px; background-color: solid white; 
            }
            
            </style>
             <link rel="stylesheet" href="style.css">
             <meta charset="utf-8">
             <meta name="viewport" content="width=device-width, initial scale=1">
        </head>
        <body>
            <img src="images/img.png" class="logo">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

                   <section id="topPic">
                    
               <div class="container"></div>
                <div class="textHeader">
    
                    <ul>
                        <li><a href="homebuilding.html">Home</a></li>
                        <li><a href="about.html">About</a></li>
                        <li><a href="#">Testing</a></li>
                        <li><a href="index.html">Start</a></li>
                    </ul>
              
               
                </div>
                </div>
                <div class="h1hometext" style="top: 45px;"><h1>Buildings</h1>
                    <p>Choose between create, read, update or delete</p></div>

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
            
            <table class= "content-table" style="left: 35%; top: -85px">
                <thead>
                    <tr>
                        <th style="text-align: center;">Adress</th>
                        </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="text-align: center;">Albogatan</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">Lundavägen</td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">Malmögatan</td>
                    </tr>
                    <tr>
                        <td  style="text-align: center;">Albogatan</td>
                    </tr>
                    <tr>
                        <td  style="text-align: center;">Albogatan</td>
                    </tr>
                    <tr>
                        <td  style="text-align: center;">Albogatan</td>
                    </tr>
                </tbody>
            </table>
            </section>   
            <div id="sidenav" style="display: none;">
                <nav>
                    <ul>
                        <li><a href="home.html">Offices</a></li>
                        <li><a href="homebuilding.html">Buildings</a></li>
                        
                    </ul>
                </nav>
            </div>
            <div id="menuBtn">
                <img src="images/white-menu-icon-0.jpg" id="menu">
            </div>

           
<script>
    var menuBtn = document.getElementById("menuBtn")
    var sidenav = document.getElementById("sidenav")
    var menu = document.getElementById("menu")
   
    
   
    menuBtn.onclick = function(){
      
        sidenav.style.display = "block";
        if(sidenav.style.right == "-250px"){
            sidenav.style.right = "0";
           
           
        }
        else{
            sidenav.style.right = "-250px";
     
        
        }
    }

</script>
        </body>
        </html>
        <script>
            $(document).ready(function(){
                $.ajax({
                    method: "GET",
            url: "http://api.ipstack.com/37.221.105.225?access_key=d21816ef8186bd478db2de2ab9ea194c",
                error: ajaxReturn_Error, 
                success: ajaxReturn_Success
            })
            function ajaxReturn_Success(result, status, xhr) {
               ParseJsonFile(result);
                
            }function ajaxReturn_Error(result, status, xhr) {
                console.log("Ajax-api-stack: "+ status);
        }
            });  
            function ParseJsonFile(result) {
                var lat = result.latitude;
                var long = result.longitude;
                var city = result.city;
                var ipNbr = result.ip
                $("#city").text(city);
                $("#ipNbr").text(ipNbr);

                $.ajax({
                    method: "GET",
                    url: "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+long+"&units=metric"+"&APPID=e1e31265eccb81355b94fc79b19e9cf2",
                    error: ajaxWeatherReturn_Error, 
                    success: ajaxWeatherReturn_Success
                })

                function ajaxWeatherReturn_Success(result, status, xhr) {
                    var sunrise = result.sys.sunrise;
                    var sunset = result.sys.sunset;
                    
                    var sunriseDate = new Date(sunrise*1000);
                    var timeStrSunrise = sunriseDate.toLocaleTimeString();
                    var sunsetDate = new Date(sunset*1000);
                    var timeStrSunset = sunsetDate.toLocaleTimeString();
                    
                    $("#sunrise").text("Sunrise: "+timeStrSunrise);
                    $("#sunset").text("Sunset: "+timeStrSunset);
                    $("#weather").text(result.weather[0].main);
                    $("#degree").text(result.main.temp+" \u2103");
                }
                function ajaxWeatherReturn_Error(result, status, xhr) {
                    alert("Error i OpenWeaterMap Ajax");
                    console.log("Ajax-api-stack:  "+status);
                }
                
            }
        </script>

       