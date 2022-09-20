<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>JSMpeg Stream Client</title>
<style type="text/css">
html, body {
    background-color: #111;
    text-align: center;
}
</style>

</head>
<body>
    <canvas id="video-canvas" width=640 height=480></canvas>
    <script type="text/javascript" src="/xeus/common/jsmpeg.min20171213.js"></script>
    <script type="text/javascript">
    
		var canvas = document.getElementById('video-canvas');
		var url = 'ws://127.0.0.1:8080/xeus/stream';
		//ts화일인 경우.. 
		var player = new JSMpeg.Player(url, {
			canvas : canvas,
			autoplay : false,
			loop : false,
		    disableGl : true,
			videoBufferSize : 512 * 1024,
			mediaId : 'CTV0000190',
            size : '320x240'
	    });
		
		if(player.isPlaying){
			$('#video-canvas').css('width','320px');
			$('#video-canvas').css('height','240px');
			
		}
		
   </script>
</body>
</html>