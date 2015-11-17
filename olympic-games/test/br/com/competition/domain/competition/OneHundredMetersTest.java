package br.com.competition.domain.competition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.competition.domain.Runner;

public class OneHundredMetersTest {

	private Competition competition;
	private Runner madson;
	private Runner jardel;
	private Runner usain;

	@Before
	public void setUp() {
		this.competition = new OneHundredMeters("100m Rasos");
		this.usain = new Runner("Usain Bolt", "Jamaica", Runner.Specialty.SPRINTER);
		this.jardel = new Runner("Jardel", "Brasil", Runner.Specialty.DISTANCE);
		this.madson = new Runner("Madson", "USA", Runner.Specialty.MIDDLE);
	}
	
	@Test
	public void shouldParticipateCompetition() {
		competition.participate(usain);
		assertEquals(1, competition.getAthletes().size());
		assertEquals("Usain Bolt", competition.getAthletes().get(0).getName());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void shouldNotLeaveCompetition() {
		competition.participate(usain);
		competition.getAthletes().remove(usain);
	}
	
	@Test
	public void shouldStartACompetition() {
		competition.participate(usain);
		competition.participate(jardel);
		competition.participate(madson);
		
		competition.start();
		
		assertEquals(3, competition.getAthletes().size());
	}
	
	@Test
	public void shouldHaveAChampion() {
		competition.participate(usain);
		competition.participate(jardel);
		competition.participate(madson);
		
		competition.start();
		
		assertEquals(usain, competition.getWinner());
	}
	
	@Test
	public void shouldNotStartOneCompetition() {
		competition.start();
		assertNull(competition.getWinner());
	}
	
	@Ignore
	@Test
	public void shouldATie() {
		competition.participate(jardel);
		competition.participate(jardel);
		competition.participate(jardel);
		
		competition.start();
		
		assertNull(competition.getWinner());
	}

}
