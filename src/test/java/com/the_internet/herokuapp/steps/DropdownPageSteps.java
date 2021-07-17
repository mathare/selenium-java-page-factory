package com.the_internet.herokuapp.steps;

import com.the_internet.herokuapp.pages.DropdownPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DropdownPageSteps {

    private final DropdownPage page = new DropdownPage();

    @When("I select {string} from the dropdown list")
    public void selectDropdownOption(String option) {
        page.selectDropdownOption(option);
    }

    @When("^I select the (\\d+).{2} option from the dropdown list$")
    public void selectDropdownOption(int index) {
        page.selectDropdownOption(index);
    }

    @Then("the page contains {int} dropdown list control")
    public void verifyNumDropdownListControls(int expectedNum) {
        assertEquals(expectedNum, page.getNumDropdownLists());
    }

    @Then("the dropdown defaults to {string}")
    @Then("the dropdown value is {string}")
    public void verifyDropdownValue(String expected) {
        assertEquals(expected, page.getCurrentDropdownValue());
    }

    @Then("the dropdown contains the following options")
    public void verifyDropdownListOptions(DataTable dataTable) {
        List<String> expectedOptions = dataTable.asList();
        assertEquals(expectedOptions, page.getAllDropdownOptions());
    }

    @Then("the dropdown only support selection of a single option at a time")
    public void verifyDropdownSupportsSingleSelectionOnly() {
        assertFalse(page.getDropdownMultiSelectionSupported());
    }
}