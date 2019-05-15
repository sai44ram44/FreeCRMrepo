package com.crm.qa.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Page Factories / ObjectRepository
	@FindBy(name="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(xpath="//img[@class='img-responsive']")
	WebElement logoCRM;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement SignUp;
	
	public String titlePage() {
		
		return  driver.getTitle();
	}
	
	public boolean verifyCRMLogo() {
		
		return logoCRM.isDisplayed();
	}
	
	public HomePage login(String un,String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		//Actions actions = new Actions(driver);
		//actions.moveToElement(loginBtn).click().perform();
		loginBtn.isDisplayed();
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		return new HomePage();
	}
	

}
