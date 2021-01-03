<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<div id='log'></div>
	<script type="text/javascript">

		document.addEventListener('keydown', (event) => {
			const keyName = event.key;
			if (keyName === 'ArrowUp') {
				request("up");
			} else if(keyName === 'ArrowDown'){
				request("down");
			}else if(keyName === 'ArrowLeft'){
				request("left");
			}else if(keyName === 'ArrowRight'){
				request("right");
			}else if(keyName === 'Enter'){
				request("f5");
			}else if(keyName === 'Escape'){
				request("escape");
			}
		});
		function updateText(string){
			var log = document.getElementById('log');
			var newP = document.createElement('p');
			newP.innerText = string + " - Har blitt sendt - " + generateTimestamp();
			log.prepend(newP);
		}
		function generateTimestamp(){
			let date = new Date(Date.now());
			let hours = date.getHours();
			let minutes = date.getMinutes();
			let seconds = date.getSeconds();
			return appendZero(hours)+":"+appendZero(minutes)+":"+appendZero(seconds);
		}
		function appendZero(number){
			if(number.length < 10){
				return "0"+ number
			}
			return number
		}
		function request(data){
			updateText(data);
			var oReq = new XMLHttpRequest();
			/*oReq.addEventListener('load',function(){
				console.log(this.response);
			});*/
			oReq.open("POST","send.php");
			oReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			oReq.withCredentials = true;
			oReq.send("key="+data);	
		}
	</script>
</body>
</html>