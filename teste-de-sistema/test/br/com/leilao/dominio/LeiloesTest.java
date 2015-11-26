package br.com.leilao.dominio;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.factory.WebDriverFactory.Browser;
import br.com.leilao.pgobjects.LeiloesPage;

public class LeiloesTest {
	
	private WebDriver driver;
	private LeiloesPage leiloesPage;
	
	@Before
	public void setUp() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		this.leiloesPage = new LeiloesPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	
}
