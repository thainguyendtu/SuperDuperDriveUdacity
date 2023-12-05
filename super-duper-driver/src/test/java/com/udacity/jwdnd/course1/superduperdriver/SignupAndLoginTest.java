package com.udacity.jwdnd.course1.superduperdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupAndLoginTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void openBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 2);
    }

    @AfterEach
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSignupAndLogin() throws InterruptedException {
        // get localhost signup page
        String baseUrl = "http://localhost:" + port;
        driver.get(baseUrl + "/signup");

        // verify access to signup page
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
        Assertions.assertEquals("Sign Up", driver.getTitle());

        Thread.sleep(2000);

        WebElement firstNameInput = driver.findElement(By.id("inputFirstName"));
        WebElement lastNameInput = driver.findElement(By.id("inputLastName"));
        WebElement usernameInput = driver.findElement(By.id("inputUsername"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword"));
        WebElement submitButton = driver.findElement(By.id("submit-button"));

        String firstNameTestedValue = "Thai";
        String lastNameTestedValue = "Nguyen";
        String usernameTestedValue = "ThaiNT11";
        String passwordTestedValue = "123456";

        Thread.sleep(1000);
        firstNameInput.sendKeys(firstNameTestedValue);
        Thread.sleep(1000);

        lastNameInput.sendKeys(lastNameTestedValue);
        Thread.sleep(1000);

        usernameInput.sendKeys(usernameTestedValue);
        Thread.sleep(2000);

        passwordInput.sendKeys(passwordTestedValue);
        Thread.sleep(2000);

        submitButton.click();
        Thread.sleep(2000);


        // verify access to login page
        webDriverWait.until(ExpectedConditions.titleContains("Login"));
        Assertions.assertEquals("Login", driver.getTitle());

        WebElement usernameLoginInput = driver.findElement(By.id("inputUsername"));
        WebElement passwordLoginInput = driver.findElement(By.id("inputPassword"));
        WebElement submitButtonLogin = driver.findElement(By.id("submit-button"));

        Thread.sleep(1000);

        usernameLoginInput.sendKeys(usernameTestedValue);
        Thread.sleep(1000);

        passwordLoginInput.sendKeys(passwordTestedValue);
        Thread.sleep(1000);

        submitButtonLogin.click();
        Thread.sleep(2000);

        // verify access to home page
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        Assertions.assertEquals("Home", driver.getTitle());

        Thread.sleep(2000);

        // logout
        WebElement logoutBtn = driver.findElement(By.id("btn-logout"));
        logoutBtn.click();

        Thread.sleep(2000);

        // verify redirect to login page after logout
        webDriverWait.until(ExpectedConditions.titleContains("Login"));
        Assertions.assertEquals("Login", driver.getTitle());

        Thread.sleep(2000);

        // get localhost home
        driver.get(baseUrl + "/home");

        Thread.sleep(2000);

        // check login
        Assertions.assertEquals("Login", driver.getTitle());
    }
}
