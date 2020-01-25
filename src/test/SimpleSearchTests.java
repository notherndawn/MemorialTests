package test;

import main.java.blocks.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import main.java.pages.FindPage;
import main.java.blocks.Menu;

import java.util.List;

public class SimpleSearchTests extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SimpleSearchTests.class);
    FindPage findPage;
    Menu menu;


    @BeforeClass
    public void openFindPage() {
        findPage = new FindPage(driver);
        findPage.open();
        menu = new Menu(driver);
    }

    @Test
    public void findBySurname() {
        findPage.surnameClick();
        findPage.surnameSendKeys("Вольдемаров");
        findPage.findButtonClick();
        Table resultTable = new Table(driver, 1);
        String surnameCell = resultTable.getValueFromCell(1, 1);
        Assert.assertTrue(surnameCell.equals("Вольдемаров"));
        logger.info("Search test by surname passed");
    }

    @Test
    public void findByName() {
        findPage.nameClick();
        findPage.nameSendKeys("Абулянт");
        findPage.findButtonClick();
        Table resultTable = new Table(driver, 1);
        String surnameCell = resultTable.getValueFromCell(1, 2);
        Assert.assertTrue(surnameCell.equals("Абулянт"));
        logger.info("Search test by name passed");
    }

    @Test
    public void findByPatronymic() {
        findPage.patronymicClick();
        findPage.patronymicSendKeys("Габдиевич");
        findPage.findButtonClick();
        Table resultTable = new Table(driver, 1);
        String surnameCell = resultTable.getValueFromCell(1, 3);
        Assert.assertTrue(surnameCell.equals("Габдиевич"));
        logger.info("Search test by patronymic passed");
    }

    @Test
    public void findByBirthdate() {
        findPage.birthdateClick();
        findPage.birthdateSendKeys("1918");
        findPage.findButtonClick();
        Table resultTable = new Table(driver, 1);
        String surnameCell = resultTable.getValueFromCell(1, 4);
        Assert.assertTrue(surnameCell.equals("1918"));
        logger.info("Search test by birth date passed");
    }

    @Test
    public void findByBurialPlace() {
        findPage.burialPlaceClick();
        findPage.burialPlaceSendKeys("Бокситогорское");
        findPage.findButtonClick();
        Table resultTable = new Table(driver, 1);
        String surnameCell = resultTable.getValueFromCell(1, 5);
        Assert.assertTrue(surnameCell.contains("Бокситогорское"));
        logger.info("Search test by burial place passed");
    }

    @Test
    public void checkBurialComboboxText(){
        findPage.burialPlaceClick();
        findPage.burialPlaceSendKeys("Бок");
        List<String> textOfPopUp = findPage.getBurialComboboxTexts();
        for (String text: textOfPopUp) {
            Assert.assertTrue(text.contains("Бок")|text.contains("бок"));
        }
        menu.menuFindTabClick();
        logger.info("Combobox content test by burial place passed");
    }

    @AfterMethod
    public void goBackAndClearFields(){
        if (findPage.displayedOfGoBackButton()){
            findPage.goBackButtonClick();
        }
        findPage.nameClear();
        findPage.surnameClear();
        findPage.patronymicClear();
        findPage.birthdateClear();
        findPage.burialPlaceClear();
    }
}