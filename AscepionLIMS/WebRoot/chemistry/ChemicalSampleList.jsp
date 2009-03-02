<!-- 化学分子列表 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@ page import="java.util.*"%>

<%
	ChemicalMolecularRepository chemicalMolecularRepo = new ChemicalMolecularRepository();
	List allInnerName = chemicalMolecularRepo.getAllInnerName();
%>
<h5>
	Sample List:
</h5>
<hr>
<center>
	<table id="samples" width="40%" cellspacing=0 cellpadding=4 border=1 >
		<tr bgcolor="lightgrey">
			<th>
				All Chemical Sample
			</th>
		</tr>
		<%
			for (int i = 0; i < allInnerName.size(); i++) {
		%>
		<tr>
			<td align="center">
				<%=allInnerName.get(i)%>
			</td>
		</tr>
		<%
			}
		%>
	</table>