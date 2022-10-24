function allCheck(){
	id = document.getElementById('id').value;
	pw = document.getElementById('pw').value;
	confirm = document.getElementById('confirm').value;
	username = document.getElementById('username').value;
	
	if(id == ""){
		alert('아이디는 필수 항목입니다.');
	}else if(pw == ""){
		alert('비밀번호는 필수 항목입니다.');
	}else if(confirm == ""){
		alert('비밀번호 확인은 필수 항목입니다.');
	}else if(username == ""){
		alert('이름은 필수 항목입니다.');
	}else{
		document.getElementById('f').submit();
	}
}
function pwCheck(){
	pw = document.getElementById('pw');
	confirm = document.getElementById('confirm');
	if(pw.value == confirm.value){
		document.getElementById('label').innerHTML = '일치';
	}
	else {
		document.getElementById('label').innerHTML = '불일치';
		pw.value="";
		confirm.value="";
		pw.focus();
	}
}
function loginCheck(){
	id = document.getElementById('id').value;
	pw = document.getElementById('pw').value;
	
	if(id == ""){
		alert('아이디는 필수 항목입니다.');
	}else if(pw == ""){
		alert('비밀번호는 필수 항목입니다.');
	}else{
		document.getElementById('f').method = 'post';
		document.getElementById('f').submit();
	}
}
