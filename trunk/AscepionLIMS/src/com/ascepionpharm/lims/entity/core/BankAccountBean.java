package com.ascepionpharm.lims.entity.core;

import com.ascepionpharm.lims.universal.*;

import java.io.*;
import java.sql.Date;


/**
 * EmployeeBean: stores records from BANKACCOUNT table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class BankAccountBean extends Item implements Serializable{
	private String bankName;
	private String accountName;
	private String accountNumber;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
		
}
