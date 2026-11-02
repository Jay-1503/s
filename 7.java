package TEST1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DEMO {

    public static void main(String[] args) {

        Workbook wb = null;
        WebDriver driver = null;

        try {
            // Read Excel File
            FileInputStream fis = new FileInputStream("D:\\SY.xlsx");
            wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheetAt(0);

            // Update Marks
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null)
                    continue;

                String name = row.getCell(0).getStringCellValue();
                int marks = (int) row.getCell(1).getNumericCellValue();

                marks = marks + 5;
                row.getCell(1).setCellValue(marks);

                System.out.println(name + " -> Updated Marks: " + marks);
            }

            fis.close();

            // Save Updated Excel
            FileOutputStream fos = new FileOutputStream("D:\\SY.xlsx");
            wb.write(fos);
            fos.close();

            // Launch Chrome
            driver = new ChromeDriver();

            driver.manage().window().maximize();

            // Open Website
            driver.get("http://localhost:8080/student");
            // Validate Data
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null)
                    continue;

                String name = row.getCell(0).getStringCellValue();
                int marks = (int) row.getCell(1).getNumericCellValue();
                driver.findElement(By.id("search")).clear();
                driver.findElement(By.id("search")).sendKeys(name);

                String webMarks = driver.findElement(By.id("marks")).getText();

                if (webMarks.equals(String.valueOf(marks))) {
                    System.out.println(name + " : Validation Passed");
                } else {
                    System.out.println(name + " : Validation Failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (driver != null) {
                driver.quit();
            }

            if (wb != null) {
                try {
                    wb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
