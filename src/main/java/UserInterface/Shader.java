package main.java.UserInterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import main.java.game.Game;
import main.java.gameObjects.Thing;
import main.java.light.AmbientLight;
import main.java.light.RadiatingLight;
import main.java.world.GameTile;

public class Shader {
	
	public Shader() {};
	
	public BufferedImage deepCopy(BufferedImage bi) {
		
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public static BufferedImage copyImage( BufferedImage source ){
	    BufferedImage b = new BufferedImage( source.getWidth(), source.getHeight(), source.getType() );
	    Graphics g = b.getGraphics();
	    g.drawImage(source, 0, 0, null);
	    g.dispose();
	    return b;
	}
	
	public BufferedImage shiftImageColor( BufferedImage img, RadiatingLight rLight, int amount ) {
		
		BufferedImage newImage = new BufferedImage( img.getWidth(), 
													img.getHeight(),
	            									BufferedImage.TYPE_INT_ARGB );
		
	    for (int x = 0; x < img.getWidth(); x++) {
	    	
	        for (int y = 0; y < img.getHeight(); y++) {
	        	
	        	int pixel = img.getRGB( x, y );
	        	
	        	if( isTransparent( pixel ) ) {
	        		
	        		newImage.setRGB(x, y, pixel );
	        		
	        	} else {
	        		
		            Color color = new Color( pixel );
		            
		            int red = color.getRed();
		            int blue = color.getBlue();
		            int green = color.getGreen();
	
		            float[] hsb = Color.RGBtoHSB(red, green, blue, null);
	
		            float hue = hsb[0];
	
		            float saturation = hsb[1];
	
		            float brightness = hsb[2];
		            
		            float brightnessIncrement = (float) (1.0 - brightness);
		            		
		            brightnessIncrement = brightnessIncrement / rLight.getDistance();
		            
		            brightness += amount * brightnessIncrement * .5;
		            
		            if( brightness > 1.0 ) {
		            	brightness = ( float ) ( 0.1 * brightness );
		            }
		            
		            if( brightness < 0 ) {
		            	brightness = ( float ) 0.1;
		            }
		            
		            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
	
		            red = (rgb>>16)&0xFF;
	
		            green = (rgb>>8)&0xFF;
	
		            blue = rgb&0xFF;
		            
		            int newColorRGB = new Color(red, green, blue ).getRGB();
		            newImage.setRGB(x, y, newColorRGB );
		        }
	        }
	    }
		return newImage;
	}
	
	public BufferedImage shadeImage( GameTile gameTile ) {
		
		Image tileImage = gameTile.getBaseTileImage();
		Image thingImage = gameTile.getCurrentThingImage();
		
		int width = tileImage.getImageWidth();
		int height = tileImage.getImageHeight();
		
		ImagePixel[][] newPixels = new ImagePixel[height][width];
		
		BufferedImage newImage = new BufferedImage( 
				tileImage.getImageWidth(), 
				tileImage.getImageHeight(),
				BufferedImage.TYPE_INT_ARGB );	
		
		if(tileImage != null ) {
		
			for( int i = 0; i < tileImage.getImageLayers().length; i++ ) {
				
				ImageLayer tileImageLayer = tileImage.getImageLayer( i );
				
				if( tileImageLayer != null ) {

					ImageFrame tileImageFrame = tileImageLayer.getActiveFrame();	
					ImagePixel[][] pixels = tileImageFrame.getFramePixels();
		    		
					for( int j = 0; j < tileImage.getImageWidth(); j++ ) {
		    			
		    			for( int k = 0; k < tileImage.getImageHeight(); k++ ) {
		    				
		    				ImagePixel pixel = pixels[ k ][ j ];
		    				int newRGB = pixel.getRgb();
		    				newPixels[ k ][ j ] = new ImagePixel();
		    				
		    				if( !isTransparent( pixel.getRgb() ) ) {
		    					
		    					newRGB = applyAmbientLight( pixel.getRgb() );

			    				for( int l = 0; l < Game.currentRoom.getAllRoomLightSourceObjects().size(); l++ ) {
			    								
			    					Thing lightSourceObject = Game.currentRoom.getAllRoomLightSourceObjects().get( l);
			    					RadiatingLight roomLightSource = ( RadiatingLight ) lightSourceObject.getLightSources().get( 0 );
			    					int lightSourceTileNumber;
			    					
			    					if( lightSourceObject == Game.currentGame.getPlayer() ) {
			    						
			    						lightSourceTileNumber = Game.currentTile.getTileNumber();
			    						
			    					} else {
			    						
			    						lightSourceTileNumber = lightSourceObject.getCurrentGameTile().getTileNumber();
			    					}
			    					
			    					int thisTileNumber = gameTile.getTileNumber();
			    					double distanceFromLightSource = Game.currentRoom.calculateDistance( lightSourceTileNumber, thisTileNumber);
			    					double lightReach = roomLightSource.getDistance();
			    					
						    		if( distanceFromLightSource <= lightReach ) {
			
						    			String relativeDirectionOfLight = Game.currentRoom.calculateRelativeDirection( lightSourceTileNumber, thisTileNumber );
		
						    			if( tileImageLayer.getName().equals( "base" ) ||
						    				tileImageLayer.getName().equals( "object" ) ) {
						    				
						    				int amount = ( int ) Math.floor( lightReach - distanceFromLightSource );
					    					newRGB =  applyLightShift( newRGB, amount, lightReach, roomLightSource );
					    					
						    			} else if (
						    					
						    				relativeDirectionOfLight.equals( "n" ) && tileImageLayer.getName().equals( "south" ) ||
					    					relativeDirectionOfLight.equals( "s" ) && tileImageLayer.getName().equals( "north" ) ||
					    					relativeDirectionOfLight.equals( "w" ) && tileImageLayer.getName().equals( "west"  ) ||
					    					relativeDirectionOfLight.equals( "e" ) && tileImageLayer.getName().equals( "east"  ) ||
					    					relativeDirectionOfLight.equals( "nw" ) && 
					    					( tileImageLayer.getName().equals( "south" ) || tileImageLayer.getName().equals( "west" ) ) ||
					    					relativeDirectionOfLight.equals( "sw" ) && 
					    					( tileImageLayer.getName().equals( "north" ) || tileImageLayer.getName().equals( "west" ) ) ||
					    					relativeDirectionOfLight.equals( "ne" ) && 
					    					( tileImageLayer.getName().equals( "south" ) || tileImageLayer.getName().equals( "east" ) ) ||
					    					relativeDirectionOfLight.equals( "se" ) && 
					    					( tileImageLayer.getName().equals( "north" ) || tileImageLayer.getName().equals( "east" ) ) ) { 
	
						    				int amount = ( int ) Math.floor( lightReach - distanceFromLightSource );
					    					newRGB =  applyLightShift( newRGB, amount * 2, lightReach, roomLightSource );
					    				}
					    			}
					    		}
			    				newPixels[ k ][ j ].setRgb( newRGB );
			    				newImage.setRGB( j, k, newPixels[ k ][ j ].getRgb() );
		    				}
		    				
		    			}		    		
		    		}
				}
			}
		}
		
		if( thingImage != null ) {
			
			for( int i = 0; i < thingImage.getImageLayers().length; i++ ) {
				
				ImageLayer thingImageLayer = thingImage.getImageLayer( 0 );
				
				if( thingImageLayer != null ) {
					
					ImageFrame thingImageFrame = thingImageLayer.getActiveFrame();
					BufferedImage frameImage = thingImageFrame.getFrameImage();
					ImagePixel[][] pixels = thingImageFrame.getFramePixels();
					
		    		for( int j = 0; j < thingImage.getImageWidth(); j++ ) {
		    			
		    			for( int k = 0; k < thingImage.getImageHeight(); k++ ) {
		    				
		    				int newRGB = frameImage.getRGB(j, k);
	
		    				if( !isTransparent( newRGB ) ) {
		    					
			    				newPixels[ k ][ j ].setRgb( newRGB );
			    				newImage.setRGB( j, k, newPixels[ k ][ j ].getRgb() );
		    				}
		    			}
					}
				}
			}
		}
		    					
//			    				newRGB = applyAmbientLight( pixel.getRgb() );

			    				
//			    				for( int l = 0; l < Game.currentRoom.getAllRoomLightSourceObjects().size(); l++ ) {
//			    					
//			    					Thing lightSourceObject = Game.currentRoom.getAllRoomLightSourceObjects().get( l );
//			    					RadiatingLight roomLightSource = ( RadiatingLight ) lightSourceObject.getLightSources().get( 0 );
//			    					int lightSourceTileNumber = lightSourceObject.getCurrentGameTile().getTileNumber();
//			    					int thisTileNumber = gameTile.getTileNumber();
//			    					double distanceFromLightSource = Game.currentRoom.calculateDistance( lightSourceTileNumber, thisTileNumber);
//			    					double lightReach = roomLightSource.getDistance();
//			    					
//						    		if( distanceFromLightSource <= lightReach ) {
//						    			
//						    			String relativeDirectionOfLight = Game.currentRoom.calculateRelativeDirection( lightSourceTileNumber, thisTileNumber );
//
//						    			if( relativeDirectionOfLight.equals( "n" ) && thingImageLayer.getName().equals( "south" ) ||
//					    					relativeDirectionOfLight.equals( "s" ) && thingImageLayer.getName().equals( "north" ) ||
//					    					relativeDirectionOfLight.equals( "w" ) && thingImageLayer.getName().equals( "east"  ) ||
//					    					relativeDirectionOfLight.equals( "e" ) && thingImageLayer.getName().equals( "west"  ) ||
//					    					relativeDirectionOfLight.equals( "nw" ) && 
//					    					( thingImageLayer.getName().equals( "south" ) || thingImageLayer.getName().equals( "east" ) ) ||
//					    					relativeDirectionOfLight.equals( "sw" ) && 
//					    					( thingImageLayer.getName().equals( "north" ) || thingImageLayer.getName().equals( "east" ) ) ||
//					    					relativeDirectionOfLight.equals( "ne" ) && 
//					    					( thingImageLayer.getName().equals( "south" ) || thingImageLayer.getName().equals( "west" ) ) ||
//					    					relativeDirectionOfLight.equals( "se" ) && 
//					    					( thingImageLayer.getName().equals( "north" ) || thingImageLayer.getName().equals( "west" ) ) ) { 
//			
//					    					int amount = ( int ) Math.floor( lightReach - distanceFromLightSource );
//	
//						    				newRGB =  applyLightShift( newRGB, amount, lightReach );
//						    				newPixels[ k ][ j ].setRgb( newRGB );	
//						    			}
//				    				}
//					    		}
//			    				newImage.setRGB( j, k, newPixels[ k ][ j ].getRgb() );
//		    	        	}
//		    			}
//		    		}
//				}
//			}
//		}
		return newImage;
	}

	public int applyAmbientLight( int pixel ) {

        Color objColor = new Color( pixel );
        Color ambLightColor = Game.currentRoom.getAmbientLight().getRGB();
    
        int objRed   = objColor.getRed();
        int objBlue  = objColor.getBlue();
        int objGreen = objColor.getGreen();

        int ambLightRed	  = ambLightColor.getRed();
        int ambLightBlue  = ambLightColor.getBlue();
        int ambLightGreen = ambLightColor.getGreen();
        
        float[] objHSB = Color.RGBtoHSB( objRed, objGreen, objBlue, null);
        float objHue 		= objHSB[0];
        float objSaturation = objHSB[1];
        float objBrightness = objHSB[2];
        
        float[] abmHSB = Color.RGBtoHSB( ambLightRed, ambLightGreen, ambLightBlue, null);
        float ambHue 		= abmHSB[ 0 ];
        float ambSaturation = abmHSB[ 1 ];
        float ambBrightness = abmHSB[ 2 ];
        
        // adjust object's hue
        objHue = ( objHue + 10 ) % 360;   // figure out how to adjust hue
        
        // adjust object's brightness
        objBrightness = Game.currentRoom.getAmbientLight().getBrightness();
        
        if( objBrightness > 1.0 ) {
        	objBrightness = ( float ) 1.0;
        }
        
        if( objBrightness < 0 ) {
        	objBrightness = ( float ) 0;
        }
        
        int objRGB = Color.HSBtoRGB( objHue, objSaturation, objBrightness );

        objRed = ( objRGB>>16 )&0xFF;

        objGreen = ( objRGB>>8)&0xFF;

        objBlue = objRGB&0xFF;
        
        int newColorRGB = new Color( objRed, objGreen, objBlue ).getRGB();
        
        return newColorRGB;
 
	}

	public int applyLightShift( int pixel, int amount, double distance, RadiatingLight light ) {
		
		Color objColor = new Color( pixel );
        Color lightColor = light.getRGB();

        int objRed  	= objColor.getRed();
        int objBlue 	= objColor.getBlue();
        int objGreen    = objColor.getGreen();

        int lightRed	= lightColor.getRed();
        int lightBlue   = lightColor.getBlue();
        int lightGreen  = lightColor.getGreen();
        
        float[] objHSB = Color.RGBtoHSB( objRed, objGreen, objBlue, null);
        float objHue 		= objHSB[0];
        float objSaturation = objHSB[1];
        float objBrightness = objHSB[2];
        
        float[] lightHSB = Color.RGBtoHSB( lightRed, lightGreen, lightBlue, null);
        float lightHue 		  = lightHSB[ 0 ];
        float lightSaturation = lightHSB[ 1 ];
        float lightBrightness = lightHSB[ 2 ];
        
        // adjust object's hue
//        objHue = ( objHue + lightHue ) % 360;   // figure out how to adjust hue
        
        // adjust saturation
//        objSaturation = ( objSaturation + lightSaturation ) / 2;  
        
        // adjust object's brightness
        
        float brightnessRemainder = (float) (1.0 - objBrightness);				// change this. Light should not affect 
        																	// the brightness if the intensity is not strong enough
        float brightnessIncrement = (float) ( brightnessRemainder / distance);
        
        objBrightness += amount * brightnessIncrement * brightnessRemainder * .5;
        
        if( objBrightness > 1.0 ) {
        	objBrightness = ( float ) 1.0;
        }
        
        if( objBrightness < 0 ) {
        	objBrightness = ( float ) 0;
        }
         
        int objRGB = Color.HSBtoRGB( objHue, objSaturation, objBrightness );

        objRed = ( objRGB>>16 )&0xFF;

        objGreen = ( objRGB>>8)&0xFF;

        objBlue = objRGB&0xFF;
        
        int newColorRGB = new Color( objRed, objGreen, objBlue ).getRGB();
        
        return newColorRGB;	
	}
	
	public boolean isTransparent( int pixel ) {

		if( (pixel>>24) == 0x00 ) {
			
		    return true;
		}
		return false;
	}
}
