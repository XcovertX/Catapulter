package main.java.characteristics;

import java.util.LinkedList;

public class ActorStats {
	
	private int level;
	private Race race;
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
	
	private LinkedList< StatModifier > baseModifiers;
	private LinkedList< StatModifier > raceModifiers;
	private LinkedList< StatModifier > inventoryModifiers;
	private LinkedList< StatModifier > wieldedModifiers;
	private LinkedList< StatModifier > consumedModifiers;
	private LinkedList< StatModifier > magicModifiers;
	private LinkedList< StatModifier > classModifiers;
	
	// stat calculations
	
	public int calculateStrength() {
		
		return 0;
	}
	public int calculateConstitution() {
		
		return 0;
	}
	public int calculateDexterity() {
		
		return 0;
	}
	public int calculateIntelligence() {
		
		return 0;
	}
	public int calculateWisdom() {
		
		return 0;
	}
	public int calculateCharisma() {
		
		return 0;
	}
	
	public int calculateArmorClass() {
		
		return 10 + this.calculateDexterity();
	}
	
	
	

	public LinkedList< StatModifier > getBaseAdditives() {
		
		return baseModifiers;
	}

	public void setBaseAdditives( LinkedList< StatModifier > baseModifiers ) {
		
		this.baseModifiers = baseModifiers;
	}

	public int getLevel() {
		
		return level;
	}

	public void setLevel( int level ) {
		
		this.level = level;
	}

	public Race getRace() {
		
		return race;
	}

	public void setRace( Race race ) {
		
		this.race = race;
	}

	public int getAge() {
		
		return age;
	}

	public void setAge( int age ) {
		
		this.age = age;
	}

	public int getHPMax() {
		
		return HPMax;
	}

	public void setHPMax( int hPMax ) {
		
		HPMax = hPMax;
	}

	public int getHPCurrent() {
		
		return HPCurrent;
	}

	public void setHPCurrent( int hPCurrent ) {
		
		HPCurrent = hPCurrent;
	}

	public int getMPMax() {
		
		return MPMax;
	}

	public void setMPMax( int mPMax ) {
		
		MPMax = mPMax;
	}

	public int getMPCurrent() {
		
		return MPCurrent;
	}

	public void setMPCurrent( int mPCurrent ) {
		
		MPCurrent = mPCurrent;
	}

	public int getXPMax() {
		
		return XPMax;
	}

	public void setXPMax( int xPMax ) {
		
		XPMax = xPMax;
	}

	public int getXPCurrent() {
		
		return XPCurrent;
	}

	public void setXPCurrent( int xPCurrent ) {
		
		XPCurrent = xPCurrent;
	}

	public int getStrength() {
		
		return strength;
	}

	public void setStrength( int strength ) {
		
		this.strength = strength;
	}

	public int getDexterity() {
		
		return dexterity;
	}

	public void setDexterity( int dexterity ) {
		
		this.dexterity = dexterity;
	}

	public int getConstitution() {
		
		return constitution;
	}

	public void setConstitution( int constitution ) {
		
		this.constitution = constitution;
	}

	public int getIntelligence() {
		
		return intelligence;
	}

	public void setIntelligence( int intelligence ) {
		
		this.intelligence = intelligence;
	}

	public int getWisdom() {
		
		return wisdom;
	}

	public void setWisdom( int wisdom ) {
		
		this.wisdom = wisdom;
	}

	public int getCharisma() {
		
		return charisma;
	}

	public void setCharisma( int charisma ) {
		
		this.charisma = charisma;
	}

	public int getLuck() {
		
		return luck;
	}

	public void setLuck( int luck ) {
		
		this.luck = luck;
	}

	public int getProficiency() {
		
		return proficiency;
	}

	public void setProficiency( int proficiency ) {
		
		this.proficiency = proficiency;
	}

	public LinkedList<StatModifier> getBaseModifiers() {
		
		return baseModifiers;
	}

	public void setBaseModifiers( LinkedList<StatModifier> baseModifiers ) {
		
		this.baseModifiers = baseModifiers;
	}

	public LinkedList<StatModifier> getRaceModifiers() {
		
		return raceModifiers;
	}

	public void setRaceModifiers( LinkedList<StatModifier> raceModifiers ) {
		
		this.raceModifiers = raceModifiers;
	}

	public LinkedList<StatModifier> getInventoryModifiers() {
		
		return inventoryModifiers;
	}

	public void setInventoryModifiers( LinkedList<StatModifier> inventoryModifiers ) {
		
		this.inventoryModifiers = inventoryModifiers;
	}

	public LinkedList<StatModifier> getWieldedModifiers() {
		
		return wieldedModifiers;
	}

	public void setWieldedModifiers( LinkedList<StatModifier> wieldedModifiers ) {
		
		this.wieldedModifiers = wieldedModifiers;
	}

	public LinkedList<StatModifier> getConsumedModifiers() {
		
		return consumedModifiers;
	}

	public void setConsumedModifiers( LinkedList<StatModifier> consumedModifiers ) {
		
		this.consumedModifiers = consumedModifiers;
	}

	public LinkedList<StatModifier> getMagicModifiers() {
		
		return magicModifiers;
	}

	public void setMagicModifiers( LinkedList<StatModifier> magicModifiers ) {
		
		this.magicModifiers = magicModifiers;
	}

	public LinkedList<StatModifier> getClassModifiers() {
		
		return classModifiers;
	}

	public void setClassModifiers( LinkedList<StatModifier> classModifiers ) {
		
		this.classModifiers = classModifiers;
	}
	
	

}
