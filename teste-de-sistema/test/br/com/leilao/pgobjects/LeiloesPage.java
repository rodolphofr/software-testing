package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeiloesPage {
	
	private WebDriver driver;
	
	public LeiloesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get("http://localhost:8080/leiloes");
	}
	
	public NovoLeilaoPage novo() {
		driver.findElement(By.xpath("//a='Novo Leilão'")).click();
		return new NovoLeilaoPage(driver);
	}
	
	public boolean existeLeilaoCadastrado(String nome, String usuario, String dataAbertura) {
		return driver.getPageSource().contains(nome) &&
				driver.getPageSource().contains(usuario) &&
				driver.getPageSource().contains(dataAbertura);
	}
}
