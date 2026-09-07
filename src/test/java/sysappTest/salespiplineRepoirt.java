package sysappTest;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class salespiplineRepoirt {

	public void salesrepo(WebDriver driver,String leadRefId) throws InterruptedException {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nv-mnuReports' and .//span[normalize-space()='Reports']]"))).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='nv-mnuReports']//a[.//span[normalize-space()='Sales Pipeline']]"))).click();
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']"))).click();
       
         LocalDate today = LocalDate.now();

         String fromDate = today.minusDays(7).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

         WebElement fromField = driver.findElement(By.xpath("//input[@id='pfrom_date']"));

         Thread.sleep(2000);

         fromField.click();

         // Select existing date but DON'T press Backspace
         fromField.sendKeys(Keys.CONTROL, "a");

         Thread.sleep(500);

         // Slowly overwrite selected value
         for (char c : fromDate.toCharArray()) {
             fromField.sendKeys(String.valueOf(c));
             Thread.sleep(300);
         }

         fromField.sendKeys(Keys.TAB);

         Thread.sleep(1000);
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']"))).click();
         WebElement exportDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'btn-subtle-info') and contains(@class,'dropdown-toggle')]")));

         exportDropdown.click();

         Thread.sleep(1000);

        		// Now click the Export option
         WebElement exportOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class,'dropdown-menu') and contains(@class,'show')]//a[@cwf-export-click='sysApp.salesPipeline.getData']")));

         exportOption.click();
        //Enter different result in salesperson search bar  
         WebElement Searchbox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='salesperson-filter']")));
         Searchbox.sendKeys("Sagar(Subaff1)");
         Searchbox.clear();
         Searchbox.sendKeys("Autouser");
         WebElement LeadIDsearch=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lead_ref_id-filter']")));
         LeadIDsearch.sendKeys("12608-3891");
         LeadIDsearch.clear();
         LeadIDsearch.sendKeys(leadRefId);
         LeadIDsearch.sendKeys(Keys.ENTER);
         WebElement Leadstatusfil=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lead_status-filter']")));
         Leadstatusfil.sendKeys("Enrolled");
         Leadstatusfil.clear();
         Leadstatusfil.sendKeys("Quotation");
         Leadstatusfil.clear();
         //Click on Filter 
         WebElement leadSearchFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='lead_status-filter-cont']//a")));

         leadSearchFilter.click();
         WebElement deselectTheOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @name='msli[]' and following-sibling::label[normalize-space()='Quotation/Proposal']]")));
         deselectTheOption.click();
         WebElement Statefilter=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='us_state_code-filter']")));
         Statefilter.sendKeys("NY");
         Statefilter.clear();
         Statefilter.sendKeys("1");
         Statefilter.clear();
       //Enter Disposition
         WebElement Disposition=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='reason-filter']")));
         Disposition.sendKeys("Fail");
         Disposition.clear();
         Disposition.sendKeys("Attempting to Contact");
      
         WebElement affsearchbor = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='afl_name-filter']")));

        		// Scroll horizontally until Affiliate filter is visible
        		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",affsearchbor);

        		Thread.sleep(1000);
         
         
         //Enter invalid feature date 
      String futureDate = today.plusDays(7).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

      WebElement fromField1 = driver.findElement(By.xpath("//input[@id='pfrom_date']"));

      Thread.sleep(2000);

      fromField1.click();

      // Select existing date
      fromField1.sendKeys(Keys.CONTROL, "a");

      Thread.sleep(500);

      // Slowly enter the future date
      for (char c : futureDate.toCharArray()) {
          fromField.sendKeys(String.valueOf(c));
          Thread.sleep(300);
      }

      fromField1.sendKeys(Keys.TAB);

      Thread.sleep(1000);

      // Check the value entered
      System.out.println("Entered From Date: " + fromField.getAttribute("value"));

      // Try clicking Apply
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Apply']"))).click();

      Thread.sleep(1000);
      
         
         

         }
        


		
		
		
	}


