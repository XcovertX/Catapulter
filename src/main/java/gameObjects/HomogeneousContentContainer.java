package gameObjects;

public class HomogeneousContentContainer extends Container {
	
	private boolean containsLiquid;
	private boolean containsSolid;
	
	private boolean canContainLiquid;
	private boolean canContainSolid;
	private boolean canContainGas;
	
	public HomogeneousContentContainer() {
		this.type = "HomogeneousContentContainer";
	}
	
	public void decreaseContents( int amount ) {
		
		if( containsLiquid() ) {
			
			int size = 0;
			Thing liquid = getThings().get( 0 );
			size = liquid.getSize();
			liquid.setSize( size - amount );
			System.out.println( "liquid size: " + liquid.getSize() );
			if( liquid.getSize() < 0 ) {
				getThings().removeFirst();
			}
		}
		
		if( containsSolid() ) {
			
			int size = 0;
			Thing solid = getThings().get( 1 );
			size = solid.getSize();
			solid.setSize( size - amount );
			if( solid.getSize() < 0 ) {
				getThings().removeFirst();
			}
		}
	}

	public boolean containsLiquid() {
		return containsLiquid;
	}

	public void setContainsLiquid(boolean containsLiquid) {
		this.containsLiquid = containsLiquid;
	}

	public boolean containsSolid() {
		return containsSolid;
	}

	public void setContainsSolid(boolean containsSolid) {
		this.containsSolid = containsSolid;
	}
	
	public boolean canContainLiquid() {
		return canContainLiquid;
	}

	public void setCanContainLiquid(boolean canContainLiquid) {
		this.canContainLiquid = canContainLiquid;
	}

	public boolean canContainSolid() {
		return canContainSolid;
	}

	public void setCanContainSolid(boolean canContainSolid) {
		this.canContainSolid = canContainSolid;
	}

	public boolean canContainGas() {
		return canContainGas;
	}

	public void setCanContainGas(boolean canContainGas) {
		this.canContainGas = canContainGas;
	}
	
	@Override
	public boolean isEmpty() {
		
		if( !getThings().isEmpty() ) {
			
			if( getThings().get( 0 ).getSize() > 0 ) {

				return false;
			}
		}
		return true;
	}
	
	public boolean isFull() {

		if( getThings().get( 0 ).getSize() == getCapacity() ) {

			return false;
		}
		return true;
	}
}
