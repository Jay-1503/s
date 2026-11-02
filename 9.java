package org.openqa.selenium.chrome;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.List;


public class test {


    public static void main(String[] args) {


        // Set the path to ChromeDriver
        
        // Launch Chrome browser
        WebDriver driver = new ChromeDriver();


        // Maximize browser window
        driver.manage().window().maximize();


        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Open the web page
        driver.get("https://www.wikipedia.org");


        // Identify elements
        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<WebElement> images = driver.findElements(By.tagName("img"));
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        List<WebElement> inputs = driver.findElements(By.tagName("input"));


        // Display counts
        System.out.println("===== Object Count on Web Page =====");
        System.out.println("Total Links   : " + links.size());
        System.out.println("Total Images  : " + images.size());
        System.out.println("Total Buttons : " + buttons.size());
        System.out.println("Total Inputs  : " + inputs.size());


        // Total objects
        int totalObjects = links.size() + images.size() + buttons.size() + inputs.size();


        System.out.println("------------------------------------");
        System.out.println("Total Objects : " + totalObjects);


        // Close browser
        driver.quit();
    }
}
