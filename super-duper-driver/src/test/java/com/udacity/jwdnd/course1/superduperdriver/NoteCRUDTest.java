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
public class NoteCRUDTest {

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
    private static String noteTitleAddedTestedValue = "New Note Title";
    private static String noteDescriptionAddedTestedValue = "New Note Description";
    private static String noteTitleUpdatedTestedValue = "Note Title Updated";
    private static String noteDescriptionUpdatedTestedValue = "Note Description Updated";

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
    public void testAddUpdateDeleteNote() throws InterruptedException {
        // signup and login
        signup();
        login();

        // verify access to home page
        webDriverWait.until(ExpectedConditions.titleContains("Home"));
        Assertions.assertEquals("Home", driver.getTitle());

        WebElement noteTab = driver.findElement(By.id("nav-notes-tab"));
        WebElement addNoteBtn = driver.findElement(By.id("add-note"));
        WebElement noteTitle = driver.findElement(By.id("note-title"));
        WebElement noteDescription = driver.findElement(By.id("note-description"));
        WebElement noteSubmitBtn = driver.findElement(By.id("submit-note"));

        noteTab.click();
        Thread.sleep(1000);
        addNoteBtn.click();

        Thread.sleep(500);
        noteTitle.sendKeys(noteTitleAddedTestedValue);
        Thread.sleep(500);
        noteDescription.sendKeys(noteDescriptionAddedTestedValue);

        noteSubmitBtn.click();
        Thread.sleep(1000);

        // verify insert note success
        verifyActionSuccess("Insert");

        updateNote();
        Thread.sleep(1000);
        // verify update note success
        verifyActionSuccess("Update");

        deleteNote();
        Thread.sleep(1000);
        // verify delete note success
        verifyActionSuccess("Delete");

    }

    public void updateNote() throws InterruptedException {
        openNotesTab();

        // open updating model
        WebElement updateNoteButton = driver.findElement(By.id("update-note-btn"));
        Thread.sleep(500);
        updateNoteButton.click();

        WebElement noteTitle = driver.findElement(By.id("note-detail-title"));
        WebElement noteDescription = driver.findElement(By.id("note-detail-description"));

        // update new values
        Thread.sleep(1000);
        noteTitle.clear();
        noteTitle.sendKeys(noteTitleUpdatedTestedValue);
        Thread.sleep(500);
        noteDescription.clear();
        noteDescription.sendKeys(noteDescriptionUpdatedTestedValue);

        // click button update
        driver.findElement(By.id("update-note")).click();
    }

    public void deleteNote() throws InterruptedException {
        openNotesTab();

        // open deleting model
        WebElement deleteNoteButton = driver.findElement(By.id("delete-note-btn"));
        Thread.sleep(500);
        deleteNoteButton.click();

        // click button to delete
        WebElement confirmDeleteButton = driver.findElement(By.id("confirm-delete-note"));
        Thread.sleep(500);
        confirmDeleteButton.click();
    }

    /**
     * Open note tab
     */
    private void openNotesTab() {
        driver.get("http://localhost:" + port + "/home");
        WebElement credentialsTab = driver.findElement(By.id("nav-notes-tab"));
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
        Assertions.assertEquals(action + " note success.", statusMessage.getText());
    }
}
