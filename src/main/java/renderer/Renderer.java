package renderer;

import components.SpriteRenderer;
//import jade.GameObject;
import userInterface.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderer {

    private final int MAX_BATCH_SIZE = 1000;
    private List< RenderBatch > batches;

    public Renderer() {
        this.batches = new ArrayList<>();
    }

    public void add( Image gameImage ) {

        SpriteRenderer sr = gameImage.getComponent( SpriteRenderer.class );

        if ( sr != null ) {

            add( sr );
        }
    }

    private void add( SpriteRenderer sprite ) {

        boolean added = false;

        for( RenderBatch batch : batches ) {

            if( batch.hasRoom() && batch.zIndex() == sprite.gameImage.zIndex() ) {

                Texture tex = sprite.getTexture();

                if( tex == null || ( batch.hasTexture( tex ) || batch.hasTextureRoom() ) ) {

                    batch.addSprite( sprite );
                    added = true;
                    break;
                }
            }
        }

        if( !added ) {

            RenderBatch newBatch = new RenderBatch( MAX_BATCH_SIZE, sprite.gameImage.zIndex() );
            newBatch.start();
            batches.add( newBatch );
            newBatch.addSprite( sprite );
            Collections.sort( batches );
        }
    }

    public void render() {

        for( RenderBatch batch : batches ) {

            batch.render();
        }
    }

}
