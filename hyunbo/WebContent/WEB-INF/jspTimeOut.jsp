<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>


<script>

var tid;
var cnt = parseInt(7200);//초기값(초단위)


function counter_init() {
	
	tid = setInterval("counter_run()", 1000);
}

function counter_reset() {
	clearInterval(tid);
	cnt = parseInt(7200);
	counter_init();
}

function counter_run() {
	document.all.counter.innerText = time_format(cnt);
	cnt--;
	if(cnt < 0) {
		clearInterval(tid);
		alert("로그인 세션이 만료되었습니다. 로그인페이지로 이동합니다.");
		stopCTI();
		top.location.href="../SP/logout.jsp";
	
	}
}
function time_format(s) {
	var nHour=0;
	var nMin=0;
	var nSec=0;
	if(s>0) {
		nMin = parseInt(s/60);
		nSec = s%60;

		if(nMin>60) {
			nHour = parseInt(nMin/60);
			nMin = nMin%60;
		}
	} 
	if(nSec<10) nSec = "0"+nSec;
	if(nMin<10) nMin = "0"+nMin;

	return ""+nHour+":"+nMin+":"+nSec;
}

function mousemove(){
	counter_reset();
}
</script>


<script>
counter_init();
</script>

<html>
<head>
<title>자바스크립트 타이머 이용 세션 아웃</title>
</head>
<body>
<div onmousemove="mousemove()">
<span id="counter"> </span> 후 자동로그아웃 <input type="button" value="연장" onclick="counter_reset()">
</div>
</body>
</html>
