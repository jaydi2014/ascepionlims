<!-- menu tree -->
<ul id="udm" class="udm">
	<li><a class="nohref">Molecular Repository   |   </a>
		<ul>
			<li><A HREF="<%= aspDir %>chemistry/ChemicalSampleList.jsp">Sample List</A></li>
	  		<li><A HREF="<%= aspDir %>chemistry/ChemicalSampleInput.jsp">Create Molecular</A></li>
	  		<li><A HREF="<%= aspDir %>chemistry/QueryChemicalMolecular.jsp">Query Chemical Molecular</A></li>
		</ul>
	</li>
	
	<li><a class="nohref">Reaction Repository   |   </a>
		<ul>
			<li><A HREF="<%= aspDir %>chemistry/AddMolToProject.jsp">Add Molecular To Project</A></li>
	  		<li><A HREF="<%= aspDir %>chemistry/SelectProjectForReaction.jsp">Create Reaction</A></li>
	  		<li><A HREF="<%= aspDir %>chemistry/QueryReaction.jsp">Query Reaction</A></li>
		</ul>
	</li>
	
	<li><a class="nohref">Experiment   |   </a>
		<ul>
			<li><A HREF="<%= aspDir %>chemistry/SelectProjectForExperiment.jsp">Synthesis Route</A></li>
	  		<li><A HREF="<%= aspDir %>chemistry/SelectProjectForLot.jsp">Experiment Lots</A></li>
		</ul>
	</li>
	
	<li><a class="nohref"><fmt:message key="module.choosesystem"/></a>
		<ul>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=admin"><fmt:message key="module.choosesystem.administration"/></A></li>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=purchase"><fmt:message key="module.choosesystem.purchasing"/></A></li>
	  		<li><A HREF="<%= mainservletUrl %>?cmd=choose-system&system=chemistry">Chemistry</A></li>
		</ul>
	</li>
</ul>
