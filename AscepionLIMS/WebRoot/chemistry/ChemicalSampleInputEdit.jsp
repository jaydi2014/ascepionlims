<!-- 输入化学分子 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<%@ page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="java.util.StringTokenizer;"%>
<h5>
	Chemical Input:
</h5>
<hr>

<body onUnLoad="document.MSketch=null">
	<input type="hidden" name="smilesTemp"
		value="<%=request.getParameter("smiles")%>">
	<center>
		<script language="JavaScript"
			src="<%=aspDir%>include/aspJavaScript.js"></script>
		<script LANGUAGE="JavaScript1.1" SRC="marvin/marvin.js"></script>
		<script LANGUAGE="JavaScript1.1">
<!--
function evaluateChemicalTerms(expression) {
    if(document.MSketch != null) {
        return document.MSketch.evaluateChemicalTerms(expression);
    } else {
        alert("Cannot evaluate expression on molecule:\n"+
              "no JavaScript to Java communication in your browser.\n");
    }
}

function setFields(){
    document.getElementById('mass').innerHTML = evaluateChemicalTerms("mass()");
    document.getElementById('atomcount').innerHTML = evaluateChemicalTerms("atomCount()");
    document.getElementById('ringcount').innerHTML = evaluateChemicalTerms("ringCount()");
    document.getElementById('logp').innerHTML = evaluateChemicalTerms("logP()");
    document.getElementById('psa').innerHTML = evaluateChemicalTerms("PSA()");
    document.CTForm.name.value = evaluateChemicalTerms("name()"); 
    document.CTForm.smiles.value = evaluateChemicalTerms("molString('smiles')");
}

function propertyChange(prop) {
    if (prop.indexOf('mol=') !=-1) {
        setFields();
    } 
}

function undo() {
    if(document.MSketch != null) {
        document.MSketch.undo();
    }
}

function redo() {
    if(document.MSketch != null) {
        document.MSketch.redo();
    }
}

function exportMol(format) {
	if(document.MSketch != null) {
		var s = document.MSketch.getMol(format);
		s = unix2local(s); // Convert "\n" to local line separator
		document.CTForm.MolTxt.value = s;
	} else {
		alert("Cannot import molecule:\n"+
		      "no JavaScript to Java communication in your browser.\n");
	}
}

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
	    if(rows.length < 5) {
	        alert("^_^");
		    return;
	    }
	
	    var rowCount = rows.length;
	    myTable.deleteRow(rowCount-1);
  }
  
  	function getDate(){
		var innername = document.CTForm.innerName.value;	
	    if(innername=="") 
	    {
	        alert("innername can not be null");  
	        return false;
	    }
		var chemicalName = document.CTForm.chemicalName.value;	
		if(chemicalName=="") 
		{
		    alert("chemicalName can not be null"); 
		    return false;
		}
		
		var molecularFormula = document.CTForm.molecularFormula.value;
		if(molecularFormula=="") 
		{
		    alert("molecularFormula can not be null"); 
		    return false;
		}
		var smiles = document.CTForm.smiles.value;
		var id = document.CTForm.id.value;
	    var pInnerName = document.CTForm.pInnerName.value;
		document.CTForm.action = "../chemistry/UpdateSample.jsp?innerName=" + innername +"&chemicalName=" + chemicalName + "&smiles=" + smiles+ "&molecularFormula=" + molecularFormula+ "&id=" + id+ "&pInnerName=" + pInnerName ;
	    document.CTForm.submit();
	    document.CTForm.submitButton.disabled="disabled"; 
	}
  

var smile=document.getElementById("smilesTemp");
alert(smile.value);
msketch_name = "MSketch";
msketch_mayscript = true;
msketch_begin("marvin", 540, 480);
if(smile.value==""||smile.value==null)
    msketch_param("mol", "template.mol");
else
    msketch_param("mol", smile.value); 
msketch_param("listenpropertychange","true");
msketch_end();
//-->
</script>
		<p>
		<form NAME="CTForm" enctype="multipart/form-data" method="post">
			<table border=0>
				<tr>
					<td>
						Molecular weight:
					</td>
					<td>
						<div ID="mass"></div>
					</td>
				</tr>
				<tr>
					<td>
						Atom count:
					</td>
					<td>
						<div ID="atomcount"></div>
					</td>
				</tr>
				<tr>
					<td>
						Ring count:
					</td>
					<td>
						<div ID="ringcount"></div>
					</td>
				</tr>
				<tr>
					<td>
						Polar surface area:
					</td>
					<td>
						<div ID="psa"></div>
					</td>
				</tr>
				<tr>
					<td>
						logP:
					</td>
					<td>
						<div ID="logp"></div>
					</td>
				</tr>
				<tr>
					<td>
						SMILES:

					</td>
					<td>
						<textarea NAME="smiles" ROWS=2 COLS=60><%=request.getParameter("smiles")%></textarea>
					</td>
				</tr>
			</table>
			<p>
				Export format:
				<select NAME="molformat">
					<option VALUE="cml">
						cml
					</option>
					<option VALUE="csmol">
						csmol
					</option>
					<option VALUE="csrdf">
						csrdf
					</option>
					<option VALUE="csrxn">
						csrxn
					</option>
					<option VALUE="cssdf">
						cssdf
					</option>
					<option SELECTED VALUE="mol">
						mol
					</option>
					<option VALUE="mrv">
						mrv
					</option>
					<option VALUE="name">
						name
					</option>
					<option VALUE="rdf">
						rdf
					</option>
					<option VALUE="rxn">
						rxn
					</option>
					<option VALUE="smarts">
						smarts
					</option>
					<option VALUE="smiles">
						smiles
					</option>
					<option VALUE="sdf">
						sdf
					</option>
					<option VALUE="sybyl">
						sybyl
					</option>
				</select>
				<input TYPE=BUTTON VALUE="Export"
					onClick="exportMol(document.CTForm.molformat.value)">
				<input TYPE=BUTTON VALUE="Undo" onClick="undo()">
				<input TYPE=BUTTON VALUE="Redo" onClick="redo()">
			</p>
			<textarea NAME="MolTxt" ROWS=10 COLS=70></textarea>
			<br />
<%
		ChemicalFileRepository chemicalFileRepos = new ChemicalFileRepository();
		String relatedFiles = "";
		String relatedFileID = "";
		String fileName = "";
		StringTokenizer token = null;           %>
		<input type="hidden" name="id" size="50" value="${param.id}" />
		<input type="hidden" name="pInnerName" size="50" value="${param.innerName}" />
			<table width="80%" cellspacing=0 cellpadding=4 border=1 id="myTable">
				<tr>
					<th align="left" bgcolor="lightgrey">
						Inner Name
					</th>
					<td>
						<input type="text" name="innerName" size="50"
							value="${param.innerName}" />
					</td>
				</tr>
				<tr>
					<th align="left" bgcolor="lightgrey">
						Chemical Name
					</th>
					<td>
						<input type="text" name="chemicalName" size="50"
							value="${param.chemicalName}" />
					</td>
				</tr>
				<tr>
					<th align="left" bgcolor="lightgrey">
						Molecular Formula
					</th>
					<td>
						<input type="text" name="molecularFormula"
							value="${param.molecularFormula}" size=50>
					</td>
				</tr>
				<tr>
					<th align="left" bgcolor="lightgrey">
						UploadFiles
					</th>
					<td>
						<table>
							<%
								relatedFiles =request.getParameter("relatedFiles");
										if("null".equals(relatedFiles)){
											relatedFiles = "";
										}
										if (relatedFiles != null && !("".equals(relatedFiles))) {
											token = new StringTokenizer(relatedFiles, ",");
											while (token.hasMoreElements()) {
												relatedFileID = token.nextElement().toString();
												if(relatedFileID != null){
													fileName = chemicalFileRepos.getNameById(Integer.parseInt(relatedFileID));
												}
							%>
							<tr>
								<td>
									<%=fileName%>
									
								</td>
							</tr>
							<%
								}
										} 
								chemicalFileRepos.destroy();
										
							%>
						</table>
						<input type="button" name="Add" value="Add" style="cursor: hand"
							onclick="AddUploadFile();" />
						<input type="button" name="Delete" value="Delete" style="cursor: hand" onclick="DeleteUploadFile();"/>

					</td>
				</tr>
			</table>
			<br />
			<br />
			<hr>
			<input name="submitButton" type="submit" value="Store This Molacular"
				style="color: red" onclick="return getDate();" style="cursor: hand;"/>

		</form>

	</center>
	<script language="javaScript1.1">
    setFields();
    </script>

</body>
