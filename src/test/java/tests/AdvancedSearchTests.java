package tests;

import blocks.Menu;
import blocks.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FindPage;


public class AdvancedSearchTests extends BaseTest {
    private static final Logger logger = LogManager.getLogger(AdvancedSearchTests.class);
    FindPage findPage;
    Menu menu;

    @BeforeClass
    public void openFindPage() {
        findPage = new FindPage(driver);
        findPage.open();
        menu = new Menu(driver);
        menu.advancedSearchTabClick();
    }

    @Test
    public void findByRank() {
        findPage.rankClick();
        findPage.rankClear();
        findPage.rankSendKeys("рядовой");
        findPage.scrollResultsDown();
        findPage.findButtonClick();
        Table resultTable = new Table(driver, 1);
        resultTable.rowClick(1);
        Table infoTable = new Table(driver, 2);
        String surnameCell = infoTable.getValueFromCell(5, 2);
        Assert.assertTrue(surnameCell.contains("рядовой"));
        logger.info("Search test by rank place passed");
    }

//    @Test
//    public void BurialLocationTest() {
//        SimpleSearchTests tests = new SimpleSearchTests();
//        tests.findByBurialPlace();
//
//    }
//
    @AfterMethod
    public void goBackAndClearFields() {
        findPage.scrollResultsUp();
        if (findPage.displayedOfGoBackButton()){
            findPage.goBackButtonClick();
        }
        findPage.rankClear();
    }
}
