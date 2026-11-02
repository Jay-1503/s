package Pract4;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa. selenium.remote.RemoteWebDriver;

public class RemoteTest {
  public static void main(String[] args) throws Exception {

URL url = new URL("http://localhost:4444");

ChromeOptions options = new ChromeOptions();

WebDriver driver = new RemoteWebDriver(url, options);

driver.get("https://www.google.com");

System.out.println(driver.getTitle());

driver.quit();
  }
}
