package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public abstract class BasePage {

    public static WebDriver driver;

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

    @FindBy(xpath = "/html/body/div[2]/a")
    static WebElement forkLink;

    @FindBy(xpath = "/html/body/div[2]/a/img")
    static WebElement forkLinkImg;

    @FindBy(id = "page-footer")
    static WebElement footer;

    @FindBy(xpath = "//*[@id = \"page-footer\"]//a")
    static WebElement footerLink;

    public BasePage() {
        PageFactory.initElements(driver, this);
    }

    public abstract String getPageTitleText();

    public static String getGitHubForkText() {
        return forkLinkImg.getAttribute("alt");
    }

    public static String getGitHubForkLinkUrl() {
        return forkLink.getAttribute("href");
    }

    public static String getGitHubForkImagePosition() {
        return forkLinkImg.getAttribute("style");
    }

    public static String getPageFooterText() {
        return footer.getText();
    }

    public static String getPageFooterLinkUrl() {
        return footerLink.getAttribute("href");
    }
}
