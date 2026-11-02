package pract8;


import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class demo1 {


    public static void main(String[] args) throws Exception {


        // Open Excel file
        FileInputStream fis = new FileInputStream(
            "C:\\Users\\Lab Server Pc\\Desktop\\DATA.xlsx"
        );


        Workbook workbook = new XSSFWorkbook(fis);


        Sheet sheet = workbook.getSheetAt(0);


        int totalStudents = 0;
        int countAbove60 = 0;




        // Read Excel rows
        for (Row row : sheet) {


            // Skip header row
            if (row.getRowNum() == 0) {
                continue;
            }


            totalStudents++;


            // Marks column (2nd column)
            int marks = (int) row.getCell(1)
                                  .getNumericCellValue();


            if (marks > 60) {
                countAbove60++;
            }
        }




        double percentage =
                ((double) countAbove60 / totalStudents) * 100;




        System.out.println("Total Students: " + totalStudents);
        System.out.println("Students scoring above 60: " + countAbove60);
        System.out.println("Percentage: " + percentage + "%");




        workbook.close();
        fis.close();
    }


