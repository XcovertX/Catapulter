package main.java.characteristics;

import java.util.LinkedList;

public class ActorStats {
	
	private int level;
	private String race;
	private int age;
	
	// health, magic, experience
	private int HPMax;
	private int HPCurrent;
	private int MPMax;
	private int MPCurrent;
	private int XPMax;
	private int XPCurrent;
	
	// baseline stats
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	
	// baseline modifiers
	private int luck;
	private int proficiency;
	
	
	private LinkedList< BaseStat > stats = new LinkedList< BaseStat >();
	private LinkedList< StatModifier > baseAdditives;

	public LinkedList< BaseStat > getStats() {
		
		return stats;
	}

	public void setStats( LinkedList< BaseStat > stats ) {
		
		this.stats = stats;
	}
	
	public LinkedList< StatModifier > getBaseAdditives() {
		
		return baseAdditives;
	}

	public void setBaseAdditives( LinkedList< StatModifier > baseAdditives ) {
		
		this.baseAdditives = baseAdditives;
	}

}
