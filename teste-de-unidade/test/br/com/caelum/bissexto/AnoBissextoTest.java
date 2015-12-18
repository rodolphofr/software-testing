package br.com.caelum.bissexto;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnoBissextoTest {

	@After
	public void finaliza() {
		System.out.println("depois do metodo");
	}
	
	@BeforeClass
	public static void antesDaClasse() {
		System.out.println("antes da classe");
	}
	
	@AfterClass
	public static void depoisDaClasse() {
		System.out.println("depois da classe");
	}
	
	@Test
	public void deveVerificarSeOAnoEhBissexto() {
		AnoBissexto ano = new AnoBissexto();
		assertTrue(ano.ehBissexto(1600));
		assertTrue(ano.ehBissexto(2004));
		assertTrue(ano.ehBissexto(2016));
		assertFalse(ano.ehBissexto(1987));
		assertFalse(ano.ehBissexto(1993));
		assertFalse(ano.ehBissexto(2015));
	}

}
