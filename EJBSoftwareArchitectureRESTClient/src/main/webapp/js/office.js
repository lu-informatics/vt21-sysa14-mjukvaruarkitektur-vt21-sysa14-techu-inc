$(document).ready(function() {
	$("#FindBtn").click(function() {
		var strValue = $("#officeNumber").val();
		if (strValue != "") {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBWebProject/Offices/" + strValue,
				error: ajaxFindReturnError,
				success: ajaxFindReturnSuccess
			})
			function ajaxFindReturnSuccess(result, status, xhr) {
				clearFields();
				parseJsonFileOffice(result);
				//$("#officeNumber").attr("placeholder", "Found office.");
				//$("#buildingAddress").attr("placeholder", "Office added.");
				//$("#temperatureSetting").attr("placeholder", "Office added.");
				//$("#VentilationSetting").attr("placeholder", "Office added.");

			}
			function ajaxFindReturnError(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Not found.");
				console.log("Ajax-find office: " + status);
			}
		}
	})//findBtnClick
	$("#DeleteBtn").click(function() {
		var strValue = $("#officeNumber").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE",
				url: "http://localhost:8080/EJBWebProject/Offices/" + strValue,
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			})
			function ajaxDelReturnSuccess(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Office deleted");
			}
			function ajaxDelReturnError(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Not found.");
				console.log("Ajax-delete office: " + status);
			}
		}
	})//deleteBtnClick
	$("#AddBtn").click(function() {
		var strBuildingAddress = $("#buildingAddress").val();
		var strTemperatureSetting = $("#temperatureSetting").val();
		var strVentilationSetting = $("#ventilationSetting").val();
		var obj = {
			buildingAddress: strBuildingAddress, 
			temperatureSetting: strTemperatureSetting, 
			ventilationSetting: strVentilationSetting};
		var jsonString = JSON.stringify(obj);
		if (strBuildingAddress != "" && strTemperatureSetting != "" && strVentilationSetting != "" ) {
			$.ajax({
				method: "POST",
				url: "http://localhost:8080/EJBWebProject/Offices/",
				data: jsonString,
				dataType: 'json',
				error: ajaxAddReturnError,
				success: ajaxAddReturnSuccess
			})
			function ajaxAddReturnSuccess(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Office added.");
			}
			function ajaxAddReturnError(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Office could not be added.");
				console.log("Ajax-create office: " + status);
			}
		}
	})//addBtnClick
	$("#UpdateBtn").click(function() {
		var strOfficeNumber = $("#officeNumber").val();
		var strBuildingAddress = $("#buildingAddress").val();
		var strTemperatureSetting = $("#temperatureSetting").val();
		var strVentilationSetting = $("#ventilationSetting").val();
		var obj = {
			officeNumber: strOfficeNumber, 
			buildingAddress: strBuildingAddress, 
			temperatureSetting: strTemperatureSetting, 
			ventilationSetting: strVentilationSetting};
		var jsonString = JSON.stringify(obj);
		if (strOfficeNumber != "" && strBuildingAddress != "" && strTemperatureSetting != "" && strVentilationSetting != "") {
			$.ajax({
				method: "PUT",
				url: "http://localhost:8080/EJBWebProject/Offices/" + strOfficeNumber,
				data: jsonString,
				dataType: 'json',
				error: ajaxUpdateReturnError,
				success: ajaxUpdateReturnSuccess
			})
			function ajaxUpdateReturnSuccess(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Office updated.");
				location.reload();
			}
			function ajaxUpdateReturnError(result, status, xhr) {
				clearFields();
				$("#officeNumber").attr("placeholder", "Office could not be updated.");
				console.log("Ajax-update office: " + status);
			}
		} else {
			clearFields();
			$("#officeNumber").attr("placeholder", "Office could not be updated.");
		}
	})//updateBtnClick
});


// E nd ready function
function parseJsonFileOffice(result) {
	$("#officeNumber").val(result.officeNumber);
	$("#buildingAddress").val(result.buildingAddress);
	$("#temperatureSetting").val(result.temperatureSetting);
	$("#ventilationSetting").val(result.ventilationSetting);
	
}
function clearFields() {
	$("#officeNumber").val("");
	$("#buildingAddress").val("");
	$("#temperatureSetting").val("");
	$("#ventilationSetting").val("")
}