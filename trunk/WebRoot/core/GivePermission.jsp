<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<%
	int employeeId;
	if(request.getParameter("selectEmployeeId")==null)employeeId = -1;
	else employeeId = Integer.parseInt(request.getParameter("selectEmployeeId"));
	EmployeeTypeRepository employeeTypeRepos = new EmployeeTypeRepository();
	PermissionRepository permissionRepos = new PermissionRepository();
	
	Item[] employeeType = employeeTypeRepos.getAll();
	
	employeeTypeRepos.destroy();
 %>

<center>
<h5>Please select a employeeType:</h5>

<form name="myForm" action="" method="post">
	<select name="employeetype" onchange="getEmployeeTypeId()">
		<option value="-1" selected>--Select--</option>
        <% for (int i=0; i < employeeType.length; i++) { %>
        	<% if(employeeType[i].getId() == employeeId) { %>
        		<option value="<%= employeeType[i].getId() %>" selected><%= employeeType[i].getType() %></option>
        	<% } else { %>
        	<option value="<%= employeeType[i].getId() %>"><%= employeeType[i].getType() %></option>
        	<% } %> 
        <% } %>  
	</select>
</form>
<hr>
<br />
<%if(employeeId > 0) {%>
<%  PermissionBean[] permission = (PermissionBean[])permissionRepos.getList(employeeId); 
	permissionRepos.destroy();
%>
<table width="90%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th>PageId</th>
		<th>URL</th>
		<th>Granted</th>
	</tr>
	<% for (int i=0; i<permission.length; i++) { 
		int id = permission[i].getPageId();
		String url = permission[i].getURI();
		int grant = permission[i].getGranted();
		int typeid = permission[i].getEmployeeTypeId();
		String string = "?cmd=give-permission&pageid="+id+"&employeetypeid="+typeid+"&granted="+grant;
	%>
	<tr>
		<td><%=id %></td>
		<td><%=url %></td>
		<td><span style="color: red"><%=grant %></span>&nbsp&nbsp&nbsp&nbsp<a href="<%=servletUrl %><%=string %>">change</a></td>
	</tr>
	<% } %>

</table>
<% } %>


