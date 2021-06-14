package com.miguel.models;

public class MixUserAccount {

	private int accountNumber;
	private String first;
	private String last;
	private String accountstatus;
	public MixUserAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MixUserAccount(int accountNumber, String first, String last, String accountstatus) {
		super();
		this.accountNumber = accountNumber;
		this.first = first;
		this.last = last;
		this.accountstatus = accountstatus;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getAccountstatus() {
		return accountstatus;
	}
	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((accountstatus == null) ? 0 : accountstatus.hashCode());
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((last == null) ? 0 : last.hashCode());
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
		MixUserAccount other = (MixUserAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountstatus == null) {
			if (other.accountstatus != null)
				return false;
		} else if (!accountstatus.equals(other.accountstatus))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MixUserAccount [accountNumber=" + accountNumber + ", first=" + first + ", last=" + last
				+ ", accountstatus=" + accountstatus + "]";
	}
	
	

}
