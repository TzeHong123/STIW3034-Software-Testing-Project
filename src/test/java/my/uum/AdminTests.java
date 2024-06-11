package my.uum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminTests {

    private WebDriver driver;

    @Before
    public void setUp() {

        ChromeOptions chromeoption=new ChromeOptions();
        chromeoption.addArguments("--remote-allow-origins=*");
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium driver\\Chrome driver\\chromedriver-win64\\chromedriver.exe"); // Adjust this path
        driver = new ChromeDriver(chromeoption);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void testUpdateJournalStatus() throws InterruptedException {
        System.out.println("Starting testUpdateJournalStatus...");

        // Navigate to the login page
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");
        System.out.println("Navigated to login page");

        //Add a sleep to ensure the browser has enough time to load the page
        try {
            Thread.sleep(2000); // Sleep for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Login
        login();
        Thread.sleep(2000);
        navigateToPapersTab();

        // Select journal to update
        Thread.sleep(2000);
        WebElement detailsLink = driver.findElement(By.xpath("//a[contains(text(),'Details')]"));
        detailsLink.click();

        // Update status to "Under Review"
        Thread.sleep(2000);
        WebElement statusDropdown = driver.findElement(By.name("status"));
        statusDropdown.sendKeys("Under Review");

        // Click update button
        Thread.sleep(2000);
        WebElement updateButton = driver.findElement(By.xpath("//button[contains(text(),'Update')]"));
        updateButton.click();

        // Verify status updated
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.className("success-message"));
        assertTrue(successMessage.isDisplayed());

        WebElement updatedStatus = driver.findElement(By.xpath("//span[contains(text(),'Under Review')]"));
        assertEquals("Under Review", updatedStatus.getText());

        System.out.println("testUpdateJournalStatus completed successfully.");
    }

    @Test
    public void testPublishJournal() throws InterruptedException {
        System.out.println("Starting testPublishJournal...");

        // Navigate to the login page
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");
        System.out.println("Navigated to login page");

        // Login
        login();
        navigateToPapersTab();

        // Select journal to update
        Thread.sleep(2000);
        WebElement detailsLink = driver.findElement(By.xpath("//a[contains(text(),'Details')]"));
        detailsLink.click();

        // Enter publication details
        Thread.sleep(2000);
        WebElement doiField = driver.findElement(By.name("doi"));
        doiField.sendKeys("10.1234/sandbox.2024");

        Thread.sleep(2000);
        WebElement pageNoField = driver.findElement(By.name("pageNo"));
        pageNoField.sendKeys("123-130");

        Thread.sleep(2000);
        WebElement uploadButton = driver.findElement(By.name("fileUpload"));
        uploadButton.sendKeys("/path/to/final-paper.pdf"); // Adjust path

        // Click submit button
        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submitButton.click();

        // Verify publication details updated
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.className("success-message"));
        assertTrue(successMessage.isDisplayed());

        System.out.println("testPublishJournal completed successfully.");
    }

    @Test
    public void testUpdatePaymentStatus() throws InterruptedException {
        System.out.println("Starting testUpdatePaymentStatus...");

        // Navigate to the login page
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");
        System.out.println("Navigated to login page");

        // Login
        login();
        navigateToPaymentsTab();

        // Select payment to update
        Thread.sleep(2000);
        WebElement folderIcon = driver.findElement(By.xpath("//i[contains(@class, 'folder-icon')]"));
        folderIcon.click();

        // Download payment receipt
        Thread.sleep(2000);
        WebElement downloadButton = driver.findElement(By.xpath("//button[contains(text(),'Download')]"));
        downloadButton.click();

        // Update payment status to "Submitted"
        Thread.sleep(2000);
        WebElement statusDropdown = driver.findElement(By.name("status"));
        statusDropdown.sendKeys("Submitted");

        // Insert payment remarks
        Thread.sleep(2000);
        WebElement remarksField = driver.findElement(By.name("remarks"));
        remarksField.sendKeys("Payment received");

        // Click update button
        Thread.sleep(2000);
        WebElement updateButton = driver.findElement(By.xpath("//button[contains(text(),'Update')]"));
        updateButton.click();

        // Verify payment status updated
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.className("success-message"));
        assertTrue(successMessage.isDisplayed());

        System.out.println("testUpdatePaymentStatus completed successfully.");
    }

    @Test
    public void testChangeConferenceTitle() throws InterruptedException {
        System.out.println("Starting testChangeConferenceTitle...");

        // Navigate to the login page
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");
        System.out.println("Navigated to login page");

        // Login
        login();
        navigateToSettingsTab();

        // Change conference title
        Thread.sleep(2000);
        WebElement changeButton = driver.findElement(By.xpath("//button[contains(text(),'Change')]"));
        changeButton.click();

        Thread.sleep(2000);
        WebElement titleField = driver.findElement(By.name("conferenceTitle"));
        titleField.clear();
        titleField.sendKeys("New Conference Title");

        // Click submit button
        Thread.sleep(2000);
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
        submitButton.click();

        // Verify conference title updated
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.className("success-message"));
        assertTrue(successMessage.isDisplayed());

        System.out.println("testChangeConferenceTitle completed successfully.");
    }

    @Test
    public void testVerifyReviewerCV() throws InterruptedException {
        System.out.println("Starting testVerifyReviewerCV...");

        // Navigate to the login page
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");
        System.out.println("Navigated to login page");

        // Login
        login();
        navigateToReviewersTab();

        // Verify reviewer CV
        Thread.sleep(2000);
        WebElement downloadCVButton = driver.findElement(By.xpath("//button[contains(text(),'Download CV')]"));
        downloadCVButton.click();

        Thread.sleep(2000);
        WebElement verifyButton = driver.findElement(By.xpath("//button[contains(text(),'Verify')]"));
        verifyButton.click();

        // Verify CV status
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.className("success-message"));
        assertTrue(successMessage.isDisplayed());

        System.out.println("testVerifyReviewerCV completed successfully.");
    }

    private void login() throws InterruptedException {
        System.out.println("Starting login...");

        // Perform login
        driver.findElement(By.name("email")).sendKeys("sandbox@soc-conferences.com");
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("Abc123");
        Thread.sleep(2000);

        // Find the drop-down element by its name attribute
        WebElement conferenceDropdown = driver.findElement(By.name("conf"));
        Thread.sleep(2000);
        // Create a Select object to interact with the drop-down
        Select selectConference = new Select(conferenceDropdown);
        Thread.sleep(2000);
        // Select "SANDBOX24" from the drop-down
        selectConference.selectByVisibleText("SANDBOX24");
        Thread.sleep(2000);

        // Click to login
        driver.findElement(By.name("submit")).click();
        Thread.sleep(2000);
        // Wait and handle the alert if it appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Thread.sleep(2000);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            assertEquals("Login Successful", alertText);
            alert.accept();
        } catch (Exception e) {
            // Alert not present, continue
        }

        // Validate the title of the page after login
        wait.until(ExpectedConditions.titleContains("SOC Conferences - Admin"));
        String title = driver.getTitle();
        assertEquals("SOC Conferences - Admin", title);
    }

    private void navigateToPapersTab() throws InterruptedException {
        System.out.println("Navigating to Papers tab...");

        Thread.sleep(2000);
        WebElement papersTab = driver.findElement(By.linkText("Papers"));
        papersTab.click();

        System.out.println("Navigated to Papers tab.");
    }

    private void navigateToPaymentsTab() throws InterruptedException {
        System.out.println("Navigating to Payments tab...");

        Thread.sleep(2000);
        WebElement paymentsTab = driver.findElement(By.linkText("Payments"));
        paymentsTab.click();

        System.out.println("Navigated to Payments tab.");
    }

    private void navigateToSettingsTab() throws InterruptedException {
        System.out.println("Navigating to Settings tab...");

        Thread.sleep(2000);
        WebElement settingsTab = driver.findElement(By.linkText("Settings"));
        settingsTab.click();

        System.out.println("Navigated to Settings tab.");
    }

    private void navigateToReviewersTab() throws InterruptedException {
        System.out.println("Navigating to Reviewers tab...");

        Thread.sleep(2000);
        WebElement reviewersTab = driver.findElement(By.linkText("Reviewers"));
        reviewersTab.click();

        System.out.println("Navigated to Reviewers tab.");
    }
}
