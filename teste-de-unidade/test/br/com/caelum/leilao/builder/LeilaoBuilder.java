package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoBuilder {

	private Leilao leilao;

	public LeilaoBuilder para(String descricao) {
		this.leilao = new Leilao(descricao);
		return this;
	}

	public LeilaoBuilder lance(Usuario usuario, double valor) {
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}
	
	public LeilaoBuilder dobra(Usuario usuario) {
		leilao.dobraLance(usuario);
		return this;
	}

	public Leilao build() {
		return leilao;
	}

}
