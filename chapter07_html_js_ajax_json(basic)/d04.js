 class DomTest
 {
	  up(){
		  var elDiv = document.getElementById("divId");
		  var cNodes = elDiv.childNodes;

          //(1) create 
		  var elFont = document.createElement("font");
		  var attrFont = document.createAttribute("style");
		  attrFont.value = "color:blue";
		  elFont.setAttributeNode(attrFont);
		  var txt = document.createTextNode("바뀌는 내용");
		  elFont.appendChild(txt);
          
		  //(2) update 
		  elDiv.replaceChild(elFont, cNodes[1]);
	  }
	  del(){
		  var elDiv = document.getElementById("divId");
		  var cNodes = elDiv.childNodes;

		  //(3) delete 
		  elDiv.removeChild(cNodes[2]);	
	  }
 }
