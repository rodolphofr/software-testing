package init;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAutomatizado {
	
	private static final String DIR_PATH = "C:/Users/Yeda/teste-automatizado/lib/";
	private static final String CHROME_DRIVER = DIR_PATH + "/chromedriver.exe";
	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	
	public static void main(String[] args) {
		System.setProperty(DRIVER_NAME, CHROME_DRIVER); //necess�rio para importar o driver do chrome
		
		WebDriver driver = getChromeDriver(); //driver respons�vel pelo firefox (abre navegador)
		
		driver.get("http://www.google.com.br/"); //url que deseja acessar pelo browser

		WebElement element = driver.findElement(By.name("q")); //digita no campo com nome "q"
		element.sendKeys("caelum");
		
		element.submit();
	}
	
	public static WebDriver getFirefoxDrive() {
		return new FirefoxDriver();
	}
	
	public static WebDriver getChromeDriver() {
		return new ChromeDriver();
	}
	
}
