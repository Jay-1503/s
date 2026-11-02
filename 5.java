package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginAutomation {
 public static void main(String[] args){
  WebDriver driver=new ChromeDriver();
  driver.get("https://practicetestautomation.com/practice-test-login/");
  driver.manage().window().maximize();
  driver.findElement(By.id("username")).sendKeys("student");
  driver.findElement(By.id("password")).sendKeys("Password123");
  driver.findElement(By.id("submit")).click();

  String expectedURL="https://practicetestautomation.com/logged-in-successfully/";
  if(driver.getCurrentUrl().equals(expectedURL))
   System.out.println("PASS - URL Validation Successful");

  String msg=driver.findElement(By.tagName("h1")).getText();
  if(msg.equals("Logged In Successfully"))
   System.out.println("PASS - Welcome Message Verified");

  if(driver.findElement(By.linkText("Log out")).isDisplayed())
   System.out.println("PASS - Logout Button is Visible");

  driver.quit();
 }
}
