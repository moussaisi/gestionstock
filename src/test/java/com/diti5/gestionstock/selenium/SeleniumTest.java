package com.diti5.gestionstock.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class SeleniumTest {
    @Test
    public void testConnexion(){
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application");
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8989/");
        driver.manage().window().maximize();

        WebElement openPopUp1 = driver.findElement(By.id("openPopUp1"));
        WebElement openPopUp2 = driver.findElement(By.id("login"));
        openPopUp1.click();
        openPopUp2.click();

        //WebElement openPopUp = driver.findElement(By.id("sing"));
        //openPopUp.click();

        WebElement connexionBtn = driver.findElement(By.id("submit"));

        WebElement login = driver.findElement(By.id("username"));
        login.sendKeys("admin");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("admin");

        connexionBtn.click();

        TakesScreenshot screenshot = (TakesScreenshot)driver;

        File source = screenshot.getScreenshotAs(OutputType.FILE);




    }
}
