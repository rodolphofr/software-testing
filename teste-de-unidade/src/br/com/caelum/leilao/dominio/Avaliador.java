package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Avaliador {
	
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private List<Lance> tresMaioresLances;
	
	public void avalia(Leilao leilao) {
		
		if (leilao.getLances().size() == 0) {
			throw new RuntimeException("Não pode haver leilão com nenhum lance");
		}
		
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorLance) {
				maiorLance = lance.getValor();
			} 
			if (lance.getValor() < menorLance) {
				menorLance = lance.getValor();
			}
		}
		pegaOsTresMaioresLances(leilao.getLances());
	}
	
	private void pegaOsTresMaioresLances(List<Lance> lances) {
		this.tresMaioresLances = new ArrayList<Lance>(lances);
		Collections.sort(tresMaioresLances, Lance.LanceComparator);
		this.tresMaioresLances = tresMaioresLances.subList(0, lances.size() > 3 ? 3 :lances.size());
	}
	
	public List<Lance> getTresMaioresLances() {
		return tresMaioresLances;
	}
	
	public double getMaiorLance() {
		return maiorLance;
	}
	
	public double getMenorLance() {
		return menorLance;
	}
}
