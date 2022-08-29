package jade;

import org.joml.Vector2f;
import userInterface.Image;

public class Transition {

    private float incrementPosX;
    private float incrementPosY;
    private float incrementScaleX;
    private float incrementScaleY;
    private int count;
    private int requiredSteps;
    private boolean complete;
    private Transform fromTransform;
    private Transform toTransform;

    public Transition( Transform fromTransform, Transform toTransform ) {

        this.fromTransform = fromTransform;
        this.toTransform = toTransform;
        calculatePositionTransition();
    }

    public Transition( Transform transform, boolean transitionToVisible ) {

        if( transitionToVisible ) {

            Transform tempTransform = new Transform( new Vector2f( 0, 0 ), new Vector2f( 0, 0 ) );
            this.toTransform = transform.copy();
            tempTransform.copy( transform );
            this.fromTransform = transform;

        } else {

            this.fromTransform = transform;
            this.toTransform = new Transform( new Vector2f( 0, 0 ), new Vector2f( 0, 0 ) );
        }
        calculateVisibilityTransition( 1 );
    }

    private void calculatePositionTransition() {

        count = 0;
        requiredSteps = 5;
        incrementPosX = ( toTransform.position.x - fromTransform.position.x ) / requiredSteps;
        incrementPosY = ( toTransform.position.y - fromTransform.position.y ) / requiredSteps;
        incrementScaleX = 0;
        incrementScaleY = 0;
        complete = false;
    }

    private void calculateVisibilityTransition( int requiredSteps ) {

        count = 0;
        this.requiredSteps = requiredSteps;
        incrementPosX = 0;
        incrementPosY = 0;
        incrementScaleX = ( toTransform.scale.x - fromTransform.scale.x ) / requiredSteps;
        incrementScaleY = ( toTransform.scale.y - fromTransform.scale.y ) / requiredSteps;

//        this.toTransform.copyPosition( this.fromTransform );

        this.fromTransform.position.x = this.toTransform.position.x;
        this.fromTransform.position.y = this.toTransform.position.y;

        complete = false;
    }

    public void update() {

        if( !complete ) {

            fromTransform.position.x += incrementPosX;
            fromTransform.position.y += incrementPosY;
            fromTransform.scale.x += incrementScaleX;
            fromTransform.scale.y += incrementScaleY;

            count += 1;

            if ( count >= requiredSteps ) {

                complete = true;
            }
        }
    }

    public boolean isComplete() { return complete; }
}
