package br.com.leilao.dominio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GeraEvidencia {
	
	public static void gerar(List<Evidencia> evidencias, String casoDeTeste) {
		int index = 1;
		for (Evidencia evidencia : evidencias) {
			try {
				BufferedImage bufferedImage = ImageIO.read(evidencia.getImagem());
				ImageIO.write(bufferedImage, evidencia.getDescricao(), new File("C:\\Users\\Yeda\\Documents\\"+ (index++) +".jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static File screenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

}
