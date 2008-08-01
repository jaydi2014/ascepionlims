<!-- 新建项目 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%
	DepartmentRepository departmentRepos = new DepartmentRepository();
	Item[] departments = departmentRepos.getAll();
	departmentRepos.destroy();
%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>

<br>
<h5>
	Please Create Project:
</h5>

<center>
<form name="myForm" action="<%=mainservletUrl%>?cmd=new-project"
		method="post" onsubmit="return validateProject()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					Project Name
				</th>
				<td>
					<input type="text" name="name" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					Department
				</th>
				<td>
					 <select name="department">
					 	<option value="" selected>--Select--</option>
            			<% for (int i=0; i < departments.length; i++) { %>
               			<option value="<%= departments[i].getId() %>"><%= departments[i].getType() %></option>
           				<% } %>   
        			 </select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					Description
				</th>
				<td colspan="3">
					<input type="text" name="description" size="100" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					Members
				</th>
				<td colspan="3">
					<input type="text" name="members" size="100" />
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
					<input type="text" name="start_time" size="20" readonly="readonly"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					End Time
					<a href="javascript:show_calendar('myForm.end_time');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="end_time" size="20" readonly="readonly"/>
				</td>
			</tr>
		</table>
	<input name="submit" type="submit" value="submit" />
</form>