<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page errorPage = "error" %>

<!DOCTYPE html>
<html>

<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

    <meta name="viewport" content='width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=0'>
    <meta name="apple-mobile-web-app-capable" content="yes" />

    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    
    <title id="title">Blobber</title>
    <link rel="icon" name="iconName" href="./img/icon.png" type="image/x-icon">
    <link rel="apple-touch-icon" name="iconName" sizes="57x57" href="./img/icon.png" />
    <link rel="apple-touch-icon" name="iconName" sizes="72x72" href="./img/icon.png" />
    <link rel="apple-touch-icon" name="iconName" sizes="114x114" href="./img/icon.png" />
    <link rel="apple-touch-icon" name="iconName" sizes="144x144" href="./img/icon.png" />

    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link href="css/css.css" rel="stylesheet">
	<link href='css/${theme}.css' rel="stylesheet">
    <link href="" rel="stylesheet">

    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

    <script src="js/content.js"></script>
    <script src="js/look.js"></script>
    <script src="js/tabs.js"></script>
    <script type="text/javascript">
    window.onload = function (event) {
        contentMoveOn();
        lookMoveOn();
        changeButtonsTheme("${theme}");
        ${errorJs}
        ${js}
    }
    </script>
    
</head>

<body>
    <div id="banner" class="banner">
        <titu onclick="showBlobs(true)">Blobber <small style="font-size: 13px">alpha</small></titu>

        <a id="theme_switcher" onclick="change_theme('dark');  location.reload();"><img id="theme_switcher_img" height="30px" src="img/light/change_theme.svg"></a>
    </div>
    <div id="derGradient" class="gradient"></div>

    <div id="alertBox">
        <svg id="alertBoxClose" width="25" height="25">
            <rect x="0" y="0" width="25" height="25" style="fill:rgb(230, 81, 81);" />
            <circle cx="12.5" cy="12.5" r="6" stroke="black" stroke-width="1" fill="None" />
        </svg>
        <div id="alertBoxContent">
        </div>
    </div>

    <div id="contentHolder">
        <div id="newBlob" class="sticky nodisplay">Neuer Blob! ↑</div>
        <div id="news" class="nodisplay">
            <div id="blobNews" class="write content" style="font-size:medium">
                <b>Blobber News</b>
            </div>
        </div>
        <div id="theRealStuff">
            <div id="loginStuff">
                <div id="login" class="write content writeClick" style="font-size:medium">
                    <b id="tab1Font" class="loginFont loginFontSelected">Link</b>
                </div>
                <div class="content contentNoHover">
                	<div id="tab1" class="center">
              			 <br>
              			 <b>${balance} Ð</b>
              			 <br><br>
              			 Send the following Link to the Person you want to send your Dogecoins to. 
              			 Be aware that <b>everyone</b> who has this link is able to claim the Dogecoins.
              			 <br><br>
                		 <b>Link:</b><br><br>
                		 <input type="text" class="uInput long" value="${link}" readonly>
                		 <br><br><br>
						 <form method="get" action="https://api.whatsapp.com/send" target="_blank">
						    <input type="text" class="nodisplay" name="text" value="${link}">
                		 	<input type="submit" class="login" value="Share on Whatsapp">
                		 </form>
                		 <br><br><br><br>
                		 <form method="get" action="./wallet">
                		 	<input type="submit" class="login register" value="Back to Wallet">
                		 </form>
                		 <br><br>
                		   
                	</div>
                <br></div>
            </div>
            <br/><br/>
            <div id="blobs">
                <div id="BlobNews" class="write content" style="font-size:medium">
                    <b>News</b>
                </div>
            </div>
        </div>

        <div id="bottomText">
            <br />
                Blobber 2019
            <br />
        </div>
    </div>

</body>

</html>