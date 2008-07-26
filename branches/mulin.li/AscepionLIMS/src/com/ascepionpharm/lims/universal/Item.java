package com.ascepionpharm.lims.universal;

/**
 * Abstract class that all entities (beans) extends.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */
public class Item {
	protected int    id;
	protected int    typeId;
	protected String type;
	protected String label;
	protected String typeDesc;
	
	public Item (int id) {
		this.id = id;
		this.type = "";
		this.label = "";
	}
	public Item () {
		this.id = 0;
		this.type = "";
		this.label = "";
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

    public void setTypeId(int id) {
      this.typeId = id;
    }

    public int getTypeId() {
      return this.typeId;
    }

    public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
	public String getTypeDesc() {
		return this.typeDesc;
	}
}