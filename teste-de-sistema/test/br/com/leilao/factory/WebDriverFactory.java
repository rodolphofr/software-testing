package br.com.leilao.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

public class WebDriverFactory {
	
	private static final String DIR_PATH = "C:/Users/Yeda/teste-de-sistema/driver/";
	private static final String CHROME_DRIVER = DIR_PATH + "/chromedriver.exe";
	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	
	public WebDriver factory(Browser browser) {
		System.setProperty(DRIVER_NAME, CHROME_DRIVER);
		switch (browser) {
		case FIREFOX:
			return new FirefoxDriver();
		case GOOGLE_CHROME:
			return new ChromeDriver(); 
		case INTERNET_EXPLORER:
			return new InternetExplorerDriver();
		case OPERA:
			return new OperaDriver();
		default:
			return null;
		}
	}

	public enum Browser {
		FIREFOX, 
		GOOGLE_CHROME, 
		INTERNET_EXPLORER,
		OPERA;
	}
}
