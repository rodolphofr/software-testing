package br.com.caelum.leilao.dominio;

import java.util.Comparator;

public class Lance {

	private Usuario usuario;
	private double valor;
	
	public Lance(Usuario usuario, double valor) {
		if (valor <= 0) throw new IllegalArgumentException("O valor do lance é inválido!");
		this.usuario = usuario;
		this.valor = valor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getValor() {
		return valor;
	}
	
	public static Comparator<Lance> LanceComparator = new Comparator<Lance>() {
		@Override
		public int compare(Lance o1, Lance o2) {
			if (o1.getValor() < o2.getValor()) return 1;
			if (o1.getValor() > o2.getValor()) return -1;
			return 0;
		}
	};

	@Override
	public String toString() {
		return "Lance [usuario=" + usuario.getNome() + "]";
	}
	
}
