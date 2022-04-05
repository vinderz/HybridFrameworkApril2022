package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchResultsPage extends BasePageWeb {
	private Logger log = Logger.getLogger(SearchResultsPage.class);

	public SearchResultsPage() {
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[contains(@class,'search-results__cluster-bottom-banner')]/a")
	private WebElement seeAllPeopleResultsLink;

	@FindBy(xpath = "//div[contains(@class,'search-results-container')]/h2")
	private WebElement searchresultsText;

	@FindBy(xpath = "//ul[contains(@class,'global-nav__primary-items')]/li/a")
	private WebElement homeTab;

	String searchResultsPageTitle = "Search | LinkedIn";

	public void validateSearchResultsPageTitle() {
		log.debug("wait for the search results page title");
		wait.until(ExpectedConditions.titleContains(searchResultsPageTitle));
		Assert.assertTrue(driver.getTitle().contains(searchResultsPageTitle));
	}

	public void clickseeAllPeopleResultsLink() throws InterruptedException {
		log.debug("click on see All People results link");
		clickseeAllPeopleResultsLink();
	}

	public long getResultsCount() throws InterruptedException {
		validateSearchResultsPageTitle();
		if (isElementPresent(By.xpath("//div[contains(@class,'search-results__cluster-bottom-banner')]/a"))) {
			click(seeAllPeopleResultsLink);
			return fetchResultsCount();
		} else {
		}
		return fetchResultsCount();
	}

	public long fetchResultsCount() {

		log.debug("fetch the  results count text");
		String resultsTxt = searchresultsText.getText();
		System.out.println("Search results Text is:" + resultsTxt);
		// resultsTxt=About 428,000 results
		String[] str = resultsTxt.split(" ");
		// str=[]=["About","428,000","results"]
		// 0 1 2
		log.debug("Results count in string format=" + str[1]);
		String s1 = str[1].replace(",", "");
		log.debug("Convert Sting into long interger");
		long count = Long.parseLong(s1);
		return count;

	}

	public void clickOnHomeTab() throws InterruptedException {
		log.debug("click on see click On HomeTab");
		click(homeTab);
	}
}
