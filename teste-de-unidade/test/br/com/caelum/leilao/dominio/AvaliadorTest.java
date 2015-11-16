package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.LeilaoBuilder;

public class AvaliadorTest {

	private static final double DELTA = 0.00001;
	private Avaliador leiloeiro;
	private Usuario fulano;
	private Usuario ciclano;
	private Usuario beltrano;

	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.fulano = new Usuario("Fulano");
		this.ciclano = new Usuario("Ciclano");
		this.beltrano = new Usuario("Beltrano");
	}
	
	@Test(expected=RuntimeException.class)
	public void deveEntenderLeilaoComNenhumLance() {
		Leilao leilao = new LeilaoBuilder().build();
		leiloeiro.avalia(leilao);
	}
	
	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(ciclano, 1000.0)
				.build();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getTresMaioresLances().size(), equalTo(1));
		assertThat(leiloeiro.getMaiorLance(), equalTo(1000.0));
		assertThat(leiloeiro.getMenorLance(), equalTo(1000.0));
	}
	
	@Test
	public void deveDevolverOMaiorLance() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 300.0)
				.lance(beltrano, 500.0)
				.lance(ciclano, 200.0)
				.build();
		
		leiloeiro.avalia(leilao);
		assertThat(leiloeiro.getMaiorLance(), equalTo(500.0));
	}
	
	@Test
	public void deveDevolverOMenorLance() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 300.0)
				.lance(beltrano, 500.0)
				.lance(ciclano, 600.0)
				.build();
		
		leiloeiro.avalia(leilao);
		
		assertThat(leiloeiro.getMenorLance(), equalTo(300.0));
	}
	
	@Test
	public void deveDevolverOsTresMaioresLances() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 300.0)
				.lance(beltrano, 500.0)
				.lance(ciclano, 200.0)
				.lance(fulano, 600.0)
				.lance(beltrano, 900.0)
				.build();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> lances = leiloeiro.getTresMaioresLances();
		
		assertThat(lances.size(), equalTo(3));
		assertEquals(900.0, lances.get(0).getValor(), DELTA);
		assertEquals(600.0, lances.get(1).getValor(), DELTA);
		assertEquals(500.0, lances.get(2).getValor(), DELTA);
	}
}
