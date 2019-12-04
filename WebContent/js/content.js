function getNews() {
    document.getElementById("news").className = "";
    $.getJSON("content/news", function (data) {
        for (i = 0; i < data.length; i++) {
            new_dat = data[i]
            $("#news").html($("#news").html() + '<div class="content newscontent"><small class="date">' + new_dat["date"] + '</small><medium style="font-size:medium">' + new_dat["header"] + "</medium><br />" + new_dat["message"] + "<br />")
        }
    });
}

function getHome() {
    $.getJSON("content/homeNews", function (data) {
        for (i = 0; i < data.length; i++) {
            new_dat = data[i]
            $("#blobs").html($("#blobs").html() + '<div class="content"><small class="date">' + new_dat["date"] + '</small><img class="blobimage" src="' + new_dat["pic"] + '" alt=""><h3>' + new_dat["header"] + "</h3><br />" + new_dat["message"] + "<br /><br />")
        }

    });
}

$.ajaxSetup ({
    // Disable caching of AJAX responses
    // Nicht mehr im cache gespeichert
    cache: false
});

function contentMoveOn() {
	getHome();
	getNews();
}

