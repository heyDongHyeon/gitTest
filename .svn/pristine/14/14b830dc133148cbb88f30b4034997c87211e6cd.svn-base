<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>JSMpeg Stream Client</title>
<script type="text/javascript">
	var console = window.console || {
		log : function() {
		}
	};

	var ws = new WebSocket("ws://127.0.0.1:8080/xeuss/event");

	ws.onopen = function() {
		console.log("websocket connected....");
	};

	ws.onmessage = function(evt) {
		var received_msg = evt.data;
		console.log("Message is received...");
		console.log(received_msg);
	};

	ws.onclose = function() {
		// websocket is closed.
		console.log("Connection is closed...");
	};

	window.onbeforeunload = function(event) {
		ws.close();
	};
</script>

</head>
<body>

</body>
</html>