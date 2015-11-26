package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditarUsuarioPage {
	
	private WebDriver driver;
	
	public EditarUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void editar(String nome, String email) {
		driver.findElement(By.name("usuario.nome")).sendKeys(nome);
		driver.findElement(By.name("usuario.email")).sendKeys(email);
		driver.findElement(By.xpath("//button='Salvar!'")).click();
	}
		
	
}
