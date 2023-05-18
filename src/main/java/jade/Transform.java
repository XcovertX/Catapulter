package jade;

import org.joml.Vector2f;

public class Transform {

    public Vector2f position;
    public Vector2f scale;
    private boolean visible;

    public Transform() { init( new Vector2f(), new Vector2f() ); }

    public Transform( Vector2f position ) { init( position, new Vector2f() ); }

    public Transform( Vector2f position, Vector2f scale ) {
        init( position, scale );
    }

    public void init( Vector2f position, Vector2f scale ) {

        this.position = position;
        this.scale = scale;
        this.visible = true;
    }

    public Transform copy() {

        return new Transform( new Vector2f( this.position ), new Vector2f( this.scale ) );
    }

    public void copy( Transform to ) {
        to.position.set( this.position );
        to.scale.set( this.scale );
    }

    public void copyPosition( Transform to ) { to.position.set( this.position ); }

    public void copyScale( Transform to ) { to.scale.set( this.scale ); }

    @Override
    public boolean equals( Object obj ) {

        if( obj == null ) return false;
        else if( !( obj instanceof Transform ) ) return false;
        Transform t = ( Transform ) obj;
        return t.position.equals(this.position) &&
                t.scale.equals(this.scale) &&
                t.visible == this.visible;
    }

    public boolean isVisible() { return visible; }

    public void setVisible( boolean visible ) { this.visible = visible; }

}
