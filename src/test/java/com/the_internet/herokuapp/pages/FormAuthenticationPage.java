package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormAuthenticationPage extends BasePage {

    @FindBy(tagName = "h2")
    WebElement pageTitle;

    @FindBy(className = "subheader")
    WebElement openingParagraph;

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(xpath = "//input[@id = \"username\"]/../label")
    WebElement usernameInputLabel;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@id = \"password\"]/../label")
    WebElement passwordInputLabel;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement loginButton;

    @FindBy(id = "flash")
    WebElement messageBanner;

    @FindBy(className = "button")
    WebElement logoutButton;

    public FormAuthenticationPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public String getOpeningParagraphText() {
        return openingParagraph.getText();
    }

    public boolean isUsernameInputDisplayed() {
        return usernameInput.isDisplayed();
    }

    public String getUsernameInputLabelText() {
        return usernameInputLabel.getText();
    }

    public boolean isPasswordInputDisplayed() {
        return passwordInput.isDisplayed();
    }

    public String getPasswordInputLabelText() {
        return passwordInputLabel.getText();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public String getLoginButtonText() {
        return loginButton.getText();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isMessageBannerDisplayed() {
        return messageBanner.isDisplayed();
    }

    public String getMessageBannerColour() {
        return messageBanner.getCssValue("background-color");
    }

    public String getMessageBannerText() {
        // This includes the 'x' to close the message so need to strip that off
        String fullMessage = messageBanner.getText();
        return fullMessage.split("\n")[0];
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public boolean isLogoutButtonDisplayed() {
        return logoutButton.isDisplayed();
    }

    public String getLogoutButtonText() {
        return logoutButton.getText();
    }
}