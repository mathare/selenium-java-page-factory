package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DynamicControlsPage extends BasePage {

    @FindBy(tagName = "h4")
    WebElement pageTitle;

    @FindBy(tagName = "p")
    WebElement paragraph;

    @FindBy(className = "subheader")
    List<WebElement> sectionHeaders;

    @FindBy(xpath = "//div[@class=\"example\"]/hr")
    List<WebElement> horizontalRules;

    @FindBy(xpath = "//*[@id=\"checkbox-example\"]//input[@type=\"checkbox\"]")
    List<WebElement> removeAddSectionCheckboxes;

    @FindBy(xpath = "//*[@id=\"checkbox-example\"]//input[@type=\"text\"]")
    List<WebElement> removeAddSectionInputs;

    @FindBy(xpath = "//*[@id=\"checkbox-example\"]//button")
    List<WebElement> removeAddSectionButtons;

    @FindBy(xpath = "//*[@id=\"input-example\"]//input[@type=\"checkbox\"]")
    List<WebElement> enableDisableSectionCheckboxes;

    @FindBy(xpath = "//*[@id=\"input-example\"]//input[@type=\"text\"]")
    List<WebElement> enableDisableSectionInputs;

    @FindBy(xpath = "//*[@id=\"input-example\"]//button")
    List<WebElement> enableDisableSectionButtons;

    @FindBy(xpath = "//input[@type=\"text\"]")
    WebElement input;

    @FindBy(id = "checkbox")
    WebElement checkbox;

    @FindBy(xpath = "//*[@id = \"checkbox\"]/..")
    WebElement checkboxParent;

    @FindBy(id = "loading")
    WebElement loadingBar;

    @FindBy(xpath = "//*[@id = \"loading\"]/img")
    WebElement loadingBarImg;

    @FindBy(id = "message")
    WebElement message;

    @FindBy(xpath = "//button[text()=\"Remove\"]")
    WebElement removeButton;

    @FindBy(xpath = "//button[text()=\"Add\"]")
    WebElement addButton;

    @FindBy(xpath = "//button[text()=\"Enable\"]")
    WebElement enableButton;

    @FindBy(xpath = "//button[text()=\"Disable\"]")
    WebElement disableButton;

    public DynamicControlsPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public String getOpeningParagraphText() {
        return paragraph.getText();
    }

    public List<String> getSectionHeadersText() {
        List<String> headers = new ArrayList<>();
        for (WebElement sectionHeader : sectionHeaders) {
            headers.add(sectionHeader.getText());
        }
        return headers;
    }

    public int getNumHorizontalRules() {
        return horizontalRules.size();
    }

    public int getNumCheckboxesInSection(String sectionName) {
        return sectionName.equals("Remove/add") ? removeAddSectionCheckboxes.size() : enableDisableSectionCheckboxes.size();
    }

    public int getNumInputsInSection(String sectionName) {
        return sectionName.equals("Remove/add") ? removeAddSectionInputs.size() : enableDisableSectionInputs.size();
    }

    public int getNumButtonsInSection(String sectionName) {
        return sectionName.equals("Remove/add") ? removeAddSectionButtons.size() : enableDisableSectionButtons.size();
    }

    public String getCheckboxLabel() {
        if (checkbox.getTagName().equals("input")) {
            return checkboxParent.getText();
        }
        return checkbox.getText();
    }

    public boolean isCheckboxChecked() {
        return checkbox.getAttribute("checked") != null;
    }

    public String getInputControlText() {
        return input.getText();
    }

    public boolean isInputControlEnabled() {
        return input.isEnabled();
    }

    public String getButtonText(String sectionName) {
        return sectionName.equals("Remove/add") ? removeAddSectionButtons.get(0).getText() : enableDisableSectionButtons.get(0).getText();
    }

    public void clickButtonByText(String buttonText) {
        if (buttonText.equalsIgnoreCase("Remove")) removeButton.click();
        else if (buttonText.equalsIgnoreCase("Add")) addButton.click();
        else if (buttonText.equalsIgnoreCase("Enable")) enableButton.click();
        else if (buttonText.equalsIgnoreCase("Disable")) disableButton.click();
    }

    public boolean isLoadingBarDisplayed() {
        return loadingBar.isDisplayed();
    }

    public String getLoadingBarLabel() {
        return loadingBar.getText();
    }

    public String getMessageText() {
        return message.getText();
    }

    public void waitForLoadingBarToDisplay() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(loadingBarImg));
    }

    public void waitForLoadingBarToDisappear() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(loadingBarImg));
    }
}