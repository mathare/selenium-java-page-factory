package com.the_internet.herokuapp.steps;

import com.the_internet.herokuapp.DriverManager;
import com.the_internet.herokuapp.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

public class CommonSteps {

    private String page;

    @Before
    public void setup() {
        BasePage.driver = DriverManager.getDriver();
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) BasePage.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        BasePage.driver.quit();
    }

    @Given("I have navigated to {string}")
    public void navigateToUrl(String url) {
        BasePage.driver.get(url);
    }

    @Given("I have navigated to the 'the-internet' {string} page")
    public void navigateTo(String pageName) {
        page = pageName;
        String url = BasePage.PAGE_URLS.get(pageName.toLowerCase());
        BasePage.driver.get(url);
        new WebDriverWait(BasePage.driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        assertEquals(url, BasePage.driver.getCurrentUrl());
    }

    @Then("the page title is {string}")
    public void verifyPageTitle(String expectedText) {
        String actual = Objects.requireNonNull(getPage()).getPageTitleText();
        assertEquals(expectedText, actual);
    }

    @Then("the {string} page opens")
    public void verifyPageOpens(String pageName) {
        String expectedUrl = BasePage.PAGE_URLS.get(pageName.toLowerCase());
        new WebDriverWait(BasePage.driver, 10).until(ExpectedConditions.urlToBe(expectedUrl));
        assertEquals(expectedUrl, BasePage.driver.getCurrentUrl());
    }

    @Then("a {string} banner is displayed in the top-right corner of the page")
    public void verifyGitHubForkBanner(String expectedText) {
        final String expectedUrl = "https://github.com/tourdedave/the-internet";
        String actualText = BasePage.getGitHubForkText();
        assertEquals(expectedText, actualText);
        assertEquals(expectedUrl, BasePage.getGitHubForkLinkUrl());
        String[] styleAttrs = BasePage.getGitHubForkImagePosition().split(";");
        for (String attr : styleAttrs) {
            if (attr.startsWith("position")) assertEquals("absolute", attr.split(": ")[1]);
            if (attr.startsWith("top")) assertEquals("0px", attr.split(": ")[1]);
            if (attr.startsWith("right")) assertEquals("0px", attr.split(": ")[1]);
            if (attr.startsWith("border")) assertEquals("0px", attr.split(": ")[1]);
        }
    }

    @Then("the page has a footer containing {string}")
    public void verifyPageFooterText(String expectedText) {
        String actual = BasePage.getPageFooterText();
        assertEquals(expectedText, actual);
    }

    @Then("the link in the page footer goes to {string}")
    public void verifyPageFooterLinkUrl(String expectedUrl) {
        String actual = BasePage.getPageFooterLinkUrl();
        assertEquals(expectedUrl, actual);
    }

    private BasePage getPage() {
        switch (page.toLowerCase()) {
            case "home":
                return new HomePage();
            case "checkboxes":
                return new CheckboxesPage();
            case "dropdown":
                return new DropdownPage();
            case "dynamic controls":
                return new DynamicControlsPage();
            case "form authentication":
                return new FormAuthenticationPage();
        }
        return null;
    }

}
