package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import br.com.leilao.dominio.base.URLBase;

public class NovoLeilaoPage {

	private WebDriver driver;
	
	public NovoLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visita() {
		driver.get(new URLBase().getUrlBase() + "/leiloes/new");
	}
	
	public void cadastraLeilao(String nome, double valor, String usuario, boolean ehUsado) {
		driver.findElement(By.name("leilao.nome")).sendKeys(nome);
		driver.findElement(By.name("leilao.valorInicial")).sendKeys(String.valueOf(valor));
		
		Select select = new Select(driver.findElement(By.name("leilao.usuario.id")));
		select.selectByVisibleText(usuario);
		
		if (ehUsado) {
			driver.findElement(By.name("leilao.usado")).click();
		}
		
		driver.findElement(By.xpath(".//*[@id='content']/form/div[5]/button")).click();
	}
	
	public boolean cadastrado() {
		return !driver.getPageSource().contains("Valor inicial deve ser maior que zero!") &&
				!driver.getPageSource().contains("Nome obrigatorio!") &&
				!driver.getPageSource().contains("???is_not_a_valid_number???");
	}
}
