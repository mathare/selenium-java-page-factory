# UI Testing with Selenium Java & Junit4 - Page Factory

## Overview
This project provides an example for testing a UI with Selenium WebDriver, written in Java with JUnit4, using the Page Factory design pattern and driven via Cucumber BDD feature files. It can be used to kickstart testing of other UIs with minimal changes to the project.

NB This is not a complete implementation of a Selenium test suite for the target UI. It is an example of how to structure a Selenium test suite in Java but only a subset of the possible tests have been added.

## Why Use Selenium?
Selenium is an open-source framework for testing web applications that is probably de facto the framework most people think of when it comes to UI testing. It supports a multitude of browsers (Chrome, Firefox, Safari, IE/Edge) as well as all major languages (there are bindings for Java, Python, JavaScript, C# and Ruby) making it suitable for almost any UI testing project. It is also highly portable so works across Windows, macOS and Linux/Unix and of course being open-source is freeware. There is an extensive and active Selenium community offering support for users and helping to extend and develop Selenium, which is always a bonus.

## Web Application Under Test
The website that is being tested by this framework is “[the-internet](http://the-internet.herokuapp.com/)”, a third-party application that contains a number of different pages, each showcasing a different aspect of UI testing, and the challenges one can face when implementing such testing. It is often flagged up as an excellent candidate website for practicing automated testing, which is why I have chosen to use it here. It is simple to understand and get started with while still offering a realistic challenge.

Only a subset of the available functionality has been tested, focusing on the following pages:
* [home/main](http://the-internet.herokuapp.com/) page
* [Checkboxes](http://the-internet.herokuapp.com/checkboxes)
* [Dropdown](http://the-internet.herokuapp.com/dropdown)
* [Dynamic Controls](http://the-internet.herokuapp.com/dynamic_controls)
* [Form Authentication](http://the-internet.herokuapp.com/login)
  That is sufficient to provide a demonstration of the main interaction and verification methods in Selenium while keeping the project size small enough to act as a template.

## Test Framework
As stated above, this project contains a Selenium Java test framework, implements the Page Factory design pattern and utilises Cucumber BDD. As such, it follows test automation best practices. The Page Factory pattern means that each individual webpage has its own class, each containing the methods specific to controls on that page. Thus, each page is independent and separate from the tests, meaning any changes to the page are isolated to only the corresponding page class. This makes for code that is cleaner, easier to read and maintain, and contains less duplication. The use of Cucumber means the tests themselves are also clean and clear, written in plain English, so they can be understood by anyone working with the project, including non-technical roles. Although this project is just an example of how to set up Selenium for UI testing in Java, in a real-life project the use of BDD is essential for collaboration between QAs, developers, and business roles (e.g. Product Owners, Business Analysts etc). Quality is everyone’s responsibility, which means the tests themselves need to be easily understood by all stakeholders.

### Tech stack
As this is a Java project, build and dependency management is handled by Maven, so there is a `pom.xml` file defining the versions of the dependencies:
* Java v11
* Selenium v3.141.59
* JUnit v4.13.2
* Cucumber v6.10.4
* WebDriverManager v4.4.3

The code is written in Java and built using v11 of the JDK. There are more up-to-date JDK versions available  - Oracle is up to Java 16.0.1 at the time of writing. However, I used Amazon Coretto 11 (the latest LTS release of this popular OpenJDK build) as it is the distribution I am most used to.

The Selenium version is from November 2018 but is the latest Selenium v3 release and also the latest production release (all the v4 releases are currently alpha or beta).

I have chosen to use the latest JUnit 4 release rather than JUnit 5 (also known as JUnit Jupiter) for easier integration with Cucumber (which is built primarily around JUnit 4).

The Cucumber version is the latest version at the time of writing (released May 2021).

WebDriverManager is a third-party extension for Selenium that makes it easier to manage the drivers for multiple browsers, making cross-browser testing simpler. It is an extension I commonly use and here I have pulled in the latest version (from May 2021).

### Project Structure
The project uses a standard structure and naming convention, incorporating the URL of the website under test, i.e. the test code is all stored under `src/test/java/com/the_internet/herokuapp`. Below that we have:
* `features`  - this folder contains the Cucumber `.feature` files, one per website page. By separating out the tests for each page into separate feature files we continue the Page Factory theme of page independence and make it easier to extend the framework in the future. Each feature file is named after the page it tests, e.g. DropdownPage.feature contains the tests for the Dropdown page.
* `pages` - the Page Factory implementation of the individual website pages, one class file per page. Each class is named after the corresponding page e.g. `HomePage.java`, `CheckboxesPage.java` etc. There is also an abstract `BasePage.java` which the other pages implement.
* `steps` - a collection of classes containing the implementation of the steps from the Cucumber feature files. As above, there is one steps class per page and each is named after the page under test, e.g. `FormAuthenticationPageSteps.java`. There is an additional `CommonSteps.java` class containing several steps that are, as the name suggests, used by more than one feature file. This avoids the need to duplicate code across individual steps classes.
* `DriverManager.java` - set up the driver, based on the selected browser, using the [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) extension.
* `TestRunner.java` - the main JUnit test runner class, decorated with the annotation required to run Cucumber tests. The class itself is empty but the `CucumberOptions` annotation defines the location of the features and associated steps.

### Page Factory Classes
As noted above, the `pages` folder contains the relevant Page Factory classes for each tested page. Each page class, including the abstract `BasePage` class, follows the same pattern:
* `WebElement` variables decorated with the `@FindBy` annotation parameterised by selector, e.g. `@FindBy(id = "page-footer")` (NB the `List<WebElement>` variable type is used when the selector is expected to match multiple elements)
* a class constructor to initialise the above Page Factory elements
* public interaction methods e.g. to click on an element, get an element’s text etc. using the above `WebElement` variables directly
  
This way we encapsulate the web elements themselves, only allowing the interactions that have been implemented via public methods, ensuring the tests (in effect, the user) can only interact with the web page in known ways.

The abstract `BasePage` class defines constants for the URL for each page, and the WebDriver that is key to automatically interacting with the browser, ensuring they are available to methods within each individual page class. Public interaction methods for the page header and footer are declared in the `BasePage` class too, to reduce code duplication. In order to get round the fact that the title element on the tested pages doesn't use a consistent HTML tag or class, a public abstract `getPageTitleText` method is declared in the `BasePage` class ensuring that each individual page class implements a method to get the text of the page title, using a selector specific to whatever to that page’s HTML uses for the title.

Note there are no assertions in the page classes as assertions are a test action rather than a page action. Page interaction methods will return the result of that interaction, such as the attribute or text value, to the calling method in the test steps classes, so that it can be asserted as correct there. In other words, we maintain independence between the tests and the pages.

**Disclaimer:** I am not a big fan of the Page Factory pattern (I prefer to use Page Object Model) and don't have a lot of experience with it so there may be ways to improve this implementation. For example, in the `DynamicControlsPage` class I have added separate Page Factory variables for buttons on the Remove/Add (checkbox) form, and the Enable/Disable (input) form in order to use the Page Factory pattern for all page controls. In the Page Object Model I would daisy-chain `findElement` calls - first getting the form element, then getting the button on that form. I don't know of a way to do this in the Page Factory element initialisation, or if it is actually possible. Similarly, for the four buttons ("Remove", "Add", "Enable" & "Disable") I have four separate WebElement variables here rather than the parameterised selector I would use in a Page Object Model implementation. One could use a hybrid Page Object Model-Page Factory class to combine the best of both patterns but in that case I would revert to just using the Page Object Model. 

### Supported Browsers
The `DriverManager` class uses the WebDriverManager dependency to manage the various browser drivers. The `getDriver` method returns the relevant WebDriver instance for the chosen browser, with support for:
* Chrome - the default option
* Firefox
* Edge
* Safari - as this code was written on a Windows OS this option is currently untested but has been included for potential uses on Macs

The browser to be used can be passed in via a Java system property with a key of `browser`, defaulting to Chrome if no such property is specified. Note there is some fallback if the browser selection is incompatible with the operating system - specifying Edge on a Mac or Safari on Windows will result in the browser selection reverting to the default i.e. Chrome.

Another Java system property, `headless`, is used to determine whether the browser should run in headless mode. Currently, this is only supported for Chrome and Firefox. Headless browsers are generally faster and consume fewer resources as they don’t actually render the webpages so are preferred when running automated UI tests. However, when debugging UI tests it is often easier to set this flag to false i.e. run “headed” so that issues with the tests can be more easily identified. `headless` is true by default.

`DriverManager` also sets the position and size of the browser window, ensuring consistency between tests and on different environments, and the implicit wait time i.e. the time Selenium will wait for an element to be displayed.

### Running tests
The tests are easy to run as they are bound to the Maven `test` goal so running the tests is as simple as executing `mvn test` within the directory in which the repo has been cloned. The `browser` and `headless` properties can also be specified on the command line, e.g. `mvn test -Dbrowser=firefox -Dheadless=false` will run the tests in normal/headed Firefox.

Alternatively, the empty `TestRunner` class can be executed using a JUnit runner within an IDE.

NB Each test opens up in a separate browser instance (which is closed at the end of the test) so is not the fastest way to run a test suite, but it is the right way as we should ensure that tests are wholly independent of one another, do not share state and can run in any order. Cucumber has no `BeforeAll` and `AfterAll` hooks, so we can’t open a single browser at the start of the test suite, navigate to the relevant page in the setup for each individual test scenario and close the browser at the end of the test suite. There are ways round this by using JUnit annotations and Maven phases but nothing that works consistently when tests may be via the IDE as well as from the command line (or CI pipelines). Having a separate browser per test also allows for test parallelisation which wouldn't otherwise be possible.

#### Test Reports
A report is generated for each test run, using the Cucumber `pretty` plugin to produce an HTML report called `cucumber-report.html` in the `target` folder. This is a simple report showing a summary of the test run (number of tests run, number passed/failed/skipped, duration, environment etc) above a breakdown of each individual feature file, showing the status if each scenario and the individual steps within that scenario. If a scenario fails, a screenshot is taken and embedded into the test report to aid investigation into the failing test.

### CI Pipeline
This repo contains a CI pipeline implemented using [GitHub Actions](https://github.com/features/actions). Any push to the `master` branch or any pull request on the `master` branch will trigger the pipeline, which runs in a Linux VM on the cloud within GitHub. The pipeline consists of two separate jobs which run in parallel:
* `run-tests-on-chrome`
* `run-tests-on-firefox`

Each job checks out the repo then runs the test suite on Chrome/Firefox via `mvn test -Dbrowser=chrome` or `mvn test -Dbrowser=firefox`. If a test fails, that job will upload the test report as a GitHub artifact. At the end of the steps the environment tears itself down and produces a [status report](https://github.com/mathare/selenium-java-page-factory/actions).

In addition to the automated triggers above, the CI pipeline has a manual trigger actionable by clicking "Run workflow" on the [Continuous Integration](https://github.com/mathare/selenium-java-page-factory/actions/workflows/ci.yml) page. This allows the user to select the branch to run the pipeline on, so tests can be run on a branch without the need for a pull request. This option is only visible if you are the repo owner.