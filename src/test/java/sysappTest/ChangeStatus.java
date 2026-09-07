package sysappTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;



public class ChangeStatus {
	public void statusschange(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.elementToBeClickable(By.id("quote_tab"))).click();
		
//Click on Edit Quote pencil icon
		
		 WebElement firstQuotation = driver.findElement(By.xpath("(//input[contains(@class,'sel-lead')])[1]"));

		        // Select the first quotation
		        if (!firstQuotation.isSelected()) {
		            firstQuotation.click();
		        }
		        
		      

// Find the edit button belonging to the same quotation row
		WebElement editButton = firstQuotation.findElement(By.xpath("./ancestor::tr[1]//a[1]"));
// Click Edit
		editButton.click();
		Thread.sleep(5000);
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnActions")));
		 
//Click on Action Button
		WebElement actions = driver.findElement(By.xpath("//div[@class='d-flex justify-content-between']//div[@role='group']//button[@id='btnActions']"));

		By actionsBtn = By.id("btnActions");

		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(actionsBtn))).click();

		//actions.click();
		WebElement approved = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Approved']")));

		approved.click();
		Thread.sleep(5000);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnClose']"))).click();
			
		
		
		
		


	
		
//Click on Bank Tab 
        WebElement bankTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("bank_tab")));
        bankTab.click();
//Click on Edit 
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnEdit"))).click();
        WebElement paymentDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("vpayment_info.payment_method")));
//Select Bank Option from drop down
        Select select = new Select(paymentDropdown);
        select.selectByValue("bank");
//Enter routing number 

        WebElement routingNo = wait.until(
        ExpectedConditions.elementToBeClickable(By.id("vpayment_info.bank.routing_no")));
        routingNo.click();
        routingNo.clear();
// 9-digit routing number (example)
       routingNo.sendKeys("042100175");
//Click on search    
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnZip")));
    	searchBtn.click();
//Enter Account Number 
    	WebElement accountNo = wait.until(ExpectedConditions.elementToBeClickable(By.id("vpayment_info.bank.account_no")));
    	accountNo.click();
    	accountNo.clear();
    	accountNo.sendKeys("123456789012345");
//Enter account Holder name 
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("vpayment_info.bank.holder_name"))).sendKeys("Test User");
    	Thread.sleep(3000);
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnEdit")));
    	//wait.until(ExpectedConditions.elementToBeClickable(By.id("btnClose"))).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("quote_tab"))).click();
//Select Approved Quote
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='sel-lead' and @data-stage='approved']"))).click();
    	WebElement actionsBtn1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnActions")));
    	actionsBtn1.click();
//Click Request down payment 
    	WebElement requestDP = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Request Down Payment']")));

    	requestDP.click();
    	
 //Select Payment method 
    	WebElement requestPayment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//b[normalize-space()='Request Payment']]")));
    	requestPayment.click();
    	WebElement yesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnYes")));

    	yesBtn.click();
    	//Thread.sleep(5000);
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading, .spinner, .overlay, .loader")));
  /*  	
 //Select checkbox
    	WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and contains(@data-prop-id,'PROP')]")));
    	checkbox.click();
    	
 //Click on Action and change status as Create contract 
    
    	WebElement actionsBtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnActions")));
    	actionsBtn2.click();

// Select Create Contract
    	WebElement createContract = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Create Contract']")));
    	createContract.click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnYes"))).click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading, .spinner, .overlay, .loader")));
    	
//Change status as Email Contract 
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and contains(@data-prop-id,'PROP')]"))).click();    	
    	
 //Click on Action tab then select Email contract 
    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnActions"))).click();
    	
    	WebElement emailContract = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Email Contract']")));
    	emailContract.click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnYes"))).click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading, .spinner, .overlay, .loader")));
    	
//Change status as Enrolled    	
    	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and contains(@data-prop-id,'PROP')]"))).click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnActions"))).click();
    	WebElement Enrolled = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Enroll']")));
    	Enrolled.click();
    	wait.until(ExpectedConditions.elementToBeClickable(By.id("btnYes"))).click();
    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading, .spinner, .overlay, .loader")));
    */	
    	
}	
}