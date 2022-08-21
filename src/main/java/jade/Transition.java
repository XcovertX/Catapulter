package jade;

import org.joml.Vector2f;

public class Transition {

    private float incrementX;
    private float incrementY;
    private int count;
    private int requiredSteps;
    private boolean complete;
    private Transform fromTransform;
    private Transform toTransform;

    public Transition( Transform fromTransform, Transform toTransform ) {

        this.fromTransform = fromTransform;
        this.toTransform = toTransform;
        calculateTransition();
    }

    private void calculateTransition() {

        count = 0;
        requiredSteps = 5;
        incrementX = ( toTransform.position.x - fromTransform.position.x ) / requiredSteps;
        incrementY = ( toTransform.position.y - fromTransform.position.y ) / requiredSteps;
        complete = false;
    }

    public void update() {

        if( !complete ) {

            fromTransform.position.x += incrementX;
            fromTransform.position.y += incrementY;
            count += 1;

            if (count >= requiredSteps) {

                complete = true;
            }
        }
    }

    public boolean isComplete() { return complete; }
}
