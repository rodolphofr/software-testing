package br.com.leilao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import br.com.leilao.factory.WebDriverFactory;
import br.com.leilao.factory.WebDriverFactory.Browser;

public class Util {
	
	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);
	}
	
	public static void wait(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void limparBaseDeDados() {
		WebDriver driver = new WebDriverFactory().factory(Browser.FIREFOX);
		driver.get("http://localhost:8080/apenas-teste/limpa");
		driver.close();
	}
}
