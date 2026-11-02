package prct10;


import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class sample {


    public static void main(String[] args) {


        // Launch Chrome
        WebDriver driver = new ChromeDriver();


        // Open the website
        driver.get("https://demoqa.com/select-menu");


        // Maximize the browser
        driver.manage().window().maximize();


        // Locate the dropdown
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));


        // Create Select object
        Select select = new Select(dropdown);


        // Count total items
        List<WebElement> options = select.getOptions();
        System.out.println("Total Items: " + options.size());


        // Display all items
        System.out.println("Dropdown Items:");
        for (WebElement option : options) {
            System.out.println(option.getText());
        }


        // Display default selected item
        System.out.println("Default Selected: " +
                select.getFirstSelectedOption().getText());


        // Select an item
        select.selectByVisibleText("Purple");


        // Verify selected item
        System.out.println("Selected Item: " +
                select.getFirstSelectedOption().getText());


        // Close the browser
        driver.quit();
    }
}
