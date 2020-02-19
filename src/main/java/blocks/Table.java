package main.java.blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private WebDriver driver;
    private WebDriverWait wait;
    private int number;

    @FindBy(css = "table[aria-label='simple table'] > thead > tr")
    private WebElement simpleHeadTable;
    @FindBy(css = "table[aria-label='simple table'] > tbody")
    private List<WebElement> simpleBodyTables;

    public Table(WebDriver driver, int tableNumber) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        number = tableNumber;
        PageFactory.initElements(driver, this);
        if(number > 1){
            wait.until(size -> simpleBodyTables.size() > 1);
        } else wait.until(size -> simpleBodyTables.size() == 1);
    }

    public List<WebElement> getRowsOfTable() {
        WebElement simpleBodyTable = simpleBodyTables.get(number - 1);
        List<WebElement> rows = simpleBodyTable.findElements(By.xpath("./tr"));
        return rows;
    }

    public List<WebElement> getHeadersOfResultsTable() {
        List<WebElement> headers = simpleHeadTable.findElements(By.xpath("./th"));
        return headers;
    }

    //список строк со списком значений ячеек
    public List<List<WebElement>> getRowsWithCells() {
        List<WebElement> rows = getRowsOfTable();
        List<List<WebElement>> rowsWithCells = new ArrayList<List<WebElement>>();
        for (WebElement row : rows) {
            List<WebElement> oneCellOfRow = row.findElements(By.xpath("./td"));
            rowsWithCells.add(oneCellOfRow);
        }
        return rowsWithCells;
    }

    public String getValueFromCell(int rowNumber, int columnNumber) {
        List<List<WebElement>> rowsWithCells = getRowsWithCells();
        WebElement cell = rowsWithCells.get(rowNumber - 1).get(columnNumber - 1);
        return cell.getText();
    }

    public void rowClick (int rowNumber){
        List<WebElement> rows = getRowsOfTable();
        rows.get(rowNumber - 1).findElement(By.xpath("./td[1]")).click();
    }
}
