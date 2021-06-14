package com.miguel.models;

import java.util.Date;

public class Transfer {
	
	// Instance Variables
	private int transferId;
	private Date datetime;
	private int accountNumber;
	private String transferType;
	private double transferAmount;
	private double accountBalance;
	private int userId;
	
	
	// Constructors
	public Transfer() {
		super();
	}

	public Transfer(int accountNumber, String transferType, double transferAmount,double accountBalance) {
		super();
		this.accountNumber = accountNumber;
		this.transferType = transferType;
		this.transferAmount = transferAmount;
		this.accountBalance = accountBalance;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Transfer [transferId=" + transferId + ", datetime=" + datetime + ", accountNumber=" + accountNumber
				+ ", transferType=" + transferType + ", transferAmount=" + transferAmount + ", accountBalance="
				+ accountBalance + ", userId=" + userId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + accountNumber;
		result = prime * result + ((datetime == null) ? 0 : datetime.hashCode());
		temp = Double.doubleToLongBits(transferAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + transferId;
		result = prime * result + ((transferType == null) ? 0 : transferType.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transfer other = (Transfer) obj;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (Double.doubleToLongBits(transferAmount) != Double.doubleToLongBits(other.transferAmount))
			return false;
		if (transferId != other.transferId)
			return false;
		if (transferType == null) {
			if (other.transferType != null)
				return false;
		} else if (!transferType.equals(other.transferType))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
