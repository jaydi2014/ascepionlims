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
<h5><fmt:message key="activeemplist.head" />:</h5>
<hr>

<center>
<table width="90%" cellspacing=0 cellpadding=4 border=1>
	<tr bgcolor="lightgrey">
		<th><fmt:message key="activeemplist.name" /></th>
		<th><fmt:message key="activeemplist.age" /></th>
		<th><fmt:message key="activeemplist.degree" /></th>
		<th><fmt:message key="activeemplist.department" /></th>
		<th><fmt:message key="activeemplist.title" /></th>
		<th><fmt:message key="activeemplist.labphone" /></th>
		<th><fmt:message key="activeemplist.mobilephone" /></th>
		<th><fmt:message key="activeemplist.email" /></th>
	</tr>
	<% for (int i=0; i<employee.length; i++) { 
		int id = employee[i].getId();
		String string = "core/EmployeeInfo.jsp?employeeid="+id;
	%>
	<tr>
		<td><a href="<%=aspDir %><%=string %>"><%=employee[i].getName() %></a></td>
		<td><%=employee[i].getAge() %></td>
		<td><%=employee[i].getDegree() %></td>
		<% for (int j=0; j<department.length; j++) {
			if(employee[i].getDepartmentId() == department[j].getId()){ %>
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