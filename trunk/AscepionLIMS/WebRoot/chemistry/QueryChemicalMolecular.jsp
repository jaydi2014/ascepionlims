<!-- 查询化学分子 -->
<%@ include file="/core/Permission.jsp"%>
<%@ page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>

<%@page import="java.util.StringTokenizer;"%>

<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>

<script LANGUAGE="JavaScript1.1"
	src="<%=aspDir%>chemistry/marvin/marvin.js"></script>
<script LANGUAGE="JavaScript1.1">
function viewSmiles(smiles){
var sm = smiles;
mview_begin("<%=aspDir%>chemistry/marvin", 75, 75);
mview_param("mol", sm );
mview_end();
}
</script>
<script type="text/javascript">
function ChangeChemicalMolecularQuary() {

	document.getElementById('innerNameDiv').style.display = "none";
	document.getElementById('chemicalNameDiv').style.display = "none";
	document.getElementById('smilesDiv').style.display = "none";
	document.getElementById('molecularFormulaDiv').style.display = "none";

	if (document.getElementById('selectway').value == "innerName") {
		document.getElementById('innerNameDiv').style.display = "block";
	}
	if (document.getElementById('selectway').value == "chemicalName") {
		document.getElementById('chemicalNameDiv').style.display = "block";
	}
	if (document.getElementById('selectway').value == "smiles") {
		document.getElementById('smilesDiv').style.display = "block";
	}
	if (document.getElementById('selectway').value == "molecularFormulas") {
		document.getElementById('molecularFormulaDiv').style.display = "block";
	}
}

function checkNull(value){
	    if(value==""||value==null) 
	    {
	        alert("value can not be null");  
	        return false;
	    }
       return true;
}



</script>

<%
	ChemicalMolecularBean[] chemicalMolecular = null;
	ChemicalMolecularRepository chemicalMolecularRepo = new ChemicalMolecularRepository();
	ChemicalFileRepository chemicalFileRepo = new ChemicalFileRepository();
	chemicalMolecular = (ChemicalMolecularBean[]) request
			.getAttribute("chemicalMolecular");

%>


<table>
	<tr>
		<td width=300>
			<br />
			<select id="selectway" onclick="ChangeChemicalMolecularQuary();">
				<option value="" selected>
					--Please Select Quary Way--
				</option>
				<option value="innerName">
					Internal Name

				</option>
				<option value="chemicalName">
					Synonym
				</option>
				<option value="smiles">
					Smiles
				</option>
				<option value="molecularFormulas">
					Molecular Formulas
				</option>
			</select>
		</td>
		<td>
			<br />
			<br />
			<div id="innerNameDiv" style="display: none">
				<form name="innerNameForm"
					action="<%=mainservletUrl%>?cmd=quary-molecular&way=innerName"
					method="post" onsubmit="return checkNull(this.innerName.value)">
					Internal Name :
					<input name="innerName" type="text" />
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>
			<div id="chemicalNameDiv" style="display: none">
				<form name="chemicalNameForm"
					action="<%=mainservletUrl%>?cmd=quary-molecular&way=chemicalName"
					method="post" onsubmit="return checkNull(this.chemicalName.value)">
					Synonym :
					<input name="chemicalName" type="text" />
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>
			<div id="smilesDiv" style="display: none">
				<form name="smilesForm"
					action="<%=mainservletUrl%>?cmd=quary-molecular&way=smiles"
					method="post" onsubmit="return checkNull(this.smiles.value)">
					Smiles :
					<input name="smiles" type="text" />
					<input name="submit" type="submit" value="submit" />

				</form>
			</div>
			<div id="molecularFormulaDiv" style="display: none">
				<form name="molecularFormulaForm"
					action="<%=mainservletUrl%>?cmd=quary-molecular&way=molecularFormula"
					method="post" onsubmit="return checkNull(this.molecularFormula.value)">
					Molecular Formulas :
					<input name="molecularFormula" type="text" />
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>
		</td>
	</tr>
</table>

<hr>
<%
	if (chemicalMolecular != null) {
		session
				.setAttribute("chemicalMolecularTemp",
						chemicalMolecular);
		String relatedFiles = "";
		String relatedFileID = "";
		ChemicalFileBean relatedFile = null;
		StringTokenizer token = null;
		String path="http://localhost:8081/AscepionLIMS/WebRoot/Temp";
%>
<center>
	<table id="purchase" width="95%" cellspacing=0 cellpadding=4 border=1>
		<tr bgcolor="lightgrey">
			<th>
				Chemical Formulas
			</th>
			<th>
				Internal Name
			</th>
			<th>
				Synonym
			</th>
			<th>
				Molecular Formulas
			</th>
			<th>
				Related Files
			</th>
		</tr>
		<%
			for (int i = 0; i < chemicalMolecular.length; i++) {
		%>
		<tr>
			<td align="center" style="height: 75px;border-right: none">
				<script LANGUAGE="JavaScript1.1">
				viewSmiles('<%=chemicalMolecular[i].getSmiles()%>')
				</script>

			</td>
			<td align="center">
				<a
					href="<%=request.getContextPath()%>/chemistry/ChemicalSampleInputEdit.jsp?smiles=<%=chemicalMolecular[i].getSmiles()%>
			      &chemicalName=<%=chemicalMolecular[i].getChemicalName()%>&molecularFormula=<%=chemicalMolecular[i].getMolecularFormula()%>
			      &relatedFiles=<%=chemicalMolecular[i].getRelatedFiles()%>&innerName=<%=chemicalMolecular[i].getInnerName()%>&id=<%=chemicalMolecular[i].getId()%>">
					<%=chemicalMolecular[i].getInnerName()%> </a>
			</td>
			<td align="center">
				<%=chemicalMolecular[i].getChemicalName()%>
			</td>
			<td align="center">
				<%=chemicalMolecular[i].getMolecularFormula()%>
			</td>
			<td align="center">
				<table>
					<%
						relatedFiles = chemicalMolecular[i].getRelatedFiles();
								if (relatedFiles != null && relatedFiles != "") {
									token = new StringTokenizer(relatedFiles, ",");
									while (token.hasMoreElements()) {
										relatedFileID = token.nextElement().toString();
										String fileName = chemicalFileRepo.getNameById(Integer.parseInt(relatedFileID));
									
					%>
					<tr>
						<td>
						<a href='<%=request.getContextPath() %>/chemistry/ChemicalMolecularFileDownload.jsp?relatedFileID=<%=relatedFileID%>'><%=fileName %></a>
						</td>
					</tr>
					<%
						}
								} else {
					%>
					<tr>
						<td>
							No RelatedFiles
						</td>
					</tr>
					
					<%
						}
								chemicalFileRepo.destroy();
								chemicalMolecularRepo.destroy();
					%>
				</table>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>