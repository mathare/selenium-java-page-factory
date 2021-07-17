package com.the_internet.herokuapp.steps;

import com.the_internet.herokuapp.pages.FormAuthenticationPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FormAuthenticationPageSteps {

    private final FormAuthenticationPage page = new FormAuthenticationPage();

    @When("I enter a Username of {string}")
    public void enterUsername(String username) {
        page.enterUsername(username);
    }

    @When("I enter a Password of {string}")
    public void enterPassword(String password) {
        page.enterPassword(password);
    }

    @When("I click the Login button")
    public void clickButton() {
        page.clickLoginButton();
    }

    @Then("the opening paragraph text is")
    public void verifyOpeningParagraphText(String expectedText) {
        //This is a different implementation to the Dynamic Controls page step as the two pages use completely different
        //HTML tags for the paragraphs - it's <p> on the Dynamic Controls page but <h4> on the Form Authentication page
        //for some unknown reason
        String actualText = page.getOpeningParagraphText();
        assertEquals(expectedText.replace("\n", " "), actualText);
    }

    @Then("^a (Username|Password) input is displayed$")
    public void verifyInputDisplayed(String inputName) {
        boolean inputDisplayed = inputName.equals("Username") ? page.isUsernameInputDisplayed() : page.isPasswordInputDisplayed();
        String actualLabel = inputName.equals("Username") ? page.getUsernameInputLabelText() : page.getPasswordInputLabelText();
        assertTrue(inputDisplayed);
        assertEquals(inputName, actualLabel);
    }

    @Then("a Login button is displayed")
    public void verifyLoginButtonDisplayed() {
        assertTrue(page.isLoginButtonDisplayed());
        assertEquals("Login", page.getLoginButtonText());
    }

    @Then("^a (red|green) \"(.*)\" message banner is displayed$")
    public void verifyMessageBannerDisplayed(String bannerColour, String expectedMsg) {
        String expectedBannerColour = System.getProperty("browser", "chrome").equals("firefox")
                ? bannerColour.equals("red") ? "rgb(198, 15, 19)" : "rgb(93, 164, 35)"
                : bannerColour.equals("red") ? "rgba(198, 15, 19, 1)" : "rgba(93, 164, 35, 1)";
        assertTrue(page.isMessageBannerDisplayed());
        assertEquals(expectedBannerColour, page.getMessageBannerColour());
        assertEquals(expectedMsg, page.getMessageBannerText());
    }

    @Then("a Logout button is displayed")
    public void verifyLogoutButtonDisplayed() {
        assertTrue(page.isLogoutButtonDisplayed());
        assertEquals("Logout", page.getLogoutButtonText());
    }
}