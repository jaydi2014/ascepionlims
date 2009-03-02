<!-- 采购列表 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ page import="com.ascepionpharm.lims.entity.core.*"%>
<%@ page import="com.ascepionpharm.lims.universal.*"%>
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>

<%
	PurchasingRepository purchaseRepos = new PurchasingRepository();
	AccountingItemRepository accountingItemRepos = new AccountingItemRepository();
	ProjectRepository projectRepos = new ProjectRepository();
	DepartmentRepository departmentRepos = new DepartmentRepository();

	PurchasingBean[] purchase = (PurchasingBean[]) purchaseRepos
			.getAll();
	AccountingItemBean[] accountItems = (AccountingItemBean[]) accountingItemRepos
			.getAll();
	ProjectBean[] projects = (ProjectBean[]) projectRepos.getAllN();
	Item[] departments = (Item[]) departmentRepos.getAll();

	purchaseRepos.destroy();
	accountingItemRepos.destroy();
	projectRepos.destroy();
	departmentRepos.destroy();
%>
<br />
<table>
	<tr>
		<td>
			<h5>
				Purchase
				List:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			</h5>
		</td>
		<td>
			<select name="Type" onchange="displayRow('purchase',this)">
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
		</td>
		<td>
			<select name="Project" onchange="displayRow('purchase',this)">
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
		</td>
		<td>
			<select name="Department" onchange="displayRow('purchase',this)">
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
		</td>
		<td>
			<select name="IsApproved" onchange="displayRow('purchase',this)">
				<option value="" selected>
					--IsApproved--
				</option>
				<option value="Yes">
					Yes
				</option>
				<option value="No">
					No
				</option>
			</select>
		</td>
		<td>
			<button onclick="showAllRow('purchase')">
				ShowAll
			</button>
		</td>
	</tr>
</table>
<hr>

<center>
	<table id="purchase" width="95%" cellspacing=0 cellpadding=4 border=1>
		<tr bgcolor="lightgrey">
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
			<th>
				IsPayed
			</th>
			<th>
				<fmt:message key="purchaserequest.isarrive" />
			</th>
		</tr>
		<%
			for (int i = 0; i < purchase.length; i++) {
				int id = purchase[i].getId();
				String string = "core/PurchaseInfo.jsp?purchaseid=" + id;
		%>
		<tr>
			<td align="center">
				<a href="<%=aspDir%><%=string%>"><%=purchase[i].getPurchaseName()%>
			</td>
			<td><%=purchase[i].getPurchasePerson()%></td>
			<%
				int flag = 0;
					for (int j = 0; j < accountItems.length; j++) {
						if (purchase[i].getItemId() == accountItems[j].getId()) {
			%>
			<td><%=accountItems[j].getName()%></td>

			<%
				flag++;
							break;
						}
			%>
			<%
				}
					if (flag == 0) {
			%>
			<td>
				No Item
			</td>
			<%
				}
			%>
			<%
				for (int j = 0; j < projects.length; j++) {
						if (purchase[i].getProjectId() == projects[j].getId()) {
			%>
			<td><%=projects[j].getName()%></td>
			<%
				break;
						}
			%>
			<%
				}
			%>
			<%
				for (int j = 0; j < departments.length; j++) {
						if (purchase[i].getDempartmentId() == departments[j]
								.getId()) {
			%>
			<td><%=departments[j].getType()%></td>
			<%
				break;
						}
			%>
			<%
				}
			%>

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
			<%
				if (purchase[i].getIsPayed() == 1) {
			%>
			<td align="center">
				Paid
			</td>
			<%
				} else {
			%>
			<td align="center">
				Not Paid
			</td>
			<%
				}
			%>
			<%
				if (purchase[i].getIsArrive() == 1) {
			%>
			<td align="center">
				Arrived
			</td>
			<%
				} else {
			%>
			<td align="center">
				Not Arrived
			</td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>