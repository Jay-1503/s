package prc11;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;


public class CheckboxValidation {


    public static void main(String[] args) {


        // Selenium 4.6+ automatically manages ChromeDriver
        WebDriver driver = new ChromeDriver();


        // Open webpage containing checkboxes
        driver.get("C:/Users/Lab Server Pc/Documents/checkbox.html");
        driver.manage().window().maximize();


        // Locate all checkboxes on the page
        List<WebElement> checkboxes = driver.findElements(
                By.xpath("//input[@type='checkbox']")
        );


        int totalCheckboxes = checkboxes.size();
        int checkedCount = 0;
        int uncheckedCount = 0;


        // Count checked and unchecked checkboxes
        for (WebElement checkbox : checkboxes) {


            if (checkbox.isSelected()) {
                checkedCount++;
            } else {
                uncheckedCount++;
            }
        }


        System.out.println("Total Checkboxes: " + totalCheckboxes);
        System.out.println("Checked Checkboxes: " + checkedCount);
        System.out.println("Unchecked Checkboxes: " + uncheckedCount);




        // Dynamically select unchecked checkboxes
        for (WebElement checkbox : checkboxes) {


            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }




        // Validate checkbox selection after clicking
        int selectedAfterClick = 0;


        for (WebElement checkbox : checkboxes) {


            if (checkbox.isSelected()) {
                selectedAfterClick++;
            }
        }


        System.out.println("Checkboxes selected after dynamic selection: "
                + selectedAfterClick);




        // Validation result
        if (selectedAfterClick == totalCheckboxes) {
            System.out.println("Validation Passed: All checkboxes are selected.");
        } else {
            System.out.println("Validation Failed: Some checkboxes are not selected.");
        }




        // Close browser
        driver.quit();
    }
}
