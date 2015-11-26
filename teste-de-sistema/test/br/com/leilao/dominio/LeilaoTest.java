package br.com.leilao.dominio;

import java.util.Date;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.factory.WebDriverFactory.Browser;
import br.com.leilao.pgobjects.NovoLeilaoPage;
import br.com.leilao.pgobjects.LeiloesPage;
import br.com.leilao.util.Util;

public class LeilaoTest {

	private WebDriver driver;
	private LeiloesPage leiloesPage;
	private NovoLeilaoPage leilaoPage;
	
	@Before
	public void setUp() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		this.leiloesPage = new LeiloesPage(driver);
		this.leilaoPage = new NovoLeilaoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.close();
	}
	
	@Test
	public void deveCadastrarUmLeilao() {
		String nome = "Geladeira", usuario = "Rodolpho Rodrigues";
		
		leilaoPage.visita();
		Util.wait(2000);
		leilaoPage.cadastraLeilao(nome, 123.00, usuario, true);
		
		assertTrue(leilaoPage.cadastrado());
		assertTrue(leiloesPage.existeLeilaoCadastrado(nome, usuario, Util.format(new Date())));
	}
	
	@Test
	public void naoDeveCadastrarLeilaoSemNomeOuValorInicial() {
		leilaoPage.visita();
		Util.wait(2000);
		leilaoPage.cadastraLeilao("", 0, "Sandra Alencar", false);
		assertFalse(leilaoPage.cadastrado());
	}
	
	
}
