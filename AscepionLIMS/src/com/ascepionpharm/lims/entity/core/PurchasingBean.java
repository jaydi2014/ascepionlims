package com.ascepionpharm.lims.entity.core;

import java.io.Serializable;
import java.sql.Date;

import com.ascepionpharm.lims.universal.*;

/**
 * PurchasingBean: stores records from PURCHASING table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */


public class PurchasingBean extends Item implements Serializable{
	private Date purchaseDate;
	private String purchaseSource;
	private String purchasePerson;
	private String purchaseName;
	private int purchaseNumber;
	private float totleprice;
	private Date invoiceArriveTime;
	private String invoiceNumber;
	private String paymentway;
	private int isArrive;
	private String commentLine;
	private int isApproved;
	private int dempartmentId;
	private int projectId;
	private int itemId;
	private int bankId;
	private int isPayed;
	
	public int getDempartmentId() {
		return dempartmentId;
	}
	public void setDempartmentId(int dempartmentId) {
		this.dempartmentId = dempartmentId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchaseSource() {
		return purchaseSource;
	}
	public void setPurchaseSource(String purchaseSource) {
		this.purchaseSource = purchaseSource;
	}
	public String getPurchasePerson() {
		return purchasePerson;
	}
	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}
	public String getPurchaseName() {
		return purchaseName;
	}
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public Date getInvoiceArriveTime() {
		return invoiceArriveTime;
	}
	public void setInvoiceArriveTime(Date invoiceArriveTime) {
		this.invoiceArriveTime = invoiceArriveTime;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getPaymentway() {
		return paymentway;
	}
	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}
	public int getIsArrive() {
		return isArrive;
	}
	public void setIsArrive(int isArrive) {
		this.isArrive = isArrive;
	}
	public String getCommentLine() {
		return commentLine;
	}
	public void setCommentLine(String commentLine) {
		this.commentLine = commentLine;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	public int getPurchaseNumber() {
		return purchaseNumber;
	}
	public void setPurchaseNumber(int purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	public float getTotleprice() {
		return totleprice;
	}
	public void setTotleprice(float totleprice) {
		this.totleprice = totleprice;
	}
	public int getIsPayed() {
		return isPayed;
	}
	public void setIsPayed(int isPayed) {
		this.isPayed = isPayed;
	}

}