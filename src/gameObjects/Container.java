package gameObjects;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Container extends ThingHolder {
	
	protected LinkedList< String > typesCanContain;
	
	
	private String accessMechanism;
	private String openAction;
	private String closeAction;
	
	protected boolean lockable;
	protected boolean openable;
	
	private boolean isHomogeneousContentContainer;
	
	protected boolean isLocked;
	protected boolean isOpen;
	
	private int capacity; //capacity in drops or grains
	
	private Lock lock;
	
	public Container(){
		super( "", "", new ThingList() );
		this.typesCanContain = new LinkedList<>(); 	//different types the container can contain
		this.setAccessMechanism( "" );
		this.setOpenAction( "" );
		this.setCloseAction( "" );
		this.setIsContainer( true );
		this.setLock( new Lock() );
		this.setLockable( true );
		this.setCapacity( 100 );
		this.type = "Container";
	}
	
	// item thing holder constructor
	public Container( String aName, String aDescription, 
			ThingList thingList, int size, int maxCanContain, String[] typesCanContain ) {
		
		super( aName, aDescription, thingList );
		this.setSize( size ); //size of object container can contain scale: 1 - 5
		this.setCapacity( maxCanContain );
		this.typesCanContain = new LinkedList<>(); //different types the container can contain
		this.setAccessMechanism( "" );
		this.setOpenAction( "" );
		this.setCloseAction( "" );
		this.setIsContainer( true );
		this.setLock( new Lock() );
		this.setLockable( true );
		this.setCapacity( 100 );
		this.type = "Container";
	
	}
	
	public boolean canContain( String type ) {
		
		if( this.typesCanContain.size() == 0 ) {
			return false;
		} else {
			for( int i = 0; i < typesCanContain.size(); i++ ) {
				if( type.contentEquals( typesCanContain.get( i ) ) ) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void lock() {
		this.isLocked = true;
	}
	
	public void unlock() {
		this.isLocked = false;
	}

	public boolean isLockable() {
		return lockable;
	}

	public void setLockable(boolean lockable) {
		this.lockable = lockable;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	// returns use container capacity percentage to the first decimal place
	public double getCapacityUsed() {
		int sumOfItemSizes = 0;
		for( int i = 0; i < this.getThings().size(); i++ ) {
			int size = this.getThings().get( i ).getSize();
			sumOfItemSizes = sumOfItemSizes + size;
		}
		double capacityUsed = ( (double) sumOfItemSizes ) / ( (double) this.getCapacity() );
		capacityUsed = Math.round( ( capacityUsed * 1000.0 ) ) / 10.0;
		return capacityUsed;
	}
	
	public String getCapacityUsedEstimation() {
		double capacityUsed = getCapacityUsed();
		if( capacityUsed == 0.0 ) {
			return "empty";
		} else if( capacityUsed < 20.0 ) {
			if( canContain( "solid" ) ) {
				return "a few";
			} else {
				return "almost empty";
			}
		} else if( capacityUsed < 45.0 ) {
			if( canContain( "solid" ) ) {
				return "several";
			} else {
				return "a quarter full";
			}
		} else if( capacityUsed < 55.0 ) {
			if( canContain( "solid" ) ) {
				return "quite a few";
			} else {
				return "half full";
			}
		} else if( capacityUsed < 80.0 ) {
			if( canContain( "solid" ) ) {
				return "many";
			} else {
			return "three quarters full";
			}
		} else if( capacityUsed < 100.0 ) {
			if( canContain( "solid" ) ) {
				return "a lot";
			} else {
				return "almost full";
			}
		} else if( capacityUsed == 100.0 ) {
			return "full";
		}
		return "something went wrong";
	}

	public boolean isOpenable() {
		return openable;
	}

	public void setOpenable(boolean openable) {
		this.openable = openable;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public String getOpenAction() {
		return openAction;
	}

	public void setOpenAction(String openAction) {
		this.openAction = openAction;
	}

	public String getAccessMechanism() {
		return accessMechanism;
	}

	public void setAccessMechanism(String accessMechanism) {
		this.accessMechanism = accessMechanism;
	}

	public String getCloseAction() {
		return closeAction;
	}

	public void setCloseAction(String closeAction) {
		this.closeAction = closeAction;
	}
	
	public void open() {
		setOpen( true );
	}
	
	public void close() {
		setOpen( false );
	}

	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	public boolean isHomogeneousContentContainer() {
		return isHomogeneousContentContainer;
	}

	public void setHomogeneousContentContainer(boolean isHomogeneousContentContainer) {
		this.isHomogeneousContentContainer = isHomogeneousContentContainer;
	}
	
	
}
