<!-- 显示化学分子存储 -->
<%@ include file="/core/Permission.jsp"%>
<%@include file="/common/Menu.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.jspsmart.upload.*"%>

<script LANGUAGE="JavaScript1.1"
	src="<%=aspDir%>chemistry/marvin/marvin.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<script LANGUAGE="JavaScript1.1">
function init1(){
	var sm = document.getElementById('reactiona').value;
	var table = document.getElementById('reaction');
	var a=table.rows[1].cells[0];
	a.innerHTML='<br/><br/><br/><br/><applet CODEBASE="<%=aspDir%>chemistry/marvin" ARCHIVE="jmarvin.jar" CODE="JMView" WIDTH=150 HEIGHT=150><param NAME="mol" VALUE=' + sm + '></applet>';
	var obj = document.getElementById('reactiona');
	var index = obj.selectedIndex;
	var label = obj.options[index].label;
	var text = obj.options[index].text;
	document.getElementById('hreactiona').value = label;
	document.getElementById('nreactiona').value = text;
}

function init2(){
	var sm = document.getElementById('reactionb').value;
	var table = document.getElementById('reaction');
	var a=table.rows[1].cells[2];
	a.innerHTML='<br/><br/><br/><br/><applet CODEBASE="<%=aspDir%>chemistry/marvin" ARCHIVE="jmarvin.jar" CODE="JMView" WIDTH=150 HEIGHT=150><param NAME="mol" VALUE=' + sm + '></applet>';
	var obj = document.getElementById('reactionb');
	var index = obj.selectedIndex;
	var label = obj.options[index].label;
	var text = obj.options[index].text;
	document.getElementById('hreactionb').value = label;
	document.getElementById('nreactionb').value = ' + ' + text;
}

function init3(){
	var sm = document.getElementById('catalysta').value;
	var table = document.getElementById('catalyst');
	var a=table.rows[0].cells[0];
	a.innerHTML='<applet CODEBASE="<%=aspDir%>chemistry/marvin" ARCHIVE="jmarvin.jar" CODE="JMView" WIDTH=75 HEIGHT=75><param NAME="mol" VALUE=' + sm + '></applet>';
	var obj = document.getElementById('catalysta');
	var index = obj.selectedIndex;
	var label = obj.options[index].label;
	document.getElementById('hcatalysta').value = label;
}

function init4(){
	var sm = document.getElementById('product').value;
	if(sm != ""){
	var table = document.getElementById('reaction');
	var a=table.rows[1].cells[4];
	a.innerHTML='<br/><br/><br/><br/><applet CODEBASE="<%=aspDir%>chemistry/marvin" ARCHIVE="jmarvin.jar" CODE="JMView" WIDTH=150 HEIGHT=150><param NAME="mol" VALUE=' + sm + '></applet>';
	var obj = document.getElementById('product');
	var index = obj.selectedIndex;
	var text = obj.options[index].text;
	var reactionName = document.getElementById('reactionName');
	reactionName.innerHTML = '  >' + text;
	
	var label = obj.options[index].label;
	document.getElementById('hproduct').value = label;
	document.getElementById('nproduct').value = ' > ' + text;
	}
}
</script>

<%
	int projectId = Integer.parseInt(request.getParameter("project"));
	ChemicalMolecularRepository chemistryMolRepos = new ChemicalMolecularRepository();
	ChemicalMolecularBean[] chemicalMoleculars = (ChemicalMolecularBean[]) chemistryMolRepos
			.getList(projectId);
	chemistryMolRepos.destroy();
%>

<br>
<h5>
	Creating reaction:
</h5>
<hr>
<br />
Reaction Name:
<strong><span id="reactionName" style="color: red"></span> </strong>
<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=create-reaction"
		method="post" onsubmit="return validateCreateReaction()">
		<input type="hidden" id="projectId" name="projectId" value="<%=projectId %>"/>
		<table id="reaction" align="center">
			<tr>
				<td align="center">
					<select id="reactiona" name="reactiona" onchange="init1();">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < chemicalMoleculars.length; i++) {
						%>
						<option value="<%=chemicalMoleculars[i].getSmiles()%>" label="<%=chemicalMoleculars[i].getId()%>"><%=chemicalMoleculars[i].getInnerName()%></option>
						<%
							}
						%>
					</select>
					<input type="hidden" id="hreactiona" name="hreactiona" value=""/>
					<input type="hidden" id="nreactiona" name="nreactiona" value=""/>
				</td>
				<td align="center">
				</td>
				<td align="center">
					<select id="reactionb" name="reactionb" onchange="init2();">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < chemicalMoleculars.length; i++) {
						%>
						<option value="<%=chemicalMoleculars[i].getSmiles()%>" label="<%=chemicalMoleculars[i].getId()%>"><%=chemicalMoleculars[i].getInnerName()%></option>
						<%
							}
						%>
					</select>
					<input type="hidden" id="hreactionb" name="hreactionb" value=""/>
					<input type="hidden" id="nreactionb" name="nreactionb" value=""/>
				</td>
				<td align="center">
					<select id="catalysta" name="catalysta" onchange="init3();">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < chemicalMoleculars.length; i++) {
						%>
						<option value="<%=chemicalMoleculars[i].getSmiles()%>" label="<%=chemicalMoleculars[i].getId()%>"><%=chemicalMoleculars[i].getInnerName()%></option>
						<%
							}
						%>
					</select>
					<input type="hidden" id="hcatalysta" name="hcatalysta" value=""/>
				</td>
				<td align="center">
					<select id="product" name="product" onchange="init4();">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < chemicalMoleculars.length; i++) {
						%>
						<option value="<%=chemicalMoleculars[i].getSmiles()%>" label="<%=chemicalMoleculars[i].getId()%>"><%=chemicalMoleculars[i].getInnerName()%></option>
						<%
							}
						%>
					</select>
					<input type="hidden" id="hproduct" name="hproduct" value=""/>
					<input type="hidden" id="nproduct" name="nproduct" value=""/>
				</td>
			</tr>
			<tr>
				<td align="center">
				</td>
				<td align="center">
					<br />
					<br />
					<br />
					<br />
					<font size="12">+</font>
				</td>
				<td align="center">
				</td>
				<td align="center">
					<table id="catalyst" cellpadding="0" cellspacing="0">
						<tr>
							<td align="center">
							</td>
						</tr>
						<tr>
							<td align="center">
								<font size="12">------></font>
							</td>
						<tr>
							<td align="center">
								Catalyst:
								<input type="text" name="catalystb" />
								<br />
								Condition:
								<input type="text" name="condition" />
							</td>
						</tr>

					</table>
				</td>
				<td align="center">
				</td>
			</tr>
		</table>
		<br />
		<br />
		<br />
		<br />
		<input name="submit" type="submit" value="Store this reaction" />
	</form>