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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentStatus {

    public void paymentst(WebDriver driver, String leadRefId)
            throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));

        By paymentStatusMenu = By.xpath("//span[@class='nav-link-text flex-grow-1' " + "and normalize-space()='Payment Status']");

        wait.until(ExpectedConditions.elementToBeClickable(paymentStatusMenu)).click();

        Thread.sleep(1000);
       
        // PAYMENT FROM DATE
      

        LocalDate today = LocalDate.now();

        String fromDate = today.minusDays(30).format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

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

            Thread.sleep(500);


            ((JavascriptExecutor) driver).executeScript(
                    "var el = document.getElementById('pfrom_date');" +

                    "if (!el) {" +
                    "    throw new Error('pfrom_date element not found');" +
                    "}" +

                    "var value = arguments[0];" +

                    "el.focus();" +

                    // Use the native value setter
                    // so JavaScript frameworks detect the change.
                    "var setter = Object.getOwnPropertyDescriptor(" +
                    "HTMLInputElement.prototype, 'value').set;" +

                    "setter.call(el, value);" +

                    "el.setAttribute('mval', value);" +
                    "el.setAttribute('orig', value);" +

                    "el.dispatchEvent(" +
                    "new Event('input', { bubbles: true })" +
                    ");" +

                    "el.dispatchEvent(" +
                    "new Event('change', { bubbles: true })" +
                    ");" +

                    "el.blur();",

                    fromDate
            );

            System.out.println("Date entered using JavaScript: " + fromDate);
        }


        Thread.sleep(1000);

        try {

            WebElement dateVerification = wait.until(ExpectedConditions.presenceOfElementLocated(fromDateLocator));

            String actualDate = dateVerification.getAttribute("value");

            System.out.println("Actual Payment From Date: " + actualDate);

        } catch (Exception e) {

            System.out.println("Could not verify Payment From Date.");
        }
       
        // SALESPERSON - SELECT ALL
  

        By salespersonDropdown = By.xpath("//select[starts-with(@id,'psalesman_id-')]" +"/following-sibling::span[" +"contains(@class,'select2-container')" +"]");


        wait.until(ExpectedConditions.elementToBeClickable(salespersonDropdown)).click();

        Thread.sleep(500);


      
        // Select "All"
        
        By allSalespersonOption = By.xpath("//li[contains(@class,'select2-results__option') " + "and normalize-space()='All']");


        wait.until(ExpectedConditions.elementToBeClickable(allSalespersonOption )).click();

    
        // TRANSACTION - SELECT ALL
       

        By transactionLocator = By.id("ptran_status");


        WebElement transaction = wait.until(ExpectedConditions.elementToBeClickable(transactionLocator));


        Select transactionSelect = new Select(transaction);

        transactionSelect.selectByVisibleText("All");

        System.out.println("Transaction: All");


      
        //  PAYMENT TYPE - SELECT ALL
    

        By paymentTypeLocator = By.id("ppayment_type");


        WebElement paymentType = wait.until(ExpectedConditions.elementToBeClickable(paymentTypeLocator));


        Select paymentTypeSelect = new Select(paymentType);

        paymentTypeSelect.selectByVisibleText("All");

        
        // APPLY
       

        By applyButton = By.xpath("//button[normalize-space()='Apply']");


        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();


      
    }
    
}