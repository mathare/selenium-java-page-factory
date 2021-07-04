package com.the_internet.herokuapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DynamicControlsPage extends BasePage {

    private static final By contentLocator = By.className("example");
    private static final By paragraphLocator = By.tagName("p");
    private static final By sectionHeaderLocator = By.className("subheader");
    private static final By horizontalRuleLocator = By.tagName("hr");
    private static final By checkboxExampleFormLocator = By.id("checkbox-example");
    private static final By inputExampleFormLocator = By.id("input-example");
    private static final By checkboxLocator = By.xpath("//input[@type=\"checkbox\"]");
    private static final By inputLocator = By.xpath("//input[@type=\"text\"]");
    private static final By buttonLocator = By.tagName("button");
    private static final By alternativeCheckboxLocator = By.id("checkbox");
    private static final By loadingBarLocator = By.id("loading");
    private static final By messageLocator = By.id("message");
    private static final String buttonByTextXpath = "//button[text()=\"%s\"]";

    private WebElement getParagraph() {
        return driver.findElement(paragraphLocator);
    }

    private List<WebElement> getSectionHeaders() {
        return driver.findElements(sectionHeaderLocator);
    }

    private List<WebElement> getHorizontalRules() {
        return driver.findElement(contentLocator).findElements(horizontalRuleLocator);
    }

    private WebElement getCheckboxExampleForm() {
        return driver.findElement(checkboxExampleFormLocator);
    }

    private WebElement getInputExampleForm() {
        return driver.findElement(inputExampleFormLocator);
    }

    private WebElement getSectionForm(String sectionName) {
        return sectionName.equals("Remove/add") ? getCheckboxExampleForm() : getInputExampleForm();
    }

    private List<WebElement> getCheckboxesInSection(String sectionName) {
        WebElement sectionForm = getSectionForm(sectionName);
        return sectionForm.findElements(checkboxLocator);
    }

    private List<WebElement> getInputsInSection(String sectionName) {
        WebElement sectionForm = getSectionForm(sectionName);
        return sectionForm.findElements(inputLocator);
    }

    private List<WebElement> getButtonsInSection(String sectionName) {
        WebElement sectionForm = getSectionForm(sectionName);
        return sectionForm.findElements(buttonLocator);
    }

    private WebElement getCheckbox() {
        return driver.findElement(alternativeCheckboxLocator);
    }

    private WebElement getCheckboxParent() {
        return getCheckbox().findElement(parentLocator);
    }

    private WebElement getInputControl() {
        // This will return the first matching element only. The element could be found within a named form instead
        // (see getButton() below) to avoid potentially getting the wrong element but given the DOM this solution will
        // work well enough
        return driver.findElement(inputLocator);
    }

    private WebElement getButton(String sectionName) {
        WebElement sectionForm = getSectionForm(sectionName);
        return sectionForm.findElement(buttonLocator);
    }

    private WebElement getButtonByText(String buttonText) {
        return driver.findElement(By.xpath(buttonByTextXpath.replace("%s", buttonText)));
    }

    private WebElement getLoadingBar() {
        return driver.findElement(loadingBarLocator);
    }

    private WebElement getLoadingBarImg() {
        return driver.findElement(loadingBarLocator).findElement(imageLocator);
    }

    private WebElement getMessage() {
        return driver.findElement(messageLocator);
    }

    public String getOpeningParagraphText() {
        return getParagraph().getText();
    }

    public List<String> getSectionHeadersText() {
        List<String> headers = new ArrayList<>();
        for (WebElement sectionHeader : getSectionHeaders()) {
            headers.add(sectionHeader.getText());
        }
        return headers;
    }

    public int getNumHorizontalRules() {
        return getHorizontalRules().size();
    }

    public int getNumCheckboxesInSection(String sectionName) {
        return getCheckboxesInSection(sectionName).size();
    }

    public int getNumInputsInSection(String sectionName) {
        return getInputsInSection(sectionName).size();
    }

    public int getNumButtonsInSection(String sectionName) {
        return getButtonsInSection(sectionName).size();
    }

    public String getCheckboxLabel() {
        if (getCheckbox().getTagName().equals("input")) {
            return getCheckboxParent().getText();
        }
        return getCheckbox().getText();
    }

    public boolean isCheckboxChecked() {
        return getCheckbox().getAttribute("checked") != null;
    }

    public String getInputControlText() {
        return getInputControl().getText();
    }

    public boolean isInputControlEnabled() {
        return getInputControl().isEnabled();
    }

    public String getButtonText(String sectionName) {
        return getButton(sectionName).getText();
    }

    public void clickButtonByText(String buttonText) {
        getButtonByText(buttonText).click();
    }

    public boolean isLoadingBarDisplayed() {
        return getLoadingBar().isDisplayed();
    }

    public String getLoadingBarLabel() {
        return getLoadingBar().getText();
    }

    public String getMessageText() {
        return getMessage().getText();
    }

    public void waitForLoadingBarToDisplay() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(getLoadingBarImg()));
    }

    public void waitForLoadingBarToDisappear() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(getLoadingBarImg()));
    }
}