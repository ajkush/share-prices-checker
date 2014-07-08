package com.google.share.entrypoint;

import java.util.Scanner;

import com.google.share.pojo.Company;
import com.google.share.service.ICompanyService;
import com.google.share.serviceimpl.CompanyService;

/**
 * 
 * Class with main method to start application. It will accept a parameter (file name) to csv read file.
 * @author  Ajay Sharma
 * 
 */
public class StartCheckingPrice {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("Enter full path to read file (eg. C:\\Users\\Ajay\\Desktop\\New\\share-prices-checker\\src\\test\\resources\\test_shares_data.csv) ");
	    String s = in.nextLine();
	    System.out.println("You entered string "+s);
	    in.close();
	    
		ICompanyService companyService = new CompanyService();
		Company maxSharePriceCompany[] = companyService
				.findCompanyMaxShare(s);
		companyService.displayMaxShareCompany(maxSharePriceCompany);
		
	}
}
