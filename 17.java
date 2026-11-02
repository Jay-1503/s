package seleniumtest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class GoogleTset {


    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void googleTest() {


        driver.get("https://www.google.com");


        String title = driver.getTitle();


        System.out.println("Title: " + title);


        Assert.assertTrue(
            title.contains("Google"),
            "Title does not contain Google"
        );


        // Keep browser open
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

<?xml version="1.0" encoding="UTF-8"?>


<suite name="Selenium Test Suite">


    <test name="Google Test">


        <classes>
            <class name="seleniumtest.GoogleTset"/>
        </classes>


    </test>


</suite>
