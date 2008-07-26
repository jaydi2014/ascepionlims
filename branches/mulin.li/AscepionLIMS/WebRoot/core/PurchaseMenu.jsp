<!-- menu tree -->
<ul id="udm" class="udm">
	<li><a class="nohref"><fmt:message key="module.purchasing.purchase"/>   |   </a>
		<ul>
	  		<li><A HREF="<%= aspDir %>core/PurchaseRequest.jsp"><fmt:message key="module.purchasing.purchaserequest"/></A></li>
	  		<li><A HREF="<%= aspDir %>core/GivePermission.jsp"><fmt:message key="module.purchasing.findpurchase"/></A></li>
			<li><A HREF="<%= aspDir %>core/PurchaseList.jsp"><fmt:message key="module.purchasing.purchasemanage"/></A></li>
		</ul>
	</li>

	<li><a class="nohref"><fmt:message key="module.choosesystem"/></a>
		<ul>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=admin"><fmt:message key="module.choosesystem.administration"/></A></li>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=purchase"><fmt:message key="module.choosesystem.purchasing"/></A></li>
		</ul>
	</li>
</ul>