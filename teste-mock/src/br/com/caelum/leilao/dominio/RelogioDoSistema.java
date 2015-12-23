package br.com.caelum.leilao.dominio;

import java.util.Calendar;

import br.com.caelum.leilao.infra.relogio.Relogio;

public class RelogioDoSistema implements Relogio {

	public Calendar hoje() {
		return Calendar.getInstance();
	}

}
