package com.cjc.bms.model;

public class Account 
{
	private int accNo;
	//private String bankname;
	
	private String name;
	private long mobNo;
	private String adharNo;
	private String gender;
	private byte age;
	private double balance;
	//private int pin;
	
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobNo() {
		return mobNo;
	}
	public void setMobNo(long mobNo) {
		this.mobNo = mobNo;
	}
	public String getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public byte getAge() {
		return age;
	}
	public void setAge(byte age) {
		this.age = age;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
//	public int getPin() {
//		return pin;
//	}
//	public void setPin(int pin) {
//		this.pin = pin;
//	}
	
	

}
