<!-- menu tree -->
<ul id="udm" class="udm">
	<li><a class="nohref"><fmt:message key="module.administration.useradmin"/>   |   </a>
		<ul>
	  		<li><A HREF="<%= aspDir %>core/InsertPage.jsp"><fmt:message key="module.administration.useradmin.newlimspage"/></A></li>
	  		<li><A HREF="<%= aspDir %>core/GivePermission.jsp"><fmt:message key="module.administration.useradmin.givepermission"/></A></li>
		</ul>
	</li>
	
	<li><a class="nohref"><fmt:message key="module.administration.employeeadmin"/>   |   </a>
		<ul>
		    <li><A HREF="<%= aspDir %>core/EmployeeList.jsp"><fmt:message key="module.administration.employeeadmin.employeelist"/></A></li>
	  		<li><A HREF="<%= aspDir %>core/NewEmployee.jsp"><fmt:message key="module.administration.employeeadmin.newemployee"/></A></li>
	  		<li><A HREF="<%= aspDir %>core/ActiveEmpList.jsp"><fmt:message key="module.administration.employeeadmin.activeemployeeadmin"/></A></li>
	  		<li><A HREF="<%= aspDir %>core/InactiveEmpList.jsp"><fmt:message key="module.administration.employeeadmin.inactiveemployeeadmin"/></A></li>
		</ul>
	</li>
	
	<li><a class="nohref">Project Administration   |   </a>
		<ul>
	  		<li><A HREF="<%= aspDir %>core/NewProject.jsp">Create Project</A></li>
	  		<li><A HREF="<%= aspDir %>core/ActiveProList.jsp">Project Admin</A></li>
	  		<li><A HREF="<%= aspDir %>core/PreviousProject.jsp">Previous Project</A></li>
		</ul>
	</li>
	
	<li><a class="nohref"><fmt:message key="module.choosesystem"/></a>
		<ul>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=admin"><fmt:message key="module.choosesystem.administration"/></A></li>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=purchase"><fmt:message key="module.choosesystem.purchasing"/></A></li>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=chemistry">Chemistry</A></li>
		</ul>
	</li>
</ul>
