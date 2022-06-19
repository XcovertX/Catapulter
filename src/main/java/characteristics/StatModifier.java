package main.java.characteristics;

public class StatModifier {

	private String name;
	private int id;
	private int modifierValue;
	private String statType;
	
	public StatModifier( String name, String statType, int modifierValue, int ID ) {
		this.setName( name );
		this.setStatType( statType );
		this.setModifierValue( modifierValue );
		this.setId( ID );
	}
	

	public int getModifierValue() {
		return modifierValue;
	}

	public void setModifierValue( int modifierValue ) {
		this.modifierValue = modifierValue;
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