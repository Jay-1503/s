package test;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class WebElementsDemo {
  public static void main(String[] args){
    WebDriver driver=new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();

    driver.get("https://demoqa.com/automation-practice-form");

    driver.findElement(By.id("firstName")).sendKeys("Sonam");
    driver.findElement(By.id("lastName")).sendKeys("Warke");
    driver.findElement(By.xpath("//label[text()='Female']")).click();

    WebElement hobbies=driver.findElement(By.xpath("//label[text()='Sports']"));
    hobbies.click();

    driver.get("https://demoqa.com/select-menu");
    new Select(driver.findElement(By.id("oldSelectMenu"))).selectByVisibleText("Purple");

    driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
    driver.switchTo().alert().accept();

    driver.get("https://the-internet.herokuapp.com/frames");
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("iFrame")));
    driver.findElement(By.linkText("iFrame")).click();
    driver.switchTo().frame("mce_0_ifr");
    driver.findElement(By.id("tinymce")).clear();
    driver.findElement(By.id("tinymce")).sendKeys("Frame handled successfully");
    driver.switchTo().defaultContent();

    driver.quit();
  }
}
