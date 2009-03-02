<!-- 实验批次列表 -->
<%@include file="/core/Permission.jsp"%>
<%@include file="/common/Menu.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.universal.*"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script LANGUAGE="JavaScript1.1"
	src="<%=aspDir%>chemistry/marvin/marvin.js"></script>

<script LANGUAGE="JavaScript1.1">
function viewSmiles(smiles){
var sm = smiles;
mview_begin("<%=aspDir%>chemistry/marvin", 150, 150);
mview_param("mol", sm );
mview_end();
}

function viewSmilesCatalyst(smiles){
var sm = smiles;
mview_begin("<%=aspDir%>chemistry/marvin", 75, 75);
mview_param("mol", sm );
mview_end();
}

function validateDeleteReaction(){
	if (!confirm("Are your sure to submit?")) {
		return false;
	}
	return true;
}
</script>

<%
	int reactionId = Integer.parseInt(request
			.getParameter("reactionid"));
	ReactionRepository reactionRepos = new ReactionRepository();
	ChemicalMolecularRepository chemistryRepos = new ChemicalMolecularRepository();
	ProjectReactionRepository projectReactionRepos = new ProjectReactionRepository();
	ReactionBean reaction = (ReactionBean) reactionRepos
			.get(reactionId);
	String ProjectName = projectReactionRepos
			.getProjectName(reactionId);
	ChemicalMolecularBean ractiona = (ChemicalMolecularBean) chemistryRepos
			.get(reaction.getReaction_a());
	ChemicalMolecularBean ractionb;
	ChemicalMolecularBean catalysta;
	String ractionbSmile = null;
	String catalystaSmile = null;
	if (reaction.getReaction_b() == -1) {
		ractionb = null;
	} else {
		ractionb = (ChemicalMolecularBean) chemistryRepos.get(reaction
				.getReaction_b());
	}
	if (reaction.getCatalyst_a() == -1) {
		catalysta = null;
	} else {
		catalysta = (ChemicalMolecularBean) chemistryRepos.get(reaction
				.getCatalyst_a());
	}
	ChemicalMolecularBean product = (ChemicalMolecularBean) chemistryRepos
			.get(reaction.getProduct());
	String ractionaSmile = ractiona.getSmiles();
	String productSmile = product.getSmiles();
	if (ractionb != null) {
		ractionbSmile = ractionb.getSmiles();
	}
	if (catalysta != null) {
		catalystaSmile = catalysta.getSmiles();
	}
	reactionRepos.destroy();
	chemistryRepos.destroy();
	projectReactionRepos.destroy();
%>

<%
	ExperimentRepository experimentRepos = new ExperimentRepository();

	ExperimentBean[] experiments = experimentRepos
			.getByReaction(reactionId);

	experimentRepos.destroy();
%>

<br>
<table width="100%">
	<tr>
		<td align="left">
			<h5>
				Experiment Lots:
			</h5>
		</td>
		<td align="right" valign="bottom">
			<form name="createLot"
				action="<%=aspDir%>chemistry/CreateExperiment.jsp" method="post">
				<input type="hidden" name="reactionid" value="<%= reactionId%>">
				<input type="submit" name="create" value="Create">
			</form>
		</td>
	</tr>
</table>
<hr>
<br />
<table width="100%">
	<tr>
		<th align="left">
			Reaction Name:
			<strong><%=reaction.getName()%></strong>
		</th>
		<th align="right">
			Project Name:
			<strong><%=ProjectName%></strong>
		</th>
	</tr>
</table>
<center>
	<input type="hidden" name="reactionId" value="<%=reactionId%>" />
	<table id="reaction" align="center">
		<tr>
			<td align="center">
				<%=ractiona.getInnerName()%>
			</td>
			<td align="center">
			</td>
			<td align="center">
				<%
					if (ractionb != null) {
				%>
				<%=ractionb.getInnerName()%>
				<%
					}
				%>
			</td>
			<td align="center">
				<%
					if (catalysta != null) {
				%>
				<%=catalysta.getInnerName()%>
				<%
					}
				%>
			</td>
			<td align="center">
				<%=product.getInnerName()%>
			</td>
		</tr>
		<tr>
			<td align="center">
				<script LANGUAGE="JavaScript1.1">
						viewSmiles('<%=ractionaSmile%>')
					</script>
			</td>
			<td align="center">
				<br />
				<%
					if (ractionb != null) {
				%>
				<font size="12">+</font>
				<%
					}
				%>
			</td>
			<td align="center">
				<%
					if (ractionb != null) {
				%>
				<script LANGUAGE="JavaScript1.1">
						viewSmiles('<%=ractionbSmile%>')
					</script>
				<%
					}
				%>
			</td>
			<td align="center">
				<table id="catalyst" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">
							<%
								if (catalystaSmile != null) {
							%>
							<script LANGUAGE="JavaScript1.1">
										viewSmilesCatalyst('<%=catalystaSmile%>')
									</script>
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<td align="center">
							<font size="12">------></font>
						</td>
					<tr>
						<td align="center">
							Catalyst:
							<input type="text" name="catalystb" readonly="readonly"
								value="<%=FormatterFeeder.validateNull(reaction.getCatalyst_b())%>" />
							<br />
							Condition:
							<input type="text" name="condition" readonly="readonly"
								value="<%=FormatterFeeder.validateNull(reaction.getConditions())%>" />
						</td>
					</tr>

				</table>
			</td>
			<td align="center">
				<script LANGUAGE="JavaScript1.1">
						viewSmiles('<%=productSmile%>')
					</script>
			</td>
		</tr>
	</table>
	<br />
	<hr>
	<br />
	<table width="100%">
		<tr>
			<th align="left">
				Lots:
			</th>
		</tr>
	</table>
	<table id="purchase" width="40%" cellspacing=0 cellpadding=4 border=1>
		<tr bgcolor="lightgrey">
			<th>
				Experiment Date
			</th>
			<th>
				Experiment Name
			</th>
		</tr>
		<%
			for (int i = 0; i < experiments.length; i++) {
		%>
		<tr>
			<td align="center">
				<%=experiments[i].getDate()%>
			</td>
			<td align="center">
				<a href="<%=aspDir%>chemistry/ExperimentDetails.jsp?reactionid=<%=reactionId %>&experimentid=<%=experiments[i].getExperiment_id()%>"><%=experiments[i].getName()%></a>
			</td>
		</tr>
		<%
			}
		%>
	</table>