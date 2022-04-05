package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb {
	private Logger log = Logger.getLogger(LinkedinLoggedinPage.class);

	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[contains(@class,'feed-identity-module__')]")
	private WebElement profileRailCard;

	@FindBy(xpath = "//img[contains(@class,'global-nav__me-photo ghost')]")
	private WebElement profilePicIcon;

	@FindBy(xpath="//a[contains(.,'Sign Out')]")		
	private WebElement signOutLink;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchEditbox;

	String LoggedinPageTitle = "(99+) Feed | LinkedIn";
	

	public void verifyLinkedinLoggedInPgTitle() {
		log.debug("wait for the linkedin Loggedin page title:" + LoggedinPageTitle);
		wait.until(ExpectedConditions.titleContains(LoggedinPageTitle));
		Assert.assertTrue(driver.getTitle().contains(LoggedinPageTitle));
	}

	public void verifyProfileRailCard() throws Exception {
		log.debug("Verify the linkedin Profile rail card element");
		wait.until(ExpectedConditions.titleContains(LoggedinPageTitle));
		Assert.assertTrue(isDisplayedElement(profileRailCard));
	}

	public void doLogOut() throws Exception {
		log.debug("started executinf the doLogOut() method");
		wait.until(ExpectedConditions.elementToBeClickable(profilePicIcon));
		wait.until(ExpectedConditions.visibilityOf(profilePicIcon));
		log.debug("click on profile image icon");
		click(profilePicIcon);
		wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		log.debug("click on sign out link");
		click(signOutLink);
	}

	public SearchResultsPage doPeopleSearch(String keyword) throws Exception {
		log.debug("Started excuting the doPeopleSearch() for the keyword::" + keyword);
		log.debug("clear the content in the search edit box");
		clearText(searchEditbox);
		log.debug("type the search keywrod:" +keyword+" in search editbox");
		sendKey(searchEditbox,keyword);
		log.debug("Press the Enter key on the search Edit box");
		//searchEditbox.click();
		searchEditbox.sendKeys(Keys.ENTER);
		
		return new SearchResultsPage();
 
	}
	public void verifyLinkedinHomePgHeaderText() {
		// TODO Auto-generated method stub
		
	}


	
}
