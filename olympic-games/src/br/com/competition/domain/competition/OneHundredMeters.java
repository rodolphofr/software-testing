package br.com.competition.domain.competition;

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
		int index = 0;
		if (isValid()) {
			while (true) {
				Runner runner = (Runner) getAthletes().get(index);
				runner.run();
				if (finished(runner.getPosition())) {
					super.setWinner(runner);
					break;
				}
				if (index == (getAthletes().size() - 1)) index = 0;
				else index++;
			}
		}
	}

	@Override
	protected boolean finished(int position) {
		return position > TOTAL_COURSE;
	}

}
