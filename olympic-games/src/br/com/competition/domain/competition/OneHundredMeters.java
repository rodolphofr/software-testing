package br.com.competition.domain.competition;

import br.com.competition.domain.Athlete;
import br.com.competition.domain.Runner;

public class OneHundredMeters extends Competition {

	/*
	 * TODO: Implementar desvantagem e vantagem de cada atleta nesta modalidade
	 * (utilizar estamina)
	 */

	private static final int TOTAL_COURSE = 100;

	public OneHundredMeters(String description) {
		super(description);
	}

	@Override
	public void start() {
		if (isValid()) {
			while (!competitionEnded()) {
				for (Athlete athlete : getCompetitors()) {
					Runner runner = (Runner) athlete;
					runner.run();
					/*TODO: Refatorar condição */
					if (finished(runner.getPosition()) && !contains(runner)) {
						addClassification(runner);
					}
				}
			}
		}
	}

	
	private boolean contains(Runner runner) {
		return getClassification().contains(runner);
	}

	@Override
	protected boolean finished(int position) {
		return position > TOTAL_COURSE;
	}

}
