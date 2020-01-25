package main.java.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class FindPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(className = "logo")
    private WebElement logo;
    @FindBy(id = "findBlock")
    private WebElement findBlock;

    @FindBy(id = "surname")
    private WebElement surname;
    @FindBy(xpath = "//label[text()='Фамилия']")
    private WebElement surnameLabel;
    @FindBy(id = "name")
    private WebElement name;
    @FindBy(xpath = "//label[text()='Имя']")
    private WebElement nameLabel;
    @FindBy(id = "patronymic")
    private WebElement patronymic;
    @FindBy(xpath = "//label[text()='Отчество']")
    private WebElement patronymicLabel;
    @FindBy(id = "birthdate")
    private WebElement birthdate;
    @FindBy(xpath = "//label[text()='Дата рождения']")
    private WebElement birthdateLabel;
    @FindBy(id = "burialplace")
    private WebElement burialPlace;
    @FindBy(xpath = "//label[text()='Место захоронения']")
    private  WebElement burialPlaceLabel;
    @FindBy(xpath = "//label[text()='Воинское звание']")
    private  WebElement rankLabel;

    @FindBy(id = "birthplace")
    private WebElement birthPlace;
    @FindBy(id = "rank")
    private WebElement rank;
    @FindBy(id = "serviceplace")
    private WebElement servicePlace;
    @FindBy(id = "draftplace")
    private WebElement draftPlace;
    @FindBy(id = "retirementsearch")
    private WebElement retirementSearch;

    @FindBy(xpath = "//button/span[text()='Искать']")
    private WebElement findButton;
    @FindBy(css = ".goBackButton")
    private WebElement goBackButton;

    @FindBy(id = "autocomplete")
    private WebElement burialCombobox;

    public FindPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://vz.lenreg.ru");
    }

    public void findButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(findButton));
        findButton.click();

        if (!displayedOfGoBackButton()){
            findButton.click();
        }
        wait.until(ExpectedConditions.visibilityOf(goBackButton));
    }

    public boolean displayedOfGoBackButton(){
        try {
            if(goBackButton.isEnabled()){
                return true;
            } else{
                return false;
            }
        }
        catch (NoSuchElementException e) { return false; }
    }

    public void goBackButtonClick() {
        wait.until(ExpectedConditions.elementToBeClickable(goBackButton));
        goBackButton.click();
        wait.until(ExpectedConditions.visibilityOf(findButton));
    }

    public void surnameClick () {
        surname.click();
    }

    public void nameClick () {
        name.click();
    }

    public void patronymicClick () {
        patronymic.click();
    }

    public void birthdateClick () {
        birthdate.click();
    }

    public void burialPlaceClick () {
        burialPlace.click();
    }
    public void rankClick () {
        rank.click();
    }

    public boolean getDataShrink(WebElement label){
        if (label.getAttribute("data-shrink").equals("true")){
            return true;
        }else return false;
    }

    public void surnameSendKeys (String text){ surname.sendKeys(text); }
    public void nameSendKeys (String text){ name.sendKeys(text); }
    public void patronymicSendKeys (String text){ patronymic.sendKeys(text); }
    public void birthdateSendKeys (String text){ birthdate.sendKeys(text); }
    public void burialPlaceSendKeys (String text){ burialPlace.sendKeys(text); }
    public void rankSendKeys (String text){ rank.sendKeys(text); }

    public void surnameClear(){ surname.clear(); }
    public void nameClear (){ name.clear(); }
    public void patronymicClear (){ patronymic.clear(); }
    public void birthdateClear (){ birthdate.clear(); }
    public void burialPlaceClear (){ burialPlace.clear(); }
    public void rankClear (){ rank.clear(); }

    public boolean getSurnameDataShrink (){ return getDataShrink(surnameLabel); }
    public boolean getNameDataShrink (){ return getDataShrink(nameLabel); }
    public boolean getPatronymicDataShrink (){ return getDataShrink(patronymicLabel); }
    public boolean getBirthdateDataShrink (){ return getDataShrink(birthdateLabel); }
    public boolean getBurialPlaceDataShrink (){ return getDataShrink(burialPlaceLabel); }
    public boolean getRankDataShrink (){ return getDataShrink(rankLabel); }

    public List<String> getBurialComboboxTexts() {
        List<WebElement> rows = burialCombobox.findElements(By.xpath("./ul/li"));
        List<String> textOfRows = new ArrayList<>();
        for (WebElement li : rows) {
            textOfRows.add(li.getText());
        }
        return textOfRows;
    }

    public void scrollResultsUp() {
        findBlock.click();
        actions.moveToElement(findBlock).click().sendKeys(Keys.PAGE_UP).build().perform();
    }

    public void scrollResultsDown() {
        findBlock.click();
        actions.moveToElement(findBlock).click().sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public Dimension getSizeLogo() {
        return logo.getSize();
    }
}
