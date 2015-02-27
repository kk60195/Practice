function popup(){

	if(false){
	alert("Hellow World");
	}
	else{
		alert("Else condition executed");
	}
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

