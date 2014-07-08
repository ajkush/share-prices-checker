package com.google.share.testsuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FileUtilTest.class, CompanyServiceTest.class })
public class AllTests {

}
