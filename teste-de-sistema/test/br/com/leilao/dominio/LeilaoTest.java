package br.com.leilao.dominio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import br.com.leilao.dominio.cenario.Cenario;
import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.pgobjects.LeiloesPage;
import br.com.leilao.pgobjects.NovoLeilaoPage;
import br.com.leilao.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeilaoTest {

	private WebDriver driver;
	private LeiloesPage leiloesPage;
	private NovoLeilaoPage leilaoPage;
	
	@Before
	public void setUp() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		this.leiloesPage = new LeiloesPage(driver);
		this.leilaoPage = new NovoLeilaoPage(driver);
		Util.limparBaseDeDados(driver);
		new Cenario(driver).criaUsuario("Rodolpho Rodrigues", "rodolpho@email.com");
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
		assertTrue(leiloesPage.existeLeilaoCadastrado(nome, usuario, Util.formatDate(new Date())));
	}
	
	@Test
	public void naoDeveCadastrarLeilaoSemNomeOuValorInicial() {
		leilaoPage.visita();
		Util.wait(2000);
		leilaoPage.cadastraLeilao("", 0, "Rodolpho Rodrigues", false);
		assertFalse(leilaoPage.cadastrado());
	}
	
	
}
