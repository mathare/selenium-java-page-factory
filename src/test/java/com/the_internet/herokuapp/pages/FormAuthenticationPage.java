package com.the_internet.herokuapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FormAuthenticationPage extends BasePage {

    private static final By openingParagraphLocator = By.className("subheader");
    private static final By usernameInputLocator = By.id("username");
    private static final By passwordInputLocator = By.id("password");
    private static final By inputLabelLocator = By.xpath("./../label");
    private static final By loginButtonLocator = By.xpath("//button[@type=\"submit\"]");
    private static final By messageBannerLocator = By.id("flash");
    private static final By logoutButtonLocator = By.className("button");

    private WebElement getOpeningParagraph() {
        return driver.findElement(openingParagraphLocator);
    }

    private WebElement getUsernameInput() {
        return driver.findElement(usernameInputLocator);
    }

    private WebElement getUsernameInputLabel() {
        return getUsernameInput().findElement(inputLabelLocator);
    }

    private WebElement getPasswordInput() {
        return driver.findElement(passwordInputLocator);
    }

    private WebElement getPasswordInputLabel() {
        return getPasswordInput().findElement(inputLabelLocator);
    }

    private WebElement getLoginButton() {
        return driver.findElement(loginButtonLocator);
    }

    private WebElement getMessageBanner() {
        return driver.findElement(messageBannerLocator);
    }

    private WebElement getLogoutButton() {
        return driver.findElement(logoutButtonLocator);
    }

    public String getOpeningParagraphText() {
        return getOpeningParagraph().getText();
    }

    public boolean isUsernameInputDisplayed() {
        return getUsernameInput().isDisplayed();
    }

    public String getUsernameInputLabelText() {
        return getUsernameInputLabel().getText();
    }

    public boolean isPasswordInputDisplayed() {
        return getPasswordInput().isDisplayed();
    }

    public String getPasswordInputLabelText() {
        return getPasswordInputLabel().getText();
    }

    public boolean isLoginButtonDisplayed() {
        return getLoginButton().isDisplayed();
    }

    public String getLoginButtonText() {
        return getLoginButton().getText();
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public boolean isMessageBannerDisplayed() {
        return getMessageBanner().isDisplayed();
    }

    public String getMessageBannerColour() {
        return getMessageBanner().getCssValue("background-color");
    }

    public String getMessageBannerText() {
        // This includes the 'x' to close the message so need to strip that off
        String fullMessage = getMessageBanner().getText();
        return fullMessage.split("\n")[0];
    }

    public void enterUsername(String username) {
        getUsernameInput().sendKeys(username);
    }

    public void enterPassword(String password) {
        getPasswordInput().sendKeys(password);
    }

    public boolean isLogoutButtonDisplayed() {
        return getLogoutButton().isDisplayed();
    }

    public String getLogoutButtonText() {
        return getLogoutButton().getText();
    }
}