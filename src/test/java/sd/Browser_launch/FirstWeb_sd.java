package sd.Browser_launch;

import base.Browser_setup;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.os.WindowsUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ResourceBundle;


public class FirstWeb_sd extends Browser_setup {

    ResourceBundle launch = ResourceBundle.getBundle("browser_setup");
    ResourceBundle eleHome = ResourceBundle.getBundle("whoisxmlapi");

    @Given("^User Launch the browser$")
    public void user_Launch_the_browser() throws MalformedURLException {
        browser();

    }


    @Given("^User goto First URL of application$")
    public void userGotoFirstURLOfApplication() {
        driver.get(launch.getString("Firsturl"));
        System.out.println("URL of application launched is: " + launch.getString("Firsturl"));

    }

    @And("^read data from file$")
    public void readDataFromFile() throws IOException, InterruptedException {
        String path = "D://Documents";
        String filename = "data1.xls";
        File file = new File(path + "//" + filename);
        FileInputStream inputStream = new FileInputStream(file);//Create an object of FileInputStream class to read excel file
        Workbook wb = null;
        String fileExtensionName = filename.substring(filename.indexOf("."));//Find the file extension by spliting file name in substring and getting only extension name
        if (fileExtensionName.equals(".xlsx")) //If it is xlsx file then create object of XSSFWorkbook class
        {
            //XSSFWorkbook workbook = new XSSFWorkbook();
            wb = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls"))//Check condition if the file is xls file
        {
            wb = new HSSFWorkbook(inputStream);//If it is xls file then create object of HSSFWorkbook class

        }

        Sheet sh = wb.getSheet("Sheet1");//Read sheet inside the workbook by its name
        int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();  //Find number of rows in excel file
        System.out.println("Number of Rows in File = " + rowCount);
        for (int i = 0; i < rowCount + 1; i++) {  //Create a loop over all the rows of excel file to read it

            Row row = sh.getRow(i);
            for (int j = 0; j < 1; j++) { //row.getLastCellNum() Create a loop to print cell values in a row
                System.out.print(row.getCell(j).getStringCellValue() + "|| ");
                driver.findElement(By.cssSelector(eleHome.getString("searchTextBox_css"))).clear();//Clear search textBox
                driver.findElement(By.cssSelector(eleHome.getString("searchTextBox_css"))).sendKeys(row.getCell(j).getStringCellValue()); //Enter data fron excel to search textBox
                //driver.findElement(By.cssSelector(eleHome.getString("jsonRadionBtn_css"))).click();//Choose data Format
                //driver.findElement(By.cssSelector(eleHome.getString("searchBtn_css"))).click();//Click at Search button
                Thread.sleep(4000);//Wait for process
                String data = driver.findElement(By.cssSelector(eleHome.getString("tabelData_css"))).getText();//extract data
                System.out.println("Extracted data is: "+data);
                //Start Writing data in file here
                FileOutputStream fos = new FileOutputStream("D:/Output.xls");
                HSSFWorkbook wbw = new HSSFWorkbook();
                HSSFSheet shw = wbw.createSheet("Results");
                Row rw = sh.createRow(i);
                Cell c1 = rw.createCell(j);
                c1.setCellValue(data);


            //Print excel data in console
               /* switch (row.getCell(j).getCellType()) {

                    case Cell.CELL_TYPE_STRING:
                        System.out.print(row.getCell(j).getStringCellValue() + "|| ");

                        break;

                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(row.getCell(j).getNumericCellValue() + "|| ");
                        break;
                    // System.out.print(row.getCell(j).getStringCellValue()+"|| ");
                }*/

        }
        System.out.println("Execution Finished");
       // WindowsUtils.tryToKillByName("soffice.bin");


    }
}}

