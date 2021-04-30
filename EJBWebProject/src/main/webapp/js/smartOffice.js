$(document).ready(function() {
    getWeatherReport();

    //Get method to get all offices and create rows for them
    //getOffices();
    
});//End document ready function

//Adds a new row with an office object
function addRow({ officeNumber, buildingAddress, temperatureSetting, ventilationSetting }) {
    $("#tableBodyOffice").
        append($('<tr>')
            .append($('<td>').text(officeNumber))
            .append($('<td>').text(buildingAddress))
            .append($('<td>').text(temperatureSetting))
            .append($('<td>').text(ventilationSetting))
        )
}

function getWeatherReport() {
    $.ajax({
        method: "GET",
        url: "http://api.ipstack.com/37.221.105.225?access_key=d21816ef8186bd478db2de2ab9ea194c",
        error: ajaxReturn_Error,
        success: ajaxReturn_Success
    })
    function ajaxReturn_Success(result, status, xhr) {
        ParseJsonFile(result);
    } function ajaxReturn_Error(result, status, xhr) {
        console.log("Ajax-api-stack: " + status);
    }
}

//Function to call get() with AJAX to get offices
function getOffices() {
    $.ajax({
        method: "GET",
        url: "SmartOfficeServlet",
        data: {operation: "viewOffices"},
        error: ajaxReturn_Error,
        success: ajaxReturn_Success
    })
    function ajaxReturn_Success(result, status, xhr) {
        ParseJsonFileOfficeList(result);
    } function ajaxReturn_Error(result, status, xhr) {
        console.log("Ajax-api-stack: " + status);
    }
}

function ParseJsonFileOfficeList(officeList) {
    for (var i = 0, len = officeList.length; i < len; i++) {
        addRow(officeList[i]);
    }
}

function ParseJsonFile(result) {
	var lat = result.latitude;
	var long = result.longitude;
	var city = result.city;
	var ipNbr = result.ip
	$("#city").text(city);
	$("#ipNbr").text(ipNbr);
	$.ajax({
		method: "GET",
		url: "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + long + "&units=metric" + "&APPID=e1e31265eccb81355b94fc79b19e9cf2",
		error: ajaxWeatherReturn_Error,
		success: ajaxWeatherReturn_Success
	})
	function ajaxWeatherReturn_Success(result, status, xhr) {
		var sunrise = result.sys.sunrise;
		var sunset = result.sys.sunset;
		var sunriseDate = new Date(sunrise * 1000);
		var timeStrSunrise = sunriseDate.toLocaleTimeString();
		var sunsetDate = new Date(sunset * 1000);
		var timeStrSunset = sunsetDate.toLocaleTimeString();
		$("#sunrise").text("Sunrise: " + timeStrSunrise);
		$("#sunset").text("Sunset: " + timeStrSunset);
        h
		$("#weather").text(result.weather[0].main);
		$("#degree").text(result.main.temp + " \u2103");
	}
	function ajaxWeatherReturn_Error(result, status, xhr) {
		alert("Error i OpenWeaterMap Ajax");
		console.log("Ajax-api-stack:  " + status);
	}
}
