package characteristics;

import java.util.LinkedList;

public class BaseStat {
	
	private String statName;
	private String statDescription;
	private int baseValue;
	private int finalValue;
	
	private LinkedList< StatModifier > baseAdditives;
	
	public BaseStat( String statName, String statDesription, int baseValue ) {
		
		this.setStatName( statName );
		this.setStatDescription( statDesription );
		this.setBaseValue( baseValue );
		this.baseAdditives = new LinkedList< StatModifier >();
	}
	
	public void addStatBonus( StatModifier statModifier ) {
		
		this.baseAdditives.add( statModifier );
	}
	
	public void removeStatBonus( StatModifier statModifier ) {
		
		this.baseAdditives.remove( statModifier );
	}
	
	private void calculateFinalValue() {
		
		this.finalValue = 0;
		for( int i = 0; i < this.baseAdditives.size(); i++ ) {
			this.finalValue += this.baseAdditives.get( i ).getModifierValue();
		}
		this.finalValue += this.baseValue;
	}

	public LinkedList< StatModifier > getBaseAdditives() {
		
		return baseAdditives;
	}

	public void setBaseAdditives( LinkedList< StatModifier > baseAdditives ) {
		
		this.baseAdditives = baseAdditives;
	}

	public String getStatName() {
		
		return statName;
	}

	public void setStatName( String statName ) {
		
		this.statName = statName;
	}

	public String getStatDescription() {
		
		return statDescription;
	}

	public void setStatDescription( String statDescription ) {
		
		this.statDescription = statDescription;
	}

	public int getBaseValue() {
		return baseValue;
	}

	public void setBaseValue( int baseValue ) {
		
		this.baseValue = baseValue;
	}

	public int getFinalStatValue() {
		
		this.calculateFinalValue();
		return finalValue;
	}

	public void setFinalValue( int finalValue ) {
		
		this.finalValue = finalValue;
	}

}
