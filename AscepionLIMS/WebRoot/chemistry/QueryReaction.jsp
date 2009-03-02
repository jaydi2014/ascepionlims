<!--  查询反应 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ page import="com.ascepionpharm.lims.entity.core.*"%>
<%@ page import="com.ascepionpharm.lims.universal.*"%>
<%@ page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>

<script type='text/javascript'
	src='/AscepionLIMS/dwr/interface/ChemicalMolecularRepository.js'></script>
<script type='text/javascript'
	src='/AscepionLIMS/dwr/interface/ProjectRepository.js'></script>
<script type='text/javascript' src='/AscepionLIMS/dwr/engine.js'></script>
<script type='text/javascript' src='/AscepionLIMS/dwr/util.js'></script>
<script type='text/javascript' src="<%=aspDir%>include/aspJavaScript.js"></script>


<script type="text/javascript">
	function checkInnerName(){
		var innerName = trim(document.getElementById('inputInnerName').value).toUpperCase();
		ChemicalMolecularRepository.checkInnerName(innerName,function callback(data){
			var table = document.getElementById('check');
			if(data == "no find"){
				table.rows[0].cells[1].innerHTML = '<font color="red">Not Find</font>';
				table.rows[1].cells[0].innerHTML = '';
			}
			else{
				table.rows[0].cells[1].innerHTML = '<font color="blue">OK</font>';
				table.rows[1].cells[0].innerHTML='<applet CODEBASE="<%=aspDir%>chemistry/marvin" ARCHIVE="jmarvin.jar" CODE="JMView" WIDTH=150 HEIGHT=150><param NAME="mol" VALUE=' + data + '></applet>'; 
			}
		});
	}

</script>

<br>
<h5>
	Please input a chemicalMolecular:
</h5>
<hr />
<br />
<center />
	<form name="myForm" action="<%=mainservletUrl%>?cmd=query-reaction"
		method="post" onsubmit="return validateQueryReaction()">
		<table id="check">
			<tr>
				<td>
					<strong>Chemicalmolecular you want to query:</strong>
					<input type="text" name="inputInnerName"
						onchange="checkInnerName();" />
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				</td>
			</tr>
		</table>
		<br />
		<table>
			<tr>
				<td>
					<strong>Your Name:</strong>
					<input type="text" name="userName"/>
				</td>
			</tr>
		</table>
		<br />
		<input type="submit" name="submit"
			value="Query reaction by this molecular">
	</form>