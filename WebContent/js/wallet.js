function showLogin() {
	$("#registerForm").addClass("nodisplay");
	$("#loginForm").removeClass("nodisplay");
	$("#registerFont").removeClass("loginFontSelected");
	$("#loginFont").addClass("loginFontSelected");
}

function showRegister() {
	$("#registerForm").removeClass("nodisplay");
	$("#loginForm").addClass("nodisplay");
	$("#registerFont").addClass("loginFontSelected");
	$("#loginFont").removeClass("loginFontSelected");
}