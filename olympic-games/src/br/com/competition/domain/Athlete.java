package br.com.competition.domain;

public class Athlete {

	private String name;
	private String nacionality;
	
	public Athlete(String name, String nacionality) {
		this.name = name;
		this.nacionality = nacionality;
	}
	
	public String getName() {
		return name;
	}

	public String getNacionality() {
		return nacionality;
	}
	
}
