<!-- 已完成项目列表 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%	
	ProjectRepository projectRepos = new ProjectRepository();
	DepartmentRepository departmentRepos = new  DepartmentRepository();
	
	ProjectBean[] projects = (ProjectBean[])projectRepos.getAllinactive();
	Item[] department = (Item[])departmentRepos.getAll();
	
	departmentRepos.destroy();
%>
<h5>Project List:</h5>
<hr>

<center>
<table width="90%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th>Project Id</th>
		<th>Project Name</th>
		<th>Description</th>
		<th>Department</th>
		<th>Status</th>
	</tr>
	<% for (int i=0; i<projects.length; i++) { 
		int id = projects[i].getId();
		String string = "core/ProjectInfo.jsp?projectid="+id;
	%>
	<tr align="center">
		<td><%=projects[i].getId() %></td>
		<td><a href="<%=aspDir %><%=string %>"><%=projects[i].getName() %></a></td>
		<td><%=projects[i].getDescription() %></td>
		<% for (int j=0; j<department.length; j++) {
			if(projects[i].getDepartmentId() == department[j].getId()){ %>
				<td><%=department[j].getType() %></td>
			<% break;} %>	
		<% } %>
		<td><span style="color: red">Complete</span></td>
	</tr>
	<% } %>
</table>