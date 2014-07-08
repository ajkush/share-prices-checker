package com.google.share.testsuit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.share.pojo.Company;
import com.google.share.service.ICompanyService;
import com.google.share.serviceimpl.CompanyService;

public class CompanyServiceTest {

	final static Logger log = Logger.getLogger(FileUtilTest.class);
	Properties p = null;
	String validFileName = null;
	Company dummyData[] = { new Company("Company-A", "2013", "Dec", 1100),
			new Company("Company-B", "1991", "Apr", 1418),
			new Company("Company-C", "1993", "Jun", 995),
			new Company("Company-D", "2002", "Apr", 999),
			new Company("Company-E", "1998", "Dec", 3000) };
	Company randomData[] = { new Company("Company-A", "1992", "Apr", 751),
			new Company("Company-B", "1993", "Jan", 522),
			new Company("Company-C", "1997", "Feb", 807),
			new Company("Company-D", "1995", "Feb", 902),
			new Company("Company-E", "1998", "Feb", 891) };

	@Before
	public void setUp() throws Exception {
		InputStream properties = getClass().getClassLoader().getResourceAsStream(
				"file.properties");
		p = new Properties();
		
		try {
			p.load(properties);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Unable to load file");
		}
		log.debug("Resource Path : " + p.getProperty("validPath"));
		validFileName =  getClass().getClassLoader().getResource(p.getProperty("validPath")).getPath();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindCompanyMaxShare() {

		String filePath = null;
		ICompanyService companyService = new CompanyService();
		
		assertNotNull(companyService.findCompanyMaxShare(validFileName));
		assertNull(companyService.findCompanyMaxShare(filePath));
		Company maxCompany[] = companyService.findCompanyMaxShare(validFileName);

		assertNotNull(maxCompany);
		// Company dummyData[] = createMaxCompanyObj();
		for (int i = 0; i < maxCompany.length; i++) {

			assertTrue(matchCompany(maxCompany[i], dummyData[i]));
		}

		// Company randomData[] = createRandomCompanyObj();
		for (int i = 0; i < maxCompany.length; i++) {

			assertFalse(matchCompany(maxCompany[i], randomData[i]));
		}
		Company tmpCompany[] = companyService.findCompanyMaxShare(p
				.getProperty("inValidPath"));
		assertNull(tmpCompany);

	}

	/**
	 * Helper function to check the company objects equality
	 */
	private boolean matchCompany(Company maxCompany, Company dummyCompany) {
		if (maxCompany.getName().equals(dummyCompany.getName())
				&& maxCompany.getMonth().equals(dummyCompany.getMonth())
				&& maxCompany.getPrice() == dummyCompany.getPrice()
				&& maxCompany.getYear().equals(dummyCompany.getYear())) {
			return true;
		}
		return false;
	}

}
