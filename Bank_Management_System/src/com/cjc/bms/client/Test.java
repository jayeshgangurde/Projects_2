package com.cjc.bms.client;

import com.cjc.bms.servise.*;
import com.cjc.bms.serviseImpl.*;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		Rbi w = new SBI();
		SBI s = new SBI();

		System.out.println("select Your Option :\n" 
		        + "\n" 
		        + "1-Create Account\n" 
				+ "2-Account Holder Details\n"
				+ "3-Deposit Ammount\n"
				+ "4-Withdrawl Ammount\n" 
				+ "5-Check Account Balance\n");

		while (true) {
			System.out.println();
			System.out.println("Enter your choice:");
			int i = sc.nextInt();

			if (i == 1) {
				s.createaccount();
			} else if (i == 2) {
				w.displayAllDetails();
			} else if (i == 3) {
				w.depositeMoney();
			} else if (i == 4) {
				w.withdrawal();
			} else if (i == 5) {
				w.balanceCheck();
			} else {
				System.out.println(" <---- Invalid Input ----> ");
				break;
			}

			
		}
	}
}
