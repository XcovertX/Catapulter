package main.java.characteristics;

import java.util.HashMap;
import java.util.Map;

import main.java.calculator.Dice;

public abstract class ActorClass {
	
	private Map< String, ActorSkill > skills = new HashMap<>();
	private Dice hitDie;
	
	public Map<String, ActorSkill> getSkills() {
		
		return skills;
	}
	public void setSkills( Map<String, ActorSkill> skills ) {
		
		this.skills = skills;
	}
	public Dice getHitDie() {
		
		return hitDie;
	}
	public void setHitDie( Dice hitDie ) {
		
		this.hitDie = hitDie;
	}

}
