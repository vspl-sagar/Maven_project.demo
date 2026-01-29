
package sysappTest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddVehicle {

    public void addleadvehicle(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // List of VIN numbers
        List<String> vinNumbers = Arrays.asList(
                "1N6DD26S21C398738",
                "1B3EL36X94N166479",
                "1FTSW21P47EA83282",
                "2C3KA63H26H479928",
                "1D7HU18N05J595582"
        );

        for (String vin : vinNumbers) {

            System.out.println("Adding vehicle with VIN: " + vin);

            // Click Add Vehicle
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add Vehicle']"))).click();

            // Enter VIN
            WebElement vinField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vin_no")));
            vinField.clear();
            vinField.sendKeys(vin);

            // Decode VIN
            driver.findElement(By.xpath("(//button[@id='cust_ops'])[1]")).click();

            // Wait until vehicle details load
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h5[text()='Year']/following::span[contains(@class,'select2-selection__rendered')][1]"),"Select")));

            // Enter Odometer & Range
            driver.findElement(By.id("ai.odometer")).clear();
            driver.findElement(By.id("ai.odometer")).sendKeys("172000");

            driver.findElement(By.id("ai.ev_curr_range")).clear();
            driver.findElement(By.id("ai.ev_curr_range")).sendKeys("17000");

            // Vehicle issue section
            WebElement vehissue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ai.pq_info.is_any_veh_issue")));
            js.executeScript("arguments[0].scrollIntoView(true);", vehissue);
            vehissue.click();

            driver.findElement(By.id("ai.pq_info.veh_issue")).sendKeys("My vehicle is facing issue of Piston Block and chain sprocket");

            WebElement vehissueqtn = driver.findElement(By.id("ai.pq_info.is_com_purp"));
            vehissueqtn.click();

            driver.findElement(By.id("ai.pq_info.com_purp")).sendKeys("Not used yet");

            // Save vehicle
            driver.findElement(By.id("btnSave")).click();

            // Wait and close
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnEdit")));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("btnClose"))).click();

            // Small wait before next VIN
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add Vehicle']")));
        }
    }
}

