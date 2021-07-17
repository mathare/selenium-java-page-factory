package com.the_internet.herokuapp.steps;

import com.the_internet.herokuapp.pages.DynamicControlsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class DynamicControlsPageSteps {

    private final DynamicControlsPage page = new DynamicControlsPage();
    private String section;

    @Given("I have clicked the {string} button")
    public void clickButtonAndWait(String buttonText) {
        clickButton(buttonText);
        page.waitForLoadingBarToDisplay();
        page.waitForLoadingBarToDisappear();
    }

    @When("I click the {string} button")
    public void clickButton(String buttonText) {
        if (buttonText.equals("Remove") || buttonText.equals("Add")) section = "Remove/add";
        else section = "Enable/disable";
        page.clickButtonByText(buttonText);
    }

    @Then("the opening paragraph text is {string}")
    public void verifyOpeningParagraphText(String expectedText) {
        String actualText = page.getOpeningParagraphText();
        assertEquals(expectedText, actualText);
    }

    @Then("the page is divided into {int} sections")
    public void verifySectionHeaders(int numSections, DataTable dataTable) {
        List<String> expectedHeaders = dataTable.asList();
        List<String> actualHeaders = page.getSectionHeadersText();
        assertEquals(numSections, actualHeaders.size());
        assertEquals(expectedHeaders, actualHeaders);
    }

    @Then("there is a horizontal line between the sections")
    public void verifySectionsDividedByHorizontalLine() {
        //This doesn't actually prove the horizontal line lies between the two sections but for now checking such an
        //element is present is sufficient
        assertEquals(1, page.getNumHorizontalRules());
    }

    @Then("^the (Remove/add|Enable/disable) section contains (\\d+) (checkbox|checkboxes|input control|input controls) and (\\d+) (?:button|buttons)$")
    public void verifySectionControls(String sectionName, int numControls, String controlType, int numButtons) {
        section = sectionName;
        assertEquals(numControls, controlType.startsWith("checkbox") ? page.getNumCheckboxesInSection(sectionName) : page.getNumInputsInSection(sectionName));
        assertEquals(numButtons, page.getNumButtonsInSection(sectionName));

    }

    @Then("the checkbox label is {string}")
    public void verifyCheckboxLabel(String expectedText) {
        String actualText = page.getCheckboxLabel();
        assertEquals(expectedText, actualText);
    }

    @Then("^the checkbox is (checked|unchecked)$")
    public void verifyCheckboxStatus(String expectedStatus) {
        assertEquals(expectedStatus.equals("checked"), page.isCheckboxChecked());
    }

    @Then("the button text is {string}")
    public void verifyButtonText(String expectedText) {
        String actualText = page.getButtonText(section);
        assertEquals(expectedText, actualText);
    }

    @Then("the input control is blank")
    public void verifyInputControlBlank() {
        assertEquals("", page.getInputControlText());
    }

    @Then("^the input control is (enabled|disabled)$")
    public void verifyInputControlStatus(String expectedStatus) {
        assertEquals(expectedStatus.equals("enabled"), page.isInputControlEnabled());
    }

    @Then("a loading bar is displayed")
    public void verifyLoadingBarDisplayed() {
        page.waitForLoadingBarToDisplay();
        assertTrue(page.isLoadingBarDisplayed());
    }

    @Then("the loading bar has a label of {string}")
    public void verifyLoadingBarLabel(String expectedText) {
        page.waitForLoadingBarToDisplay();
        String actualText = page.getLoadingBarLabel();
        assertEquals(expectedText, actualText);
    }

    @Then("after a few seconds the loading bar disappears")
    public void verifyLoadingBarNotDisplayed() {
        page.waitForLoadingBarToDisappear();
        assertFalse(page.isLoadingBarDisplayed());
    }

    @Then("the checkbox is no longer displayed")
    public void verifyCheckboxNotDisplayed() {
        assertEquals(0, page.getNumCheckboxesInSection("Remove/add"));
    }

    @Then("a(n) {string} message is displayed")
    public void verifyMessageText(String expectedText) {
        String actualText = page.getMessageText();
        assertEquals(expectedText, actualText);
    }

    @Then("the checkbox is redisplayed")
    public void verifyCheckboxRedisplayed() {
        assertEquals(1, page.getNumCheckboxesInSection("Remove/add"));
    }
}