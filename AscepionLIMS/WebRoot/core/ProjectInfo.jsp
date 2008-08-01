<!-- 项目信息 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%
	ProjectRepository projectRepos =new ProjectRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();
	int projectId = Integer.parseInt(request.getParameter("projectid"));
	
	ProjectBean project = (ProjectBean)projectRepos.get(projectId);
	Item[] departments = departmentRepos.getAll();
	
	projectRepos.destroy();
	departmentRepos.destroy();
 %>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>

<br>
<h5>
	Please Edit Project:
</h5>

<center>
<form name="myForm" action="<%=mainservletUrl%>?cmd=update-project"
		method="post" onsubmit="return validateProject()">
		<input type="hidden" name="id" value="<%=project.getId() %>">
		<p align="left">
		<span style="color: red">Status:</span>
		<select name="status">
			<% if(project.getStatus() == 1) { %>
				<option value="1" selected="selected">Ongoing</option>
				<option value="0">Complete</option>
			<% } else { %>
				<option value="1">Ongoing</option>
				<option value="0" selected="selected">Complete</option>
			<% } %>
		</select>
		</p>
		<br />
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					Project Name
				</th>
				<td>
					<input type="text" name="name" size="25" value="<%=FormatterFeeder.validateNull(project.getName()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					Department
				</th>
				<td>
					  <select name="department">
            			<% for (int i=0; i < departments.length; i++) { 
            				if(project.getDepartmentId() == departments[i].getId()) {
            			%>
               				<option value="<%= departments[i].getId() %>" selected><%= departments[i].getType() %></option>
           					<% } else { %>
           					<option value="<%= departments[i].getId() %>"><%= departments[i].getType() %></option>
           					<% } %>
           				<% } %>   
        			 </select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					Description
				</th>
				<td colspan="3">
					<input type="text" name="description" size="100" value="<%=FormatterFeeder.validateNull(project.getDescription()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					Members
				</th>
				<td colspan="3">
					<input type="text" name="members" size="100" value="<%=FormatterFeeder.validateNull(project.getMembers()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					Start Time
					<a href="javascript:show_calendar('myForm.start_time');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="start_time" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(project.getStart_time(),"MM/dd/yyyy")) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					End Time
					<a href="javascript:show_calendar('myForm.end_time');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="end_time" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(project.getEnd_time(),"MM/dd/yyyy")) %>"/>
				</td>
			</tr>
		</table>
	<input name="submit" type="submit" value="submit" />
</form>