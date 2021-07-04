package com.the_internet.herokuapp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/the_internet/herokuapp/features",
        glue = "com.the_internet.herokuapp.steps",
        tags = "not @skip",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        publish = true
)

public class TestRunner {
}
