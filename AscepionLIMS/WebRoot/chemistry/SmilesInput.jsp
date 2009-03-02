<!-- 输入化学分子信息 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script type="text/javascript">
<!--

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
	
	    if(rows.length < 6) {
	        alert("^_^");
		    return;
	    }
	
	    var rowCount = rows.length;
	    myTable.deleteRow(rowCount-1);
  }
  
  	function getDate(){
		var innername = document.myForm.innerName.value;	
	    if(innername=="") 
	    {
	        alert("innername can not be null");  
	        return false;
	    }
		var chemicalName = document.myForm.chemicalName.value;	
		if(chemicalName=="") 
		{
		    alert("chemicalName can not be null"); 
		    return false;
		}
		var smiles = document.myForm.smiles.value;
		if(smiles=="") 
		{
		    alert("smiles can not be null"); 
		    return false;
		}
		
		var molecularFormula = document.myForm.molecularFormula.value;
		if(molecularFormula=="") 
		{
		    alert("molecularFormula can not be null"); 
		    return false;
		}
		document.myForm.action = "../chemistry/ProcessSmilesInput.jsp?innerName=" + innername +"&chemicalName=" + chemicalName + "&smiles=" + smiles+ "&molecularFormula=" + molecularFormula ;
	    document.myForm.submit();
	    document.myForm.submitButton.disabled="disabled"; 
	}

//-->
</script>

<h5>
	Store Molecular:
</h5>
<hr>

<center>
	<form name="myForm" enctype="multipart/form-data"  method="post">
		<table width="80%" cellspacing=0 cellpadding=4 border=1 id="myTable">
			<tr>
				<th align="left" bgcolor="lightgrey">
					Internal Name
				</th>
				<td>
					<input type="text" name="innerName" size="50" />
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					Synonym
				</th>
				<td>
					<input type="text" name="chemicalName" size="50" />
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					Smiles
				</th>
				<td>
					<input type="text" name="smiles" 
						readonly="readonly" size=80 value="${param.smiles}">
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					Molecular Formula
				</th>
				<td>
					<input type="text" name="molecularFormula" 
						 size=50 >
				</td>
			</tr>
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
		<input name="submitButton" type="submit" value="Store This Molacular"
			style="color: red" onclick="return getDate();" style="cursor: hand"/>
	</form>