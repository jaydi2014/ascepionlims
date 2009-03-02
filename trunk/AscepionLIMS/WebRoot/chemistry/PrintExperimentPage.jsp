<!-- 实验详细信息   -->
<%@include file="/core/Permission.jsp"%>
<%@include file="/common/CommonLibs.jsp"%>
<%@page import="java.util.*"%>
<%@page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.repository.chemistry.*"%>
<%@page import="com.ascepionpharm.lims.universal.*"%>

<script language="JavaScript" src="<%=aspDir%>include/aspJavaScript.js"></script>
<script language="JavaScript" src="<%=aspDir%>include/date-picker.js"></script>
<script LANGUAGE="JavaScript1.1"
	src="<%=aspDir%>chemistry/marvin/marvin.js"></script>

<script LANGUAGE="JavaScript1.1">
function viewSmiles(smiles){
var sm = smiles;
mview_begin("<%=aspDir%>chemistry/marvin", 120, 120);
mview_param("mol", sm );
mview_end();
}

function viewSmilesCatalyst(smiles){
	var sm = smiles;
	mview_begin("<%=aspDir%>chemistry/marvin", 60, 60);
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

	int experimentId = Integer.parseInt(request
			.getParameter("experimentid"));
	ExperimentRepository experimentRepos = new ExperimentRepository();
	ExperimentBean experiment = (ExperimentBean) experimentRepos
			.get(experimentId);
	Map rawRatioMap = (HashMap) experiment.getRawRatio();
	experimentRepos.destroy();
%>
<strong><div style="font-size: large;font-style: oblique" align="center">Ascepion Chemistry Synthesis Experiment Report </div></strong>
<br>
<form name="myForm"
	action="<%=aspDir%>chemistry/ProcessExperimentInput.jsp" method="post"
	onsubmit="return validateData()">
	<input name="reactionId" type="hidden" value="<%=reactionId%>" />
	<table width="100%">
		<tr>
			<th align="left">
				Project Name:
				<strong style="font-style: italic"><%=ProjectName%></strong>
			</th>
		</tr>
		<tr>
			<th align="left">
				Experiment Name:
				<strong style="font-style: italic; color: red;font-size: small"><%=experiment.getName()%></strong>
			</th>
			<th align="right">
				Date:
				<strong><%=experiment.getDate()%></strong>
			</th>
		</tr>
		<tr>
			<th align="left">
			</th>
			<th align="right">
				TEMPERATURE/HUMIDITY:
				<strong style="font-style: italic"><%=experiment.getTemperature()%></strong>
				<sup>
					o
				</sup>
				C / RH
				<strong style="font-style: italic"><%=experiment.getHumidity()%></strong>
				%
			</th>
		</tr>
	</table>

	<strong style="color: blue">1.Reaction</strong>
	<center>
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
					<table id="catalyst" cellpadding="0" cellspacing="0" >
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
								<img name="findRoute" src="../include/images/reactionarrow.gif">
							</td>
						</tr>
						<tr>
							<td align="center" style="padding: 0,0,0,0;margin: 0,0,0,0">
							<% 
								if(reaction.getCatalyst_b() != null){
							%>
								<div style="font-size: x-small"><strong>Catalyst:</strong>    <%=FormatterFeeder.validateNull(reaction.getCatalyst_b())%></div>
							<%
								}if(reaction.getConditions() != null){
							%>
								<div style="font-size: x-small"><strong>Condition:</strong>   <%=FormatterFeeder.validateNull(reaction.getConditions())%></div>
							<%
								}
							%>
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
	</center>
	<br>
	<strong style="color: blue">2.Raw Ratio Table</strong>
	<br>
	<center>
		<input type="hidden" name="rawLength" value="4" />
		<table width="100%" id="rawRatio" cellpadding="2" cellspacing="2"
			style="background-color: #CCE8CF">
			<tr align="center">
				<th>
					Raw Name
				</th>
				<th>
					Molecular Weight
				</th>
				<th>
					Raw Weight
				</th>
				<th>
					mmol
				</th>
				<th>
					Mol Ratio
				</th>
				<th>
					Comment
				</th>
			</tr>
			<%
				for (int i = 1; i <= rawRatioMap.size(); i++) {
					List raw = (ArrayList) (rawRatioMap.get(i));
			%>
			<tr align="center">
				<td>
					<%=FormatterFeeder.validateNull(raw.get(0))%>
				</td>
				<td>
					<%=FormatterFeeder.validateNull(raw.get(1))%>
				</td>
				<td>
					<%=FormatterFeeder.validateNull(raw.get(2))%>
				</td>
				<td>
					<%=FormatterFeeder.validateNull(raw.get(3))%>
				</td>
				<td>
					<%=FormatterFeeder.validateNull(raw.get(4))%>
				</td>
				<td>
					<%=FormatterFeeder.validateNull(raw.get(5))%>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</center>
	<br>
	<strong style="color: blue">3.Experiment Process</strong>
	<br>
	<div style="width: 100%; background-color: #CCE8CF; padding: 5, 5, 5, 5">
	<%
		String[] pa = FormatterFeeder.validateNull(experiment.getProcess())
				.split("\n");
		for (int i = 0; i < pa.length; i++) {
	%>
	<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
	<%=FormatterFeeder.validateNull(pa[i])%>
	<br>
	<%
		}
	%>
	</div>
	<br>
	<strong style="color: blue">4.Notes And Comments</strong>
	<br>
	<div style="width: 100%; background-color: #CCE8CF; padding: 5, 5, 5, 5">
	<%
		String[] ca = FormatterFeeder.validateNull(experiment.getComment())
				.split("\n");
		for (int i = 0; i < ca.length; i++) {
	%>
	<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
	<%=FormatterFeeder.validateNull(ca[i])%>
	<br>
	<%
		}
	%>
	</div>
	<br>
	<strong style="color: blue">5.Result</strong>
	<br>
	<div style="width: 100%; background-color: #CCE8CF; padding: 5, 5, 5, 5">
	<%
		String[] ra = FormatterFeeder.validateNull(experiment.getResult())
				.split("\n");
		for (int i = 0; i < ra.length; i++) {
	%>
	<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
	<%=FormatterFeeder.validateNull(ra[i])%>
	<br>
	<%
		}
	%>
	</div>
	<br>
	<strong style="color: blue">6.RelatedFiles</strong>
	<br>
	<table>
		<%
			ExperimentFileRepository experimentFileRepos = new ExperimentFileRepository();
			ExperimentFileBean[] files = experimentFileRepos.getList(experimentId);
			experimentFileRepos.destroy();
			if(files != null){
				for(int i=0;i<files.length;i++) {
					String fileName =files[i].getFileName();
					int relatedFileID =files[i].getFile_id();
		%>
		<tr>
			<td>
				<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
				<a
					href='<%=request.getContextPath()%>/chemistry/ExperimentFileDownload.jsp?relatedFileID=<%=relatedFileID%>'><%=fileName%></a>
			</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td>
				No RelatedFiles
			</td>
		</tr>

		<%
			}
		%>
	</table>
</form>