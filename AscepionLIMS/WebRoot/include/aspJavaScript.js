
/**************************************************************************
Ascepion LIMS JavaScript
Version: 
Author: Li Jun Mulin
**************************************************************************/
function validateEmployee() {
	var name = document.myForm.name.value;
	var department = document.myForm.department.value;
	if (name == "") {
		alert("Please input name");
		document.myForm.name.focus();
		return false;
	}
	if (department == "") {
		alert("Please input department");
		return false;
	}
	return true;
}
function validateInsertPage() {
	var url = document.myForm.url.value;
	if (url == "") {
		alert("Please input url");
		document.myForm.url.focus();
		return false;
	}
	return true;
}
function getEmployeeTypeId() {
	var id = document.myForm.employeetype.value;
	location.href = "GivePermission.jsp?selectEmployeeId=" + id;
}
function validatePurchaseing() {
	var name = document.myForm.purchasePerson.value;
	var type = document.myForm.accountItem.value;
	var project = document.myForm.project.value;
	var department = document.myForm.department.value;
	var number = document.myForm.purchaseNumber.value;
	var price = document.myForm.totleprice.value;
	if (name == "") {
		alert("Please input name");
		document.myForm.purchasePerson.focus();
		return false;
	}
	if (type == "") {
		alert("Please input type");
		return false;
	}
	if (project == "") {
		alert("Please input project");
		return false;
	}
	if (department == "") {
		alert("Please input department");
		return false;
	}
	if (number == "") {
		alert("Please input purchaseNumber");
		return false;
	}
	if (price == "") {
		alert("Please input totleprice");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validateUpdatePurchaseing() {
	var name = document.myForm.purchasePerson.value;
	var number = document.myForm.purchaseNumber.value;
	var price = document.myForm.totleprice.value;
	if (name == "") {
		alert("Please input name");
		document.myForm.purchasePerson.focus();
		return false;
	}
	if (number == "") {
		alert("Please input purchaseNumber");
		return false;
	}
	if (price == "") {
		alert("Please input totleprice");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validateProject() {
	var name = document.myForm.name.value;
	var department = document.myForm.department.value;
	if (name == "") {
		alert("Please input name");
		document.myForm.name.focus();
		return false;
	}
	if (department == "") {
		alert("Please input department");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validateItem() {
	var name = document.myForm.name.value;
	if (name == "") {
		alert("Please input name");
		document.myForm.name.focus();
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validateBank() {
	var name = document.myForm.bankName.value;
	var accountnumber = document.myForm.accountNumber.value;
	if (name == "") {
		alert("Please input Bankname");
		document.myForm.bankName.focus();
		return false;
	}
	if (accountnumber == "") {
		alert("Please input accountNumber");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
//去左空格;
function ltrim(s) {
	return s.replace(/^\s*/, "");
}
//去右空格;
function rtrim(s) {
	return s.replace(/\s*$/, "");
}
function trim(s) {
	return rtrim(ltrim(s));
}
function getCol(tableId, data) {
	var table = document.getElementById(tableId);
	var colCount = table.rows[0].cells.length;
	var oneCol;
	for (var i = colCount - 1; i >= 0; i--) {
		oneCol = table.rows[0].cells[i].innerHTML;
		if (data == trim(oneCol)) {
			return i;
		}
	}
}
function displayRow(tableId, item) {
	var table = document.getElementById(tableId);
	var rows = table.rows;
	var rowCount = rows.length;
	if (rowCount < 2) {
		return;
	}
	var col = getCol(tableId, item.name);
	var textp;
	var text;
	for (var i = item.options.length - 1; i >= 0; i--) {
		if (item.options[i].selected) {
			textp = item.options[i].innerHTML;
			text = trim(textp);
			break;
		}
	}
	var oneRow;
	for (var i = rowCount - 1; i > 0; i--) {
		oneRow = rows[i].cells[col].innerHTML;
		if (text != "--Type--" || text != "--Project--" || text != "--Department--") {
			if (trim(oneRow) == text) {
				table.rows[i].style.display = "";
			} else {
				table.rows[i].style.display = "none";
			}
		} else {
			table.rows[i].style.display = "none";
		}
	}
}
function showAllRow(tableId){
	var table = document.getElementById(tableId);
	var rows = table.rows;
	var rowCount = rows.length;
	
	for (var i = rowCount - 1; i > 0; i--) {
		table.rows[i].style.display = "";
	}
}
function ChangePurchaseQuary() {
	if (document.all["selectway"].value == "purchaseperson") {
		document.all["purchaseperson"].style.display = "";
		document.all["purchasename"].style.display = "none";
		document.all["purchasedate"].style.display = "none";
		document.all["purchasetype"].style.display = "none";
		document.all["purchaseproject"].style.display = "none";
		document.all["purchasedepartment"].style.display = "none";
		document.all["accountname"].style.display = "none";
	}
	if (document.all["selectway"].value == "purchasename") {
		document.all["purchasename"].style.display = "";
		document.all["purchaseperson"].style.display = "none";
		document.all["purchasedate"].style.display = "none";
		document.all["purchasetype"].style.display = "none";
		document.all["purchaseproject"].style.display = "none";
		document.all["purchasedepartment"].style.display = "none";
		document.all["accountname"].style.display = "none";
	}
	if (document.all["selectway"].value == "purchasedate") {
		document.all["purchasedate"].style.display = "";
		document.all["purchasename"].style.display = "none";
		document.all["purchaseperson"].style.display = "none";
		document.all["purchasetype"].style.display = "none";
		document.all["purchaseproject"].style.display = "none";
		document.all["purchasedepartment"].style.display = "none";
		document.all["accountname"].style.display = "none";
	}
	if (document.all["selectway"].value == "purchasetype") {
		document.all["purchasetype"].style.display = "";
		document.all["purchasename"].style.display = "none";
		document.all["purchaseperson"].style.display = "none";
		document.all["purchasedate"].style.display = "none";
		document.all["purchaseproject"].style.display = "none";
		document.all["purchasedepartment"].style.display = "none";
		document.all["accountname"].style.display = "none";
	}
	if (document.all["selectway"].value == "purchaseproject") {
		document.all["purchaseproject"].style.display = "";
		document.all["purchasename"].style.display = "none";
		document.all["purchaseperson"].style.display = "none";
		document.all["purchasetype"].style.display = "none";
		document.all["purchasedate"].style.display = "none";
		document.all["purchasedepartment"].style.display = "none";
		document.all["accountname"].style.display = "none";
	}
	if (document.all["selectway"].value == "purchasedepartment") {
		document.all["purchasedepartment"].style.display = "";
		document.all["purchasename"].style.display = "none";
		document.all["purchaseperson"].style.display = "none";
		document.all["purchasetype"].style.display = "none";
		document.all["purchaseproject"].style.display = "none";
		document.all["purchasedate"].style.display = "none";
		document.all["accountname"].style.display = "none";
	}
	if (document.all["selectway"].value == "accountname") {
		document.all["accountname"].style.display = "";
		document.all["purchasename"].style.display = "none";
		document.all["purchaseperson"].style.display = "none";
		document.all["purchasetype"].style.display = "none";
		document.all["purchaseproject"].style.display = "none";
		document.all["purchasedate"].style.display = "none";
		document.all["purchasedepartment"].style.display = "none";
	}
}
function validatePurchaseQuaryPerson() {
	var person = document.qperson.yourname.value;
	if (person == "") {
		alert("Please input purchaseperson");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validatePurchaseQuaryName() {
	var name = document.qname.warename.value;
	if (name == "") {
		alert("Please input purchasename");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validatePurchaseQuaryDate() {
	var startdate = document.qdate.startdate.value;
	var enddate = document.qdate.enddate.value;
	if (startdate == "") {
		alert("Please input startdate");
		return false;
	}
	if (enddate == "") {
		alert("Please input enddate");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validatePurchaseQuaryType() {
	var type = document.qtype.type.value;
	if (type == "") {
		alert("Please select purchasetype");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validatePurchaseQuaryProject() {
	var project = document.qproject.project.value;
	if (project == "") {
		alert("Please select purchaseproject");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validatePurchaseQuaryDepartment() {
	var department = document.qdepartment.department.value;
	if (department == "") {
		alert("Please select purchasedepartment");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
function validatePurchaseQuaryAccount() {
	var person = document.qaccount.account.value;
	if (person == "") {
		alert("Please select purchaseaccount");
		return false;
	}
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}

