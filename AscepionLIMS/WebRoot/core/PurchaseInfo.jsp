<!-- 采购信息 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ page import="com.ascepionpharm.lims.entity.core.*"%>
<%@ page import="com.ascepionpharm.lims.universal.*"%>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>

<%
	int id = Integer.parseInt(request.getParameter("purchaseid"));
	PurchasingRepository purchaseRepos = new PurchasingRepository();
	BankAccountRepository bankRepos = new BankAccountRepository();
	AccountingItemRepository accountingItemRepos = new AccountingItemRepository();
	ProjectRepository projectRepos = new ProjectRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();

	PurchasingBean purchase = (PurchasingBean) purchaseRepos.get(id);
	BankAccountBean[] bank = (BankAccountBean[]) bankRepos.getAll();
	AccountingItemBean[] accountItems = (AccountingItemBean[]) accountingItemRepos
			.getAll();
	ProjectBean[] projects = (ProjectBean[]) projectRepos.getAllN();
	Item[] departments = (Item[]) departmentRepos.getAll();

	purchaseRepos.destroy();
	bankRepos.destroy();
	accountingItemRepos.destroy();
	projectRepos.destroy();
	departmentRepos.destroy();
%>

<br>
<table>
	<tr>
		<td>
			<h5>
				Purchase Informations&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			</h5>
		</td>
		<td align="right">
			<form name="myForm" action="<%=aspDir%>core/ApprovePurchase.jsp" 
				method="post" onsubmit="return validateApproved()">
				<input type="hidden" name="purchaseId" value="<%=purchase.getId()%>" />
				<input name="submit" type="submit" value="Approved" style="color: red"/>
			</form>
			
		</td>
		<td align="right">
			<form name="myForm" action="<%=aspDir%>core/DeletePurchase.jsp"
				 method="post" onsubmit="return validateDelete()">
				<input type="hidden" name="purchaseId" value="<%=purchase.getId()%>" />
				<input name="submit" type="submit" value="Delete" style="color: red"/>
			</form>
			
		</td>
	</tr>
</table>

<center>
	<form name="myForm" action="<%=mainservletUrl%>?cmd=modify-purchase"
		method="post" onsubmit="return validateUpdatePurchaseing()">
		<table width="80%" cellspacing=0 cellpadding=4 border=1>
			<input type="hidden" name="purchaseId" value="<%=purchase.getId()%>" />
			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchaseperson" />
				</th>
				<td>
					<input type="text" name="purchasePerson" size="25"
						value="<%=FormatterFeeder
							.validateNull(purchase.getPurchasePerson())%>" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasestype" />
				</th>
				<td>
					<%
						int flag=0;
						for (int i = 0; i < accountItems.length; i++) {
							if (purchase.getItemId() == accountItems[i].getId()) {
					%>
					<%=accountItems[i].getName()%>
					<%
					flag++;
						}
						}
						if(flag == 0){
					%>
					No Item
					<%	} %>
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesproject" />
				</th>
				<td>
					<%
						for (int i = 0; i < projects.length; i++) {
							if (purchase.getProjectId() == projects[i].getId()) {
					%>
					<%=projects[i].getName()%>
					<%
						}
						}
					%>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesdepartment" />
				</th>
				<td>
					<%
						for (int i = 0; i < departments.length; i++) {
							if (purchase.getDempartmentId() == departments[i].getId()) {
					%>
					<%=departments[i].getType()%>
					<%
						}
						}
					%>
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasename" />
				</th>
				<td>
					<input type="text" name="purchaseName" size="25"
						value="<%=FormatterFeeder.validateNull(purchase.getPurchaseName())%>" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesource" />
				</th>
				<td>
					<input type="text" name="purchaseSource" size="25"
						value="<%=FormatterFeeder
							.validateNull(purchase.getPurchaseSource())%>" />
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasenumber" />
				</th>
				<td>
					<input type="text" name="purchaseNumber" size="25"
						value="<%=FormatterFeeder
							.validateNull(purchase.getPurchaseNumber())%>" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.totleprice" />
				</th>
				<td>
					<input type="text" name="totleprice" size="25"
						value="<%=FormatterFeeder.validateNull(purchase.getTotleprice())%>" />
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="addbankaccount.accountname" />
				</th>
				<td>
					<select name="bankId">
						<option value="0" selected>
							--Select--
						</option>
						<%
							for (int i = 0; i < bank.length; i++) {
								if (purchase.getBankId() == bank[i].getId()) {
						%>
						<option value="<%=bank[i].getId()%>" selected><%=bank[i].getAccountName()%></option>
						<%
							} else {
						%>
						<option value="<%=bank[i].getId()%>"><%=bank[i].getAccountName()%></option>
						<%
							}
						%>
						<%
							}
						%>
					</select>
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.isapproved" />
				</th>
				<td>
					<%
						if (purchase.getIsApproved() == 1) {
					%>
					<span style="color: red">YES</span>
					<input type="hidden" name="isApproved" value="1"></input>
					<%
						} else {
					%>
					<span style="color: red">NO</span>
					<input type="hidden" name="isApproved" value="0"></input>
					<%
						}
					%>
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.purchasesdate" />
					<a href="javascript:show_calendar('myForm.purchaseDate');"
						onmouseover="window.status='Date Picker';return true"
						onmouseout="window.status='';return true;"><img border="none"
							src="<%=aspDir%>include/images/show-calendar.gif"> </a>
				</th>
				<td>
					<input type="text" name="purchaseDate" size="20"
						readonly="readonly"
						value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(
							purchase.getPurchaseDate(), "MM/dd/yyyy"))%>" />
				</td>
				<th align="left" bgcolor="lightgrey">
					IsPayed
				</th>
				<td>
					<select name="isPayed">
						<%
							if (purchase.getIsPayed() == 1) {
						%>
						<option value="1" selected>
							Yes
						</option>
						<%
							} else {
						%>
						<option value="1">
							Yes
						</option>
						<%
							}
						%>
						<%
							if (purchase.getIsPayed() == 0) {
						%>
						<option value="0" selected>
							No
						</option>
						<%
							} else {
						%>
						<option value="0">
							No
						</option>
						<%
							}
						%>
					</select>
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.invoicenumber" />
				</th>
				<td>
					<input type="text" name="invoiceNumber" size="40"
						value="<%=FormatterFeeder.validateNull(purchase
									.getInvoiceNumber())%>" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.isarrive" />
				</th>
				<td>
					<select name="isArrive">
						<%
							if (purchase.getIsArrive() == 1) {
						%>
						<option value="1" selected>
							Yes
						</option>
						<%
							} else {
						%>
						<option value="1">
							Yes
						</option>
						<%
							}
						%>
						<%
							if (purchase.getIsArrive() == 0) {
						%>
						<option value="0" selected>
							No
						</option>
						<%
							} else {
						%>
						<option value="0">
							No
						</option>
						<%
							}
						%>
					</select>
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.invoicearrivetime" />
					<a href="javascript:show_calendar('myForm.invoiceArriveTime');"
						onmouseover="window.status='Date Picker';return true"
						onmouseout="window.status='';return true;"><img border="none"
							src="<%=aspDir%>include/images/show-calendar.gif"> </a>
				</th>
				<td>
					<input type="text" name="invoiceArriveTime" size="20"
						readonly="readonly"
						value="<%=FormatterFeeder.validateNull(DateFeeder.parseDate(
							purchase.getInvoiceArriveTime(), "MM/dd/yyyy"))%>" />
				</td>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.paymentway" />
				</th>
				<td>
					<input type="text" name="paymentway" size="25"
						value="<%=FormatterFeeder.validateNull(purchase.getPaymentway())%>" />
				</td>
			</tr>

			<tr>
				<th align="left" bgcolor="lightgrey">
					<fmt:message key="purchaserequest.commentline" />
				</th>
				<td colspan="3">
					<input type="text" name="commentLine" size="60"
						value="<%=FormatterFeeder.validateNull(purchase.getCommentLine())%>" />
				</td>
			</tr>
		</table>
		<br />
		<input name="submit" type="submit"
			value="<fmt:message key="purchaserequest.submit" />" />
	</form>