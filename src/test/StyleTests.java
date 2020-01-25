package test;

import main.java.blocks.Menu;
import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import main.java.pages.FindPage;

public class StyleTests extends BaseTest {
    FindPage findPage;
    Menu menu;

    @BeforeClass
    public void openFindPage() {
        findPage = new FindPage(driver);
        findPage.open();
        menu = new Menu(driver);
    }

    @Test
    public void checkTitle(){
        Assert.assertEquals(driver.getTitle(), "Воинские захоронения Ленинградской Области");
    }

    @Test
    public void checkLogoSize(){
        Dimension sizeLogo = findPage.getSizeLogo();
        Assert.assertTrue(sizeLogo.height == 115 || sizeLogo.width == 360);
    }

    @Test
    public void checkActiveMenuTab() {
        Assert.assertTrue(menu.getClassByMenuFind().contains("activeMenuItem"));
    }

    @Test
    public void checkActiveSearchTab() {
        Assert.assertTrue(menu.getClassBySearchTab().contains("activeMenuItem"));
    }

    @Test
    public void checkActiveSurname() {
        findPage.surnameClick();
        Assert.assertTrue(findPage.getSurnameDataShrink());
    }
    @Test
    public void checkActiveName() {
        findPage.nameClick();
        Assert.assertTrue(findPage.getNameDataShrink());
    }

    @Test
    public void checkActivePatronymic() {
        findPage.patronymicClick();
        Assert.assertTrue(findPage.getPatronymicDataShrink());
    }

    @Test
    public void checkStyleBirthdate() {
        findPage.birthdateClick();
        Assert.assertTrue(findPage.getBirthdateDataShrink());
    }

    @Test
    public void checkStyleBurialPlace() {
        findPage.burialPlaceClick();
        Assert.assertTrue(findPage.getBurialPlaceDataShrink());
    }

    @Test(priority = 1)
    public void checkActiveAdvSearchTab() {
        menu.advancedSearchTabClick();
        Assert.assertTrue(menu.getClassByAdvancedSearchTab().contains("activeMenuItem"));
    }

}
