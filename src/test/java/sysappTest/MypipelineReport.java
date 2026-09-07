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

public class MypipelineReport {

    public void mypipeline(WebDriver driver, String leadRefId)
            throws InterruptedException {

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));

       
        // Click My Pipeline

        By myPipeline = By.xpath("//a[@route='/sls/report/my-pipeline-rpt']");

        wait.until(ExpectedConditions.elementToBeClickable(myPipeline)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pfrom_date")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pto_date")));

        LocalDate toDate = LocalDate.now();

        LocalDate fromDate = toDate.minusDays(10);

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MM/dd/yyyy");

        String fromDateValue = fromDate.format(formatter);
        String toDateValue = toDate.format(formatter);

        System.out.println("-----------------------------------------");
        System.out.println("My Pipeline Report Date Range");
        System.out.println("From Date : " + fromDateValue);
        System.out.println("To Date   : " + toDateValue);
        System.out.println("-----------------------------------------");
       //Set from date
        setDate(driver,wait,By.id("pfrom_date"),fromDateValue);
        //Set To date 
        setDate(
                driver,wait,By.id("pto_date"),toDateValue);

        // Click Apply
 
        By applyButton = By.xpath("//button[normalize-space()='Apply']");

        wait.until(ExpectedConditions.elementToBeClickable(applyButton)).click();

       // Open Export dropdown
     
        By exportDropdown = By.xpath("//button[contains(@class,'btn-subtle-info') " + "and contains(@class,'dropdown-toggle')]");

        wait.until(ExpectedConditions.elementToBeClickable(exportDropdown)).click();

        // ============================================================
        // 8. Click Export
        // ============================================================

        By exportOption = By.xpath(
                "//ul[contains(@class,'dropdown-menu')]//a[" +
                "@cwf-export-click='sysApp.myPipeline.getData' " +
                "and normalize-space()='Export']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(exportOption)
        ).click();

        // ============================================================
        // 9. Salesperson Search
        // ============================================================

        By salespersonSearch = By.id("salesperson-filter");

        WebElement searchbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        salespersonSearch
                )
        );

        searchbox.sendKeys("Sagar(Subaff1)");

        searchbox.sendKeys(Keys.CONTROL, "a");

        searchbox.sendKeys("Autouser");

        // ============================================================
        // 10. Lead Reference ID Search
        // ============================================================

        By leadIdSearch = By.id("lead_ref_id-filter");

        WebElement leadIDsearch = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        leadIdSearch
                )
        );

        leadIDsearch.sendKeys("12608-3891");

        leadIDsearch.sendKeys(Keys.CONTROL, "a");

        leadIDsearch.sendKeys(leadRefId);

        leadIDsearch.sendKeys(Keys.ENTER);

        // ============================================================
        // 11. Lead Status Filter
        // ============================================================

        By leadStatus = By.id("lead_status-filter");

        WebElement leadStatusFilter = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        leadStatus
                )
        );

        leadStatusFilter.sendKeys("Enrolled");

        leadStatusFilter.sendKeys(Keys.CONTROL, "a");

        leadStatusFilter.sendKeys("Quotation");

        leadStatusFilter.sendKeys(Keys.CONTROL, "a");

        // ============================================================
        // 12. Click Lead Status Filter
        // ============================================================

        By leadSearchFilter = By.xpath(
                "//div[@id='lead_status-filter-cont']//a"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        leadSearchFilter
                )
        ).click();

        // ============================================================
        // 13. Deselect Quotation/Proposal
        // ============================================================

        By quotationCheckbox = By.xpath(
                "//input[@type='checkbox' " +
                "and @name='msli[]' " +
                "and following-sibling::label[" +
                "normalize-space()='Quotation/Proposal']]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        quotationCheckbox
                )
        ).click();

        // ============================================================
        // 14. State Filter
        // ============================================================

        By stateFilter = By.id("us_state_code-filter");

        WebElement state = wait.until(
                ExpectedConditions.elementToBeClickable(
                        stateFilter
                )
        );

        state.sendKeys("NY");

        state.sendKeys(Keys.CONTROL, "a");

        state.sendKeys("1");

        state.sendKeys(Keys.CONTROL, "a");

        // ============================================================
        // 15. Disposition
        // ============================================================

        By dispositionLocator = By.id("reason-filter");

        WebElement disposition = wait.until(
                ExpectedConditions.elementToBeClickable(
                        dispositionLocator
                )
        );

        disposition.sendKeys("Fail");

        disposition.sendKeys(Keys.CONTROL, "a");

        disposition.sendKeys("Attempting to Contact");

        // ============================================================
        // 16. Affiliate Search
        // ============================================================

        By affiliateSearch = By.id("afl_name-filter");

        WebElement affSearchBox = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        affiliateSearch
                )
        );

        // Scroll horizontally/vertically until Affiliate field
        // is visible

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({" +
                "behavior:'instant'," +
                "block:'center'," +
                "inline:'center'" +
                "});",
                affSearchBox
        );

        // ============================================================
        // 17. Final Apply
        // ============================================================

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        applyButton
                )
        ).click();

        System.out.println(
                "My Pipeline report completed successfully."
        );
    }


    // ================================================================
    // Reusable Date Method
    // ================================================================

    private void setDate(
            WebDriver driver,
            WebDriverWait wait,
            By locator,
            String date)
            throws InterruptedException {

        // ------------------------------------------------------------
        // Find the field
        // ------------------------------------------------------------

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator
                )
        );

        // ------------------------------------------------------------
        // Scroll into view
        // ------------------------------------------------------------

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({" +
                "behavior:'instant'," +
                "block:'center'," +
                "inline:'center'" +
                "});",
                field
        );

        Thread.sleep(300);

        // ------------------------------------------------------------
        // Click the field
        // ------------------------------------------------------------

        wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();

        Thread.sleep(500);

        // ------------------------------------------------------------
        // IMPORTANT:
        // The application can re-render the date input after click.
        // Therefore find the element AGAIN.
        // ------------------------------------------------------------

        field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        locator
                )
        );

        // ------------------------------------------------------------
        // Select the existing date
        // ------------------------------------------------------------

        field.sendKeys(Keys.CONTROL, "a");

        Thread.sleep(200);

        // ------------------------------------------------------------
        // Enter new date
        // ------------------------------------------------------------

        field.sendKeys(date);

        Thread.sleep(300);

        // ------------------------------------------------------------
        // TAB triggers blur/change handling in many applications
        // ------------------------------------------------------------

        field.sendKeys(Keys.TAB);

        Thread.sleep(500);

        System.out.println(
                "Date entered: " + date
        );
    }
}
