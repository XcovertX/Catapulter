package main.java.UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.StyledDocument;

import javafx.geometry.Rectangle2D;
import main.java.game.Game;
import main.java.utility.SignReader;
import main.java.world.GameRoom;
import main.java.world.GameTile;

public class DisplayNew {
	
	private JFrame mainFrame;
	
	private JPanel consolePanel;
	private JTextPane consoleDisplay;
	private JTextField consoleInput;
	private JScrollPane consoleScrollpane;
	public StyledDocument document;
	
	private JPanel mapHolder;
	private JPanel mapViewerPanel;
	private JTextArea map;
	
	private JPanel characterViewerPanel;
	private JTextArea character;
	private String textMap;
	private int[] mapEdges;
	private ArrayList<String> mapList;
	private ArrayList< ArrayList< TileChar > > tileChars;
	
	
	public DisplayNew() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {}
		
		mainFrame = new JFrame(); 
		mainFrame.setLayout(new BorderLayout( 5, 5 ));
		
		consolePanel = new JPanel();
		consolePanel.setLayout(new BorderLayout());
		consolePanel.setBackground(new Color(50, 50, 50));
         
		consoleDisplay = new JTextPane();
		consoleDisplay.setEditable(false);
		consoleDisplay.setFont(new Font("Consolas", Font.BOLD, 14));
		consoleDisplay.setOpaque(false);
		
		//graphicDisplay = 
		
		document = consoleDisplay.getStyledDocument();
		
		consoleInput = new JTextField();
		consoleInput.setEditable(true);
		consoleInput.setBackground(new Color(50, 50, 50));
		consoleInput.setForeground(Color.WHITE);
		consoleInput.setCaretColor(Color.WHITE);
		consoleInput.setFont(new Font("Consolas", Font.BOLD, 14));
		consoleInput.setOpaque(false);
		
		// scrollpane for output
		consoleScrollpane = new JScrollPane(consoleDisplay);
		consoleScrollpane.setOpaque(false);
		consoleScrollpane.getViewport().setOpaque(false);
		consoleScrollpane.setBorder(null);
		
		consolePanel.add(consoleInput, BorderLayout.SOUTH);
		consolePanel.add(consoleScrollpane, BorderLayout.CENTER);
		
		mapHolder = new JPanel();
		mapHolder.setLayout( new GridLayout(2, 1, 1, 1) );
        mapHolder.setBackground( new Color( 50, 50, 50 ) );
        mapHolder.setBorder( BorderFactory.createLineBorder( Color.cyan ) );
        mapHolder.setPreferredSize( new Dimension( 300, 300 ) );
		
        mapViewerPanel = new JPanel();
        mapViewerPanel.setBackground( Color.white );
        mapViewerPanel.setBorder( BorderFactory.createLineBorder( Color.orange ) );
        mapViewerPanel.setLayout( new GridBagLayout() );
        mapViewerPanel.setBackground(new Color(50, 50, 50));
        
        map = new JTextArea();
        map.setFont( new Font( "Consolas", Font.PLAIN, 17 ) );
        map.setBackground( new Color( 50, 50, 50 ) );
        map.setBorder( BorderFactory.createLineBorder( Color.green ) );
        map.setForeground( Color.white );
        
        //***********************************************
        //not used
        characterViewerPanel = new JPanel();
        characterViewerPanel.setBackground( Color.white );
        characterViewerPanel.setBorder( BorderFactory.createLineBorder( Color.pink ) );
        characterViewerPanel.setLayout( new GridBagLayout() );
        characterViewerPanel.setBackground( new Color( 50, 50, 50 ) );
        
        // make character.txt an attribute of Actor
        String c = new SignReader( "/files/graphics/", "character.txt" ).getText();
        character = new JTextArea( c );
        character.setFont( new Font( "Consolas", Font.BOLD, 10 ) );
        character.setBackground( new Color( 50, 50, 50 ) );
        character.setBorder( BorderFactory.createLineBorder( Color.red ) );
        character.setForeground( Color.white );
        
        //***********************************************
        
        characterViewerPanel.add( character );
        mapViewerPanel.add( map );
        
        mapHolder.add( mapViewerPanel );
        mapHolder.add( characterViewerPanel );
        
		mainFrame.add( consolePanel, BorderLayout.CENTER );
        mainFrame.add( mapHolder, BorderLayout.EAST );
        mainFrame.pack();
		mainFrame.setTitle("Catapulter");
		mainFrame.setSize(1300, 650);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	public void setRoom( GameRoom aRoom ) {
		
		String mapRep = "";
		textMap = "";
		mapList = new ArrayList<>();
		tileChars = new ArrayList<>();
		String[] mapArray = new String[ aRoom.getRoomWidth() * aRoom.getRoomLength() ];
		camera();
		int tileNumberTotal = aRoom.getRoomLength() * aRoom.getRoomWidth();
		mainFrame.setTitle( aRoom.getName() );
		
		int k = 0;
	    for( int i = aRoom.getRoomWidth(); i > 0; i-- ) {
	    	
	    	ArrayList< TileChar > row = new ArrayList<>();
	    	for( int j = aRoom.getRoomLength(); j > 0; j-- ) {
	    		
	    		int index = tileNumberTotal - j;
	    		GameTile gt = ( GameTile ) aRoom.getTiles().get( index );
	    		TileChar tc = gt.getCurrentTileChar();
	    		row.add( tc );
	    		String c = tc.getChar();
	    		mapRep += c;
	    		textMap += ( " " + Integer.toString( index ) );
	    		mapList.add( Integer.toString( index ) );
	    		mapArray[ k ] = ( ( GameTile ) aRoom.getTiles().get( index ) ).getCurrentTileChar().getChar();
	    		
	    		k++;
	    	}
	    	tileChars.add( row );
	    	textMap += "\r\n";
	    	if( i > 1 ) {
	    		mapRep += "\r\n";
	    	}
	    	mapList.add( "\r\n" );
	    	tileNumberTotal = tileNumberTotal - aRoom.getRoomLength();
    	}
		
	    int x = 0;
	    for(int i = 0; i < aRoom.getRoomWidth(); i++ ) {
	    	for( int j = 0; j < aRoom.getRoomLength(); j++ ) {
	    		System.out.print( mapArray[x] );
	    		x++;
	    	}
	    	System.out.println();
	    }
		map.setText( mapRep ); // TODO remove once new GUI is in place
		
		Game.currentGame.getUI().getGuiController().setMapChars( mapRep, tileChars ) ;
		
	}

	public void camera() {
		
		int length = 10;
		int width = 10;
		
		Rectangle2D cam = new Rectangle2D( 0, 0, length, width );
		
		
		
		mapEdges = calculateMapEdgeDistances(); // 0 = n, 1 = s, 2 = e, 3 = w
		
		int roomWidth = Game.currentRoom.getRoomWidth();
		int roomLength = Game.currentRoom.getRoomLength();	
		int currentTileNum = Game.currentTile.getTileNumber();
		int currentRow = mapEdges[ 3 ];
		int currentColumn = mapEdges[ 1 ];
		
		System.out.println( currentRow );
		System.out.println( currentColumn );
		System.out.println( Arrays.toString( mapEdges ) );
		
		ArrayList<String> westWallTrimmed;
		
		if( roomWidth > 10 ) {
			
			// west wall
			int westWallDistance = mapEdges[ 3 ];
			if( westWallDistance > 5 ) {
				int westWallDifference = westWallDistance - 5;
				for( int i = 0; i < mapList.size(); i++ ) {
					
				}
			}
			
		}
			
		
		if( mapEdges[ 0 ] > 10 ) {
			
		}
		
		
	}
	
	// direction calculations
    public int northEquation( GameTile gt ) {
    
    	return gt.getTileNumber() + Game.currentRoom.getRoomLength();
    }
    
    public int southEquation( GameTile gt ) {
    	
    	return gt.getTileNumber() - Game.currentRoom.getRoomLength();
    }
    
    public int eastEquation( GameTile gt ) {
    	
    	return gt.getTileNumber() + 1;
    }
    
    public int westEquation( GameTile gt ) {
    	
    	return gt.getTileNumber() - 1;
    }
	
    // map edge calculator
    
    public int[] calculateMapEdgeDistances() {
    	
    	GameTile startTile = Game.currentTile;
    	
    	int[] wallDistances = new int[ 4 ]; 			// 0 = n, 1 = s, 2 = e, 3 = w
    	
    	for( int i = 0; i < Game.currentRoom.getRoomWidth(); i++ ) {
    		
    		if( northWall( startTile ) ) {

    			wallDistances[ 0 ] = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( northEquation( startTile ) );
    		}
    	}
    	
    	for( int i = 0; i < Game.currentRoom.getRoomWidth(); i++ ) {
    		
    		if( southWall( startTile ) ) {
    			
    			wallDistances[ 1 ] = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( southEquation( startTile ) );
    		}
    	}
    	
    	for( int i = 0; i < Game.currentRoom.getRoomLength(); i++ ) {
    		
    		if( eastWall( startTile ) ) {
    			
    			wallDistances[ 2 ] = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( eastEquation( startTile ) );
    		}
    		
    	}
    	
    	for( int i = 0; i < Game.currentRoom.getRoomLength(); i++ ) {
    		
    		if( westWall( startTile ) ) {
    			
    			wallDistances[ 3 ] = i;
    			startTile = Game.currentTile;
    			break;
    			
    		} else {
    			
    			startTile = ( GameTile ) Game.currentRoom.getTiles().get( westEquation( startTile ) );
    		}
    	}
    	
    	return wallDistances;
    }
    
	// map wall detection
    public boolean northWall( GameTile gt ) {
    	
    	if( northEquation( gt ) > Game.currentRoom.getRoomSize() - 1 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean southWall( GameTile gt ) {
    	
    	if( southEquation( gt ) < 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean eastWall( GameTile gt ) {

    	if ( ( eastEquation( gt ) ) % ( Game.currentRoom.getRoomLength() )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean westWall( GameTile gt ) {

    	if ( ( gt.getTileNumber() ) % ( Game.currentRoom.getRoomLength() )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
		
    public JTextField getInputField() {
    	return consoleInput;
    }
    
    public JTextPane getConsoleDisplay() {
    	return consoleDisplay;
    }

}
