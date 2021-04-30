/**
 * 
 */
$(document).ready(function() {
	$("#FindBtn").click(function() {
		var strValue = $("#address").val();
		if (strValue != "") {
			$.ajax({
				method: "GET",
				url: "http://localhost:8080/EJBWebProject/Buildings/" + strValue,
				error: ajaxFindReturnError,
				success: ajaxFindReturnSuccess
			})
			function ajaxFindReturnSuccess(result, status, xhr) {
				parseJsonFileBuilding(result);
				clearFields();
				$("#address").attr("placeholder", "Found building");

			}
			function ajaxFindReturnError(result, status, xhr) {
				clearFields(); 
				$("#address").attr("placeholder", "Not found");
				console.log("Ajax-find building: " + status);
			}
		}
	})//findBtnClick
	$("#DeleteBtn").click(function() {
		var strValue = $("#address").val();
		if (strValue != "") {
			$.ajax({
				method: "DELETE",
				url: "http://localhost:8080/EJBWebProject/Buildings/" + strValue,
				error: ajaxDelReturnError,
				success: ajaxDelReturnSuccess
			})
			function ajaxDelReturnSuccess(result, status, xhr) {
				clearFields();
				$("#address").attr("placeholder", "Building deleted");
			}
			function ajaxDelReturnError(result, status, xhr) {
				clearFields();
				$("#address").attr("placeholder", "Not found, could not be deleted.");
				console.log("Ajax-delete building: " + status);
			}
		}
	})//deleteBtnClick
			$("#AddBtn").click(function() {
			var strAddress = $("#address").val();
			var obj = {address: strAddress};
			var jsonString = JSON.stringify(obj);
			if (strAddress != "") {
				$.ajax({
					method: "POST",
					url: "http://localhost:8080/EJBWebProject/Buildings/",
					data: jsonString,
					dataType: 'json',
					error: ajaxAddReturnError,
					success: ajaxAddReturnSuccess
				})
				function ajaxAddReturnSuccess(result, status, xhr) {
					clearFields();
					$("#address").attr("placeholder", "Building added");
				}
				function ajaxAddReturnError(result, status, xhr) {
					clearFields();
					$("#address").attr("placeholder", "Building could not be added.");
					console.log("Ajax-create building: " + status);
				}
			}
		})//addBtnClick
});
	
// E nd ready function
function parseJsonFileBuilding(result) {
	$("#address").val(result.address);
}
function clearFields() {
	$("#address").val("");
}
