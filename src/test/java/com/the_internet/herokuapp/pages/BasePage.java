package com.the_internet.herokuapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public abstract class BasePage {

    public static final String BASE_URL = "https://the-internet.herokuapp.com";

    public static final Map<String, String> PAGE_URLS = Map.ofEntries(
            Map.entry("home", BASE_URL + "/"),
            Map.entry("checkboxes", BASE_URL + "/checkboxes"),
            Map.entry("dropdown", BASE_URL + "/dropdown"),
            Map.entry("dynamic controls", BASE_URL + "/dynamic_controls"),
            Map.entry("form authentication", BASE_URL + "/login"),
            Map.entry("inputs", BASE_URL + "/inputs"),
            Map.entry("secure area", BASE_URL + "/secure")
    );
    protected static final By listElementLocator = By.tagName("li");
    protected static final By anchorLocator = By.tagName("a");
    protected static final By imageLocator = By.tagName("img");
    protected static final By parentLocator = By.xpath("./..");
    private static final By header1Locator = By.tagName("h1");
    private static final By header2Locator = By.tagName("h2");
    private static final By header3Locator = By.tagName("h3");
    private static final By header4Locator = By.tagName("h4");
    private static final By forkLinkLocator = By.xpath("/html/body/div[2]/a");
    private static final By footerLocator = By.id("page-footer");
    public static WebDriver driver;

    public static String getHeaderText() {
        return driver.findElement(header1Locator).getText();
    }

    public static String getHeader2Text() {
        return driver.findElement(header2Locator).getText();
    }

    private static String getHeader3Text() {
        return driver.findElement(header3Locator).getText();
    }

    private static String getHeader4Text() {
        return driver.findElement(header4Locator).getText();
    }

    private static WebElement getGitHubForkLink() {
        return driver.findElement(forkLinkLocator);
    }

    private static WebElement getPageFooter() {
        return driver.findElement(footerLocator);
    }

    public static String getPageTitleText() {
        // As there is no consistency in terms of the heaading level used for the page titles across the various pages
        // use a nested try-catch block to find the right heading to use as the page title
        try {
            return getHeader2Text();
        } catch (org.openqa.selenium.NoSuchElementException e1) {
            try {
                return getHeader3Text();
            } catch (org.openqa.selenium.NoSuchElementException e2) {
                try {
                    return getHeader4Text();
                } catch (org.openqa.selenium.NoSuchElementException e3) {
                    return "No page title element found";
                }
            }
        }
    }

    public static String getGitHubForkText() {
        return getGitHubForkLink().findElement(imageLocator).getAttribute("alt");
    }

    public static String getGitHubForkLinkUrl() {
        return getGitHubForkLink().getAttribute("href");
    }

    public static String getGitHubForkImagePosition() {
        return getGitHubForkLink().findElement(imageLocator).getAttribute("style");
    }

    public static String getPageFooterText() {
        return getPageFooter().getText();
    }

    public static String getPageFooterLinkUrl() {
        return getPageFooter().findElement(anchorLocator).getAttribute("href");
    }
}
