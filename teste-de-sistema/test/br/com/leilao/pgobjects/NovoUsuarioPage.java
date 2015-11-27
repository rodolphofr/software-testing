package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.leilao.dominio.base.URLBase;

public class NovoUsuarioPage {
	
	private WebDriver driver;
	
	public NovoUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get(new URLBase().getUrlBase() + "/usuarios/new");
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
