<!-- 在职员工通讯录 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%	
	EmployeeRepository employeeRepos = new EmployeeRepository();
	DepartmentRepository departmentRepos = new  DepartmentRepository();
	
	EmployeeBean[] employee = (EmployeeBean[])employeeRepos.getAllActive();
	Item[] department = (Item[])departmentRepos.getAll();
	
	employeeRepos.destroy();
	departmentRepos.destroy();
%>
<h5>Employee List:</h5>
<hr>

<center>
<table width="90%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th><fmt:message key="activeemplist.name" /></th>
		<th><fmt:message key="activeemplist.department" /></th>
		<th><fmt:message key="activeemplist.title" /></th>
		<th><fmt:message key="activeemplist.labphone" /></th>
		<th><fmt:message key="activeemplist.mobilephone" /></th>
		<th><fmt:message key="activeemplist.email" /></th>
	</tr>
	<% for (int i=0; i<employee.length; i++) { 
		int id = employee[i].getId();
	%>
	<tr>
		<td><%=employee[i].getName() %></td>
		<% for (int j=0; j<department.length; j++) {
			if(department[j].getId()==employee[i].getDepartmentId()){ %>
				<td><%=department[j].getType() %></td>
			<% break;} %>	
		<% } %>
		<td><%=employee[i].getTitle() %></td>
		<td><%=employee[i].getLabPhone() %></td>
		<td><%=employee[i].getMobilePhone() %></td>
		<td><%=employee[i].getEmail() %></td>
	</tr>
	<% } %>
</table>