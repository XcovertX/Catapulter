package main.java.UserInterface;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

class TmxParser {

	private TMX tmx;
	public int size;
	public int layers, map_cols, map_rows, img_cols, img_rows;
	public ArrayList<Rect> objs = new ArrayList<Rect>();
	public BufferedImage image;
	public BufferedImage[] images;
	public BufferedImage tiles[][][];

	public TmxParser(String path) throws Exception {
		tmx = new TMX(path);
		
		images = getImages();
		
		size = tmx.tilewidth;
		
		layers = tmx.layerList.getLength();
		System.out.println(layers);
		map_cols = (int) (tmx.mapwidth);
		map_rows = (int) (tmx.mapheight);
		tiles = new BufferedImage[layers][map_rows][map_cols];

		for (int i = 0; i < tmx.tiles.length; i++) { // for each layer
			System.out.println("layer: " + i);
			for (int j = 0; j < tmx.tiles[0].length; j++) { // for each row 
//				System.out.println("row: " + j);
				for (int k = 0; k < tmx.tiles[0][0].length; k++) { // for each column
//					System.out.println("col: " + k);
					int tmxTileNum = tmx.tiles[i][j][k];
//					System.out.print(j + ", " + k);
//					System.out.print(", ");
					
					int tileSetNum = getTSX(tmxTileNum);
					if( tileSetNum >= 0 ) {
						int firstgID = Integer.valueOf(tmx.tilesets.item(tileSetNum).getAttributes().item(0).getNodeValue());
						int position = tmxTileNum - firstgID;
						if( position >= 0) {
							BufferedImage tileSetImage = ImageIO.read(new File(tmx.image_paths[tileSetNum]));
							tiles[i][j][k] = tileSetImage.getSubimage(position * size, 0, size, size);
						}
					}
				}
			}
		}

		objs = tmx.objs;
	}
	
	public BufferedImage[] getImages() throws IOException {
		BufferedImage[] imgs = new BufferedImage[ tmx.image_paths.length ];
		for(int i = 0; i < imgs.length; i++) {
			imgs[i] = ImageIO.read(new File(tmx.image_paths[i]));
		}
		return imgs;
	}
	
	public int getTSX( int tmxTileNum ) throws IOException {
		
		for(int i = 0; i < tmx.tsx_paths.length; i++) {
			int firstgID_1 = Integer.valueOf(tmx.tilesets.item(i).getAttributes().item(0).getNodeValue());
			if( tmxTileNum >= firstgID_1 ) {
				if( i + 1 < tmx.tsx_paths.length  ) {
					int firstgID_2 = Integer.valueOf(tmx.tilesets.item(i+1).getAttributes().item(0).getNodeValue());
					if( tmxTileNum < firstgID_2 ) {
						return i;
					}
				
				} else {
					
					return i;
				}
			}
		}
		return -1;
	}

}
