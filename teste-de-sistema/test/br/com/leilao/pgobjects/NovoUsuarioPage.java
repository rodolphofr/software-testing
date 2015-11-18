package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoUsuarioPage {
	
	private WebDriver driver;
	private static final String CADASTRO_USUARIO_URL = "http://localhost:8080/usuarios/new";
	
	public NovoUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get(CADASTRO_USUARIO_URL);
	}
	
	public void cadastra(String nome, String email) {
		WebElement txtNome = driver.findElement(By.name("usuario.nome"));
		WebElement txtEmail = driver.findElement(By.name("usuario.email"));
		txtNome.sendKeys(nome);
		txtEmail.sendKeys(email);
		
		driver.findElement(By.id("btnSalvar")).click();
	}
	
	public boolean cadastrado() {
		return (!driver.getPageSource().contains("Nome obrigatorio!")) &&
			   (!driver.getPageSource().contains("E-mail obrigatorio!"));
	}

	
}
