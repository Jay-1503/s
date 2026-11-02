package automation;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FileUploadDownload {


    public static void main(String[] args) throws Exception {


        // =========================================
        // CHROME SETUP
        // =========================================


        String downloadPath =
                System.getProperty("user.home") + "\\Downloads";


        // Delete old downloaded file if it exists
        File oldFile =
                new File(downloadPath + "\\testfile.txt");


        if (oldFile.exists()) {
            oldFile.delete();
        }


        ChromeOptions options = new ChromeOptions();


        // Set download location
        options.addArguments("--disable-notifications");


        options.setExperimentalOption(
                "prefs",
                java.util.Map.of(
                        "download.default_directory", downloadPath,
                        "download.prompt_for_download", false,
                        "download.directory_upgrade", true,
                        "safebrowsing.enabled", true
                )
        );


        // Start Chrome
        WebDriver driver = new ChromeDriver(options);


        driver.manage().window().maximize();


        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));


        try {


            // =========================================
            // OPEN HTML PAGE
            // =========================================


            driver.get(
                "file:///C:/Automation/upload_download.html"
            );


            System.out.println("Web page opened successfully.");


            // =========================================
            // FILE UPLOAD
            // =========================================


            System.out.println("\nStarting file upload...");


            // Use JavaScript to click Choose File
            JavascriptExecutor js =
                    (JavascriptExecutor) driver;


            js.executeScript(
                    "document.getElementById('uploadFile').click();"
            );


            // File path
            String filePath =
                    "C:\\Automation\\testfile.txt";


            // Copy path to clipboard
            StringSelection selection =
                    new StringSelection(filePath);


            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(selection, null);


            // =========================================
            // ROBOT CLASS
            // =========================================


            Robot robot = new Robot();


            robot.delay(1000);


            // CTRL + V
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);


            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);


            robot.delay(500);


            // ENTER
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);


            robot.delay(1000);


            System.out.println("File selected using Robot Class.");


            // =========================================
            // VALIDATE UPLOAD
            // =========================================


            String uploadMessage =
                    wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                            By.id("uploadStatus")
                        )
                    ).getText();


            System.out.println(
                    "Upload Result: " + uploadMessage
            );


            if (uploadMessage.contains("testfile.txt")) {


                System.out.println(
                        "UPLOAD SUCCESSFUL"
                );


            } else {


                System.out.println(
                        "UPLOAD FAILED"
                );
            }


            // =========================================
            // FILE DOWNLOAD
            // =========================================


            System.out.println(
                    "\nStarting file download..."
            );


            driver.findElement(
                    By.id("downloadButton")
            ).click();


            System.out.println(
                    "Download button clicked."
            );


            // =========================================
            // WAIT FOR DOWNLOAD
            // =========================================


            File downloadedFile =
                    new File(
                        downloadPath + "\\testfile.txt"
                    );


            int waitTime = 0;


            while (!downloadedFile.exists()
                    && waitTime < 10) {


                Thread.sleep(1000);
                waitTime++;
            }


            // =========================================
            // VALIDATE DOWNLOAD
            // =========================================


            if (downloadedFile.exists()) {


                System.out.println(
                        "Downloaded file found:"
                );


                System.out.println(
                        downloadedFile.getAbsolutePath()
                );


                System.out.println(
                        "DOWNLOAD SUCCESSFUL"
                );


            } else {


                System.out.println(
                        "DOWNLOAD FAILED"
                );


                System.out.println(
                        "File was not found in Downloads folder."
                );
            }


        } finally {


            // Close browser
            driver.quit();


            System.out.println(
                    "\nAutomation completed."
            );
        }
    }
}

Create new folder in c: 🡪Automation🡪testfile.txt
In testfile.txt just write tour full name.
Also create one html file upload_download.html and write code:
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>File Upload and Download Automation</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 40px;
        }

        .container {
            width: 600px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px #cccccc;
        }

        h1 {
            text-align: center;
        }

        h2 {
            margin-top: 30px;
        }

        .section {
            padding: 20px;
            border: 1px solid #dddddd;
            border-radius: 8px;
            margin-top: 15px;
        }

        input[type="file"] {
            margin-top: 10px;
        }

        button {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 15px;
        }

        #downloadFile {
            text-decoration: none;
        }

        #downloadButton {
            background-color: #2196F3;
            color: white;
        }

        #uploadStatus {
            margin-top: 15px;
            font-weight: bold;
        }
    </style>
</head>

<body>

    <div class="container">

        <h1>File Upload and Download</h1>

        <!-- ===================== -->
        <!-- FILE UPLOAD SECTION -->
        <!-- ===================== -->

        <div class="section">

            <h2>Upload File</h2>

            <label for="uploadFile">
                Select a file to upload:
            </label>

            <br><br>

            <input
                type="file"
                id="uploadFile"
                name="uploadFile">

            <p id="uploadStatus"></p>

        </div>


        <!-- ===================== -->
        <!-- FILE DOWNLOAD SECTION -->
        <!-- ===================== -->

        <div class="section">

            <h2>Download File</h2>

            <a
                id="downloadFile"
                href="testfile.txt"
                download="testfile.txt">

                <button id="downloadButton">
                    Download testfile.txt
                </button>

            </a>

        </div>

    </div>


    <script>

        const uploadFile =
            document.getElementById("uploadFile");

        const uploadStatus =
            document.getElementById("uploadStatus");


        uploadFile.addEventListener("change", function () {

            if (uploadFile.files.length > 0) {

                const fileName =
                    uploadFile.files[0].name;

                uploadStatus.innerText =
                    "File Uploaded Successfully: " + fileName;

            } else {

                uploadStatus.innerText =
                    "No file selected.";

            }

        });

    </script>

</body>

</html>
Run As 🡪java 
