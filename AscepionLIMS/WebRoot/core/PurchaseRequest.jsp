<!-- 采购申请 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*" %>
<%@ page import="com.ascepionpharm.lims.entity.core.*" %>
<%@ page import="com.ascepionpharm.lims.universal.*" %>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<%
	AccountingItemRepository accountingItemRepos = new AccountingItemRepository();
	ProjectRepository projectRepos = new ProjectRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();
	
	AccountingItemBean[] accountItems = (AccountingItemBean[])accountingItemRepos.getAll();
	ProjectBean[] projects = (ProjectBean[])projectRepos.getAll();
	Item[] departments = (Item[])departmentRepos.getAll();
	
	accountingItemRepos.destroy();
	projectRepos.destroy();
	departmentRepos.destroy();
%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<br>
<h5>
	<fmt:message key="purchaserequest.head" />
	:
</h5>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=purchase-request"
		method="post" onsubmit="return validatePurchaseing()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchaseperson" />
				</th>
				<td>
					<input type="text" name="purchasePerson" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasestype" />
				</th>
				<td>
					 <select name="accountItem">
					 	<option value="" selected>--Select--</option>
            			<% for (int i=0; i < accountItems.length; i++) { %>
               			<option value="<%= accountItems[i].getId() %>"><%= accountItems[i].getName() %></option>
           				<% } %>   
        			 </select>
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesproject" />
				</th>
				<td>
					 <select name="project">
					 	<option value="" selected>--Select--</option>
            			<% for (int i=0; i < projects.length; i++) { %>
               			<option value="<%= projects[i].getId() %>"><%= projects[i].getName() %></option>
           				<% } %>   
        			 </select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesdepartment" />
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
					<fmt:message key="purchaserequest.purchasename" />
				</th>
				<td>
					<input type="text" name="purchaseName" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesource" />
				</th>
				<td>
					<input type="text" name="purchaseSource" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasenumber" />
				</th>
				<td>
					<input type="text" name="purchaseNumber" size="25" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.totleprice" />
				</th>
				<td>
					<input type="text" name="totleprice" size="25" />
				</td>
			</tr>
			
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.commentline" />
				</th>
				<td colspan="3">
					<input type="text" name="commentLine" size="60"/>
				</td>
			</tr>
		</table>
		<br />
		<input name="submit" type="submit" value="<fmt:message key="purchaserequest.submit" />" />
	</form>
