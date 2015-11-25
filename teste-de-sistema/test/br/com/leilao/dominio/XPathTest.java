package br.com.leilao.dominio;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.factory.WebDriverFactory.Browser;

public class XPathTest {

	
	private WebDriver driver;
	
	@Before
	public void init() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		driver.get("http://www.original.com.br/");
	}
	
	@After
	public void finalize() {
		this.driver.close();
	}
	
	@Test
	public void devePegarTabela() {
		List<WebElement> elementos = driver.findElements(By.xpath("//*[@id='pf4']"));
	}
} 
