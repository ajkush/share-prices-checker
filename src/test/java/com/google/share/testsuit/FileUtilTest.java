/**
 * 
 */
package com.google.share.testsuit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.share.util.FileUtil;

/**
 * @author Ajay Sharma
 * 
 */
public class FileUtilTest {
	final static Logger log = Logger.getLogger(FileUtilTest.class);
	Properties p = null;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp()  {
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
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Test method for
	 * {@link com.google.share.util.FileUtil#readFile(java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadFile() throws IOException {
		URL testFileName = getClass().getClassLoader().getResource(p.getProperty("validPath"));
		String filename = null;
		assertNotNull(FileUtil.readFile(testFileName.getPath()));
		assertNull(FileUtil.readFile(filename));
	}

	/**
	 * Test method for
	 * {@link com.google.share.util.FileUtil#isEmpty(java.lang.String)}.
	 */
	@Test
	public void testIsEmpty() {
		assertNotNull(FileUtil.isEmpty("NotEmpty"));
		assertEquals(FileUtil.isEmpty("NotEmpty"),false);
		assertEquals(FileUtil.isEmpty(""),true);
	}

	/**
	 * Test method for
	 * {@link com.google.share.util.FileUtil#parseDoubleWithDefault(java.lang.String)}
	 * .
	 */
	@Test
	public void testParseDoubleWithDefault() {
		assertEquals(FileUtil.parseDoubleWithDefault("aa"),new Double(0.0));
		assertEquals(FileUtil.parseDoubleWithDefault("32"),new Double(32.0));
	}

}
