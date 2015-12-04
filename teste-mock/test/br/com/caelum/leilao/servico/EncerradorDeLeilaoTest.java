package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InOrder;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.infra.dao.EnviadorDeEmail;
import br.com.caelum.leilao.infra.dao.RepositorioLeiloes;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EncerradorDeLeilaoTest {
	
	private RepositorioLeiloes dao;
	private CriadorDeLeilao criador;
	private EncerradorDeLeilao encerrador;
	private EnviadorDeEmail carteiro;
	private InOrder inOrder;

	@Before
	public void setUp() {
		dao = mock(RepositorioLeiloes.class);
		carteiro = mock(EnviadorDeEmail.class);
		criador = new CriadorDeLeilao();
		encerrador = new EncerradorDeLeilao(dao, carteiro);
		inOrder = inOrder(dao, carteiro);
	}
	
	@Test
	public void deveEncerrarLeiloesAntigos() {
		Leilao leilao1 = criador.para("Geladeira Usada").naData(antiga()).constroi();
		Leilao leilao2 = criador.para("Notebook Novo").naData(antiga()).constroi();
		
		List<Leilao> leiloes = Arrays.asList(leilao1, leilao2);
		
		when(dao.correntes()).thenReturn(leiloes);

		encerrador.encerra();
		
		assertTrue(leilao1.isEncerrado());
		assertTrue(leilao2.isEncerrado());
		assertEquals(2, encerrador.getTotalEncerrados());
		verify(dao, atMost(2)).atualiza(leilao1);
		inOrder.verify(dao, times(1)).atualiza(leilao1);
		inOrder.verify(carteiro, times(1)).envia(leilao1);
	}
	
	@Test
	public void naoDeveEncerrarLeiloesQueComecaramNaSemanaAtual() {
		Leilao leilao1 = criador.para("Geladeira Usada").naData(ontem()).constroi();
		Leilao leilao2 = criador.para("Notebook Novo").naData(ontem()).constroi();
		
		when(dao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
		
		encerrador.encerra();
		
		assertFalse(leilao1.isEncerrado());
		assertFalse(leilao2.isEncerrado());
		assertEquals(0, encerrador.getTotalEncerrados());
		verify(dao, never()).atualiza(leilao1);
		verify(dao, never()).atualiza(leilao2);
	}
	
	@Test
	public void naoDeveEncerrarNenhumLeilao() {
		when(dao.correntes()).thenReturn(new ArrayList<Leilao>());
		encerrador.encerra();
		assertEquals(0, encerrador.getTotalEncerrados());
	}
	
	@Test
	public void deveAtualizarLeilao() {
		Leilao leilao = criador.para("Celular Novo").naData(antiga()).constroi();
		when(dao.correntes()).thenReturn(Arrays.asList(leilao));
		
		encerrador.encerra();
		
		verify(dao, times(1)).atualiza(leilao);
	}
	
	@Test
	public void deveLancarExcecaoQuandoDaoFalhar() {
		Leilao leilao1 = criador.para("Telefone").naData(antiga()).constroi();
		Leilao leilao2 = criador.para("Computador").naData(antiga()).constroi();
		
		when(dao.correntes()).thenReturn(Arrays.asList(leilao1, leilao2));
		doThrow(new RuntimeException()).when(dao).atualiza(leilao1);
		
		encerrador.encerra();
		
		verify(dao).atualiza(leilao1);
		verify(dao, timeout(0)).atualiza(leilao1);
	}
	
	private Calendar ontem() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar;
	}

	private Calendar antiga() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990, 5, 21);
		return calendar;
	}
}
