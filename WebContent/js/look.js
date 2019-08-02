var isMobile = false;
var isSignedIn = false;
var isWindowsApp = false;

var currentScrollPos = 0;
const scrollPosForNewConst = 20;
var scrollPosForNew = scrollPosForNewConst;
var homeScrollPos =0;
var canScroll = true;

var maxBlobs = 0;
var current_sorting = "new";
var hereAmI = window.location.href


function alertBox(isClosable = true) {
    document.getElementById("alertBox").style.display = "block";
    document.getElementById("banner").style.opacity = "0.3";
    document.getElementById("contentHolder").style.opacity = "0.3";
    document.getElementById("derGradient").style.opacity = "0.3";

    if (!isClosable) {
        $("#alertBoxClose").addClass("nodisplay");
        $("#alertBoxContent").css("padding-top", $("#alertBoxContent").css("padding-left"));
        return
    }
    $("#alertBoxClose").removeClass("nodisplay");
    $("#alertBoxContent").css("padding-top", "")
    setTimeout(function timeout() {
        $('body').click(function (e) {
            console.log($(e.target));

            if ($("#alertBox").find(e.target).length || $(e.target).attr('id') == "alertBox") { return }

            console.log("ok");
            closeAlertBox();
            $('body').unbind("click");

        });
        document.getElementById("alertBox").onclick = function (event) {
            closeAlertBox();
            $('body').unbind("click");
        }
    }, 100);
}

function textAlertBoxDelay(text, delay) {
    document.getElementById("alertBox").style.display = "block";
    $("#alertBoxContent").html(text+"<br />")
    $("#alertBoxClose").addClass("nodisplay");
    $("#alertBoxContent").css("padding-top", $("#alertBoxContent").css("padding-left"));

    setTimeout(function timeout() {
        closeAlertBox();
    }, delay);
}

function closeAlertBox() {
    document.getElementById("alertBox").style.display = "none";
    document.getElementById("banner").style.opacity = "1";
    document.getElementById("contentHolder").style.opacity = "1";
    document.getElementById("derGradient").style.opacity = "1";
}

window.onresize = function (event) {
    stuffToTheRightPlace();
}

function stuffToTheRightPlace() {
    document.getElementById("contentHolder").style.height = window.innerHeight - 102 + "px";
    document.getElementById("contentHolder").style.paddingLeft = (window.innerWidth- 1200)/2 + "px";
    document.getElementById("contentHolder").style.paddingRight = (window.innerWidth- 1200)/2 + "px";
    if ((window.innerWidth - 1200)/2 < 20) {
        document.getElementById("news").className = "nodisplay";
        document.getElementById("theRealStuff").style.width = "100%";
            
        var the_width = window.innerWidth - 500;
        if (the_width < 0) {
            the_width = 0;
        }
        if (the_width <= 16) {
            document.getElementById("contentHolder").style.paddingTop = the_width + "px";
            document.getElementById("contentHolder").style.paddingBottom = the_width+ "px";
            document.getElementById("contentHolder").style.height = (window.innerHeight - the_width - $("#banner").height() - 1) + "px";
        } else {
            document.getElementById("contentHolder").style.paddingTop = 16 + "px";
            document.getElementById("contentHolder").style.paddingBottom = 16+ "px";
        }
        document.getElementById("contentHolder").style.paddingLeft = the_width /2 + "px";
        document.getElementById("contentHolder").style.paddingRight = the_width /2 + "px";
    } else {
        document.getElementById("news").className = "";
        document.getElementById("theRealStuff").style.width = "60%";
    }
    document.getElementById("derGradient").style.width = window.innerWidth + "px";
}


function lookMoveOn() {
    if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|BB|PlayBook|IEMobile|Windows Phone|Kindle|Silk|Opera Mini/i.test(navigator.userAgent)) {
        isMobile = true;
    }
    var mode = "light";
    if (window.Windows) {

    	isWindowsApp = true;
        var uiSettings = new Windows.UI.ViewManagement.UISettings();
        var color = uiSettings.getColorValue(Windows.UI.ViewManagement.UIColorType.background);
        if (color["b"] != 255) {
            mode = "dark";
        }
    }
    set_cookie_theme(mode);

    stuffToTheRightPlace();
}


function change_theme(theme, setcookie = true) {
    document.getElementsByTagName("link").item(7).href = "css/" + theme + ".css";
    
    var date = new Date();
    tage = 365 
    date.setTime(date.getTime() + (tage*24*60*60*1000));
    if (setcookie) {
        document.cookie = 'theme=' + theme + '; expires=' + date.toUTCString() + '; path=/'
    }
    upvoteButton = "./img/" + theme + "/upvote.svg"
    upvoteButtonPress = "./img/" + theme + "/upvoted.svg"
    downvoteButton = "./img/" + theme + "/downvote.svg"
    downvoteButtonPress = "./img/" + theme + "/downvoted.svg"
    $("#sendImg").attr("src", "./img/" + theme + "/send.svg")
}

function set_cookie_theme(defaultMode) {
    cook = document.cookie;
    doDefault = true;
    try {
        split = cook.split("; ");
    } catch (error) {
        split = "theme=" + defaultMode + ";"
    }
    for (i = 0; i < split.length; i++) {
        theme = split[i].split("=");
        if (theme[0] == "theme") {
            change_theme(theme[1]);
            changeButtonsTheme(theme[1]);
            doDefault = false;
        }
        // some error stuff (sorry)
        if (theme[0] == "error") {
        	textAlertBoxDelay(theme[1], 2500);
        	//delete Cookie
        	document.cookie = 'error=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
        }
    }
    if (doDefault) {
    	change_theme(defaultMode, false);
    	changeButtonsTheme(defaultMode);
    }
}

function changeButtonsTheme(theme) {
    if (theme == "dark") {
        $("#theme_switcher").attr("onclick","change_theme('light'); location.reload(); ");
    } else {
        $("#theme_switcher").attr("onclick","change_theme('dark'),  location.reload(); ");
    }
    $("#theme_switcher_img").attr("src","img/"+ theme + "/change_theme.svg")
}

//Nächste Funktion wurde dreist kopiert von: http://www.jquerybyexample.net/2012/06/get-url-parameters-using-jquery.html
function GetURLParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

