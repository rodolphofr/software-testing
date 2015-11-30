package br.com.leilao.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import br.com.leilao.dominio.Browser;

public class WebDriverFactory {
	
	private static final String DIR_PATH = System.getProperty("user.dir") + "/driver";
	private static final String CHROME_DRIVER = DIR_PATH + "/chromedriver.exe";
	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	
	public WebDriver factory(Browser browser) {
		switch (browser) {
		case FIREFOX:
			return new FirefoxDriver();
		case CHROME:
			System.setProperty(DRIVER_NAME, CHROME_DRIVER);
			return new ChromeDriver(); 
		case INTERNET_EXPLORER:
			return new InternetExplorerDriver();
		default:
			return null;
		}
	}

	protected static void fechar(WebDriver driver) { 
		if (driver != null) {
			driver.close();
		}
	}
	
}
