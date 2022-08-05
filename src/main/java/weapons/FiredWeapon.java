package weapons;

public abstract class FiredWeapon extends RangeWeapon {
	
	private String projectileType;
	private int loadingTime;
	
	public String getProjectileType() {
		
		return projectileType;
	}

	public void setProjectileType( String projectileType ) {
		
		this.projectileType = projectileType;
	}

	public int getLoadingTime() {
		
		return loadingTime;
	}

	public void setLoadingTime(int loadingTime) {
		
		this.loadingTime = loadingTime;
	}

}
