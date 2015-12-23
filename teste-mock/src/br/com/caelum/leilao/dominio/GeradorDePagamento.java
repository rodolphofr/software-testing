package br.com.caelum.leilao.dominio;

import java.util.Calendar;
import java.util.List;

import br.com.caelum.leilao.infra.dao.LeilaoDao;
import br.com.caelum.leilao.infra.relogio.Relogio;
import br.com.caelum.leilao.infra.repositorios.RepositorioDePagamentos;
import br.com.caelum.leilao.servico.Avaliador;

public class GeradorDePagamento {

	private final LeilaoDao leilaoDao;
	private final Avaliador avaliador;
	private final RepositorioDePagamentos pagamentos;
	private final RelogioDoSistema relogio;

	public GeradorDePagamento(LeilaoDao leilaoDao, Avaliador avaliador, 
								RepositorioDePagamentos pagamentos, RelogioDoSistema relogio) {
		this.leilaoDao = leilaoDao;
		this.avaliador = avaliador;
		this.pagamentos = pagamentos;
		this.relogio = relogio;
	}
	
	public GeradorDePagamento(LeilaoDao leilaoDao, Avaliador avaliador, 
			RepositorioDePagamentos pagamentos) {
		this(leilaoDao, avaliador, pagamentos, new RelogioDoSistema());
	}

	public void gera() {
		List<Leilao> encerrados = leilaoDao.encerrados();

		for (Leilao leilao : encerrados) {
			avaliador.avalia(leilao);

			Pagamento pagamento = new Pagamento(avaliador.getMaiorLance(), diaUtil());
			pagamentos.salvar(pagamento);
		}
	}
	
	private Calendar diaUtil() {
		Calendar calendar = relogio.hoje();
		
		int diaDaSemana = Calendar.DAY_OF_WEEK;
		int diaDoMes = Calendar.DAY_OF_MONTH;
		
		if (calendar.get(diaDaSemana) == Calendar.SATURDAY) {
			calendar.add(diaDoMes, 2);
			
		} else if (calendar.get(diaDaSemana) == Calendar.SUNDAY) {
			calendar.add(diaDoMes, 1);
		}
		
		return calendar;
	}
}
