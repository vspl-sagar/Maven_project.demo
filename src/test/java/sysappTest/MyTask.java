package sysappTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyTask {
	
	
	
	public void mytaskmet(WebDriver driver, String leadRefId) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='nav-link-text'][normalize-space()='My Tasks']"))).click();
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'My Tasks')]"))).click();
		
		WebElement Primaryfilter=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class,'select2-selection__rendered') and @title='My Tasks']")));
		Primaryfilter.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='select2-search__field']"))).click();
       
        driver.findElement(By.xpath("//li[normalize-space()='Subordinate Tasks']")).click();
        WebElement periodFilter =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='pperiod_filter']")));
        periodFilter.click();
        Select select =new Select(periodFilter);
        select.selectByVisibleText("By Created On");

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
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']"))).click();
        
        Primaryfilter.click();
        driver.findElement(By.xpath("//li[normalize-space()='My Tasks']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']"))).click();
        
        

		    }
		

		
	   

		
		
	}


