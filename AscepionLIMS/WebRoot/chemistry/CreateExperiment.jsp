<!-- 创建实验   -->
<%@include file="/common/Menu.jsp"%>
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

function AddUploadFile()
  {

		var myTable=document.getElementById("myTable");
		var initRows = myTable.rows;	 
		var toRow = myTable.insertRow(myTable.rows.length);	
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span><input type="file" name="uploadFiles"  size=30 onchange="prompt(this);"/> ';
  }
  
 
  function DeleteUploadFile()
  {
    	var myTable=document.getElementById("myTable");
	    var rows = myTable.rows;
	
	    if(rows.length < 2) {
	        alert("^_^");
		    return;
	    }
	
	    var rowCount = rows.length;
	    myTable.deleteRow(rowCount-1);
  }
  
  function AddRawRatio()
  {
		var myTable=document.getElementById("rawRatio");
		var toRow = myTable.insertRow(myTable.rows.length);	
		var length = myTable.rows.length-1
		toRow.align = "center";
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<td><input type="text" name="'+ length +'1" size="20" value="" /></td >';
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<td><input type="text" name="'+ length +'2" size="20" value="" /></td>';
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<td><input type="text" name="'+ length +'3" size="20" value="" /></td>';
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<td><input type="text" name="'+ length +'4" size="20" value="" /></td>';
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<td><input type="text" name="'+ length +'5" size="20" value="" /></td>';
		newCell = document.createElement("td"); 
		toRow.appendChild(newCell); 
		newCell.innerHTML='<td><input type="text" name="'+ length +'6" size="20" value="" /></td>';
  		document.getElementById("rawLength").value = myTable.rows.length-1;
  }
  
    function DeleteRawRatio()
  {
    	var myTable=document.getElementById("rawRatio");
	    var rows = myTable.rows;
	
	    if(rows.length < 6) {
	        alert("^_^");
		    return;
	    }
	
	    var rowCount = rows.length;
	    myTable.deleteRow(rowCount-1);
	    document.getElementById("rawLength").value = myTable.rows.length-1;
  }
  
  	function validateData(){
		var experimentname = document.myForm.experimentName.value;	
	    if(experimentname=="") 
	    {
	        alert("experimentname can not be null");  
	        return false;
	    }
		var experimentdate = document.myForm.experimentDate.value;	
		if(experimentdate=="") 
		{
		    alert("experimentdate can not be null"); 
		    return false;
		}
		var temperature = document.myForm.temperature.value;	
		if(temperature=="") 
		{
		    alert("temperature can not be null"); 
		    return false;
		}
		var humidity = document.myForm.humidity.value;
		if(humidity=="") 
		{
		    alert("humidity can not be null"); 
		    return false;
		}
		
		//document.myForm.action = "../chemistry/ProcessSmilesInput.jsp?experimentName=" + experimentname +"&experimentDate=" + experimentdate + "&temperature=" + temperature+ "&humidity=" + humidity ;
	    //document.myForm.submit();
	    document.myForm.submitButton.disabled="disabled";
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

<br>
<h5>
	Create Experiment:
</h5>
<hr>
<br />
<form name="myForm" action="<%=aspDir%>chemistry/ProcessExperimentInput.jsp"
	method="post" onsubmit="return validateData()">
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
				<input type="text" name="experimentName" size="20" value="" />
			</th>
			<th align="right">
				Date:
				<a href="javascript:show_calendar('myForm.experimentDate');"
					onmouseover="window.status='Date Picker';return true"
					onmouseout="window.status='';return true;"><img border="none"
						src="<%=aspDir%>include/images/show-calendar.gif"> </a>
				<input type="text" name="experimentDate" size="20"
					readonly="readonly" value="" />
			</th>
		</tr>
		<tr>
			<th align="left">
			</th>
			<th align="right">
				TEMPERATURE/HUMIDITY:
				<input type="text" name="temperature" size="2" value="" />
				<sup>
					o
				</sup>
				C / RH
				<input type="text" name="humidity" size="2" value="" />
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
	</center>
	<br>
	<br>
	<strong style="color: blue">2.Raw Ratio Table</strong>
	<br>
	<br>
	<center>
		<input type="hidden" name="rawLength" value="4" />
		<table width="100%" id="rawRatio" cellpadding="0" cellspacing="0">
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
			<tr align="center">
				<td>
					<input type="text" name="11" size="20" value="" />
				</td>
				<td>
					<input type="text" name="12" size="20" value="" />
				</td>
				<td>
					<input type="text" name="13" size="20" value="" />
				</td>
				<td>
					<input type="text" name="14" size="20" value="" />
				</td>
				<td>
					<input type="text" name="15" size="20" value="" />
				</td>
				<td>
					<input type="text" name="16" size="20" value="" />
				</td>
			</tr>
			<tr align="center">
				<td>
					<input type="text" name="21" size="20" value="" />
				</td>
				<td>
					<input type="text" name="22" size="20" value="" />
				</td>
				<td>
					<input type="text" name="23" size="20" value="" />
				</td>
				<td>
					<input type="text" name="24" size="20" value="" />
				</td>
				<td>
					<input type="text" name="25" size="20" value="" />
				</td>
				<td>
					<input type="text" name="26" size="20" value="" />
				</td>
			</tr>
			<tr align="center">
				<td>
					<input type="text" name="31" size="20" value="" />
				</td>
				<td>
					<input type="text" name="32" size="20" value="" />
				</td>
				<td>
					<input type="text" name="33" size="20" value="" />
				</td>
				<td>
					<input type="text" name="34" size="20" value="" />
				</td>
				<td>
					<input type="text" name="35" size="20" value="" />
				</td>
				<td>
					<input type="text" name="36" size="20" value="" />
				</td>
			</tr>
			<tr align="center">
				<td>
					<input type="text" name="41" size="20" value="" />
				</td>
				<td>
					<input type="text" name="42" size="20" value="" />
				</td>
				<td>
					<input type="text" name="43" size="20" value="" />
				</td>
				<td>
					<input type="text" name="44" size="20" value="" />
				</td>
				<td>
					<input type="text" name="45" size="20" value="" />
				</td>
				<td>
					<input type="text" name="46" size="20" value="" />
				</td>
			</tr>
		</table>
	</center>
	<input type="button" name="Add" value="Add" style="cursor: hand"
		onclick="AddRawRatio();" />
	<input type="button" name="Delete" value="Delete" style="cursor: hand"
		onclick="DeleteRawRatio();" />
	<br>
	<br>
	<strong style="color: blue">3.Experiment Process</strong> (less 4000)
	<br>
	<br>
	<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
	<textarea name="process" rows="20" cols="100%" ></textarea>
	<br>
	<br>
	<strong style="color: blue">4.Notes And Comments</strong> (less 4000)
	<br>
	<br>
	<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
	<textarea name="comment" rows="10" cols="100%" ></textarea>
	<br>
	<br>
	<strong style="color: blue">5.Result</strong> (less 4000)
	<br>
	<br>
	<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
	<textarea name="result" rows="10" cols="100%" ></textarea>
	<br>
	<br>
	<center>
	<input name="submitButton" type="submit" value="Store This Experiment"
		style="color: red; cursor: hand" />
</form>