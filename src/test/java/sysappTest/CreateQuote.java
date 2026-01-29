package sysappTest;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateQuote {

	public void quotecreate(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Open Quote tab
		wait.until(ExpectedConditions.elementToBeClickable(By.id("quote_tab"))).click();
		int totalVehicles = 5;
		int quotesPerVehicle = 4;
		// ===== OUTER LOOP : VEHICLES =====
		for (int v = 1; v <= totalVehicles; v++) {
			System.out.println("Vehicle " + v + " quotations start");
			// ===== INNER LOOP : QUOTATIONS =====
			for (int q = 1; q <= quotesPerVehicle; q++) {
				System.out.println("  Creating quote " + q);
				// Create quotation
				wait.until(ExpectedConditions.elementToBeClickable(By.id("create_edit_prop"))).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doc_date")));
				// Open drop down
				WebElement vehicleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[normalize-space()='Select Vehicle']/following::span[contains(@class,'select2-selection')][1]")));
				vehicleDropdown.click();
				// Wait for select2 options
				WebElement resultsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='select2-results__options']")));
				// Get all enabled options
				List<WebElement> vehicleOptions = resultsContainer.findElements(By.xpath(".//li[contains(@class,'select2-results__option') and not(contains(@class,'select2-results__option--disabled'))]"));
				// Skip index 0 ("Select a vehicle")
				// v = 1 → index 1 (2nd option)
				// v = 5 → index 5 (6th option)
				vehicleOptions.get(v).click();
				// Select Product BC
				WebElement dropdownpt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='BC']")));
				js.executeScript("arguments[0].scrollIntoView(true);", dropdownpt);
				dropdownpt.click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@role='searchbox']"))).sendKeys("BC");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='BC']"))).click();
				// Select Coverage
				WebElement coverage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//select[@class='form-select form-select-sm'])[1]")));
				Select select = new Select(coverage);
				select.selectByValue("ALL");
// Select Product
				WebElement prodopt = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='checkbox'])[3]")));
				js.executeScript("arguments[0].scrollIntoView(true);", prodopt);
				driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();

				// ---------- RANDOM ADDONS ----------
				List<WebElement> addonCards = driver.findElements(By.xpath("//div[contains(@class,'card')]//h3[text()='Product Addons']/ancestor::div[contains(@class,'card')]"));

				Random random = new Random();
				for (WebElement card : addonCards) {
					List<WebElement> addons = card.findElements(By.xpath(".//input[@type='checkbox' and not(@disabled)]"));
					if (!addons.isEmpty()) {
						Collections.shuffle(addons);
						int count = random.nextInt(addons.size()) + 1;
						for (int i = 0; i < count; i++) {
							if (!addons.get(i).isSelected()) {
								js.executeScript("arguments[0].click();", addons.get(i));
							}
						}}}
				
				// Pricing
				WebElement disc = driver.findElement(By.id("ai.nx_info.adjust_amt"));
				disc.clear();
				disc.sendKeys("10");

				WebElement month = driver.findElement(By.id("ai.nx_info.fin_info.month"));
				month.clear();
				month.sendKeys("6");

				WebElement downpay = driver.findElement(By.id("ai.nx_info.down_payment.downp_amt"));
				downpay.clear();
				downpay.sendKeys("1");

				// Save & Close
				driver.findElement(By.id("btnSave")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnActions")));
				wait.until(ExpectedConditions.elementToBeClickable(By.id("btnClose"))).click();

				// Wait for next quote button
				wait.until(ExpectedConditions.elementToBeClickable(By.id("create_edit_prop")));
			}
		}
	}
}
