package com.qa.linkedin.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

public class SearchDataDrivenTest extends TestBase {
	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
	LinkedinHomePage lhmpg;
	LinkedinLoggedinPage llpg;
	SearchResultsPage srpg;

	@Test(priority = 1)
	public void doLoginTest() throws Exception {
		log.debug("excuting the doLoginTest() mehtod");
		log.debug("verifying the Linkedin HomePage Title and elements");
		lhmpg.verifyLinkedinHomePgHeaderText();
		lhmpg.verifyLinkedinHomePgTitle();
		lhmpg.verifyLinkedinLogo();
		log.debug("Click on sign in link");
		lhmpg.clickSigninLink();
		log.debug("---Verify Linkedin login page elements and Title---");
		lhmpg.verifySignInHeaderText();
		lhmpg.verifyLoginPageTitle();
		log.debug("perofrm the login test");
		llpg = lhmpg.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));

	}

	@Test(dataProvider = "dp", priority = 2)
	public void dosearchPeopleTest(String s) throws Exception {
		log.debug("perform dosearchPeopleTest for:" + s);
		llpg.verifyLinkedinLoggedInPgTitle();
		llpg.verifyProfileRailCard();
		log.debug("Type the value " + s + " in Search editbox");
		 srpg=llpg.doPeopleSearch(s);
		log.debug("fetch the search results count for:" + s);
		long count = srpg.getResultsCount();
		log.debug("search the results count for:" + s + " is: " + count);
		log.debug("click on Home Tab");
		srpg.clickOnHomeTab();
	}

	@DataProvider
	public Object[][] dp() throws InvalidFormatException, IOException {
		log.debug("reading the excel sheet data to @Dataprovider annotation");
		Object[][] data = new ExcelUtils().getTestData(path,"Sheet1");
		return data;

	}

	@BeforeClass
	public void intializeObjects() {
		log.debug("Intilize the page objects");
		lhmpg = new LinkedinHomePage();
		llpg = new LinkedinLoggedinPage();
		srpg = new SearchResultsPage();

	}

	@AfterClass
	public void logoutTest() throws Exception {
		log.debug("logout from the application");
		llpg.doLogOut();
	}

}
