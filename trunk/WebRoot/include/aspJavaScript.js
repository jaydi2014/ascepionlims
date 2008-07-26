/**************************************************************************
Ascepion LIMS JavaScript
Version: 
Author: Li Jun Mulin
**************************************************************************/

function validateEmployee(){
	var name = document.myForm.name.value;
	var department = document.myForm.department.value;
	if(name == ""){
		alert("Please input name");
		document.myForm.name.focus();
		return false;
	}
	
	if(department == ""){
		alert("Please input department");
		return false;
	}
	return true;
}

function validateInsertPage(){
	var url = document.myForm.url.value;
	if(url == ""){
		alert("Please input url");
		document.myForm.url.focus();
		return false;
	}
	return true;
}

function getEmployeeTypeId(){
	var id = document.myForm.employeetype.value;
	location.href= "GivePermission.jsp?selectEmployeeId="+id;
}

function validatePurchase(){
	var name = document.myForm.purchasePerson.value;
	if(name == ""){
		alert("Please input name");
		document.myForm.purchasePerson.focus();
		return false;
	}
	if(!confirm("Are your sure to submit?")){
		return false;
	}
	return true;
}
