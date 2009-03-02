<!-- 反应列表 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.ReactionBean"%>
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@ page import="java.util.*"%>

<%
	ReactionBean[] reactions = (ReactionBean[])request.getAttribute("reactionQuery");
	String innerName = (String)request.getAttribute("innerName");
%>
<h5>
	Sample List:
</h5>
<hr>
<center>
	<table id="purchase" width="40%" cellspacing=0 cellpadding=4 border=1 >
		<tr bgcolor="lightgrey">
			<th>
				Reaction
			</th>
		</tr>
		<%
			for (int i = 0; i < reactions.length; i++) {
				String reaction = (reactions[i].getName()).replaceAll(">","\\+");
				String[] rs = reaction.split("\\+");
				int id = reactions[i].getReaction_id();
				String string = "chemistry/ReactionInfo.jsp?reactionid="+id;
		%>
		<tr>
			<td align="center">
				<a href="<%=aspDir %><%=string %>">
				<%if(rs.length == 3) {%>
					<%if(rs[0].trim().equals(innerName)) {%>
						<strong><font color="red"><%=rs[0] %></font></strong> +
					<%} else {%>
						<%=rs[0]%> +
					<%} %>
					<%if(rs[1].trim().equals(innerName)) {%>
						<strong><font color="red"><%=rs[1] %></font></strong> >
					<%} else {%>
						<%=rs[1]%> >
					<%} %>
					<%if(rs[2].trim().equals(innerName)) {%>
						<strong><font color="red"><%=rs[2] %></font></strong>
					<%} else {%>
						<%=rs[2]%>
				<%} }%>
				<%if(rs.length == 2) {%>
					<%if(rs[0].trim().equals(innerName)) {%>
						<strong><font color="red"><%=rs[0] %></font></strong> >
					<%} else {%>
						<%=rs[0]%> >
					<%} %>
					<%if(rs[1].trim().equals(innerName)) {%>
						<strong><font color="red"><%=rs[1] %></font></strong>
					<%} else {%>
						<%=rs[1]%>
				<%} }%>
				</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>