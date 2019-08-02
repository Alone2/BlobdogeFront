function showTab1() {
	$("#tab1").addClass("nodisplay");
	$("#tab2").removeClass("nodisplay");
	$("#tab1Font").removeClass("loginFontSelected");
	$("#tab2Font").addClass("loginFontSelected");
}

function showTab2() {
	$("#tab1").removeClass("nodisplay");
	$("#tab2").addClass("nodisplay");
	$("#tab1Font").addClass("loginFontSelected");
	$("#tab2Font").removeClass("loginFontSelected");
}

function showTab31() {
	$("#tab1").removeClass("nodisplay");
	$("#tab2").addClass("nodisplay");
	$("#tab3").addClass("nodisplay");
	$("#tab1Font").addClass("loginFontSelected");
	$("#tab2Font").removeClass("loginFontSelected");
	$("#tab3Font").removeClass("loginFontSelected");
}

function showTab32() {
	$("#tab1").addClass("nodisplay");
	$("#tab2").removeClass("nodisplay");
	$("#tab3").addClass("nodisplay");
	$("#tab1Font").removeClass("loginFontSelected");
	$("#tab2Font").addClass("loginFontSelected");
	$("#tab3Font").removeClass("loginFontSelected");
}

function showTab33() {
	$("#tab1").addClass("nodisplay");
	$("#tab2").addClass("nodisplay");
	$("#tab3").removeClass("nodisplay");
	$("#tab1Font").removeClass("loginFontSelected");
	$("#tab2Font").removeClass("loginFontSelected");
	$("#tab3Font").addClass("loginFontSelected");
}