package br.com.leilao.dominio;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;

import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.factory.WebDriverFactory.Browser;
import br.com.leilao.pgobjects.EditarUsuarioPage;
import br.com.leilao.pgobjects.NovoUsuarioPage;
import br.com.leilao.pgobjects.UsuariosPage;
import br.com.leilao.util.Util;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTest {

	private WebDriver driver;
	private UsuariosPage usuariosPage;
	private NovoUsuarioPage novoUsuarioPage;
	private EditarUsuarioPage editarUsuarioPage;
	
	@Before
	public void init() {
		this.driver = new WebDriverFactory().factory(Browser.FIREFOX);
		this.usuariosPage = new UsuariosPage(driver);
		this.novoUsuarioPage = new NovoUsuarioPage(driver);
		this.editarUsuarioPage = new EditarUsuarioPage(driver);
	}
	
	@After
	public void finaliza() {
		this.driver.close();
	}

//	@Test
//	public void deveCadastradorUsuario() {
//		String nome = "Xavier Toledo", email = "xavier@email.com.br";
//		usuariosPage.visita();
//		Util.wait(2000);
//		usuariosPage.novo().cadastra(nome, email);;
//		assertTrue(usuariosPage.existeNaListagem(nome, email));
//	}
//	
//	@Test
//	public void naoDeveCadastrarUsuarioSemNome() {
//		novoUsuarioPage.visita();
//		Util.wait(2000);
//		novoUsuarioPage.cadastra("", "test@email.com");
//		assertFalse(novoUsuarioPage.cadastrado());
//	}
//	
//	@Test
//	public void naoDeveCadastrarUsuarioSemNomeEEmail() {
//		novoUsuarioPage.visita();
//		Util.wait(2000);
//		novoUsuarioPage.cadastra(null, null);
//		assertFalse(novoUsuarioPage.cadastrado());
//	}
//	
//	@Test
//	public void deveExcluirUmUsuario() {
//		String nome = "usuario", email = "usuario@email.com";
//		
//		usuariosPage.visita();
//		Util.wait(2000);
//		usuariosPage.novo().cadastra(nome, email);
//		assertTrue(usuariosPage.existeNaListagem(nome, email));
//		
//		usuariosPage.excluiUltimoUsuarioCadastrado();
//		assertFalse(usuariosPage.existeNaListagem(nome, email));
//	}
	
	@Test
	public void deveAlterarUmUsuario() {
		String nome = "Feliciano Pastor", email = "feliciano@propina.com.br";
		
		novoUsuarioPage.visita();
		Util.wait(2000);
		
		novoUsuarioPage.cadastra(nome, email);
		
		assertTrue(novoUsuarioPage.cadastrado());
		
		usuariosPage.existeNaListagem(nome, email);
		
		usuariosPage.editarUsuario(email);
		Util.wait(2000);
		
		String novoEmail = "feliciano@ladrao.com.br";
		
		editarUsuarioPage.editar(nome, novoEmail);
		assertTrue(usuariosPage.existeNaListagem(nome, novoEmail));
	}
	
} 
