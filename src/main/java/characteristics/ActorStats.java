package characteristics;

import java.util.LinkedList;

public class ActorStats {
	
	private int level;
	private int age;
	private ActorRace race;
	private ActorClass classType;
	private int ArmorClass;
	
	// health, magic, experience
	private int HPMax;
	private int HPCurrent;
	private int MPMax;
	private int MPCurrent;
	private int XPMax;
	private int XPCurrent;
	
	// strength stats
	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;
	
	private int attackSpeed;
	
	
	// baseline modifiers
	private int luck;
	private int proficiency;
	private int hunger;
	private int thirst;
	
	private LinkedList< StatModifier > baseModifiers;
	private LinkedList< StatModifier > raceModifiers;
	private LinkedList< StatModifier > inventoryModifiers;
	private LinkedList< StatModifier > wieldedModifiers;
	private LinkedList< StatModifier > consumedModifiers;
	private LinkedList< StatModifier > magicModifiers;
	private LinkedList< StatModifier > classModifiers;
	
	private ActorAdvancement advancement = new ActorAdvancement();
	
	public ActorStats() {
		this.setLevel( 1 );
		this.setAge( 204 );
		this.setCharisma( 8 );
		this.setDexterity( 13 );
		this.setIntelligence( 10 );
		this.setWisdom( 12 );
		this.setStrength( 15 );
		this.setConstitution( 14 );
		
		this.setClassType( new Fighter() );
		this.setRace( new Dwarf() );
		this.setArmorClass( calculateArmorClass() );
		
		this.setRaceModifiers( this.getRace().getRaceModifiers() );
		
		this.setThirst( 0 );
		this.setHunger( 0 );
	}
	
	// stat calculations
	
	public void levelUp() {
		
		
	}
	
	public int calculateEncumbrance() {
		
		int total;
		if( this.race.getAverageSize().equals( "small" ) ) {
			
			total = this.strength * 8;
			
		} else if( this.race.getAverageSize().equals( "medium" ) ) {
			
			total = this.strength * 30;
			
		} else if( this.race.getAverageSize().equals( "large" ) ) {
			
			total = this.strength * 60;
			
		} else {
			
			total = -1;
		}
		
		return total;
	}
	
	public int calculateHPMax() {
		
		return this.getClassType().getHitDie().getMax() + this.calculateConstitution();
	}
	
	public int calculateStrength() {
		
		return this.strength;
	}
	public int calculateConstitution() {
		
		return this.constitution;
	}
	public int calculateDexterity() {
		
		return this.dexterity;
	}
	public int calculateIntelligence() {
		
		return this.intelligence;
	}
	public int calculateWisdom() {
		
		return this.wisdom;
	}
	public int calculateCharisma() {
		
		return this.charisma;
	}
	
	public int calculateArmorClass() {
		
		return 50 + this.calculateDexterity();
	}

	public ActorClass getClassType() {
		return classType;
	}

	public void setClassType(ActorClass classType) {
		this.classType = classType;
	}

	public int getHunger() {
		return hunger;
	}

	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	public int getThirst() {
		return this.thirst;
	}

	public void setThirst(int thirst) {
		this.thirst = thirst;
	}

	public ActorAdvancement getAdvancement() {
		return advancement;
	}

	public void setAdvancement(ActorAdvancement advancement) {
		this.advancement = advancement;
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

	public ActorRace getRace() {
		
		return race;
	}

	public void setRace( ActorRace race ) {
		
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

	public int getArmorClass() {
		return ArmorClass;
	}

	public void setArmorClass(int armorClass) {
		ArmorClass = armorClass;
	}


}
