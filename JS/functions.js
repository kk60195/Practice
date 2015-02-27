function popup(){


	 confirm("Hello World");
	
	//if(reply){
	//	document.alert("you have clicked yes : " + var);
	//}
	//else{
	//	document.alert("you pressed no : " + var )
	//}
	
}

function confirmation(){


	var answer  = confirm("leave this page?");

	if(answer){

		alert("you are staying i guess");
		
	}

	else{
		alert("Bye!");
		window.location = "http://www.google.com";

	}
}

function goBiography(){
	window.location = "biography.html";
}

function goMain(){
	window.location = "main.html";
}


function openWindow(){
	window.open("http://www.yahoo.com");
}