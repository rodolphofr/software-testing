package init;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class VotacaoNick {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://kca.mundonick.com.br/");
		
		Thread.sleep(7000);
		
		irParaVotacao(driver);
		
		for (int i = 0; i < 100000; i++) {
			votar(driver);
			voltarParaOQuiz(driver);
		}	
		
		driver.close();
	}
	
	/**
	 * Vota em coisas de babaca
	 * @param driver
	 * @throws InterruptedException 
	 */
	private static void votar(WebDriver driver) throws InterruptedException {
		clicarNoBonitao(driver);
	}
	
	private static void clicarNaMeleca(WebDriver driver) throws InterruptedException {
		WebElement fotoMeleca = driver.findElement(By.className("last"));
		fotoMeleca.click();
	}
	
	private static void clicarNoBonitao(WebDriver driver) throws InterruptedException {
		WebElement fotoBonitao = driver.findElement(By.className("first"));
		fotoBonitao.click();
	}
	
	private static void irParaVotacao(WebDriver driver) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		actions.sendKeys(Keys.PAGE_DOWN).perform();
		Thread.sleep(2000); //espera 2 segundos
	}
	
	private static void voltarParaOQuiz(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		WebElement votarDeNovo = driver.findElement(By.linkText("VOTE DE NOVO"));
		votarDeNovo.click();
	}
}
