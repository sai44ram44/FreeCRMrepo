package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {
	
	public ContactsPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(xpath="//select[@name='title']")
	WebElement title;
	
	@FindBy(id="first_name")
	WebElement fName;
	
	@FindBy(id="surname")
	WebElement lName;
	
	@FindBy(id="email")
	WebElement email;
	
	
	public boolean verifyContactsLabel() {
		
		return contactsLabel.isDisplayed();
	}
	
	
	public void checkContactsListName(String name) {
		
		driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]//parent::td[@class='datalistrow']//"
				+ "preceding-sibling::td[@class='datalistrow']/input[@type='checkbox']")).click();
	}
	
	public void createNewContact(String tle, String fn,String ln,String em) {
		
		Select sc = new Select(title);
		sc.selectByValue(tle);
		fName.sendKeys(fn);
		lName.sendKeys(ln);
		email.sendKeys(em);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Save']")).click();
	}

}
