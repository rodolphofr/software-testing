package br.com.competition.domain;

public class Runner extends Athlete {
	
	private Specialty specialty;	
	private int positionToRun;
	
	public Runner(String name, String nacionality, Specialty specialty) {
		super(name, nacionality);
		this.specialty = specialty;
	}
	
	public void run() {
		this.positionToRun += specialty.getSpeed();
	}
	
	public Specialty getSpecialty() {
		return specialty;
	}

	public int getPosition() {
		return positionToRun;
	}
	
	public enum Specialty {
		SPRINTER(8, 1), 
		MIDDLE(4, 2), 
		DISTANCE(2, 5);
		
		private int speed;
		private int stamina;
		
		private Specialty(int speed, int stamina) {
			this.speed = speed;
			this.stamina = stamina;
		}
		
		public int getSpeed() {
			return speed;
		}
		
		public int getStamina() {
			return stamina;
		}
		
	}
	
	@Override
	public String toString() {
		return super.getName();
	}
	
}
