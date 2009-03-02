<!-- 删除采购类型 -->
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ include file="/core/Permission.jsp"%>

<%
	int id = Integer.parseInt(request.getParameter("itemid"));
	AccountingItemRepository itemRepos = new AccountingItemRepository();
	
	if(id>0){
		itemRepos.deleteById(id);
	}
	
	itemRepos.destroy();
%>
<jsp:forward page="PurchaseItemList.jsp"></jsp:forward>