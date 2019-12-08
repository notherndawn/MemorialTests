package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;

    @BeforeTest
    public static void setUp() {
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver0250");
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriverMac78_0_3904_105");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public static void tearDown() {
        if(driver != null){
        driver.quit();
        }
    }
}
