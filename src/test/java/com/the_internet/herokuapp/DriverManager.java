package com.the_internet.herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static final boolean headless = System.getProperty("headless", "true").equals("true");

    public static WebDriver getDriver() {
        WebDriver driver;
        switch (System.getProperty("browser", "chrome").toLowerCase()) {
            case "edge":
                driver = SystemUtils.IS_OS_WINDOWS ? getEdgeDriver() : getChromeDriver();
                break;
            case "firefox":
                driver = getFirefoxDriver();
                break;
            case "safari":
                driver = SystemUtils.IS_OS_MAC ? getSafariDriver() : getChromeDriver();
                break;
            default:
                driver = getChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        return driver;
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(headless);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        return new ChromeDriver(options);
    }

    //To be made headless
    private static WebDriver getEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private static WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(headless);
        return new FirefoxDriver(options);
    }

    //Don't think Safari can be made headless
    private static WebDriver getSafariDriver() {
        return new SafariDriver();
    }
}
