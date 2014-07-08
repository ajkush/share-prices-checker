package com.google.share.service;

import com.google.share.pojo.Company;
/**
 * Interface for Company service
 * @author Ajay Sharma
 *
 */
public interface ICompanyService {
	/**
	 * Method to accept file name and it will return array of each Company year and month in which the share price was highest.
	 * @param filePath
	 * @return array of each Company year and month in which the share price was highest.
	 */
	public Company[] findCompanyMaxShare(String filePath);
	
	/**
	 * Method to iterate and display all companies
	 * @param array of Company object to display
	 */
	public void displayMaxShareCompany(Company... companies);
}
