package com.google.share.serviceimpl;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.share.pojo.Company;
import com.google.share.service.ICompanyService;
import com.google.share.util.FileUtil;

/**
 * 
 * @author Ajay Sharma
 * Service class to process csv file and display companies with maximum share price, month and year.
 *
 */
public class CompanyService implements ICompanyService {

	private String companyNames[];
	private Company companyMaxPriceHolder[];
	private static final Logger log = Logger.getLogger(CompanyService.class);

	/*
	 * (non-Javadoc)
	 * @see com.google.share.service.ICompanyService#findCompanyMaxShare(java.lang.String)
	 */
	public Company[] findCompanyMaxShare(final String filePath){
		boolean isHeading=true;
		Scanner scanner=FileUtil.readFile(filePath);

		if(scanner == null){
			log.info("File not Found. Returning null");
			return null;
		}
		while (scanner.hasNextLine()) {
			if (isHeading){  
				String rowRecord[] = (scanner.nextLine()).split(",");
				companyNames=new String[rowRecord.length-2];
				for(int i=0;i<companyNames.length;i++){
					companyNames[i]=rowRecord[i+2];
				}
				if(scanner.hasNextLine()){
					try{
						rowRecord= (scanner.nextLine()).split(",");
						Company company=null;
						companyMaxPriceHolder=new Company[companyNames.length];
						for(int j=0;j<companyMaxPriceHolder.length;j++){
							company=new Company(companyNames[j],rowRecord[0],rowRecord[1],FileUtil.parseDoubleWithDefault(rowRecord[j+2]));
							companyMaxPriceHolder[j]=company;
						}
					}catch(ArrayIndexOutOfBoundsException e){
						log.error("Corrupted file : Some records are incomplete. " + e.toString());
					}
				}
				isHeading=false;
				continue;
			}
			String[] rowRecord = (scanner.nextLine()).split(",");
			try{
				for(int k=0;k<companyNames.length;k++){
					Company oldCompany = companyMaxPriceHolder[k];


					if (oldCompany != null && FileUtil.parseDoubleWithDefault(rowRecord[k+2]) > oldCompany.getPrice()){   
						oldCompany.setPrice(FileUtil.parseDoubleWithDefault(rowRecord[k+2]) );
						oldCompany.setMonth(rowRecord[1]);
						oldCompany.setYear(rowRecord[0]);
						companyMaxPriceHolder[k]= oldCompany;
					}
				}
			}catch(ArrayIndexOutOfBoundsException e){
				log.error("Corrupted file : Some records are incomplete. " + e.toString());
			}
		}
		return companyMaxPriceHolder;
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.google.share.service.ICompanyService#displayMaxShareCompany(com.google.share.pojo.Company[])
	 */
	public void displayMaxShareCompany(final Company... companies){
		if(companies!=null){
			for (Company company : companies) {  
				System.out.println(company);  
			} 
		}else{
			System.out.println("No records found !");
		}
	}

}
