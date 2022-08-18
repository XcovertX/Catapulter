package jade;

import userInterface.Image;

public abstract class Component {

//    public GameObject gameObject = null;

    public Image gameImage = null;

    public void start() { }

    public abstract void update( float dt );
}
