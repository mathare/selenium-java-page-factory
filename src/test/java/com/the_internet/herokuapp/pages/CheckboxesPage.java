package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class CheckboxesPage extends BasePage {

    @FindBy(tagName = "h3")
    WebElement pageTitle;

    @FindBy(id = "checkboxes")
    WebElement checkboxesForm;

    @FindBy(tagName = "input")
    List<WebElement> allCheckboxes;

    public CheckboxesPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    public int getNumCheckboxes() {
        return allCheckboxes.size();
    }

    public List<String> getAllCheckboxLabels() {
        String[] checkboxLabels = checkboxesForm.getText().split("\n");
        return Arrays.asList(checkboxLabels.clone());
    }

    public boolean getCheckboxCheckedState(int index) {
        return allCheckboxes.get(index).getAttribute("checked") != null;
    }

    public void clickOnCheckbox(int index) {
        allCheckboxes.get(index).click();
    }

}
