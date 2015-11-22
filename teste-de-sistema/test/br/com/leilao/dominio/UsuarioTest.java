package br.com.leilao.dominio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.factory.WebDriverFactory.Browser;
import br.com.leilao.pgobjects.NovoUsuarioPage;
import br.com.leilao.pgobjects.UsuariosPage;

public class UsuarioTest {

	private WebDriver driver;
	private UsuariosPage usuariosPage;
	private NovoUsuarioPage novoUsuarioPage;
	
	@Before
	public void init() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		this.usuariosPage = new UsuariosPage(driver);
		this.novoUsuarioPage = new NovoUsuarioPage(driver);
	}
	
	@After
	public void finaliza() {
		this.driver.close();
	}

	@Test
	public void deveCadastradorUsuario() {
		String nome = "Xavier Toledo", email = "xavier@email.com.br";
		usuariosPage.visita();
		usuariosPage.novo().cadastra(nome, email);;
		assertTrue(usuariosPage.existeNaListagem(nome, email));
	}
	
	@Test
	public void naoDeveCadastrarUsuarioSemNome() {
		novoUsuarioPage.visita();
		novoUsuarioPage.cadastra("", "test@email.com");
		assertFalse(novoUsuarioPage.cadastrado());
	}
	
	@Test
	public void naoDeveCadastrarUsuarioSemNomeEEmail() {
		novoUsuarioPage.visita();
		novoUsuarioPage.cadastra(null, null);
		assertFalse(novoUsuarioPage.cadastrado());
	}
	
	@Test
	public void deveExcluirUmUsuario() {
		String nome = "usuario", email = "usuario@email.com";
		
		usuariosPage.novo().cadastra(nome, email);
		assertTrue(usuariosPage.existeNaListagem(nome, email));
		
		usuariosPage.excluiUltimoUsuarioCadastrado();
		assertFalse(usuariosPage.existeNaListagem(nome, email));
	}
} 
