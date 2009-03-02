<!-- 化学实验反应路线 -->
<%@ include file="/core/Permission.jsp"%>
<%@ include file="/common/Menu.jsp"%>
<%@ page import="com.ascepionpharm.lims.repository.core.*"%>
<%@ page import="com.ascepionpharm.lims.entity.core.*"%>
<%@ page import="com.ascepionpharm.lims.universal.*"%>
<%@ page import="com.ascepionpharm.lims.entity.chemistry.*"%>
<%@ page import="com.ascepionpharm.lims.repository.chemistry.*"%>

<script type='text/javascript'
	src='/AscepionLIMS/dwr/interface/ChemicalMolecularRepository.js'></script>
<script type='text/javascript'
	src='/AscepionLIMS/dwr/interface/ReactionRepository.js'></script>
<script type='text/javascript' src='/AscepionLIMS/dwr/engine.js'></script>
<script type='text/javascript' src='/AscepionLIMS/dwr/util.js'></script>
<script type='text/javascript' src="<%=aspDir%>include/aspJavaScript.js"></script>


<script type="text/javascript">
	function displayInnerName(){
			dwr.util.removeAllRows('reaction'); 
			var smiles = document.getElementById('InnerName').value;
			var table = document.getElementById('check');
			table.rows[0].cells[1].innerHTML='<applet CODEBASE="<%=aspDir%>chemistry/marvin" ARCHIVE="jmarvin.jar" CODE="JMView" WIDTH=150 HEIGHT=150><param NAME="mol" VALUE=' + smiles + '></applet>'; 
	}
	
	function findReaction(){
		var smiles = document.getElementById('InnerName').value;
		var projectId = document.getElementById('projectId').value;
		ReactionRepository.getNameBySmilesAndProject(smiles,projectId,reactionList);
	}
	
	function reactionList(data){
	     var cellfuncs = [    
	         function(data){    
		         var name = data.name; 
		         return name;    
		     },
		      function(data){    
		         var idd = data.reaction_id;
		         var showButton = document.createElement("<INPUT TYPE='button' onclick='showReaction("+ idd +")'>");    
             	 showButton.setAttribute("name","Display");    
             	 showButton.setAttribute("value","Display");                
		         return showButton;    
		     }];    
	     dwr.util.removeAllRows('reaction'); 
	     dwr.util.addRows('reaction', data,cellfuncs,{    
	     rowCreator:function(options) {    
	         var row = document.createElement("tr");    
	         var index = options.rowIndex * 50;    
	         row.setAttribute("id",options.rowData.id);    
	         row.style.collapse = "separate";    
	         row.style.color = "rgb(" + index + ",0,0)";    
	         return row;    
	     },    
	     cellCreator:function(options) {    
	         var td = document.createElement("td");    
	         var index = 255 - (options.rowIndex * 50);    
	         td.style.backgroundColor = "rgb(" + index + ",200,200)";    
	         td.style.fontWeight = "bold";    
	         td.style.align = "center";    
	         return td;    
	     }           
	     });    
     }
     
     function showReaction(id){
     	location.href = "RouteReactionInfo.jsp?reactionid="+ id;
     
     }  

</script>

<%
	String projectId = request.getParameter("project").trim();
	ProjectChemicalMolecularRepository ProjectChemicalMolecularRepos = new ProjectChemicalMolecularRepository();
	ProjectRepository projectRepos = new ProjectRepository();

	ChemicalMolecularBean[] chemicalMoleculars = (ChemicalMolecularBean[]) ProjectChemicalMolecularRepos
			.getList(Integer.parseInt(projectId));
	String projectName = ((ProjectBean) projectRepos.get(Integer
			.parseInt(projectId))).getName();

	ProjectChemicalMolecularRepos.destroy();
	projectRepos.destroy();
%>

<br>
<center />
	<table width="100%">
		<tr>
			<th align="left">
				<h5>
					Synthesis Route:
				</h5>
			</th>
			<th align="right">
				Project Name:
				<strong><%=projectName%></strong>
			</th>
		</tr>
	</table>
	<hr />
	<br>
	<input type="hidden" name="projectId" value="<%= projectId%>">
	<table id="check" width="60%">
		<tr>
			<td align="left">
				<strong>Product Chemicalmolecular:</strong>
				<select name="InnerName" onchange="displayInnerName()">
					<option value="" selected="selected">
						--please select--
					</option>
					<%
						for (int i = 0; i < chemicalMoleculars.length; i++) {
					%>
					<option value="<%=chemicalMoleculars[i].getSmiles()%>"><%=chemicalMoleculars[i].getInnerName()%></option>
					<%
						}
					%>
				</select>
			</td>
			<td align="right">
			</td>
		</tr>
	</table>
	<br>
	<img name="findRoute" src="../include/images/arrows.gif" style="cursor: pointer" onclick="findReaction()">
	<br>
	<br>
	<table width="80%">
		<tbody id="reaction" align="center">
		</tbody>
	</table>