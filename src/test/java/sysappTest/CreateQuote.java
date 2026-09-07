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
	
		
		WebElement selectElement = driver.findElement(By.cssSelector("select.form-select.form-select-sm"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",selectElement);
		
		Select select = new Select(selectElement);
		select.selectByVisibleText("ALL");
		
		
		WebElement termDropdown = driver.findElement(By.xpath("//span[normalize-space()='Term']/following-sibling::select"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",termDropdown);

			termDropdown.click();

			Select term = new Select(termDropdown);
			term.selectByVisibleText("ALL");
		
		
			WebElement productAddons = driver.findElement(By.xpath("//h3[normalize-space()='Product Addons']"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});",productAddons);
		
			// Find all product addon checkboxes
			
			List<WebElement> addons = driver.findElements(By.xpath("//h3[normalize-space()='Product Addons']/ancestor::div[@class='row'][1]//input[@type='checkbox']"));
			// Shuffle the list randomly
			Collections.shuffle(addons);

			// Select 3 random addons
			int numberToSelect = 3;

			for (int i = 0; i < numberToSelect && i < addons.size(); i++) {
			    WebElement addon = addons.get(i);

			    if (!addon.isSelected()) {
			        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",addon);
			    }
			}
	
		//Enter month
			WebElement month = wait.until(ExpectedConditions.elementToBeClickable(By.id("ai.nx_info.fin_info.month")));
				month.clear();
				month.sendKeys("1");
		
		//Enter amount field value
			WebElement amount = wait.until(ExpectedConditions.elementToBeClickable(By.id("ai.nx_info.down_payment.downp_amt")));
				amount.clear();
				amount.sendKeys("100");
		//Click on Save 
		    WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave")));
				saveButton.click();
		
				// Wait until Cancel button becomes invisible
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnActions")));
		
			WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnClose")));
			closeButton.click();
				
		
		
		
	
	}}

