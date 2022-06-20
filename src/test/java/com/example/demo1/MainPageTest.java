package com.example.demo1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;


public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    static Logger log = LogManager.getLogger();

    @BeforeTest
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://plexusworldwide.com/");

        mainPage = new MainPage(driver);
        log.info("Navigate to https://plexusworldwide.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
    @Test
    public void validateAltAttributeAndURLChanged()  {
        assertEquals(mainPage.plexusLogo.getAttribute("alt"), "Plexus logo");
        log.info("Validate that Plexus Logo contains the 'alt' attribute with the value \"Plexus logo\"");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//div/a[@href=\"/experience-plexus/your-opportunities\"]")));
        js.executeScript("window.scrollBy(0,-150)");
        log.info("Scroll down the page so the element is visible");
        mainPage.buttonYourOportunities.click();
        log.info("Clicked on the button \"Your Opportunities\"");
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "https://plexusworldwide.com/experience-plexus/your-opportunities");
        log.info("Assert that the URL matches with the expected outcome");
    }
}
