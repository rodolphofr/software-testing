package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditarUsuarioPage {
	
	private WebDriver driver;
	
	public EditarUsuarioPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void editar(String nome, String email) {
		WebElement nomeTxt = driver.findElement(By.name("usuario.nome"));
		nomeTxt.clear();
		nomeTxt.sendKeys(nome);
		
		WebElement emailTxt = driver.findElement(By.name("usuario.email"));
		emailTxt.clear();
		emailTxt.sendKeys(email);
		
		driver.findElement(By.xpath("//*[@id='btnSalvar']")).click();
	}
		
	
}
