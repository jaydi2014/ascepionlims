<!-- 采购查询 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ page import="com.ascepionpharm.lims.entity.core.*"%>
<%@ page import="com.ascepionpharm.lims.universal.*"%>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>
<%
	PurchasingBean[] purchase = null;
	AccountingItemRepository accountingItemRepos = new AccountingItemRepository();
	ProjectRepository projectRepos = new ProjectRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();
	BankAccountRepository bankRepos = new BankAccountRepository();

	purchase = (PurchasingBean[])request.getAttribute("purchase");
	AccountingItemBean[] accountItems = (AccountingItemBean[]) accountingItemRepos.getAll();
	ProjectBean[] projects = (ProjectBean[]) projectRepos.getAll();
	Item[] departments = (Item[]) departmentRepos.getAll();
	BankAccountBean[] banks = (BankAccountBean[])bankRepos.getAll();

	accountingItemRepos.destroy();
	projectRepos.destroy();
	departmentRepos.destroy();
	bankRepos.destroy();;
%>
<table>
	<tr>
		<td width=300>
			<br />
			<select name="selectway" onChange="ChangePurchaseQuary();">
				<option value="" selected>
					--Please Select Quary Way--
				</option>
				<option value="purchaseperson">
					<fmt:message key="purchaserequest.purchaseperson" />
				</option>
				<option value="purchasename">
					<fmt:message key="purchaserequest.purchasename" />
				</option>
				<option value="purchasedate">
					<fmt:message key="purchaserequest.purchasesdate" />
				</option>
				<option value="purchasetype">
					<fmt:message key="purchaserequest.purchasestype" />
				</option>
				<option value="purchaseproject">
					<fmt:message key="purchaserequest.purchasesproject" />
				</option>
				<option value="purchasedepartment">
					<fmt:message key="purchaserequest.purchasesdepartment" />
				</option>
				<option value="accountname">
					<fmt:message key="addbankaccount.accountname" />
				</option>
			</select>
		</td>
		<td>
			<br />
			<br />
			<div id="purchaseperson" style="display: none">
				<form name="qperson"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=purchaseperson"
					method="post" onsubmit="return validatePurchaseQuaryPerson()">
					<fmt:message key="purchaserequest.purchaseperson" />
					:
					<input name="yourname" type="text" />
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>

			<div id="purchasename" style="display: none">
				<form name="qname"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=purchasename"
					method="post" onsubmit="return validatePurchaseQuaryName()">
					<fmt:message key="purchaserequest.purchasename" />
					:
					<input name="warename" type="text" />
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>

			<div id="purchasedate" style="display: none">
				<form name="qdate"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=purchasedate"
					method="post" onsubmit="return validatePurchaseQuaryDate()">
					<fmt:message key="purchaserequest.purchasesdate" />
					:
					<a href="javascript:show_calendar('qdate.startdate');"
						onmouseover="window.status='Date Picker';return true"
						onmouseout="window.status='';return true;"><img border="none"
							src="<%=aspDir%>include/images/show-calendar.gif"> </a>
					<input name="startdate" type="text" readonly="readonly" />
					------
					<a href="javascript:show_calendar('qdate.enddate');"
						onmouseover="window.status='Date Picker';return true"
						onmouseout="window.status='';return true;"><img border="none"
							src="<%=aspDir%>include/images/show-calendar.gif"> </a>
					<input name="enddate" type="text" readonly="readonly" />
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>

			<div id="purchasetype" style="display: none">
				<form name="qtype"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=purchasetype"
					method="post" onsubmit="return validatePurchaseQuaryType()">
					<fmt:message key="purchaserequest.purchasestype" />
					:
					<select name="type">
						<option value="" selected>
							--Type--
						</option>
						<%
							for (int i = 0; i < accountItems.length; i++) {
						%>
						<option value="<%=accountItems[i].getId()%>"><%=accountItems[i].getName()%></option>
						<%
							}
						%>
					</select>
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>

			<div id="purchaseproject" style="display: none">
				<form name="qproject"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=purchaseproject"
					method="post" onsubmit="return validatePurchaseQuaryProject()">
					<fmt:message key="purchaserequest.purchasesproject" />
					:
					<select name="project">
						<option value="" selected>
							--Project--
						</option>
						<%
							for (int i = 0; i < projects.length; i++) {
						%>
						<option value="<%=projects[i].getId()%>"><%=projects[i].getName()%></option>
						<%
							}
						%>
					</select>
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>

			<div id="purchasedepartment" style="display: none">
				<form name="qdepartment"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=purchasedepartment"
					method="post" onsubmit="return validatePurchaseQuaryDepartment()">
					<fmt:message key="purchaserequest.purchasesdepartment" />
					:
					<select name="department">
						<option value="" selected>
							--Department--
						</option>
						<%
							for (int i = 0; i < departments.length; i++) {
						%>
						<option value="<%=departments[i].getId()%>"><%=departments[i].getType()%></option>
						<%
							}
						%>
					</select>
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>
			
			<div id="accountname" style="display: none">
				<form name="qaccount"
					action="<%=mainservletUrl%>?cmd=quary-purchase&way=accountname"
					method="post" onsubmit="return validatePurchaseQuaryAccount()">
					<fmt:message key="addbankaccount.accountname" />
					:
					<select name="account">
						<option value="" selected>
							--Account--
						</option>
						<%
							for (int i = 0; i < banks.length; i++) {
						%>
						<option value="<%=banks[i].getId()%>"><%=banks[i].getAccountName()%></option>
						<%
							}
						%>
					</select>
					<input name="submit" type="submit" value="submit" />
				</form>
			</div>
		</td>
	</tr>
</table>

<hr>
	<% if(purchase != null) { %>
		<center>
	<table id="purchase" width="95%" cellspacing=0 cellpadding=4 border=1>
		<tr bgcolor="lightgrey">
			<th>
				<fmt:message key="purchaserequest.purchasesid" />
			</th>
			<th>
				<fmt:message key="purchaserequest.purchasename" />
			</th>
			<th>
				<fmt:message key="purchaserequest.purchaseperson" />
			</th>
			
			<th>
				<fmt:message key="purchaserequest.purchasestype" />
			</th>
			<th>
				<fmt:message key="purchaserequest.purchasesproject" />
			</th>
			<th>
				<fmt:message key="purchaserequest.purchasesdepartment" />
			</th>
			<th>
				<fmt:message key="purchaserequest.purchasesource" />
			</th>
			<th>
				<fmt:message key="purchaserequest.purchasenumber" />
			</th>
			<th>
				<fmt:message key="purchaserequest.totleprice" />
			</th>
			<th>
				<fmt:message key="purchaserequest.isapproved" />
			</th>
		</tr>
		<%
			float sum = 0; 	
			for (int i = 0; i < purchase.length; i++) {
			int id = purchase[i].getId();
			sum+=purchase[i].getTotleprice();
			String string = "core/PurchaseInfo.jsp?purchaseid=" + id;
		%>
		<tr>
			<td align="center"><%=purchase[i].getId()%></td>
			<td align="center"><a href="<%=aspDir%><%=string%>"><%=purchase[i].getPurchaseName()%></td>
			<td><%=purchase[i].getPurchasePerson()%></td>
			<% for (int j=0; j<accountItems.length; j++) {
				if(purchase[i].getItemId() == accountItems[j].getId()){ %>
				<td><%=accountItems[j].getName() %></td>
				<% break;} %>	
			<% } %>
			<% for (int j=0; j<projects.length; j++) {
				if(purchase[i].getProjectId() == projects[j].getId()){ %>
				<td><%=projects[j].getName() %></td>
				<% break;} %>	
			<% } %>
			<% for (int j=0; j<departments.length; j++) {
				if(purchase[i].getDempartmentId() == departments[j].getId()){ %>
				<td><%=departments[j].getType() %></td>
				<% break;} %>	
			<% } %>
			<td><%=purchase[i].getPurchaseSource()%></td>
			<td><%=purchase[i].getPurchaseNumber()%></td>
			<td><%=purchase[i].getTotleprice()%></td>
			<%
			if (purchase[i].getIsApproved() == 1) {
			%>
			<td align="center" style="color: red">
				Yes
			</td>
			<%
			} else {
			%>
			<td align="center" style="color: red">
				No
			</td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
		<tr>
			<td align="right" colspan="8">Sum Price</td>
			<td colspan="2" style="color: red">$&nbsp<%= sum %></td>
		</tr>
	</table>
	<% } %>


