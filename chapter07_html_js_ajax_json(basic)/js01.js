function f(){
	document.getElementById("h1Id").innerHTML="제목";
	document.getElementById("h1Id").style.color="red";
	document.getElementById("h1Id").style.backgroundColor="black";
	// document.getElementById("pId").style.color="green";
	// document.getElementById("pId").innerHTML="내용이 변경됨";
	document.getElementsByTagName("p")[1].style.color="blue";
	// var el = document.getElementsByTagName("p");
	// el[0].style.color="blue";
	// el[1].style.color="red";
}