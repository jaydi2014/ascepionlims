package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * ReactionBean: stores records from REACTION table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ReactionBean extends Item implements Serializable{
	 private int reaction_id;
	 private String name;
	 private int product;
	 private int reaction_a;
	 private int reaction_b;
	 private int reaction_c;
	 private int catalyst_a;
	 private String catalyst_b;
	 private String conditions;
	 
	public int getReaction_id() {
		return reaction_id;
	}
	public void setReaction_id(int reaction_id) {
		this.reaction_id = reaction_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getReaction_a() {
		return reaction_a;
	}
	public void setReaction_a(int reaction_a) {
		this.reaction_a = reaction_a;
	}
	public int getReaction_b() {
		return reaction_b;
	}
	public void setReaction_b(int reaction_b) {
		this.reaction_b = reaction_b;
	}
	public int getReaction_c() {
		return reaction_c;
	}
	public void setReaction_c(int reaction_c) {
		this.reaction_c = reaction_c;
	}
	public int getCatalyst_a() {
		return catalyst_a;
	}
	public void setCatalyst_a(int catalyst_a) {
		this.catalyst_a = catalyst_a;
	}
	public String getCatalyst_b() {
		return catalyst_b;
	}
	public void setCatalyst_b(String catalyst_b) {
		this.catalyst_b = catalyst_b;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

}
