package main.java.gameObjects;

public class Lock extends Thing {
	
	private int lockShape;
	private int lockCode;
	private boolean isLocked;

	public Lock() {
		this.setName( "lock" );
		this.setDescription( "sturdy" );
		this.setLockShape( 0001 );
		this.setLockCode( 0001 );
		this.isLocked = false;
		this.type = "Lock";
	}
	
	public Lock(String name, String description, int lockShape, int lockCode ) {
		this.setName( name );
		this.setDescription( description );
		this.setLockShape( lockShape );
		this.setLockCode( lockCode );
	}

	public int getLockShape() {
		return lockShape;
	}

	public void setLockShape(int lockShape) {
		this.lockShape = lockShape;
	}

	public int getLockCode() {
		return lockCode;
	}

	public void setLockCode(int lockCode) {
		this.lockCode = lockCode;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
}
