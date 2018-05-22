package sd.Browser_launch;

import base.Browser_setup;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.openqa.selenium.By;


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
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
        for (int i = 0; i < rowCount; i++) {  //Create a loop over all the rows of excel file to read it

            Row row = sh.getRow(i);

            for (int j = 0; j < 1; j++) { //row.getLastCellNum() Create a loop to print cell values in a row
                System.out.print("Current Domain:->  " + row.getCell(j).getStringCellValue() + " Extracted data is : ");
                driver.findElement(By.cssSelector(eleHome.getString("searchTextBox_css"))).clear();//Clear search textBox
                driver.findElement(By.cssSelector(eleHome.getString("searchTextBox_css"))).sendKeys(row.getCell(j).getStringCellValue()); //Enter data fron excel to search textBox
                driver.findElement(By.cssSelector(eleHome.getString("jsonRadionBtn_css"))).click();//Choose data Format
                driver.findElement(By.cssSelector(eleHome.getString("searchBtn_css"))).click();//Click at Search button
                Thread.sleep(7000);//Wait for process
               // String data = driver.findElement(By.cssSelector(eleHome.getString("tabelData_css"))).getText();//extract data
                String data = driver.findElement(By.cssSelector(eleHome.getString("tabelData_css"))).getText();//extract data
                System.out.println("Extracted data is: " + data);
                //Write Excel
                //Create blank workbook
                /*FileWriter fr=new FileWriter("d:\\data"+i+".json");
                BufferedWriter br=new BufferedWriter(fr);
                JSONObject obj = new JSONObject(data);
                obj.write(data);
                br.write(data);

                br.newLine();
                fr.flush();
                fr.close();*/
                int rowCountw = 1;
//                for (int n=0;n<rowCount+1;n++) {
                    Row roww = sheet.createRow(i);
                    int columnCount = 1;
//                    for (int k=0;k<1;k++) {
                        Cell cell = roww.createCell(j);
                        cell.setCellValue(data);
//                    }
//                }

            }
//
            }
            try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
                workbook.write(outputStream);
        }
        System.out.println("Execution Finished");


    }
}


