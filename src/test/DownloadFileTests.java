package test;

import main.java.blocks.Menu;
import main.java.pages.FindPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class DownloadFileTests extends BaseTest{
    private static final Logger logger = LogManager.getLogger(AdvancedSearchTests.class);
    FindPage findPage;
    Menu menu;
    File fileInstruction;

    @BeforeClass
    public void openFindPage() {
        findPage = new FindPage(driver);
        findPage.open();
        menu = new Menu(driver);
        menu.menuInstructionsTabClick();
    }
    
    @Test
    public void downloadInstruction() {
        menu.linkInstructionClick();
        fileInstruction = new File(BaseTest.downloadPath,"instruction_rus.72c46832.pdf");
        wait.until(f -> fileInstruction.exists() == true);
        logger.info("Наличие файла инструкции после загрузки: " + fileInstruction.exists());
        Assert.assertTrue(fileInstruction.exists());
    }

    @AfterMethod
    public void deleteFiles() {
        fileInstruction.delete();
    }
}
