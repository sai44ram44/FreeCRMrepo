package com.crm.qa.testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class ContactsPageTests extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	ContactsPage contactspage;
	TestUtil testutils;
	String sheetName = "contacts";
	Logger log = Logger.getLogger(ContactsPageTests.class);
	
	public ContactsPageTests() {
		
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		TestBase.initialization();
		log.info("******************Before login to the page************");
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		testutils = new TestUtil();
		testutils.switchtoFrame();
		contactspage = homepage.clickOnContactsLink();
		
	}
	
	@Test(priority=1)
	public void verifyContactsLabelTest() {
		
		//testutils.switchtoFrame();
		boolean flag = contactspage.verifyContactsLabel();
		Assert.assertTrue(flag);
		
	}
	
	@Test(priority=2)
	public void checkContactsListNameTest() {
		
		 contactspage.checkContactsListName("test1 test1");
	}
	
	@Test(priority=3, dataProvider="getCRMTestData")
	public void createNewContactTest(String t,String f,String l,String e) {
		
		homepage.clickonNewContactLink();
		contactspage.createNewContact(t, f, l, e);
	}
	
	@DataProvider
	public Object[][] getCRMTestData() throws InvalidFormatException, IOException {
		
		Object[][] result = testutils.getTestData("Contacts");
		return result;
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}

}
