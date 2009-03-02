<!-- 输入化学分子 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<h5>
	Chemical Input:
</h5>
<hr>

<body onUnLoad="document.MSketch=null">

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


msketch_name = "MSketch";
msketch_mayscript = true;
msketch_begin("marvin", 540, 480);
msketch_param("mol", "template.mol");
msketch_param("listenpropertychange","true");
msketch_end();
//-->
</script>
		<p>
		<form NAME="CTForm" action="<%=aspDir%>chemistry/SmilesInput.jsp"
			method="post" onsubmit="return validateChemicalSample()">
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
						<textarea NAME="smiles" ROWS=2 COLS=60 READONLY="readonly"></textarea>
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
			<br />
			<hr>
			<input type="submit" name="submit" value="Store this molecular">
		</form>

	</center>
	<script language="javaScript1.1">
    setFields();
    </script>

</body>
