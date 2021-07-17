package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(tagName = "h1")
    WebElement pageTitle;

    @FindBy(tagName = "h2")
    WebElement subheader;

    @FindBy(xpath = "//*[@id=\"content\"]/ul/li")
    List<WebElement> allSubPages;

    @FindBy(xpath = "//*[@id=\"content\"]/ul/li//a")
    List<WebElement> allSubPageLinks;

    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public int getNumSubPages() {
        return allSubPages.size();
    }

    public String getSubheaderText() {
        return subheader.getText();
    }

    public List<String> getSubPageNames() {
        List<String> subPageNames = new ArrayList<>();
        for (WebElement subPage : allSubPages) {
            // Where a list element includes additional details in brackets e.g. login credentials exclude these from the page name
            subPageNames.add(subPage.getText().split(" \\(")[0]);
        }
        return subPageNames;
    }

    public void clickOnPageLink(String pageName) {
        for (WebElement subPage : allSubPageLinks) {
            if (subPage.getText().startsWith(pageName)) {
                subPage.click();
                break;
            }
        }
    }
}
