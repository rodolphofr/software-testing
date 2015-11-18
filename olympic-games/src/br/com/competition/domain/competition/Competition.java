package br.com.competition.domain.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.com.competition.domain.Athlete;

public abstract class Competition {

	private String description;
	private List<Athlete> competitors;
	private List<Athlete> classification;
	
	public Competition(String description) {
		this.description = description;
		this.competitors = new LinkedList<Athlete>();
		this.classification = new ArrayList<Athlete>();
	}
	
	public void participate(Athlete athlete) {
		this.competitors.add(athlete);
	}
	
	public void addClassification(Athlete athlete) {
		this.classification.add(athlete);
	}

	public boolean isValid() {
		return competitors.size() > 1;
	}

	public boolean competitionEnded() {
		return classification.size() == competitors.size();
	}
	
	public List<Athlete> getCompetitors() {
		return Collections.unmodifiableList(competitors);
	}
	
	public String getDescription() {
		return description;
	}

	public Athlete getWinner() {
		return classification.get(0);
	}
	
	public List<Athlete> getClassification() {
		return classification;
	}
	
	public List<Athlete> getPodium() {
		int toIndex = classification.size() > 3 ? 3 : classification.size();
		return Collections.unmodifiableList(classification.subList(0, toIndex));
	}
	
	public abstract void start();
	protected abstract boolean finished(int position);
}
