package init;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizado {
	
	private static final String DIR_PATH = System.getProperty("user.dir") + "/lib";
	private static final String CHROME_DRIVER = DIR_PATH + "/chromedriver.exe";
	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty(DRIVER_NAME, CHROME_DRIVER); 

		WebDriver driver = getFirefoxDriver();  
		driver.manage().window().maximize(); //fullscreen
		
		driver.get("http://www.google.com.br/"); 
	
		WebElement campoDePesquisa = driver.findElement(By.name("q"));
		
		Thread.sleep(2000);

		campoDePesquisa.sendKeys("Lucas Pagliuca");
		
		Thread.sleep(1000);
		
		campoDePesquisa.submit();
		
		Thread.sleep(1000);
		
		WebElement linkLucas = driver.findElement(By.linkText("Lucas Pagliuca Profiles | Facebook"));
		linkLucas.click();
		
		Thread.sleep(10000);
		
		WebElement linkLucasFacebook = driver.findElement(By.xpath(".//*[@id='all_search_results']/div[1]/div[1]/div/div/a/img"));
		linkLucasFacebook.click();
		
		System.out.println("ESSA PORRA FUNCIONA!!!");
	}
	
	public static WebDriver getFirefoxDriver() {
		return new FirefoxDriver();
	}
	
	public static WebDriver getChromeDriver() {
		return new ChromeDriver();
	}
	
}
