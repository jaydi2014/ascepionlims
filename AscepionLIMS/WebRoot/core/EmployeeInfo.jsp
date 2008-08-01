<!-- 员工信息 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>


<%
	EmployeeRepository employeeRepos =new EmployeeRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();
	int employeeId = Integer.parseInt(request.getParameter("employeeid"));
	
	EmployeeBean[] employees = (EmployeeBean[])employeeRepos.getAllActive();
	EmployeeBean employee = (EmployeeBean)employeeRepos.get(employeeId);
	Item[] departments = departmentRepos.getAll();
	
	employeeRepos.destroy();
	departmentRepos.destroy();
 %>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>

<br>
<h5>
	<fmt:message key="employeeinfo.head" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=update-employee"
		method="post" onsubmit="return validateEmployee()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<input type="hidden" name="id" value="<%=employee.getId() %>"/>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.name" />
				</th>
				<td>
					<input type="text" name="name" size="25" value="<%=FormatterFeeder.validateNull(employee.getName()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.employeenumber" />
				</th>
				<td>
					<input type="text" name="employeeNumber" size="25" value="<%=FormatterFeeder.validateNull(employee.getEmployeeNumber()) %>"/>
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.birth" />
					<a href="javascript:show_calendar('myForm.brith');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="brith" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(employee.getBrith(),"MM/dd/yyyy")) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.age" />
				</th>
				<td>
					<select name="age" > 
					<% for(int i=18;i<=50;i++) { %>
						<% if(employee.getAge() == i) { %>
							<option value='<%=i %>' selected><%=i %></option>
						<% } else { %>
							<option value='<%=i %>'><%=i %></option>
						<% } %>
					<% } %>
					</select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.sex" />
				</th>
				<td>
					<select name="sex">
					    <% if(employee.getSex().equals("male")) { %>
							<option value='male' selected="selected"><fmt:message key="newemployee.male" /></option>
							<option value='female'><fmt:message key="newemployee.female" /></option>
						<% } %>
						<% if(employee.getSex().equals("female")) { %>
							<option value='male'><fmt:message key="newemployee.male" /></option>
							<option value='female' selected="selected"><fmt:message key="newemployee.female" /></option>
						<% } %>
					</select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.nativeplace" />
				</th>
				<td>
					<input type="text" name="nativeplace" size="25" value="<%=FormatterFeeder.validateNull(employee.getNativeplace()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.degree" />
				</th>
				<td>
					<input type="text" name="degree" size="25" value="<%=FormatterFeeder.validateNull(employee.getDegree()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.school" />
				</th>
				<td>
					<input type="text" name="school" size="25" value="<%=FormatterFeeder.validateNull(employee.getSchool()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.party" />
				</th>
				<td>
					<input type="text" name="party" size="25" value="<%=FormatterFeeder.validateNull(employee.getParty()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.recordplace" />
				</th>
				<td>
					<input type="text" name="recordplace" size="25" value="<%=FormatterFeeder.validateNull(employee.getRecordplace()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.identity" />
				</th>
				<td>
					<input type="text" name="identity" size="25" value="<%=FormatterFeeder.validateNull(employee.getIdentity()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.navisioncode" />
				</th>
				<td>
					<input type="text" name="navisionCode" size="25" value="<%=FormatterFeeder.validateNull(employee.getNavisionCode()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.pactstarttime" />
					<a href="javascript:show_calendar('myForm.pactStartTime');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="pactStartTime" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(employee.getPactStartTime(),"MM/dd/yyyy")) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.pactendtime" />
					<a href="javascript:show_calendar('myForm.pactEndTime');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="pactEndTime" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(employee.getPactEndTime(),"MM/dd/yyyy")) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.entrancetime" />
					<a href="javascript:show_calendar('myForm.entranceTime');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="entranceTime" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(employee.getEntranceTime(),"MM/dd/yyyy")) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.leavetime" />
					<a href="javascript:show_calendar('myForm.leaveTime');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="leaveTime" size="20" readonly="readonly" value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(employee.getLeaveTime(),"MM/dd/yyyy")) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.email" />
				</th>
				<td>
					<input type="text" name="email" size="25" value="<%=FormatterFeeder.validateNull(employee.getEmail()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.title" />
				</th>
				<td>
					<input type="text" name="title" size="25" value="<%=FormatterFeeder.validateNull(employee.getTitle()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.department" />
				</th>
				<td>
					 <select name="department">
            			<% for (int i=0; i < departments.length; i++) { 
            				if(employee.getDepartmentId() == departments[i].getId()) {
            			%>
               				<option value="<%= departments[i].getId() %>" selected><%= departments[i].getType() %></option>
           					<% } else { %>
           					<option value="<%= departments[i].getId() %>"><%= departments[i].getType() %></option>
           					<% } %>
           				<% } %>   
        			 </select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.active" />
				</th>
				<td>
					<select name="active">
						<% if(employee.getActive() == 1) { %>
							<option value="1" selected>Active</option>
						<% } else { %>
							<option value="1">Active</option>
						<% } %>
						<% if(employee.getActive() == 0) { %>
							<option value="0" selected>Inactive</option>
						<% } else { %>
							<option value="0">Inactive</option>
						<% } %>
					</select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.labphone" />
				</th>
				<td>
					<input type="text" name="labPhone" size="25" value="<%=FormatterFeeder.validateNull(employee.getLabPhone()) %>"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.mobilephone" />
				</th>
				<td>
					<input type="text" name="mobilePhone" size="25" value="<%=FormatterFeeder.validateNull(employee.getMobilePhone()) %>"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.supervisorname" />
				</th>
				<td>
					<select name="supervisorName">
					 	<% for (int i=0; i < employees.length; i++) { 
            				if(employee.getSupervisorId() == employees[i].getId()) {
            			%>
               				<option value="<%= employees[i].getId() %>" selected><%= employees[i].getName() %></option>
           					<% } else { %>
           					<option value="<%= employees[i].getId() %>"><%= employees[i].getName() %></option>
           					<% } %>
           				<% } %>   
        			 </select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.init" />
				</th>
				<td>
					<select name="init"> 
					<% for(int i=1;i<=10;i++) { %>
						<% if(Integer.parseInt(employee.getInit()) == i) { %>
							<option value='<%=i %>' selected><%=i %></option>
						<% } else { %>
							<option value='<%=i %>'><%=i %></option>
						<% } %>
					<% } %>
					</select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.comment" />
				</th>
				<td colspan="3"> 
					<input type="text" name="comment" size="60" value="<%=FormatterFeeder.validateNull(employee.getCommentline()) %>"/>
				</td>
			</tr>
		</table>
		<br />
		<input name="submit" type="submit" value="<fmt:message key="newemployee.submit" />" />
	</form>