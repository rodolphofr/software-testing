package br.com.leilao.dominio;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class TestUtil {
	
	public static void wait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

}
