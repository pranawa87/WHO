/*@author: Pranawa Mishra, Date: 18-05-2018*/
package sd.Browser_launch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
        String path = "D:";
        String filename = "JavaBooks.xlsx";
        File file = new File(path + "\\" + filename);
        FileInputStream inputStream = new FileInputStream(file);//Create an object of FileInputStream class to read excel file
        Workbook wb = null;
        String fileExtensionName = filename.substring(filename.indexOf("."));//Find the file extension by spliting file name in substring and getting only extension name
        if (fileExtensionName.equals(".xlsx")) //If it is xlsx file then create object of XSSFWorkbook class
        {
            XSSFWorkbook workbook = new XSSFWorkbook();
            wb = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls"))//Check condition if the file is xls file
        {
            wb = new HSSFWorkbook(inputStream);//If it is xls file then create object of HSSFWorkbook class

        }

        Sheet sh = wb.getSheet("Java Books");//Read sheet inside the workbook by its name
        int rowCount = sh.getLastRowNum() - sh.getFirstRowNum();  //Find number of rows in excel file
        System.out.println("Number of Rows in File = " + rowCount);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
        for (int i = 0; i <= rowCount; i++) {  //Create a loop over all the rows of excel file to read it

            Row row = sh.getRow(i);


            for (int j = 0; j < 1; j++) {
                String data = row.getCell(j).getStringCellValue();

                System.out.print("Current JSON Data  " + row.getCell(j).getStringCellValue());
               /* try {
                    FileWriter fstream = new FileWriter("d:\\data.json", true);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(data + ",");
                    out.newLine();
                    out.close();
                } catch (Exception e) {
                    System.err.println("Error while writing to file: " +
                            e.getMessage());
                }*/


            }


        }
    }

}