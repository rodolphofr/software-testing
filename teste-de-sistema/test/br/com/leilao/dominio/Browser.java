package br.com.leilao.dominio;

public enum Browser {

	FIREFOX("Firefox"), CHROME("Chrome"), INTERNET_EXPLORER("Internet Explorer");

	private String name;

	private Browser(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
