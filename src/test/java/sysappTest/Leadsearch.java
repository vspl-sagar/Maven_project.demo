package sysappTest;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class Leadsearch {

	    WebDriver driver;

	    public void search(WebDriver driver, String leadRefId) {

	        driver.findElement(By.xpath("//span[normalize-space()='Lead Search']")).click();

	        driver.findElement(By.id("plead_ref_id")).sendKeys(leadRefId);

	        driver.findElement(By.xpath("//button[normalize-space()='Apply']")).click();
	    }
	}


