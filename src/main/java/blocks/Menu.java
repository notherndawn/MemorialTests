package blocks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Menu {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='menucont']/div[1]")
    private WebElement menuFind;
    @FindBy(xpath = "//div[@class='menucont']/div[2]")
    private WebElement menuInstructions;
    @FindBy(xpath = "//div[@class='menucont']/div[3]")
    private WebElement menuFeedback;
    @FindBy(xpath = "//div[@class='menucont']/div[4]")
    private WebElement menuAbout;

    @FindBy(xpath = "//div[text()='Поиск']")
    private WebElement searchTab;
    @FindBy(xpath = "//div[text()='Расширенный поиск']")
    private WebElement advancedSearchTab;

    public Menu(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String getClassByMenuFind () {
        String classByMenuFind = menuFind.getAttribute("class");
        return classByMenuFind;
    }

    public String getClassBySearchTab () {
        String classBySearchTab = searchTab.getAttribute("class");
        return classBySearchTab;
    }

    public String getClassByAdvancedSearchTab () {
        String classBySearchTab = advancedSearchTab.getAttribute("class");
        return classBySearchTab;
    }

    public void searchTabClick() { searchTab.click(); }
    public void advancedSearchTabClick() { advancedSearchTab.click(); }
    public void menuFindTabClick() { menuFind.click(); }

}
