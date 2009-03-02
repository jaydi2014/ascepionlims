<!-- 为反应选择项目 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<%@page import="com.ascepionpharm.lims.repository.core.ProjectRepository"%>
<%@page import="com.ascepionpharm.lims.entity.core.ProjectBean"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<%
	ProjectRepository projectRepos = new ProjectRepository();
	
	ProjectBean[] projects =(ProjectBean[])projectRepos.getAll();
	
	projectRepos.destroy();
%>
<br>
<h5>
	Please select a project for creating reaction:
</h5>
<hr>

<center>
	<form name="myForm" action="<%=aspDir%>chemistry/CreateReaction.jsp"
		method="post" onsubmit="return validateSelectionProjectForPorject()">
		<table width="60%">
			<tr align="center">
				<td>
					<strong>Project:</strong>
					<select name="project">
						<option value="" selected="selected">
							--please select--
						</option>
						<%
							for (int i = 0; i < projects.length; i++) {
						%>
						<option value="<%=projects[i].getId()%>"><%=projects[i].getName()%></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
		</table>
		<br/ >
		<br/ >
		<input name="submit" type="submit" value="next" />
	</form>