package sysappTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadEdit {

    public void editLeadmthd(WebDriver driver,String leadRefId) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
 // Click on lead operation
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-link-text flex-grow-1'][normalize-space()='Lead Operations']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='coll-filter-form']"))).click();
 //Select lead status        
        WebElement leadStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'select2-selection--single')]")));
        leadStatus.click();
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@role='searchbox']")));
        searchBox.sendKeys("All");
        WebElement allOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option' and text()='All']")));
        allOption.click();
 //Enter Lead ref id in search field
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='cf_plead_ref_id']"))).sendKeys(leadRefId);
        driver.findElement(By.xpath("//button[@id='btnRefresh']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@title='Open in current window'])[1]"))).click();
 //Click on edit        
        WebElement editleadit=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='edit_lead']")));
        editleadit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Lead Information']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnEdit"))).click();
 //Double click on Birth date field 
        WebElement BOD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[name()='svg'][@class='svg-inline--fa fa-eye'])[1]")));
        Actions actions=new Actions(driver);
        actions.doubleClick(BOD).perform();
 //Enter Date of Birth
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(By.id("vdob_dec")));
        Thread.sleep(5000);
        js.executeScript("arguments[0].value='05/10/2001'; arguments[0].dispatchEvent(new Event('change'));",dobField);
 //Enter Mobile number  
         
     // Wait for mobile field
        WebElement mobileField = wait.until(ExpectedConditions.elementToBeClickable(By.id("ai.cust_info.phone_no")));

        String mobileNumber = "9" + String.valueOf(System.currentTimeMillis()).substring(3, 12);
        mobileField.click();
        mobileField.clear();

        for (char c : mobileNumber.toCharArray()) {
            mobileField.sendKeys(String.valueOf(c));
            Thread.sleep(100);
        }
       mobileField.sendKeys(mobileNumber);
 //Enter email 
        String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("mmss"));
        String Emailname="Test"+dt+"@gmail.com";
        driver.findElement(By.xpath("//input[@id='ai.cust_info.email']")).sendKeys(Emailname );
 //Enter Address       
        driver.findElement(By.xpath("//input[@id='ai.cust_info.addr_info.street']")).sendKeys("4672 Hanover Street");
        driver.findElement(By.xpath("//input[@id='ai.cust_info.addr_info.zip']")).sendKeys("39601");
        driver.findElement(By.xpath("//input[@id='ai.cust_info.addr_info.city']")).sendKeys("Garden City");
        driver.findElement(By.xpath("//h5[text()='State']/following::span[contains(@class,'select2-selection--single')][1]")).click();
        driver.findElement(By.xpath("(//input[@role='searchbox'])[1]")).sendKeys("New York");
        driver.findElement(By.xpath("//li[@role='option' and text()='New York']")).click();
       // driver.findElement(By.xpath("//input[@id='ai.cust_info.ph_addr_info.same_as_mailing_addr']")).click();
 //Click on save
     // Click Save
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
        saveButton.click();

        // Wait until Save button is no longer visible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("btnSave")));

        // Wait until Edit button becomes visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnEdit")));


    }
}

