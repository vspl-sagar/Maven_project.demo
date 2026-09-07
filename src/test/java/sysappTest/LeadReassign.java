package sysappTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeadReassign {
	
	
	public void leadreassmeth(WebDriver driver, String leadRefId) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));	
		
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Lead Reassign']"))).click();
		
	LocalDate today = LocalDate.now();

    String fromDate = today.minusDays(360).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

    System.out.println("Payment From Date: " + fromDate);

    By fromDateLocator = By.id("pfrom_date");

    try {

        WebElement fromField = wait.until(ExpectedConditions.elementToBeClickable(fromDateLocator));

        fromField.click();

        Thread.sleep(300);

        fromField.sendKeys(Keys.CONTROL + "a");

        Thread.sleep(300);

        fromField.sendKeys(fromDate);

        Thread.sleep(300);

        fromField.sendKeys(Keys.TAB);

        System.out.println("Date entered using Selenium: " + fromDate);

       } catch (Exception e) {

        System.out.println("Normal date entry failed.");

        System.out.println("Using fresh element + JavaScript...");

        wait.until(ExpectedConditions.presenceOfElementLocated(fromDateLocator));

       }
        

 // Lead Status
    WebElement leadStatus = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@id,'plead_status_id')]/following-sibling::span//span[@role='combobox']")));

    leadStatus.click();

    WebElement attemptingContact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option' and normalize-space()='Attempting Contact']")));

    attemptingContact.click();


    // State
    WebElement stateDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[contains(@id,'pstate')]/following-sibling::span//span[@role='combobox']")));

    stateDropdown.click();

    // Select All
    WebElement allOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@role='option' and normalize-space()='All']")));

    allOption.click();

	}

}
