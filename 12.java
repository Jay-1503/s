package prc12;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DynamicTableValidation {
	public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();


        // Open local HTML file
        driver.get("C:/Users/Lab Server Pc/Documents/dynamic_table.html");


        driver.manage().window().maximize();




        // Locate table rows dynamically
        List<WebElement> rows = driver.findElements(
                By.xpath("//table[@id='employeeTable']//tr")
        );




        System.out.println("Total Rows: " + (rows.size()-1));




        // Extract table data
        System.out.println("Employee Details:");


        for(int i=1; i<rows.size(); i++) {


            List<WebElement> columns =
                    rows.get(i).findElements(By.tagName("td"));


            System.out.println(
                    columns.get(0).getText() + " "
                    + columns.get(1).getText() + " "
                    + columns.get(2).getText()
            );
        }




        // Store original names
        List<String> actualNames = new ArrayList<>();


        for(int i=1; i<rows.size(); i++) {


            String name = rows.get(i)
                    .findElements(By.tagName("td"))
                    .get(1)
                    .getText();


            actualNames.add(name);
        }




        // Click column header to sort
        driver.findElement(
                By.xpath("//th[contains(text(),'Name')]")
        ).click();




        // Get sorted names
        List<WebElement> sortedRows =
                driver.findElements(
                By.xpath("//table[@id='employeeTable']//tr")
                );




        List<String> sortedNames = new ArrayList<>();


        for(int i=1; i<sortedRows.size(); i++) {


            String name = sortedRows.get(i)
                    .findElements(By.tagName("td"))
                    .get(1)
                    .getText();


            sortedNames.add(name);
        }




        // Validate sorting
        List<String> expectedNames =
                new ArrayList<>(actualNames);


        Collections.sort(expectedNames);




        if(expectedNames.equals(sortedNames)) {


            System.out.println(
                    "Sorting Validation Passed"
            );


        } else {


            System.out.println(
                    "Sorting Validation Failed"
            );
        }




        // Filter validation


        WebElement search =
                driver.findElement(By.id("search"));


        search.sendKeys("Alice");


        driver.findElement(
                By.xpath("//button[text()='Filter']")
        ).click();




        List<WebElement> filteredRows =
                driver.findElements(
                By.xpath("//table[@id='employeeTable']//tr")
                );




        boolean resultFound = false;




        for(int i=1; i<filteredRows.size(); i++) {


            if(filteredRows.get(i).isDisplayed()) {


                String name =
                        filteredRows.get(i)
                        .findElements(By.tagName("td"))
                        .get(1)
                        .getText();


                if(name.equals("Alice")) {
                    resultFound = true;
                }
            }
        }




        if(resultFound) {


            System.out.println(
                    "Filter Validation Passed"
            );


        } else {


            System.out.println(
                    "Filter Validation Failed"
            );
        }




        driver.quit();
    }
}

DYNAMIC_TABLE.html
<!DOCTYPE html>
<html>
<head>
<title>Dynamic Table</title>

<script>
function sortTable() {
    let table = document.getElementById("employeeTable");
    let rows = Array.from(table.rows).slice(1);

    rows.sort((a,b) =>
        a.cells[1].innerHTML.localeCompare(b.cells[1].innerHTML)
    );

    rows.forEach(row => table.appendChild(row));
}

function filterTable() {
    let input = document.getElementById("search").value.toLowerCase();
    let rows = document.getElementById("employeeTable").rows;

    for(let i=1; i<rows.length; i++) {
        let name = rows[i].cells[1].innerHTML.toLowerCase();

        if(name.includes(input))
            rows[i].style.display="";
        else
            rows[i].style.display="none";
    }
}
</script>

</head>

<body>

<h2>Employee Details</h2>

<input type="text" id="search" placeholder="Search Name">
<button onclick="filterTable()">Filter</button>

<br><br>

<table border="1" id="employeeTable">

<tr>
<th>ID</th>
<th onclick="sortTable()">Name (Sort)</th>
<th>Department</th>
</tr>

<tr>
<td>101</td>
<td>John</td>
<td>Testing</td>
</tr>

<tr>
<td>102</td>
<td>Alice</td>
<td>Development</td>
</tr>

<tr>
<td>103</td>
<td>David</td>
<td>Database</td>
</tr>
<tr>
<td>104</td>
<td>Bob</td>
<td>Testing</td>
</tr>
</table>
</body>
</html>
