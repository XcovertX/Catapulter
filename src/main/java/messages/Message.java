package messages;

import gameObjects.Thing;

public abstract class Message {

    public abstract void run();

    public abstract void run( Thing aThing );

    public abstract void run( String string );
}
