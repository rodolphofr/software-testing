package br.com.leilao.dominio;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import br.com.leilao.dominio.cenario.Cenario;
import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.pgobjects.DetalhesDoLancePage;
import br.com.leilao.pgobjects.LeiloesPage;
import br.com.leilao.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeiloesTest {
	
	private WebDriver driver;
	private LeiloesPage leiloesPage;
	
	@Before
	public void setUp() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		this.leiloesPage = new LeiloesPage(driver);
		Util.limparBaseDeDados(driver);
		Util.wait(5000);
		new Cenario(driver)
				.criaUsuario("Cris Zena", "criz@opa.com")
				.criaUsuario("Ronaldo Cruz", "rona@opa.com")
				.criaLeilao("Casa Roubada", 500000, "Cris Zena", true)
				.criaLeilao("Roupa de Marca", 2000, "Ronaldo Cruz", false);
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	
	@Test
	public void deveDarLance() {
		DetalhesDoLancePage detalhesDoLancePage = leiloesPage.detalhes(2);
		detalhesDoLancePage.lance("Cris Zena", 12400.0);
		assertTrue(detalhesDoLancePage.existeLance("Cris Zena", 12400.00));
	}
	
}
