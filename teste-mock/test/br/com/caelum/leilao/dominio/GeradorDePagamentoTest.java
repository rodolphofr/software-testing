package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.infra.repositorios.RepositorioDePagamentos;
import br.com.caelum.leilao.servico.Avaliador;

public class GeradorDePagamentoTest {

	@Test
	public void deveReceberPagamentoComoMaiorLanceDado() {
		LeilaoDao leilaoDao = mock(LeilaoDao.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
		Avaliador avaliador = new Avaliador();

		Leilao leilao = new CriadorDeLeilao().para("Playstation").lance(new Usuario("Paulo José"), 1500.00)
				.lance(new Usuario("Maria José"), 2000.00).lance(new Usuario("Afranio"), 2400.00)
				.lance(new Usuario("Domingues"), 2900.00).constroi();

		when(leilaoDao.encerrados()).thenReturn(Arrays.asList(leilao));

		GeradorDePagamento geradorDePagamento = new GeradorDePagamento(leilaoDao, avaliador, pagamentos);
		geradorDePagamento.gera();

		ArgumentCaptor<Pagamento> argument = ArgumentCaptor.forClass(Pagamento.class);

		verify(pagamentos).salvar(argument.capture());

		Pagamento pagamento = argument.getValue();

		assertEquals(avaliador.getMaiorLance(), pagamento.getValor(), 0.00001);
	}

	@Test
	public void deveReceberPagamentosApenasEmDiaUteis() {
		LeilaoDao leilaoDao = mock(LeilaoDao.class);
		RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);
		RelogioDoSistema relogio = mock(RelogioDoSistema.class);

		Leilao leilao = new CriadorDeLeilao().para("Playstation")
				.lance(new Usuario("Paulo José"), 1500.00)
				.lance(new Usuario("Maria José"), 2000.00)
				.lance(new Usuario("Afranio"), 2400.00)
				.lance(new Usuario("Domingues"), 2900.00)
				.constroi();

		when(leilaoDao.encerrados()).thenReturn(Arrays.asList(leilao));
		when(relogio.hoje()).thenReturn(domingo());

		GeradorDePagamento gerador = new GeradorDePagamento(leilaoDao, new Avaliador(), 
				pagamentos, relogio);
		gerador.gera();
		
		ArgumentCaptor<Pagamento> argument = ArgumentCaptor.forClass(Pagamento.class);
		
		verify(pagamentos).salvar(argument.capture());
		
		Pagamento pagamento = argument.getValue();
		
		assertEquals(Calendar.MONDAY, pagamento.getData().get(Calendar.DAY_OF_WEEK));
		assertEquals(9, pagamento.getData().get(Calendar.DAY_OF_MONTH));
		
	}

	private Calendar sabado() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, Calendar.APRIL, 7);
		return calendar;
	}

	private Calendar domingo() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, Calendar.APRIL, 8);
		return calendar;
	}
}
