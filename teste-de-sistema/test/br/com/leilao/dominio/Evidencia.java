package br.com.leilao.dominio;

import java.io.File;

public class Evidencia {
	
	private String descricao;
	
	private File imagem;
	
	public Evidencia(String descricao, File imagem) {
		this.descricao = descricao;
		this.imagem = imagem;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public File getImagem() {
		return imagem;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setImagem(File imagem) {
		this.imagem = imagem;
	}
	
	
	
	
}
