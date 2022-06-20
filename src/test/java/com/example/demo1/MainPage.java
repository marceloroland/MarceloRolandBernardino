package com.example.demo1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    @FindBy(xpath = "//a/img[@alt=\"Plexus logo\"]")
    public WebElement plexusLogo;

    @FindBy(xpath = "//div/a[@href=\"/experience-plexus/your-opportunities\"]")
    public WebElement buttonYourOportunities;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
