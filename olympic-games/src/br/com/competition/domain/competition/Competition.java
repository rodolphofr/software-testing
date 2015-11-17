package br.com.competition.domain.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.competition.domain.Athlete;

public abstract class Competition {

	/*TODO: Criar pódio*/
	
	private String description;
	private Athlete winner;
	private List<Athlete> athletes;
	
	public Competition(String description) {
		this.description = description;
		this.athletes = new ArrayList<Athlete>();
	}
	
	public void participate(Athlete athlete) {
		this.athletes.add(athlete);
	}
	
	public boolean isValid() {
		return athletes.size() > 1;
	}

	public List<Athlete> getAthletes() {
		return Collections.unmodifiableList(athletes);
	}
	
	public String getDescription() {
		return description;
	}

	public Athlete getWinner() {
		return winner;
	}
	
	public void setWinner(Athlete winner) {
		this.winner = winner;
	}

	public abstract void start();
	protected abstract boolean finished(int position);
}
