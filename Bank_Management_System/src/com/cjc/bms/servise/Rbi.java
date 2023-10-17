package com.cjc.bms.servise;

public interface Rbi 
 {
    void createaccount() throws Exception;
    void displayAllDetails() throws Exception;
	void depositeMoney() throws Exception;
	void withdrawal() throws Exception;
	void balanceCheck() throws Exception;	
}
