package com.the_internet.herokuapp.steps;

import com.the_internet.herokuapp.pages.CheckboxesPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class CheckboxesPageSteps {

    private final CheckboxesPage page = new CheckboxesPage();

    @When("^I click on the (\\d).{2} checkbox")
    public void clickOnCheckbox(int index) {
        page.clickOnCheckbox(index - 1);
    }

    @Then("the page contains {int} checkboxes")
    public void verifyNumCheckboxes(int expected) {
        assertEquals(expected, page.getNumCheckboxes());
    }

    @Then("the checkbox labels are")
    public void verifyCheckboxLabels(DataTable dataTable) {
        List<String> expected = dataTable.asList();
        assertEquals(expected, page.getAllCheckboxLabels());
    }

    @Then("^the (\\d).{2} checkbox is (checked|unchecked)$")
    public void verifyCheckboxState(int index, String state) {
        boolean checkedState = page.getCheckboxCheckedState(index - 1);
        if (state.equals("checked")) {
            assertTrue(checkedState);
        } else {
            assertFalse(checkedState);
        }
    }
}
