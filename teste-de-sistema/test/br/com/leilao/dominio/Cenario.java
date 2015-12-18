package br.com.leilao.dominio;

import org.openqa.selenium.WebDriver;

import br.com.leilao.pgobjects.NovoLeilaoPage;
import br.com.leilao.pgobjects.NovoUsuarioPage;

public class Cenario {

	private WebDriver driver;

	public Cenario(WebDriver driver) {
		this.driver = driver;
	}

	public Cenario criaUsuario(String nome, String email) {
		NovoUsuarioPage novoUsuarioPage = new NovoUsuarioPage(driver);
		novoUsuarioPage.visita();
		novoUsuarioPage.cadastra(nome, email);
		return this;
	}

	public Cenario criaLeilao(String nome, double valor, String usuario, boolean ehUsado) {
		NovoLeilaoPage novoLeilaoPage = new NovoLeilaoPage(driver);
		novoLeilaoPage.visita();
		novoLeilaoPage.cadastraLeilao(nome, valor, usuario, ehUsado);
		return this;
	}
	

}
