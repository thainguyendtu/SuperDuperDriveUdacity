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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialCRUDTest {

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

    private static String firstNameTestedValue = "Thai";
    private static String lastNameTestedValue = "Nguyen";
    private static String usernameTestedValue = "ThaiNT11";
    private static String paswordTestedValue = "123456";
    private static String credentialURLAddedTestedValue = "http://addnew.com";
    private static String credentialUsernameAddedTestedValue = "usernameTest";
    private static String credentialPasswordAddedTestedValue = "123456";
    private static String credentialURLUpdatedTestedValue = "http://update.com";
    private static String credentialUsernameUpdatedTestedValue = "usernameUpdated";
    private static String credentialPasswordUpdatedTestedValue = "123456updated";

    private void signup() throws InterruptedException {
        String baseUrl = "http://localhost:" + port;
        driver.get(baseUrl + "/signup");
        Thread.sleep(2000);

        WebElement firstNameInput = driver.findElement(By.id("inputFirstName"));
        WebElement lastNameInput = driver.findElement(By.id("inputLastName"));
        WebElement usernameInput = driver.findElement(By.id("inputUsername"));
        WebElement passwordInput = driver.findElement(By.id("inputPassword"));
        WebElement submitButton = driver.findElement(By.id("submit-button"));

        Thread.sleep(1000);
        firstNameInput.sendKeys(firstNameTestedValue);

        Thread.sleep(500);
        lastNameInput.sendKeys(lastNameTestedValue);

        Thread.sleep(500);
        usernameInput.sendKeys(usernameTestedValue);

        Thread.sleep(500);
        passwordInput.sendKeys(paswordTestedValue);

        submitButton.click();
        Thread.sleep(1000);
    }

    private void login() throws InterruptedException {

        WebElement usernameLoginInput = driver.findElement(By.id("inputUsername"));
        WebElement passwordLoginInput = driver.findElement(By.id("inputPassword"));
        WebElement submitButtonLogin = driver.findElement(By.id("submit-button"));

        Thread.sleep(1000);
        usernameLoginInput.sendKeys(usernameTestedValue);

        Thread.sleep(500);
        passwordLoginInput.sendKeys(paswordTestedValue);

        submitButtonLogin.click();
        Thread.sleep(1000);
    }

    @Test
    public void testAddUpdateDeleteCredential() throws InterruptedException {
        // signup and login
        signup();
        login();

        // verify access to home page
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        Assertions.assertEquals("Home", driver.getTitle());

        WebElement credentialTab = driver.findElement(By.id("nav-credentials-tab"));
        WebElement addCredentialBtn = driver.findElement(By.id("add-credential"));
        WebElement credentialUrl = driver.findElement(By.id("credential-url"));
        WebElement credentialUsername = driver.findElement(By.id("credential-username"));
        WebElement credentialPassword = driver.findElement(By.id("credential-password"));
        WebElement credentialSubmitBtn = driver.findElement(By.id("submit-credential"));

        credentialTab.click();
        Thread.sleep(1000);
        addCredentialBtn.click();

        Thread.sleep(500);
        credentialUrl.sendKeys(credentialURLAddedTestedValue);
        Thread.sleep(500);
        credentialUsername.sendKeys(credentialUsernameAddedTestedValue);
        Thread.sleep(500);
        credentialPassword.sendKeys(credentialPasswordAddedTestedValue);

        credentialSubmitBtn.click();
        Thread.sleep(1000);

        // verify insert credential success
        verifyActionSuccess("Insert");

        updateCredential();
        Thread.sleep(1000);
        // verify update credential success
        verifyActionSuccess("Update");

        deleteCredential();
        Thread.sleep(1000);
        // verify delete credential success
        verifyActionSuccess("Delete");

    }

    public void updateCredential() throws InterruptedException {
        openCredentialsTab();

        // open updating model
        WebElement updateCredentialButton = driver.findElement(By.id("update-credential-btn"));
        Thread.sleep(500);
        updateCredentialButton.click();

        WebElement credentialURL = driver.findElement(By.id("credential-detail-url"));
        WebElement credentialUsername = driver.findElement(By.id("credential-detail-username"));
        WebElement credentialPassword = driver.findElement(By.id("credential-detail-password"));

        // update new values
        Thread.sleep(1000);
        credentialURL.clear();
        credentialURL.sendKeys(credentialURLUpdatedTestedValue);
        Thread.sleep(500);
        credentialUsername.clear();
        credentialUsername.sendKeys(credentialUsernameUpdatedTestedValue);
        Thread.sleep(500);
        credentialPassword.clear();
        credentialPassword.sendKeys(credentialPasswordUpdatedTestedValue);

        // click button update
        driver.findElement(By.id("update-credential")).click();
    }

    public void deleteCredential() throws InterruptedException {
        openCredentialsTab();

        // open deleting model
        WebElement deleteCredentialButton = driver.findElement(By.id("delete-credential-btn"));
        Thread.sleep(500);
        deleteCredentialButton.click();

        // click button to delete
        WebElement confirmDeleteButton = driver.findElement(By.id("confirm-delete-credential"));
        Thread.sleep(500);
        confirmDeleteButton.click();
    }

    /**
     * Open note tab
     */
    private void openCredentialsTab() {
        driver.get("http://localhost:" + port + "/home");
        WebElement credentialsTab = driver.findElement(By.id("nav-credentials-tab"));
        credentialsTab.click();
        webDriverWait.until(ExpectedConditions.attributeContains(credentialsTab, "class", "active"));
    }

    /**
     * Verify CRUD actions success
     * @param action
     */
    private void verifyActionSuccess(String action) {
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        Assertions.assertEquals("Home", driver.getTitle());
        WebElement statusMessage = driver.findElement(By.id("success"));
        Assertions.assertEquals(action + " credential success.", statusMessage.getText());
    }
}
