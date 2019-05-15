package com.crm.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;


public class HomePageTests extends TestBase {
	
	LoginPage loginpage;
	HomePage homepage;
	TestUtil testutil;
	ContactsPage contactspage;
	public HomePageTests() {
		super();
	} //ctrl+shift+O to add all the dependency packages
	
	@BeforeMethod
	public void setUp()  {
		
		initialization();
		loginpage = new LoginPage();
		testutil = new TestUtil();
		//Thread.sleep(5000);
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		
		String homepageTitle = homepage.verifyHomePageLink();
		Assert.assertEquals(homepageTitle, "CRMPRO","Home Page title not matched");
	}
	
	@Test(priority=2)
	public void verifyUserNameLabelTest() {
		testutil.switchtoFrame();
		boolean flag = homepage.verifyUserNameLabel();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void clickOnContactsLinkTest() {
		testutil.switchtoFrame();
		contactspage = homepage.clickOnContactsLink();
	}
		
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	

}
