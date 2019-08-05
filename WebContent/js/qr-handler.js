function qrMoveOn() {
	Instascan.Camera.getCameras().then(function (cameras) {
	      if (cameras.length > 0) {
	    	  $("#camButton").removeClass("nodisplay");
	      } 
	    }).catch(function (e) {
	      console.error(e);
	 });
}

function CamOn() {
	$("#preview").removeClass("nodisplay");
	
	let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
    scanner.addListener('scan', function (content) {
      console.log(content);
      $("#codeInput").val(content);
      CamOff();
      scanner.stop();
    });
    Instascan.Camera.getCameras().then(function (cameras) {
      if (cameras.length > 0) {
        scanner.start(cameras[0]);
      } else {
        console.error('No cameras found.');
      }
    }).catch(function (e) {
      console.error(e);
    });
}

function CamOff() {
	$("#preview").addClass("nodisplay");
}