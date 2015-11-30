package br.com.leilao.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import br.com.leilao.dominio.Browser;
import br.com.leilao.factory.WebDriverFactory;

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
	
	public void screenShot(WebDriver driver) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyDirectory(srcFile, new File("C:\\Users\\RODOLROD\\Documents\\screenshots\\screenshot.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
