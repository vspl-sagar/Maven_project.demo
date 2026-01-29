package testUtils;

	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.edge.EdgeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.firefox.FirefoxOptions;

	public class baseutilspkg {
		  private WebDriver driver;
		public WebDriver startUp(String bName) {
			
			
			
			if(bName.equalsIgnoreCase("ch") || bName.equalsIgnoreCase("chrome")) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("--start-maximized");
				driver=new ChromeDriver(options);
			}
			else if(bName.equalsIgnoreCase("edge")){
				EdgeOptions options=new EdgeOptions();
				options.addArguments("--start-maximized");
				driver=new EdgeDriver(options);
			}
			else if(bName.equalsIgnoreCase("ff") || bName.equalsIgnoreCase("firefox")) {
				FirefoxOptions options=new FirefoxOptions();
				options.addArguments("--start-maximized");
				driver=new FirefoxDriver(options);
			}
			else {
				System.out.println("Invalid Browser Name..!!");
			}	
			driver.get("https://uat.sysappai.net/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.findElement(By.id("usercaptcha-user_name")).sendKeys("Autouser");
			driver.findElement(By.id("usercaptcha-verifycode")).sendKeys("12345");
			driver.findElement(By.id("login-button")).click();
	        driver.findElement(By.id("user_pass")).sendKeys("Sagar123#");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			return driver;
		}
		}




