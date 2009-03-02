<!-- 为实验增加文件 -->
<%@include file="/common/Menu.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.universal.*"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>
<script LANGUAGE="JavaScript1.1"
	src="<%=aspDir%>chemistry/marvin/marvin.js"></script>

<script LANGUAGE="JavaScript1.1">
  function AddUploadFile()
  {

		var myTable=document.getElementById("myTable");
		var initRows = myTable.rows;	 
		var toRow = myTable.insertRow(myTable.rows.length);		
		var newCell =document.createElement("th");
		toRow.appendChild(newCell); 
		 
		newCell.align = "left";
		newCell.bgColor="lightgrey";
		newCell.innerHTML="UploadFiles";
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<input type="file" name="uploadFiles"  size=30 onchange="prompt(this);"/> ';
  }
  
  function DeleteUploadFile()
  {
    	var myTable=document.getElementById("myTable");
	    var rows = myTable.rows;
	
	    if(rows.length < 2) {
	        alert("^_^");
		    return;
	    }
	
	    var rowCount = rows.length;
	    myTable.deleteRow(rowCount-1);
  }
  
  function getDate(){
  		var experimentId = document.myForm.experimentid.value;
  
		document.myForm.action = "../chemistry/ProcessExperimentFileInput.jsp?experimentid=" + experimentId;
	    document.myForm.submit();
	    document.myForm.submitButton.disabled="disabled"; 
	}
  
</script>
<center>
<h5>
	Experiment created successfully:
</h5>
<hr>
<br>
<div align="left">Add related files:</div>
	<form name="myForm" enctype="multipart/form-data"  method="post">
	<input type="hidden" name="experimentid" value="${param.experimentid}">
		<table width="80%" cellspacing=0 cellpadding=4 border=1 id="myTable">
			<tr>
				<th align="left" bgcolor="lightgrey">
					UploadFiles
				</th>
				<td>
					<input type="file" name="uploadFiles" 
						 size=30 onchange="prompt(this);"/> 
				    <input type="button" name="Add" value="Add" style="cursor: hand" onclick="AddUploadFile();"/>
				    <input type="button" name="Delete" value="Delete" style="cursor: hand" onclick="DeleteUploadFile();"/>
				   
				</td>			
			</tr>
		</table>
		<br />
		<input name="submitButton" type="submit" value="Upload those files"
			style="color: red" onclick="return getDate();" style="cursor: hand"/>
	</form>

