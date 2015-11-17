package br.com.caelum.leilao.dominio;

import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.LeilaoBuilder;

public class LanceTest {

	private Usuario usuario;

	@Before
	public void setUp() {
		usuario = new Usuario("Fulano");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarValoresIgualAZero() {
		new Lance(usuario, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarValoresNegativos() {
		new Lance(usuario, 10);
	}

	@Test
	public void deveConterUmLance() {
		Leilao leilao = new LeilaoBuilder().para("Mouse Óptico").build();
		
		Lance lance = new Lance(usuario, 400.0);
		leilao.propoe(lance);
		
		assertThat(leilao, temUmLance(lance));
	}
}
