package br.com.leilao.pgobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.leilao.dominio.URLBase;

public class UsuariosPage {

	private WebDriver driver;
	
	public UsuariosPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get(new URLBase().getUrlBase() + "/usuarios");
	}
	
	public NovoUsuarioPage novo() {
		driver.findElement(By.linkText("Novo Usuário")).click();
		return new NovoUsuarioPage(driver);
	}
	
	public boolean existeNaListagem(String nome, String email) {
		return driver.getPageSource().contains(nome) && driver.getPageSource().contains(email);
	}
	
	public void editarUsuario(String email) {
		driver.findElement(By.xpath("//tr[td//text()[contains(., '"+ email +"')]]/td[3]/a[2]")).click();
	}
	
	public void excluiUltimoUsuarioCadastrado() {
		List<WebElement> botoes = driver.findElements(By.tagName("button"));
		botoes.get(botoes.size() - 1).click(); //pega e clica no botao do ultimo elemento inserido na lista
		driver.switchTo().alert().accept();
	}
}
