package characteristics;

import java.util.LinkedList;

public class Dwarf extends ActorRace {

	public Dwarf() {
		this.setCharismaBenefit( 0 );
		this.setConstitutionBenefit( 2 );
		this.setDexterityBenefit( 0 );
		this.setIntelligenceBenefit( 0 );
		this.setLuckBenefit( 0 );
		this.setStrengthBenefit( 0 );
		this.setWisdomBenefit( 0 );
		
		this.setAverageSize( "medium" );
		
		this.setRaceModifiers( new LinkedList< StatModifier >() );
		this.getRaceModifiers().add( new StatModifier( "Race", "constitution", 2, 0001 ) );
		
	}
}
