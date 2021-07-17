package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DropdownPage extends BasePage {

    @FindBy(tagName = "h3")
    WebElement pageTitle;

    @FindBy(id = "dropdown")
    WebElement dropdownList;

    @FindBy(tagName = "select")
    List<WebElement> selectListControls;

    public DropdownPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    @Override
    public String getPageTitleText() {
        return pageTitle.getText();
    }

    private Select getDropdownList() {
        return new Select(dropdownList);
    }

    public int getNumDropdownLists() {
        return selectListControls.size();
    }

    public String getCurrentDropdownValue() {
        return getDropdownList().getFirstSelectedOption().getText();
    }

    public List<String> getAllDropdownOptions() {
        List<String> options = new ArrayList<>();
        for (WebElement option : getDropdownList().getOptions()) {
            options.add(option.getText());
        }
        return options;
    }

    public boolean getDropdownMultiSelectionSupported() {
        return getDropdownList().getWrappedElement().getAttribute("multiple") != null;
    }

    public void selectDropdownOption(String option) {
        getDropdownList().selectByVisibleText(option);
    }

    public void selectDropdownOption(int index) {
        getDropdownList().selectByIndex(index);
    }
}
