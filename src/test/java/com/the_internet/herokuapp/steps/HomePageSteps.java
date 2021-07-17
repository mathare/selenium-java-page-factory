package com.the_internet.herokuapp.steps;

import com.the_internet.herokuapp.pages.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class HomePageSteps {

    private final HomePage page = new HomePage();

    @When("I click on the {string} link")
    public void clickOnPageLink(String link) {
        page.clickOnPageLink(link);
    }

    @Then("the sub-header text is {string}")
    public void verifySubheaderText(String expectedText) {
        String actual = page.getSubheaderText();
        assertEquals(expectedText, actual);
    }

    @Then("a list of the following sub-pages is displayed")
    public void verifyListSubPages(DataTable dt) {
        List<String> subPageNames = dt.asList();
        assertEquals(subPageNames.size(), page.getNumSubPages());
        assertEquals(subPageNames, page.getSubPageNames());
    }

}
