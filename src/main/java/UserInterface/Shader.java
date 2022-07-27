package main.java.UserInterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

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
	
	public BufferedImage shiftBlue( BufferedImage img, int amount ) {

//		BufferedImage bi = copyImage( img );
		
	    BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);

	    
	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {
	            Color color = new Color(img.getRGB(x, y));
	            int red = color.getRed();
	            int blue = color.getBlue();
	            int green = color.getGreen();

	            blue = blue + 20 - ( amount * 5 );
	            
	            if ( blue > 255 ) {
	            	int newColorRGB = new Color(red, green, 255 ).getRGB();
	                newImage.setRGB(x, y, newColorRGB);
	            } else if( blue < 0 ) {
	            	int newColorRGB = new Color(red, green, 0 ).getRGB();
	                newImage.setRGB(x, y, newColorRGB);
	            } else {
	            	int newColorRGB = new Color(red, green, blue ).getRGB();
	                newImage.setRGB(x, y, newColorRGB );
	            }
	        }
	    }
		return newImage;
	}
	
	public BufferedImage shiftBlueNearPlayer( BufferedImage img, int xPosition, int yPosition ) {

//		BufferedImage bi = copyImage( img );
		
	    BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(),
	            BufferedImage.TYPE_INT_ARGB);

	    
	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {
	            Color color = new Color(img.getRGB(x, y));
	            int red = color.getRed();
	            int blue = color.getBlue();
	            int green = color.getGreen();

	            if( x >= xPosition + 5 || x <= xPosition - 5) {
	            	if( y >= yPosition + 5 || y <= yPosition - 5) {
			            int newColorRGB = new Color( red, green, blue + 15 - Math.abs( xPosition ) ).getRGB();
			            if (blue != 255) {
			            	
			                newImage.setRGB(x, y, newColorRGB);

			            }
		            }
	            } else {
	            	
	                newImage.setRGB(x, y, color.getRGB());
	                
	            }
	        }
	    }
		return newImage;
	}
}
