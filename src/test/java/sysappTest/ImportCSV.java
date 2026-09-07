package sysappTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ImportCSV {

	public void impcsv(WebDriver driver, String leadRefId) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nv-mnuSalesUtils']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Import CSV']"))).click();
		WebElement Impcsv=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='csv_type_en']")));
		Impcsv.click();
		Select select=new Select(Impcsv);
		select.selectByVisibleText("Prospective Lead CSV");
		WebElement fileInput = driver.findElement(By.xpath("//input[@id='csv_upload']"));

	    fileInput.sendKeys("/home/vspl/Desktop/Prospective Lead Upload file");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload']"))).click();
		

        WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='btnYes']")));

        yesButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Import CSV']"))).click();

		
		
	}
	
	
	
	
}
