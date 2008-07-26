<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>


<%
	EmployeeRepository employeeRepos =new EmployeeRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();
	
	EmployeeBean[] employees = (EmployeeBean[])employeeRepos.getAll();
	Item[] departments = departmentRepos.getAll();
	
	employeeRepos.destroy();
	departmentRepos.destroy();
 %>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>

<br>
<h5>
	<fmt:message key="newemployee.head" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=new-employee"
		method="post" onsubmit="return validateEmployee()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.name" />
				</th>
				<td>
					<input type="text" name="name" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.employeenumber" />
				</th>
				<td>
					<input type="text" name="employeeNumber" size="25" />
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
					<input type="text" name="brith" size="20" readonly="readonly"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.age" />
				</th>
				<td>
					<select name="age"> 
						<option value='18' selected>18</option> 
						<option value='19'>19</option> 
						<option value='20'>20</option> 
						<option value='21'>21</option> 
						<option value='22'>22</option> 
						<option value='23'>23</option>
						<option value='24'>24</option> 
						<option value='25'>25</option> 
						<option value='26'>26</option> 
						<option value='27'>27</option> 
						<option value='28'>28</option> 
						<option value='29'>29</option>
						<option value='30'>30</option> 
						<option value='31'>31</option> 
						<option value='32'>32</option> 
						<option value='33'>33</option> 
						<option value='34'>34</option> 
						<option value='35'>35</option>
						<option value='36'>36</option> 
						<option value='37'>37</option> 
						<option value='38'>38</option> 
						<option value='39'>39</option> 
						<option value='40'>40</option> 
						<option value='41'>41</option>
						<option value='42'>42</option> 
						<option value='43'>43</option> 
						<option value='44'>44</option> 
						<option value='45'>45</option>
						<option value='46'>46</option> 
						<option value='47'>47</option> 
						<option value='48'>48</option> 
						<option value='49'>49</option> 
						<option value='50'>50</option> 
					</select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.sex" />
				</th>
				<td>
					<select name="sex">
						<option value="male" selected="selected"><fmt:message key="newemployee.male" /></option>
						<option value="female"><fmt:message key="newemployee.female" /></option>
					</select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.nativeplace" />
				</th>
				<td>
					<input type="text" name="nativeplace" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.degree" />
				</th>
				<td>
					<input type="text" name="degree" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.school" />
				</th>
				<td>
					<input type="text" name="school" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.party" />
				</th>
				<td>
					<input type="text" name="party" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.recordplace" />
				</th>
				<td>
					<input type="text" name="recordplace" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.identity" />
				</th>
				<td>
					<input type="text" name="identity" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.navisioncode" />
				</th>
				<td>
					<input type="text" name="navisionCode" size="25" />
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
					<input type="text" name="pactStartTime" size="20" readonly="readonly"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.pactendtime" />
					<a href="javascript:show_calendar('myForm.pactEndTime');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="pactEndTime" size="20" readonly="readonly"/>
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
					<input type="text" name="entranceTime" size="20" readonly="readonly"/>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.leavetime" />
					<a href="javascript:show_calendar('myForm.leaveTime');"
                     onmouseover="window.status='Date Picker';return true"
                     onmouseout="window.status='';return true;"><img border="none" src="<%= aspDir %>include/images/show-calendar.gif"></a>                 
				</th>
				<td>
					<input type="text" name="leaveTime" size="20" readonly="readonly"/>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.email" />
				</th>
				<td>
					<input type="text" name="email" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.title" />
				</th>
				<td>
					<input type="text" name="title" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.department" />
				</th>
				<td colspan="3">
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
					<fmt:message key="newemployee.labphone" />
				</th>
				<td>
					<input type="text" name="labPhone" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.mobilephone" />
				</th>
				<td>
					<input type="text" name="mobilePhone" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.supervisorname" />
				</th>
				<td>
					<select name="supervisorName">
					 	<option value="0" selected>--Select--</option>
            			<% for (int i=0; i < employees.length; i++) { %>
               			<option value="<%= employees[i].getId() %>"><%= employees[i].getName() %></option>
           				<% } %>   
        			 </select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.init" />
				</th>
				<td>
					<select name="init"> 
						<option value='1'>1</option> 
						<option value='2'>2</option> 
						<option value='3'>3</option> 
						<option value='4'>4</option> 
						<option value='5'>5</option> 
						<option value='6'>6</option>
						<option value='7'>7</option> 
						<option value='8'>8</option> 
						<option value='9'>9</option> 
						<option value='10' selected>10</option> 
					</select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="newemployee.comment" />
				</th>
				<td colspan="3"> 
					<input type="text" name="comment" size="60" />
				</td>
			</tr>
		</table>
		<br />
		<input name="submit" type="submit" value="<fmt:message key="newemployee.submit" />" />
	</form>