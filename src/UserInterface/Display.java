package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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

import utility.SignReader;
//import world.GameMap;
import world.GameRoom;
import world.GameTile;

public class Display {
	
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
	
	
	public Display() {
		
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
        String c = new SignReader( "/files/graphics/", "character.txt").getText();
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
		int roomNumberTotal = aRoom.getRoomLength() * aRoom.getRoomWidth();
		mainFrame.setTitle( aRoom.getName() );
		
		
	    for( int i = aRoom.getRoomWidth(); i > 0; i-- ) {
	    	
	    	for( int j = aRoom.getRoomLength(); j > 0; j-- ) {
	    		
	    		int index = roomNumberTotal - j;
	    		mapRep += ( ( GameTile ) aRoom.getTiles().get( index ) ).getTileChar();
	    	}
	    	mapRep += "\r\n";
	    	roomNumberTotal = roomNumberTotal - aRoom.getRoomLength();
    	}
		
		map.setText( mapRep );
	}
		
    public JTextField getInputField() {
    	return consoleInput;
    }
    
    public JTextPane getConsoleDisplay() {
    	return consoleDisplay;
    }

}
