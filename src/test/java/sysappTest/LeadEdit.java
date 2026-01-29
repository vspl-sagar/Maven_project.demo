package sysappTest;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadEdit {



    public void editLeadmthd(WebDriver driver,String leadRefId) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-link-text flex-grow-1'][normalize-space()='Lead Operations']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='coll-filter-form']"))).click();
        
        WebElement leadStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'select2-selection--single')]")));
        leadStatus.click();
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@role='searchbox']")));
        searchBox.sendKeys("All");
        WebElement allOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option' and text()='All']")));
        allOption.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='cf_plead_ref_id']"))).sendKeys(leadRefId);
        driver.findElement(By.xpath("//button[@id='btnRefresh']")).click();
        driver.findElement(By.xpath("//a[@title='Open in current window']//*[name()='svg']")).click();
        WebElement editleadit=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='edit_lead']")));
        editleadit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Client Information']")));
       // wait.until(ExpectedConditions.elementToBeClickable(By.id("form-header"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnEdit"))).click();
        WebElement BOD=driver.findElement(By.xpath("(//*[name()='svg'][@class='svg-inline--fa fa-eye'])[1]"));
        Actions actions=new Actions(driver);
        actions.doubleClick(BOD).perform();
        driver.findElement(By.xpath("//input[@id='vdob_dec']")).clear();
        driver.findElement(By.xpath("//input[@id='vdob_dec']")).sendKeys("05/10/2001",Keys.TAB);
        long mobileNumber = (long) (Math.random() * 10000000000L);
        String mobilenum = String.valueOf(mobileNumber);
        System.out.println("Mobile Number: " + mobileNumber);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='ai.cust_info.phone_no']"))).sendKeys(mobilenum);
        String dt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("mmss"));
        String Emailname="Test"+dt+"@gmail.com";
        driver.findElement(By.xpath("//input[@id='ai.cust_info.email']")).sendKeys(Emailname );
        driver.findElement(By.xpath("//input[@id='ai.cust_info.addr_info.street']")).sendKeys("Sai Palace");
        driver.findElement(By.xpath("//input[@id='ai.cust_info.addr_info.zip']")).sendKeys("23421");
        driver.findElement(By.xpath("//input[@id='ai.cust_info.addr_info.city']")).sendKeys("Satara");
        driver.findElement(By.xpath("//h5[text()='State']/following::span[contains(@class,'select2-selection--single')][1]")).click();
        driver.findElement(By.xpath("(//input[@role='searchbox'])[1]")).sendKeys("New York");
        driver.findElement(By.xpath("//li[@role='option' and text()='New York']")).click();
        driver.findElement(By.xpath("//input[@id='ai.cust_info.ph_addr_info.same_as_mailing_addr']")).click();
        driver.findElement(By.xpath("//button[@id='btnSave']")).click();


    }
}

