package main.java.characteristics;

public abstract class StatModifier {

	private String name;
	private int id;
	private int modifierValue;
	private String statType;
	

	public int getModifierValue() {
		return modifierValue;
	}

	public void setModifierValue( int bonusValue ) {
		this.modifierValue = bonusValue;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType( String statType ) {
		this.statType = statType;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}
}