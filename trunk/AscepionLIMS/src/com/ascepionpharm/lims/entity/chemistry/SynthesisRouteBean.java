package com.ascepionpharm.lims.entity.chemistry;

import com.ascepionpharm.lims.universal.*;

import java.io.*;

/**
 * SynthesisRouteBean: stores records from SynthesisRoute table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class SynthesisRouteBean extends Item implements Serializable {
	private int last_reaction;
	private int next_reaction;
	
	public int getLast_reaction() {
		return last_reaction;
	}
	public void setLast_reaction(int last_reaction) {
		this.last_reaction = last_reaction;
	}
	public int getNext_reaction() {
		return next_reaction;
	}
	public void setNext_reaction(int next_reaction) {
		this.next_reaction = next_reaction;
	}
}
