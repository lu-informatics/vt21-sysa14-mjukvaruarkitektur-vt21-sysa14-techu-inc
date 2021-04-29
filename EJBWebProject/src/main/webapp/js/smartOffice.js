$(document).ready(function() {
	var menuBtn = $("#menuBtn")
	var sidenav = $("#sidenav")
	var menu = $("#menu")

	menuBtn.onclick(function() {
		sidenav.style.display = "block";
		if (sidenav.style.right == "-250px") {
			sidenav.style.right = "0";
		}
		else {
			sidenav.style.right = "-250px";
		}
	});
});