package br.com.competition.domain.competition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.competition.domain.Runner;

public class OneHundredMetersTest {

	private Competition competition;
	private Runner madson;
	private Runner jardel;
	private Runner usain;
	private Runner john;
	private Runner kevin;
	private Runner marcus;

	@Before
	public void setUp() {
		this.competition = new OneHundredMeters("100m Rasos");
		this.usain = new Runner("Usain", "Jamaica", Runner.Specialty.SPRINTER);
		this.marcus = new Runner("Marcus", "Brazil", Runner.Specialty.SPRINTER);
		this.kevin = new Runner("Kevin", "Poland", Runner.Specialty.SPRINTER);
		this.madson = new Runner("Madson", "USA", Runner.Specialty.MIDDLE);
		this.john = new Runner("John", "UK", Runner.Specialty.MIDDLE);
		this.jardel = new Runner("Jardel", "Brazil", Runner.Specialty.DISTANCE);
	}
	
	@Test
	public void shouldParticipateCompetition() {
		competition.participate(usain);
		competition.participate(jardel);
		assertEquals(2, competition.getCompetitors().size());
		assertEquals(usain, competition.getCompetitors().get(0));
		assertEquals(jardel, competition.getCompetitors().get(1));
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void shouldNotLeaveCompetition() {
		competition.participate(usain);
		competition.getCompetitors().remove(usain);
	}
	
	@Test
	public void shouldStartACompetition() {
		competition.participate(usain);
		competition.participate(jardel);
		competition.participate(madson);
		
		competition.start();
		
		assertEquals(3, competition.getClassification().size());
	}
	
	@Test
	public void shouldHaveAChampion() {
		competition.participate(jardel);
		competition.participate(usain);
		competition.participate(madson);
		
		competition.start();
		
		assertEquals(usain, competition.getWinner());
	}
	
	@Test
	public void shouldNotStartCompetitionWithNoOne() {
		competition.start();
		
		assertTrue(competition.getCompetitors().isEmpty());
		assertTrue(competition.getClassification().isEmpty());
		assertTrue(competition.getPodium().isEmpty());
	}
	
	@Test
	public void shouldNotStartCompetitionWithOneAthlete() {
		competition.participate(jardel);
		competition.start();
		
		assertEquals(1, competition.getCompetitors().size());
		assertEquals(jardel, competition.getCompetitors().get(0));
		assertTrue(competition.getClassification().isEmpty());
		assertTrue(competition.getPodium().isEmpty());
	}
	 
	@Test
	public void shouldUnderstandCompetitionWithTwoAthletes() {
		competition.participate(madson);
		competition.participate(jardel);
		competition.start();
		
		assertEquals(2, competition.getCompetitors().size());
		assertEquals(2, competition.getClassification().size());
		assertEquals(madson, competition.getClassification().get(0));
		assertEquals(jardel, competition.getClassification().get(1));
	}
	
	@Test
	public void shouldUnderstandCompetitionWithMoreAthletes() {
		competition.participate(madson);
		competition.participate(jardel);
		competition.participate(usain);
		competition.participate(kevin);
		competition.participate(marcus);
		competition.participate(john);
		
		competition.start();
		
		assertEquals(6, competition.getCompetitors().size());
		assertEquals(6, competition.getClassification().size());
		assertEquals(3, competition.getPodium().size());
		assertEquals(usain, competition.getWinner());
		assertEquals(kevin, competition.getPodium().get(1));
		assertEquals(marcus, competition.getPodium().get(2));
	}
	
}
