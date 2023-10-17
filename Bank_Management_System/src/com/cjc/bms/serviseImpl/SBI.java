package com.cjc.bms.serviseImpl;
//import Package from another class package
import com.cjc.bms.model.*;
import com.cjc.bms.servise.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import com.utility.JdbcHelper;
import java.sql.PreparedStatement;
import java.util.Scanner;

//Here I have take reference of interface(RBI) & create an object of their implemented class(SBI).
public class SBI implements Rbi {
	Scanner sc = new Scanner(System.in);

	Account a = new Account();// Global Object of Account Class

	// For Account Number with Validation
	public void accountNumber(Account a) {

		while (true) {
			System.out.println("Enter New Account Number");
			int AccNo = (sc.nextInt());
			if (Integer.toString(AccNo).length() == 8) {
				a.setAccNo(AccNo);
				break;
			} else {
				System.out.println("Invalid  Number");

			}
		}

	}
	// For Mobile Number with Validation
	public void mobileNumber(Account a) {

		System.out.println("Enter Account Holder Mobile :");
		long MobNo = (sc.nextLong());
		if (new Long(MobNo).toString().length() == 10) {
			a.setMobNo(MobNo);
		} else {
			System.out.println("Invalid  Number");
			mobileNumber(a);
		}

	}

	// For Name 
	public void name(Account a) {
		System.out.println("Enter Account Holder Name:");
		a.setName(sc.next() + sc.nextLine());
	}
	
    // For Age with Validation
	public void age(Account a) {
		System.out.println("Enter Age :");
		byte Age = (sc.nextByte());
		if (Age >= 18) {
			a.setAge(Age);
		} else {
			System.out.println("Invalid age");
			age(a);
		}
	}

	// For Gender with Validation
	public void gender(Account a) {

		System.out.println("Enter Gender :");
		String Gender = (sc.next());

		if (Gender.equalsIgnoreCase("m") || Gender.equalsIgnoreCase("male") || Gender.equalsIgnoreCase("f")
				|| Gender.equalsIgnoreCase("female")) {
			a.setGender(Gender);
		} else {
			System.out.println("Gender is Incorrect");
			gender(a);
		}

	}
	
	// For aadhar Number with Validation
	public void aadhar(Account a) {
		System.out.println("Enter Account Holder Aadhar Number:");
		String AdharNo = (sc.next());
		if (AdharNo.length() <= 12) {
			a.setAdharNo(AdharNo);
		} else {
			System.out.println("number is Incorrect");
			aadhar(a);

		}
	}
    
	// For Account Balance 
	public void AccountBalance(Account a) {
	
		System.out.println("Enter Account Balance :");
		a.setBalance(sc.nextDouble());

	}
//for create account and connect with Database (JDBC).
	public void createaccount() throws Exception {
		accountNumber(a);
		name(a);
		gender(a);
		aadhar(a);
		age(a);
		mobileNumber(a);
		AccountBalance(a);
    
		Connection con = JdbcHelper.getConnectionObject();
		String sql = "insert into account values(?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		try {
			ps.setInt(1, a.getAccNo());
			ps.setString(2, a.getName());
			ps.setLong(3, a.getMobNo());
			ps.setString(4, a.getAdharNo());
			ps.setString(5, a.getGender());
			ps.setByte(6, a.getAge());
			ps.setDouble(7, a.getBalance());
			ps.execute();

		} catch (Exception e1) {

			e1.printStackTrace();
		}
		
		System.out.println("Data Inserted Succesfully");

	}
	
//For View all details of Account Holder
	@Override
	public void displayAllDetails() throws Exception {
		System.out.println("*** Account Holder Details ***");
		Connection con = JdbcHelper.getConnectionObject();
		String sql = "select * from account";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			System.out.println("Holder Account Number  :" + rs.getInt(1));
			System.out.println("Holder Name            :"+rs.getString(2));
			System.out.println("Holder Mobile Number   :"+rs.getLong(3));
			System.out.println("Holder Aadhar Number   :"+rs.getString(4));
			System.out.println("Holder Gender          :"+rs.getString(5));
			System.out.println("Holder Age             :"+rs.getInt(6));
			System.out.println("Holder Account Balance :"+rs.getInt(7));
			System.out.println();

		}
		System.out.println("Data Fetched Successfully");
	}

	//For Deposit money in Account holder Account
	@Override
	public void depositeMoney() throws Exception {
		Connection con = JdbcHelper.getConnectionObject();
		System.out.println("*** Deposite Amount ***");
		System.out.println("");
		
		System.out.println("Enter Valid Account Number");
		int an = sc.nextInt();
		String s="select AccountBalance from account where accountNumber="+an+"";
		Statement ps = con.createStatement();
		ResultSet rs=ps.executeQuery(s);
		if(rs.next()) {
		System.out.println("Enter Deposit Amount");
		double amt = sc.nextDouble();
		double y = rs.getDouble(1)+ amt;
		a.setBalance(y);
		String sql = "Update account set AccountBalance=" + y + " where accountNumber=" + an + " ";
		PreparedStatement ps1 = con.prepareStatement(sql);
		ps1.execute();
		System.out.println();
		System.out.println("Money Deposit Successfully");

	}
	}
	//For withdrawal money from Account holder Account
	@Override
	public void withdrawal() throws Exception {
		Connection con = JdbcHelper.getConnectionObject();
		System.out.println("*** Withdrawal Ammount ***");
		System.out.println("");
		while (true) {

			System.out.println();
			System.out.println("Enter Valid Account Number");
			int wb = sc.nextInt();
			System.out.println("Enter Withdrawl Ammount ");
			double p = sc.nextDouble();

			if (a.getBalance() >= p) {
				double y = a.getBalance() - p;
				a.setBalance(y);

				String sql = "Update account set AccountBalance=" + y + " where accountNumber=" + wb + " ";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.execute();
				System.out.println();
				
				System.out.println("Money Withdrawal Successfully");
				break;
			} else {
				System.out.println("Insufficient-Balance\n"+"Check and then withdrwal money");
				withdrawal();
			}
		}

	}

	@Override
	public void balanceCheck() throws Exception {

		System.out.println("");
		System.out.println(" *** Account Balance ***");
		System.out.println("");
		System.out.println("Enter Valid Account Number");
		int ab = sc.nextInt();
		Connection con = JdbcHelper.getConnectionObject();
		String sql = "select * from account  where accountNumber=" + ab + "";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			System.out.println("Available Account Balance = " + rs.getInt(7));
		}
	}

}
