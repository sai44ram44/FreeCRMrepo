package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	//Initialization page objects
	public HomePage() {
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//td[@class='headertext' and contains(text(),'Sairam D')]")
	WebElement usrLabel;
	
	@FindBy(xpath="//a[@title='Contacts']")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[@title='Deals']")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[@title='Tasks']")
	WebElement tasksLink;
	
	@FindBy(xpath="//a[@title='New Contact']")
	WebElement newContactLink;
	
	public String verifyHomePageLink() {
		
		return driver.getTitle();
	}
	
	public boolean verifyUserNameLabel() {
		
		return usrLabel.isDisplayed();
	}
	
	public DealsPage clickOnDealsLink() {
		
		dealsLink.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasksLink() {
		
		tasksLink.click();
		return new TasksPage();
	}

	public ContactsPage clickOnContactsLink() {
	
		contactsLink.click();
		return new ContactsPage();
	}
	
	public void clickonNewContactLink() {
		
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
		
	}
	
	

}
