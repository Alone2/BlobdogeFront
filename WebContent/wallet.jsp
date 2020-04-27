<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="ch.blobber.blobdogefront.connection.BlobdogeConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <script src="js/qrcode.js"></script>
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
        <titu onclick="window.location='/wallet';">Blobber <small style="font-size: 13px">alpha</small></titu>

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
                    <b onclick="showTab31()" id="tab1Font" class="loginFont loginFontSelected">Upload</b><b onclick="showTab32()" id="tab2Font" class="loginFont">Send</b><b onclick="showTab33()" id="tab3Font" class="loginFont">Other</b>
                </div>
                <div class="content contentNoHover">
                	<div id="tab1" class="center">
                		 <b>Your Balance: </b><br>
                		 <b>${balance} Ð</b>
                		 <br><br>
                		 <div class="center" id="qrcode"></div>
						    <script type="text/javascript">
						    new QRCode("qrcode", {
						        text: "${address}",
						        width: 150,
						        height: 150,
						    });
							</script>
                		 <br><b>Dogecoin Address:</b><br>
                		 <small>${address}</small><br><br><br>
                		 Transaction time takes about 1 minute. Reload the page 1 minute after you've sent dogecoin to this address to see if your transaction was successful. (Address changes as soon as the transaction starts processing)
                	</div>
                	<div id="tab2" class="nodisplay center">
                		<b>Your Balance: </b><br>
                		<b>${balance} Ð</b>
                		<br><br>
                		How do you want to send your Dogecoins to your receiver?
						<br><br>
						<input  type="submit" class="login register" value="Share Link" onclick="window.location='/createLink?tab=1';">
						<!-- <input  type="submit" class="login register" value="Scan QR-Code" onclick="window.location='/createLink?tab=2';"> -->
						<br><br>
						<input type="submit" class="login register" value="Type Dogecoin Address" onclick="window.location='/createLink?tab=3';">
                	</div>
                	<div id="tab3" class="nodisplay center">
                		<br>
                		<form method="post" action="./logout">
							<input type="submit" class="login logout" value="logout">
						</form>
						<br><br>
						My unclaimed links:<br><br>						
						<c:forEach items="${urls}" var="link">
						    <div class="copyHolder">
								<input type="text" id="outputInput" class="uInput copyInput" value="${link}" readonly>
								<input type="button" onclick="copy()" class="login register copyInput" value="copy">
							</div><br>
						</c:forEach>
						<br><br>	
						<span><small>Dev-Token:<br>${devtoken}</small></span>
                	</div>
                <br></div>
            </div>
            <br/><br/>
            <div id="blobs">
                <div class="content center">
                		<small class="date">Ad</small>
            			<script type="text/javascript" src="//uprimp.com/bnr.php?section=General&pub=964945&format=300x250&ga=g"></script>
						<noscript><a href="https://yllix.com/publishers/964945" target="_blank"><img src="//ylx-aff.advertica-cdn.com/pub/300x250.png" style="border:none;margin:0;padding:0;vertical-align:baseline;" /></a></noscript>
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