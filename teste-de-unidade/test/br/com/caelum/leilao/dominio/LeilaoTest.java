package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.LeilaoBuilder;

public class LeilaoTest {

	private Usuario fulano;
	private Usuario ciclano;
	private Usuario beltrano;

	@Before
	public void setUp() {
		this.fulano = new Usuario("Fulano");
		this.ciclano = new Usuario("Ciclano");
		this.beltrano = new Usuario("Beltrano");
	}
	
	@Test
	public void deveDesconsiderarLancesSeguidosDeUmMesmoUsuario() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 3000)
				.lance(fulano, 4000)
				.build();
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(3000, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 3000)
				.lance(ciclano, 4000)
				.lance(fulano, 5000)
				.lance(ciclano, 6000)
				.lance(fulano, 7000)
				.lance(ciclano, 8000)
				.lance(fulano, 9000)
				.lance(ciclano, 10000)
				.lance(fulano, 11000)
				.lance(ciclano, 12000)
				.lance(fulano, 16000)
				.build();
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(12000, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
	}
	
	@Test
	public void deveDobrarLance() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 15)
				.lance(ciclano, 20)
				.lance(beltrano, 35)
				.dobra(fulano)
				.dobra(ciclano)
				.build();
		
		Lance ultimoLance = leilao.getLances().get(leilao.getLances().size() - 1);

		assertEquals(5, leilao.getLances().size());
		assertEquals(40, ultimoLance.getValor(), 0.00001);
	}
	
	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new LeilaoBuilder().para("Radio AM/FM")
				.lance(fulano, 15)
				.lance(ciclano, 20)
				.dobra(beltrano)
				.build();
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(20, leilao.getLances().get(1).getValor(), 0.00001);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
