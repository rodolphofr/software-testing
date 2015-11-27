package br.com.leilao.pgobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetalhesDoLancePage {

	private WebDriver driver;

	public DetalhesDoLancePage(WebDriver driver) {
		this.driver = driver;
	}

	public void lance(String usuario, double valor) {
		Select combo = new Select(driver.findElement(By.name("lance.usuario.id")));
		combo.selectByVisibleText(usuario);
		
		driver.findElement(By.name("lance.valor")).sendKeys(String.valueOf(valor));
		driver.findElement(By.id("btnDarLance")).click();
	}
	
	public boolean existeLance(String usuario, double valor) {
		Boolean usuarioEncontrado = new WebDriverWait(driver, 10)
		.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("lancesDados")), usuario));
		
		if (usuarioEncontrado) return driver.getPageSource().contains(String.valueOf(valor));
		
		return false;
	}

}
