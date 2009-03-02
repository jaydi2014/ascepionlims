<!-- 添加分子到项目 -->
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
	
	function changeProjectsByDepartment(){
		var departmentId = document.getElementById('department').value;
		ProjectRepository.getByDepartment(departmentId,fillInSelectByType);
	}
	function fillInSelectByType(projects){
		dwr.util.removeAllOptions('projectSingleSelect');
		var project = document.getElementById('project');
		project.options[0] = new Option("--please select--", "");
		dwr.util.addOptions('projectSingleSelect',projects,'id','name');
	}
</script>

<%
	DepartmentRepository departmentRepos = new DepartmentRepository();
	EmployeeRepository employeeRepos = new EmployeeRepository();

	Item[] departments = departmentRepos.getAll();
	EmployeeBean[] employee = (EmployeeBean[])employeeRepos.getAll();

	departmentRepos.destroy();
	employeeRepos.destroy();
%>

<br>
<h5>
	Please Add ChemicalMolecular To Project:
</h5>
<hr />
<br />
<center />
	<form name="myForm" action="<%=mainservletUrl%>?cmd=add-moltoproject"
		method="post" onsubmit="return validateAddMolToProject()">
		<table width="60%">
			<tr>
				<td width="30%" align="center">
					<strong>Department:</strong>
					<select name="departmentType" id="department"
						onchange="changeProjectsByDepartment(this);">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < departments.length; i++) {
						%>
						<option value="<%=departments[i].getId()%>"><%=departments[i].getType()%></option>
						<%
							}
						%>
					</select>
				</td>
				<td width="30%" align="center">
					<strong>Project:</strong>
					<select name="projectSingleSelect" id="project">
						<option value="" selected="selected">
							--please select--
						</option>

					</select>
				</td>
			</tr>
		</table>
		<br />
		<br />
		<table id="check">
			<tr>
				<td>
					<strong>Added Chemicalmolecular:</strong>
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
					<select name="userName">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < employee.length; i++) {
						%>
						<option value="<%=employee[i].getName()%>"><%=employee[i].getName()%></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
		</table>
		<br />
		<input type="submit" name="submit"
			value="Add this molecular to project">
	</form>