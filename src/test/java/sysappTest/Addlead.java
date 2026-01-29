package sysappTest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Addlead {

	public String addLead(WebDriver driver) throws InterruptedException {
        // Click Sales menu
        driver.findElement(By.xpath("//a[@href='#nv-mnuSales']")).click();
        driver.findElement(By.xpath("//span[text()='Add Lead']")).click();

        String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("mmss"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bo-form-body-cont")));

        driver.findElement(By.id("ai.cust_info.first_name")).sendKeys("Test" + dt);

        driver.findElement(By.id("ai.cust_info.last_name")).sendKeys("User" + dt);

        // Click Save
        driver.findElement(By.id("btnSave")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnEdit")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col-xl-4']")));
        WebElement leadRefElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ai.lead_ref_id")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", leadRefElement);
        Thread.sleep(500);
        String leadRefId = leadRefElement.getAttribute("value");

        System.out.println("Lead Ref ID: " + leadRefId);
        driver.findElement(By.id("btnClose")).click();

        return leadRefId;
        
    }

	
	
	
	
	
	
	
}
