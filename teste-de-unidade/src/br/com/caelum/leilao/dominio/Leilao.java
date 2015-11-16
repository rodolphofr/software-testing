package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if (lances.isEmpty() || ehlanceValido(lance.getUsuario())) {
			lances.add(lance);
		}
	}
	
	private boolean ehlanceValido(Usuario usuario) {
		return !ultimoLanceDado().getUsuario().equals(usuario) && totalDeLances(usuario) < 5;
	}
	
	private int totalDeLances(Usuario usuario) {
		int total = 0;
		
		for (Lance lance : lances) {
			if (lance.getUsuario().equals(usuario)) total++;
		}
		
		return total;
	}
	
	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}
	
	public void dobraLance(Usuario usuario) {
		Lance lance = ultimoLanceDado(usuario);
		if (lance != null) {
			propoe(new Lance(usuario, lance.getValor() * 2));
		}
	}

	private Lance ultimoLanceDado(Usuario usuario) {
		Lance lance = null;
		for (int i = lances.size() - 1; i >= 0; i--) {
			Lance lanceDado = lances.get(i);
			if (lanceDado.getUsuario().equals(usuario)) {
				lance = lanceDado;
				break;
			}
		}
		return lance;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
