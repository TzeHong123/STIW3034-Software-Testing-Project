package my.uum;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AdminLoginTest {
    private WebDriver driver;

    @Before
    public void setUp() {

        ChromeOptions chromeoption=new ChromeOptions();
        chromeoption.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:/Selenium driver/Chrome driver/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver(chromeoption);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void testLogin() {
        // Navigate to the admin login page
        driver.get("https://sandbox.soc-conferences.com/admin/login.php");

        // Perform login
        driver.findElement(By.name("email")).sendKeys("sandbox@soc-conferences.com");
        driver.findElement(By.name("password")).sendKeys("Abc123");

        // Find the drop-down element by its name attribute
        WebElement conferenceDropdown = driver.findElement(By.name("conf"));

        // Create a Select object to interact with the drop-down
        Select selectConference = new Select(conferenceDropdown);

        // Select "SANDBOX24" from the drop-down
        selectConference.selectByVisibleText("SANDBOX24");

        // Interact with the checkbox
        WebElement checkBoxElement = driver.findElement(By.name("remember"));
        checkBoxElement.click();

        // Click to login
        driver.findElement(By.name("submit")).click();

        // Wait and handle the alert if it appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
