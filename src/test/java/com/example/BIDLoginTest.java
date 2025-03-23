package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BIDLoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // Auto-manage ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the BID.ai login page
        driver.get("https://www.bidai.in/login");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        // Locate the phone number input field
        WebElement phoneNumberBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//input[@placeholder='Enter 10-digit mobile number']")));
        assertTrue(phoneNumberBox.isDisplayed(), "Phone number input box is not visible");

        // Enter valid phone number
        phoneNumberBox.sendKeys("6299134440");

        // Click "Send OTP" button
        WebElement sendOtpButton = driver.findElement(By.id("//button[normalize-space()='Send OTP']"));
        sendOtpButton.click();

        // Verify if OTP process starts
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("//button[normalize-space()='Verify OTP']")));
        assertTrue(successMessage.getText().contains("OTP sent"), "OTP success message not found!");
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}



