package com.qa.linkedin.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinHomePage extends BasePageWeb {
	private Logger log=Logger.getLogger(LinkedinHomePage.class);
	//create a constructor
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);	
	}
	@FindBy(css="a.nav__logo-link")
	private WebElement linkedinLogo;
	
	@FindBy(css="a.nav__button-secondary")
	private WebElement signInLink;
	
	@FindBy(css="h1[data-test-id='hero__headline']")
	private WebElement linkedininHomePgHeaderTxt;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement signInHeaderText;
	
	@FindBy(id="username")
	private WebElement emailEditBox;
	
	@FindBy(xpath="//input[@name='session_password']")
	private WebElement passwordEditBox;
	
	@FindBy(xpath="//button[contains(@class,'button--floating')]")
	private WebElement signInButton;
	
	

	
	String LoginPageTitle="LinkedIn Login, Sign in | LinkedIn";
	
	String linkedinHomePgTitle="LinkedIn: Log In or Sign Up";
	public void verifyLinkedinHomePgTitle() {
		log.debug("wait for the linkedin homepage title:"+linkedinHomePgTitle);
		wait.until(ExpectedConditions.titleContains(linkedinHomePgTitle));
		Assert.assertEquals(driver.getTitle(), linkedinHomePgTitle);
	}
		
		public void verifyLinkedinHomePgHeaderText() throws InterruptedException {
			log.debug("wait for the linkedin Home Page Header Text :"+linkedininHomePgHeaderTxt);
			wait.until(ExpectedConditions.visibilityOf(linkedininHomePgHeaderTxt));
			Assert.assertTrue(isDisplayedElement(linkedininHomePgHeaderTxt));	
		}
	
	
	public void verifyLinkedinLogo() throws InterruptedException {
		log.debug("wait for the linkedin logo:"+linkedinLogo);
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(isDisplayedElement(linkedinLogo));	
	}
	
	public void clickSigninLink() throws Exception{
		log.debug("click on sign in link in homepage");
		click(signInLink);	
	}
	public void verifyLoginPageTitle() {
		log.debug("wait for the linkedin Login Page Title:"+LoginPageTitle);
		wait.until(ExpectedConditions.titleContains(LoginPageTitle));
		Assert.assertEquals(driver.getTitle(), LoginPageTitle);
	}
	public void verifySignInHeaderText() throws InterruptedException {
		log.debug("wait for the linkedin Home Page Header Text :"+signInHeaderText);
		wait.until(ExpectedConditions.visibilityOf(signInHeaderText));
		Assert.assertTrue(isDisplayedElement(signInHeaderText));	
	}
	public void clickSigninButton() throws Exception{
		log.debug("click on sign in Button in homepage");
		click(signInButton);	
	}
	public LinkedinLoggedinPage doLogin(String uname,String pwd) throws Exception {
		log.debug("started excuting the dolLogin ");
		log.debug("clear the in the email editbox");
		clearText(emailEditBox);
		log.debug("typethe "+uname+" in emaileditbox");
		sendKey(emailEditBox,uname);
		log.debug("clear the content in the password editbox");
		clearText(passwordEditBox);
		log.debug("type the password in the password editbox");
		sendKey(passwordEditBox,pwd);
		clickSigninButton();
		return new LinkedinLoggedinPage();
		
	}
	
	

}
