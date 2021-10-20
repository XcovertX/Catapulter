package mapBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import gameObjects.Actor;
import gameObjects.Chest;
import gameObjects.Key;
import gameObjects.NonPlayerActor;
import gameObjects.Table;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import gameObjects.TileList;
import gameObjects.Treasure;
import globals.Direction;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;


import utility.PopUpMessage;
import utility.ThingTypeRegistry;
import wearableObjects.Ring;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;

public class RoomBuilderView extends JFrame implements ActionListener, ListSelectionListener {
	
	//TODO make dynamic
	private String currentMapName = "Mappy";
	private String currentMapDescription ="blahhh";
	private String currentRoomName = "Saloon";
	private String currentRoomDescription = "It is dirty";
	
	private JTextField widthInput;
	private JTextField lengthInput;
	private int roomLength;
	private int roomWidth;
	
	private JFrame roomLayout;
	private JPanel layout;
	private JButton[] roomTiles = null;

	// current local state
	private Container mainContainer;
	
	private RoomBuilder currentRoomBuilder;
	
	private GameWorld currentWorld;
	private GameMap currentMap;
	private GameRoom currentRoom;
	private GameTile currentTile;
	private int currentTileNumber;
	private String currentTileName;
	private String currentTileDescription;
	private ThingList currentTileList;
	private String currentTileDefaultTileChar;
	
	private ThingList currentTileThingList;
	private ThingList currentTileNPCList;	
	private JList< Thing > currentTileNPCJList;
	private DefaultListModel< Thing > npcModel;
	private JList< Thing > currentTileItemJList;
	private DefaultListModel< Thing > itemModel;

	private JCheckBox isNotTile;
	private boolean notTile;
	
	private JLabel selectedTileNumber;
	private JTextField userInTileName;
	private JTextArea userInTileDescription;
	private ListSelectionModel currentLSM;
	private int currentSelectedIndex;
	
	private JLabel currentTileLabel;
	private JLabel northTile;
	private JLabel southTile;
	private JLabel eastTile;
	private JLabel westTile;
	private JLabel northEastTile;
	private JLabel northWestTile;
	private JLabel southEastTile;
	private JLabel southWestTile;
	private JLabel upTile;
	private JLabel downTile;
	private JLabel specialTile;
	
	private int north;
	private int south;
	private int east;
	private int west;
	private int northWest;
	private int northEast;
	private int southWest;
	private int southEast;
	private int up;
	private int down;
	private int special;
	private static final int NOEXIT = -1;
	
	private JButton addNorthExit;
	private JButton addSouthExit;
	private JButton addEastExit;
	private JButton addWestExit;
	private JButton addNorthWestExit;
	private JButton addNorthEastExit;
	private JButton addSouthWestExit;
	private JButton addSouthEastExit;
	private JButton addUpExit;
	private JButton addDownExit;
	private JButton addSpecialExit;
	private JLabel tile;

	// colorscheme
	final static Color catapulterWhite = Color.WHITE;
	final static Color catapulterBlack = Color.BLACK;
	final static Color catapulterGray = Color.GRAY;
	final static Color catapulterRed = new Color( 255, 78, 55 );
	final static Color catapulterGreen = new Color( 0, 211, 130 );
	
	public RoomBuilderView( RoomBuilder aRoomBuilder, String title, GameWorld aGameWorld ) {
		
		super(title);
		this.currentRoomBuilder = aRoomBuilder;
		this.currentWorld = aGameWorld;
		this.currentMap = (GameMap) currentWorld.getMaps().get(0);
	}
	
	// room builder starting frame
	public void startBuilder() {
		
		JFrame mainStartFrame = new JFrame();

        JPanel startPanel = new JPanel();
        startPanel.setBackground( catapulterGray );
        startPanel.setLayout( new BoxLayout( startPanel, BoxLayout.X_AXIS ) );
        
        JButton newRoom = new JButton( "New Room" );
        newRoom.addActionListener( new ActionListener() {
        	
        	public void actionPerformed( ActionEvent e ) {
        		
        		mainStartFrame.dispose();
    			roomSize();	
        	}
        });
        
        JButton exit = new JButton( "Exit Room Builder" );
        exit.addActionListener( this );
        
        JButton loadRoom = new JButton( "Load Room" );
        loadRoom.addActionListener( new ActionListener() {
        	
        	public void actionPerformed( ActionEvent e ) {
        		
        		mainStartFrame.dispose();
    			getRoom();	
        	}
        });
        
        JButton buildActor = new JButton( "Build Actor" );
        buildActor.addActionListener( new ActionListener() {
        	
        	public void actionPerformed( ActionEvent e ) {
        		
        		mainStartFrame.dispose();
//    			new ActorBuilder().builderFrame();	
        	}
        });
        
        startPanel.add( Box.createRigidArea( new Dimension( 10, 10 ) ) );
        startPanel.add( Box.createHorizontalGlue() );
        startPanel.add( newRoom );
        startPanel.add( Box.createHorizontalGlue() );
        startPanel.add( exit );
        startPanel.add( Box.createHorizontalGlue() );
        startPanel.add( loadRoom );
        startPanel.add( Box.createHorizontalGlue() );
        startPanel.add( buildActor );
        startPanel.add( Box.createHorizontalGlue() );
		startPanel.add( Box.createRigidArea( new Dimension( 10, 10 ) ) );
		
        mainStartFrame.add( startPanel );
		mainStartFrame.setSize( 400, 200 );
        mainStartFrame.setTitle( "Room Builder" );
        mainStartFrame.setLocationRelativeTo( null );
        mainStartFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainStartFrame.setVisible( true );
    }
    
	//TODO build gets location of map to be loaded
    public void getRoom() {
    	
    	JFrame roomSizeFrame = new JFrame();
    	
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout( new GridLayout( 3, 1, 5, 5 ) );
    	mainPanel.setBackground( catapulterGray );
    	
        JPanel dimensionsInputPanelTop = new JPanel();
        dimensionsInputPanelTop.setLayout( new FlowLayout(1, 5, 5) );
        dimensionsInputPanelTop.setBackground( catapulterGray );
        
        JPanel dimensionsInputPanelBottom = new JPanel();
        dimensionsInputPanelBottom.setLayout( new FlowLayout( 1, 5, 5 ) );
        dimensionsInputPanelBottom.setBackground( catapulterGray );
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
        buttonPanel.setBackground( catapulterGray );
  
        JLabel labelLocation = new JLabel( "File Location" );
        dimensionsInputPanelTop.add( labelLocation );
        
    	JTextField location = new JTextField( "", 20 );
    	dimensionsInputPanelTop.add( location );
    	
    	dimensionsInputPanelTop.add( labelLocation );
    	dimensionsInputPanelTop.add( location );
    	
    	mainPanel.add( dimensionsInputPanelTop );
    	mainPanel.add( dimensionsInputPanelBottom );
    	
    	// Create Layout button
    	JButton createLayout = new JButton( "Cancel" );
		createLayout.addActionListener( this );
    
        JButton exit = new JButton( "Load Room" );
        exit.addActionListener( this );
        
		
		buttonPanel.add( createLayout );
        buttonPanel.add( exit );
        
        mainPanel.add( buttonPanel );
        
        roomSizeFrame.add( mainPanel );
        
        roomSizeFrame.getContentPane().setBackground( new Color( 50, 50, 50 ) );
        roomSizeFrame.setSize( 400, 200 );
        roomSizeFrame.setLocationRelativeTo( null );
        roomSizeFrame.setResizable( true );
        roomSizeFrame.setVisible( true );

    }
		
	// gets desired map size from user
    public void roomSize() {
    	
    	JFrame roomSizeFrame = new JFrame();
    	
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout( new GridLayout( 3, 1, 5, 5 ) );
    	mainPanel.setBackground( catapulterGray );
    	
        JPanel dimensionsInputPanelTop = new JPanel();
        dimensionsInputPanelTop.setLayout( new FlowLayout(1, 5, 5) );
        dimensionsInputPanelTop.setBackground( catapulterGray );
        
        JPanel dimensionsInputPanelBottom = new JPanel();
        dimensionsInputPanelBottom.setLayout( new FlowLayout( 1, 5, 5 ) );
        dimensionsInputPanelBottom.setBackground( catapulterGray );
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
        buttonPanel.setBackground( catapulterGray );

        
        JLabel labelLength = new JLabel( "Length" );
        dimensionsInputPanelTop.add( labelLength );
        
    	lengthInput = new JTextField( "", 5 );
    	dimensionsInputPanelTop.add( lengthInput );
    	
    	dimensionsInputPanelTop.add( labelLength );
    	dimensionsInputPanelTop.add( lengthInput );
    	
        JLabel labelWidth = new JLabel( "Width" );
        dimensionsInputPanelBottom.add( labelWidth );
        
    	widthInput = new JTextField( "", 5 );
    	dimensionsInputPanelBottom.add( widthInput );
    	
    	dimensionsInputPanelBottom.add( labelWidth );
    	dimensionsInputPanelBottom.add( widthInput );
    	
    	mainPanel.add( dimensionsInputPanelTop );
    	mainPanel.add( dimensionsInputPanelBottom );
    	
    	// Create Layout button
    	JButton createLayout = new JButton( "Create Room Layout" );
		createLayout.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed( ActionEvent e ) {
				
				roomSizeFrame.dispose();
				
				if( lengthInput.getText().matches( "^[0-9]{1,2}$" ) && widthInput.getText().matches( "^[0-9]{1,2}$" ) ) {
					
					int length = Integer.parseInt( lengthInput.getText() );
					int width = Integer.parseInt( widthInput.getText() );
					
					if( length > 0 && width > 0 ) {
						
						roomLayout( length, width );
						
						buildMainGUI();
						
						setCurrentTile( 0 );
						
						currentRoomBuilder.setViewVisible();
					}
						
				} else {
					
					JFrame mainFrame = new JFrame();
					mainFrame.setSize( 500, 130 );
					
					JPanel message = new JPanel();
					message.setLayout( new GridLayout( 2, 1, 5, 5 ) );
					message.setBackground( catapulterWhite );
					
			        JPanel topPanel = new JPanel();
			        topPanel.setLayout( new FlowLayout(1, 5, 5) );
			        topPanel.setBackground( catapulterWhite );
			        
			        JPanel bottomPanel = new JPanel();
			        bottomPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
			        bottomPanel.setBackground( catapulterWhite );
					
					JLabel warning = new JLabel( "You must enter nubers that are greater than zero.");
					warning.setFont( new Font( "Courier New", Font.BOLD, 14 ) );;
					warning.setForeground( catapulterRed );
					warning.setHorizontalAlignment( SwingConstants.CENTER );
					warning.setBackground( catapulterWhite );
					
					
					JButton ok = new JButton( "Ok." );
					ok.addActionListener( new ActionListener() {
						
						@Override
						public void actionPerformed( ActionEvent e ) {
							
							mainFrame.dispose();
							roomSizeFrame.setEnabled( true );
							roomSizeFrame.setVisible( true );
							
						}
					});
					
					topPanel.add( warning );
					bottomPanel.add( ok );
					message.add( topPanel );
					message.add( bottomPanel );
					mainFrame.add( message );
					mainFrame.setVisible( true );
				}
			}
							
		});

        JButton exit = new JButton( "Exit" );
        exit.addActionListener( this );
        
        buttonPanel.add( createLayout );
        buttonPanel.add( exit );
        
        mainPanel.add( buttonPanel );
        
        roomSizeFrame.add( mainPanel );
        
        roomSizeFrame.getContentPane().setBackground( new Color( 50, 50, 50 ) );
        roomSizeFrame.setSize( 400, 200 );
        roomSizeFrame.setLocationRelativeTo( null );
        roomSizeFrame.setResizable( true );
        roomSizeFrame.setVisible( true );

    }
    
    // builds frame with all rooms
    private void roomLayout(int length, int width) {
    	
    	this.roomLength = length;
    	this.roomWidth = width;
	    
	    int tileNumberTotal = length * width;
    	
	    roomLayout = new JFrame();
	    
	    // Builds the map master copy in MapBuilder
	   // currentMapBuilder.buildLayout( roomNumberTotal );
	    
	    // Builds the map working copy in view
	    buildLayout( tileNumberTotal );
	    
	    layout = new JPanel();
	    layout.setLayout( new GridLayout( width, length ) );
	    layout.setBackground( catapulterGray );
	    
	    roomTiles = new JButton[ tileNumberTotal ] ;
	    
	    for( int i = width; i > 0; i-- ) {
	    	
	    	for( int j = length; j > 0; j-- ) {
	    		
	    		int index = tileNumberTotal - j;
	    		
	    		addButton( layout, Integer.toString( index ) );
	    	}
	    	
	    	tileNumberTotal = tileNumberTotal - length;
    	}
	    
	    roomLayout.add( layout );
	    roomLayout.setTitle( "Room Layout" );
	    roomLayout.setEnabled( true );
	    roomLayout.setSize( 370, 700 );
		setLocationToRight( roomLayout );
		roomLayout.setVisible( true );
		roomLayout.setDefaultCloseOperation( HIDE_ON_CLOSE );
    }
    
    // adds buttons to mapLayout
    private void addButton(JPanel pPanel, String pText) {
    	
        JButton button = new JButton( pText );
        
        int tileNumber = Integer.parseInt( pText );
        
		if( tileNumber > 99 ) {
			
			button.setFont( new Font( "Courier New", Font.BOLD, 7 ) );
			
		} else if( tileNumber > 9 ){
			
			button.setFont( new Font( "Courier New", Font.BOLD, 10 ) );
			
		} else {
			
			button.setFont( new Font( "Courier New", Font.BOLD, 14 ) );
		}
		
        roomTiles[ tileNumber ] = button;
        roomTiles[ tileNumber ].addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent roomEvent ) {
        		
        		saveCurrentTile();
        		setCurrentTile( tileNumber );
        	}
        } );
        
        pPanel.add( button );
    }
    
    // adds new item to room
    private void newItem( Thing aThingHolder, DefaultListModel< Thing > upperModel ) {
    	
    	List< Thing > newItems = new ArrayList< Thing >();
    
    	
    	DefaultListModel< Thing > underModel = new DefaultListModel< Thing >();
    	
    	JFrame mainFrame = new JFrame();
    	mainFrame.setLayout( new BorderLayout( 8, 6 ) );
    	
    	JPanel topPanel = new JPanel();
    	topPanel.setLayout( new BoxLayout( topPanel, BoxLayout.X_AXIS ) );
    	
    	JPanel topPanelSecondRow = new JPanel();
    	topPanelSecondRow.setLayout( new GridLayout( 1, 3, 5, 5 ) );
    	
    	JPanel centerStack = new JPanel();
    	centerStack.setLayout( new GridLayout( 0, 1, 5, 5 ) );
    	
    	JPanel centerFirstRow = new JPanel();
    	centerFirstRow.setLayout( new GridLayout( 0, 2, 5, 5 ) );
    	
    	JPanel centerPanel = new JPanel();
    	centerPanel.setLayout( new BoxLayout( centerPanel, BoxLayout.Y_AXIS ) );
    	
    	JPanel topHolderStack = new JPanel();
    	topHolderStack.setLayout( new GridLayout( 0, 1, 5, 5 ) );
    	
    	JPanel topHolder = new JPanel();
    	topHolder.setLayout( new GridLayout( 0, 4, 5, 5 ) );
    	
    	JPanel matterStates = new JPanel();
    	matterStates.setLayout( new GridLayout( 0, 1, 5, 5 ) );
    	
    	JPanel ables = new JPanel();
    	ables.setLayout( new GridLayout( 5, 0, 5, 5 ) );
    	
    	JPanel location = new JPanel();
    	location.setLayout( new GridLayout( 5, 0, 5, 5 ) );
    	
    	JLabel itemName = new JLabel( "Item Name: " );
    	JTextField userInName = new JTextField( "", 30 );

    	JLabel itemType = new JLabel( "Item Type: " );
    	JTextField userInType = new JTextField( "", 30 );
    	
    	JLabel itemSize = new JLabel( "Item Size: " );
    	JTextField userInSize = new JTextField( "", 5 );
    	
    	JLabel itemWeight = new JLabel( "Item Weight: " );
    	JTextField userInWeight = new JTextField( "", 5 );
    	
    	JLabel itemLocationInRoom = new JLabel( "Item Location In Room: " );
    	JTextField userInLocationInRoom = new JTextField( "", 30 );
    	
    	JLabel itemValue = new JLabel( "Item Value:" );
    	JTextField userInValue = new JTextField( "", 10 );
    	
    	JLabel largeText = new JLabel( "Large Text:" );
    	JTextField userInLargeText = new JTextField( "", 30 );
    	
    	JLabel largeTextLocation = new JLabel( "Large Text Location:" );
    	JTextField userInLargeTextLocation = new JTextField( "", 10 );
    	
    	JLabel smallText = new JLabel( "Small Text:" );
    	JTextField userInSmallText = new JTextField( "", 30 );
    	
    	JLabel smallTextLocation = new JLabel( "Small Text Location:" );
    	JTextField userInSmallTextLocation = new JTextField( "", 10 );
    	
    	JLabel altNamesLabel = new JLabel( "Alternative Names:" );
    	JTextField userInAltNames = new JTextField( "", 30 );
    	
    	JLabel descriptorsLabel = new JLabel( "Descriptors:" );
    	JTextField userInDescriptors = new JTextField( "", 30 );
    	
    	JLabel itemAttributes = new JLabel( "Item Attributes: " );
    	
    	JLabel matterStateLabel = new JLabel( "Matter State: " );
    	JCheckBox solid = new JCheckBox( "Solid" );
    	JCheckBox liquid = new JCheckBox( "Liquid" );
    	JCheckBox gas = new JCheckBox( "Gas" );
    	
    	JLabel ableLabel = new JLabel( "Abilities: " );

    	JCheckBox drinkable = new JCheckBox( "Drinkable" );
    	
    	JCheckBox eatable = new JCheckBox( "Eatable" );
    	
    	JCheckBox visible = new JCheckBox( "Visible" );
    	
    	JCheckBox holdable = new JCheckBox( "Holdable" );
    	
    	JCheckBox readable = new JCheckBox( "Readable" );
    	
    	JCheckBox smellable = new JCheckBox( "Smellable" );
    	
    	JCheckBox attackable = new JCheckBox( "Attackable" );
    	
    	JCheckBox wieldable = new JCheckBox( "Wieldable" );
    	
    	JCheckBox wearable = new JCheckBox( "Wearable" );

    	JCheckBox isTranslucent = new JCheckBox( "isTranslucent" );
    	
    	JLabel ltLabel = new JLabel( "Has Large Text: " );
    	JCheckBox hasLargeText = new JCheckBox();
    	
    	JLabel stLabel = new JLabel( "Has Small Text: " );
    	JCheckBox hasSmallText = new JCheckBox();
    	
    	JLabel locatedLabel = new JLabel( "Located: " );
    	
    	JCheckBox isOnTopOf = new JCheckBox( "Is On Top Of" );

    	JCheckBox isUnderneath = new JCheckBox( "Is Underneath" );

    	JCheckBox isContainedWithin = new JCheckBox( "Is Contained Within" );
    	
    	topHolder.add( itemAttributes );
    	topHolder.add( Box.createRigidArea( new Dimension() ) );
    	topHolder.add( Box.createRigidArea( new Dimension() ) );
    	topHolder.add( Box.createRigidArea( new Dimension() ) );
    	topHolder.add( itemName );
    	topHolder.add( userInName );
    	topHolder.add( itemType );
    	topHolder.add( userInType );
    	topHolder.add( altNamesLabel );
    	topHolder.add( userInAltNames );
    	topHolder.add( itemWeight );
    	topHolder.add( userInWeight );
    	topHolder.add( itemSize );
    	topHolder.add( userInSize );
    	topHolder.add( itemValue );
    	topHolder.add( userInValue );
    	topHolder.add( largeText );
    	topHolder.add( userInLargeText );
    	topHolder.add( largeTextLocation );
    	topHolder.add( userInLargeTextLocation );
    	topHolder.add( smallText );
    	topHolder.add( userInSmallText );
    	topHolder.add( smallTextLocation );
    	topHolder.add( userInSmallTextLocation );
    	topHolder.add( itemLocationInRoom );
    	topHolder.add( userInLocationInRoom );
    	
    	matterStates.add( matterStateLabel );
    	matterStates.add( solid );
    	matterStates.add( liquid );
    	matterStates.add( gas );
    	matterStates.add( isTranslucent );
    	
    	ables.add( ableLabel );
    	ables.add( eatable );
    	ables.add( drinkable );
    	ables.add( smellable );
    	ables.add( visible );
    	ables.add( readable );
    	ables.add( holdable );
    	ables.add( wieldable );
    	ables.add( attackable );
    	ables.add( wearable );
    	
    	location.add( locatedLabel );
    	location.add( isOnTopOf );
    	location.add( isUnderneath );
    	location.add( isContainedWithin );
    	location.add( Box.createRigidArea( new Dimension() ) );
    	
    	topPanelSecondRow.add( matterStates );
    	topPanelSecondRow.add( ables );
    	topPanelSecondRow.add( location );

    	topHolderStack.add ( topHolder );
    	topHolderStack.add( topPanelSecondRow );
    	
    	//center components
    	

    	JPanel centerBottom = new JPanel();
    	centerBottom.setLayout( new GridLayout( 0, 2, 5, 5 ) );
 
    	JLabel itemDescription = new JLabel( "Item Description:" );
    	JTextArea userInDescription = new JTextArea( 24, 10 );
    	JScrollPane desScroll = new JScrollPane( userInDescription );
    	
    	centerFirstRow.add( descriptorsLabel );
    	centerFirstRow.add( userInDescriptors );
    	
    	centerBottom.add( itemDescription );
    	centerBottom.add( desScroll );
    	
    	centerStack.add( centerFirstRow );
    	centerStack.add( centerBottom );
    	
    	// right panel
    	
    	JPanel leftPanelStack = new JPanel();
    	leftPanelStack.setLayout( new GridLayout( 0, 1, 5, 5) );
//    	
//		JPanel rightPanel = new JPanel();
//		rightPanel.setBackground( catapulterGray );
//		rightPanel.setLayout( new BoxLayout( rightPanel, BoxLayout.Y_AXIS ) );
		
		JPanel middleLeftPanel = new JPanel();
		//middleLeftPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		middleLeftPanel.setBackground( catapulterGray );
		middleLeftPanel.setLayout( new GridLayout( 2, 1, 5, 5 ) );
		
		JPanel middleLeftTopPanel = new JPanel();
		middleLeftTopPanel.setBorder( new LineBorder( catapulterGray, 3 ) );
		//middleLeftTopPanel.setBackground( catapulterWhite );
		middleLeftTopPanel.setLayout( new BoxLayout( middleLeftTopPanel, BoxLayout.Y_AXIS ) );
		
		JPanel listItemHolder = new JPanel();
		listItemHolder.setBackground( catapulterGray );
		listItemHolder.setLayout( new BoxLayout( listItemHolder, BoxLayout.Y_AXIS ) );
		
		JLabel listItem = new JLabel( "Items held by", SwingConstants.CENTER );
		JPanel listItemLabelHolder = new JPanel(); 
		listItemLabelHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
		listItemLabelHolder.add( listItem );
		
		JPanel middleLeftTopButtonsPanel = new JPanel();
		middleLeftTopButtonsPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		middleLeftTopButtonsPanel.setBackground( catapulterGray );
		
		JButton addItem = new JButton( "Add Item" );
		addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
        		if( newItems.isEmpty() ) {
        			Thing newItem = buildItem( aThingHolder, upperModel, userInName.getText(), userInDescription.getText(), userInType.getText() );
					newItems.add( 0, newItem );
        		}
        		
        		// setting attributes for thing
        		Thing thing = newItems.get(0);
					
        		thing.setName( userInName.getText() );
        		thing.setDescription( userInDescription.getText() );
        		thing.setLocationInRoom( userInLocationInRoom.getText() );
    			
    			if( !userInSize.getText().equals("") && userInSize.getText().matches( "^[0-9]{1,9}$" ) ) {
    				
    				thing.setSize( Integer.parseInt( userInSize.getText() ) );
    			}
    			if( !userInWeight.getText().equals("") && userInWeight.getText().matches( "([0-9]*)\\.([0-9]*)" ) ) {

    				thing.setWeight( Double.parseDouble( userInWeight.getText() ) );
    			}
    			if( !userInLargeText.getText().equals("") ) {
    				
    				thing.setHasLargeText( true );
    				thing.setReadableLargeText( userInLargeText.getText() );
    				thing.setReadableLargeTextLocation( userInLargeTextLocation.getText() );
    				thing.setReadable( true );
    			}
    			if( !userInSmallText.getText().equals("") ) {
    				
    				thing.setHasSmallText( true );
    				thing.setReadableSmallText( userInSmallText.getText() );
    				thing.setReadableSmallTextLocation( userInSmallTextLocation.getText() );
    				thing.setReadable( true );
    			}
    			
    			if( !userInValue.getText().equals("") 
    					&& userInValue.getText().matches( "^[0-9]{1,9}$" ) ) {
    				
    				thing.setValue( Integer.parseInt( userInValue.getText() ) );
    			}
				
				if( solid.isSelected() ) {
					thing.setMatterState( "solid" );
				} else if( liquid.isSelected() ) {
					thing.setMatterState( "liquid" );
				} else if( gas.isSelected() ) {
					thing.setMatterState( "gas" );
				}
				
        		if( eatable.isSelected() ) {
        			thing.setEatable( true );
        		}
        		if( drinkable.isSelected() ) {
        			thing.setDrinkable( true );
        		}
        		if( smellable.isSelected() ) {
        			thing.setSmellable( true );
        		}
        		if( visible.isSelected() ) {
        			thing.setVisible( true );
        		}
        		if( readable.isSelected() ) {
        			thing.setReadable( true );
        		}
        		if( visible.isSelected() ) {
        			thing.setVisible( true );
        		}
        		if( holdable.isSelected() ) {
        			thing.setHoldable( true );
        		}
        		if( visible.isSelected() ) {
        			thing.setVisible( true );
        		}
        		if( wearable.isSelected() ) {
        			thing.setWearable( true );
        		}
        		if( attackable.isSelected() ) {
        			thing.setAttackable( true );
        		}
        		if( isOnTopOf.isSelected() ) {
        			thing.setOnTopOf( true );
        		}
        		if( isUnderneath.isSelected() ) {
        			thing.setUnderneath( true );
        		}
        		if( isTranslucent.isSelected() ) {
        			thing.setTranslucence( true );
        		}
        		
				if( newItems.get(0).isThingHolder() ) {
					
					// set attributes for thing holders
					ThingHolder tHolder = ( ThingHolder ) newItems.get(0);
					
					newItem( tHolder, underModel );
					
				} else {
					
					newItem( thing, underModel );
				}
			}
		});
		addItem.setBackground( catapulterGreen );
		
		JButton deleteItem = new JButton( "Delete Item");
		deleteItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				deleteItem();
			}
		});
		deleteItem.setBackground( catapulterRed );
		middleLeftTopButtonsPanel.add( addItem );
		middleLeftTopButtonsPanel.add( deleteItem );
		
		// room item list display
		
//		DefaultListModel< Thing > underModel = new DefaultListModel<>();
		currentTileItemJList = new JList<>( underModel );
		currentTileItemJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		currentTileItemJList.setSelectedIndex( 0 );
		
		ListSelectionModel itemListSelectionModel = currentTileItemJList.getSelectionModel();
		itemListSelectionModel.addListSelectionListener( this );
		currentTileItemJList.setVisibleRowCount( 5 );
		
		JScrollPane tileItemListScrollPane = new JScrollPane( currentTileItemJList );
		
		listItemHolder.add( listItemLabelHolder );
		listItemHolder.add( tileItemListScrollPane );
		listItemHolder.add( middleLeftTopButtonsPanel );
		
		middleLeftTopPanel.add( listItemHolder ); 
		
		leftPanelStack.add( middleLeftTopPanel );
		
		// bottom pane
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		
		JButton save = new JButton( "Save" );
		save.addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent e ) {
        	
        		if( newItems.isEmpty() ) {
        			
					newItems.add( 0, buildItem( aThingHolder, upperModel, userInName.getText(), userInDescription.getText(), userInType.getText() ) );
        		}
        		
        		Thing thing = newItems.get( 0 );
        		
				if( newItems.get(0).isThingHolder() ) {
					
					ThingHolder tHolder = ( ThingHolder ) newItems.get(0);
					
        			tHolder.setName( userInName.getText() );
        			tHolder.setDescription( userInDescription.getText() );
        			tHolder.setLocationInRoom( userInLocationInRoom.getText() );
        			
        			if( !userInSize.getText().equals("") && userInSize.getText().matches( "^[0-9]{1,9}$" ) ) {
        				
        				tHolder.setSize( Integer.parseInt( userInSize.getText() ) );
        			}
        			if( !userInWeight.getText().equals("") && userInWeight.getText().matches( "([0-9]*)\\.([0-9]*)" ) ) {

            			tHolder.setWeight( Double.parseDouble( userInWeight.getText() ) );
        			}
        			if( !userInLargeText.getText().equals("") ) {
        				
        				tHolder.setHasLargeText( true );
        				tHolder.setReadableLargeText( userInLargeText.getText() );
        				tHolder.setReadableLargeTextLocation( userInLargeTextLocation.getText() );
        				tHolder.setReadable( true );
        			}
        			if( !userInSmallText.getText().equals("") ) {
        				
        				tHolder.setHasSmallText( true );
        				tHolder.setReadableSmallText( userInSmallText.getText() );
        				tHolder.setReadableSmallTextLocation( userInSmallTextLocation.getText() );
        				tHolder.setReadable( true );
        			}
        			
        			if( !userInValue.getText().equals("") 
        					&& userInValue.getText().matches( "^[0-9]{1,9}$" ) ) {
        				
        				tHolder.setValue( Integer.parseInt( userInValue.getText() ) );
        			}
					
					if( solid.isSelected() ) {
						tHolder.setMatterState( "solid" );
					} else if( liquid.isSelected() ) {
						tHolder.setMatterState( "liquid" );
					} else if( gas.isSelected() ) {
						tHolder.setMatterState( "gas" );
					}
					
	        		if( eatable.isSelected() ) {
	        			tHolder.setEatable( true );
	        		}
	        		if( drinkable.isSelected() ) {
	        			tHolder.setDrinkable( true );
	        		}
	        		if( smellable.isSelected() ) {
	        			tHolder.setSmellable( true );
	        		}
	        		if( visible.isSelected() ) {
	        			tHolder.setVisible( true );
	        		}
	        		if( readable.isSelected() ) {
	        			tHolder.setReadable( true );
	        		}
	        		if( visible.isSelected() ) {
	        			tHolder.setVisible( true );
	        		}
	        		if( holdable.isSelected() ) {
	        			tHolder.setHoldable( true );
	        		}
	        		if( visible.isSelected() ) {
	        			tHolder.setVisible( true );
	        		}
	        		if( wearable.isSelected() ) {
	        			tHolder.setWearable( true );
	        		}
	        		if( attackable.isSelected() ) {
	        			tHolder.setAttackable( true );
	        		}
	        		if( isOnTopOf.isSelected() ) {
	        			tHolder.setOnTopOf( true );
	        		}
	        		if( isUnderneath.isSelected() ) {
	        			tHolder.setUnderneath( true );
	        		}
	        		if( isTranslucent.isSelected() ) {
	        			tHolder.setTranslucence( true );
	        		}
	        		
	        		newItem( tHolder, underModel );
        		}
        	}
        } );
		
		JButton exit = new JButton( "Exit" );
		exit.addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent roomEvent ) {
        		
        		mainFrame.dispose();
        	}
        } );

		bottomPanel.add( exit );
		bottomPanel.add( save );
		
        mainFrame.add( topHolderStack, BorderLayout.NORTH );
    	mainFrame.add( centerStack, BorderLayout.CENTER );
    	mainFrame.add( leftPanelStack, BorderLayout.WEST );
    	mainFrame.add( bottomPanel, BorderLayout.SOUTH );

    	mainFrame.setSize( 1200, 700 );
    	mainFrame.setLocation(10, 10);
    	mainFrame.setVisible( true );
    }
    
    // adds new NPC to room
    public void newNPC() {
    	
    	ThingList npcInvetory = new ThingList();
    	
    	JFrame mainFrame = new JFrame();
    	mainFrame.setLayout( new BorderLayout( 8, 6 ) );
    	
    	JPanel topPanel = new JPanel();
    	topPanel.setLayout( new BoxLayout( topPanel, BoxLayout.X_AXIS ) );
    	
    	JPanel centerPanel = new JPanel();
    	centerPanel.setLayout( new BoxLayout( centerPanel, BoxLayout.Y_AXIS ) );
    	
    	JPanel topHolder = new JPanel();
    	topHolder.setLayout( new GridLayout( 2, 1, 5, 5 ) );
    	JLabel NPCName = new JLabel( "NPC Name: " );
    	JTextField userInName = new JTextField( "", 30 );
    	
    	JLabel NPCType = new JLabel( "NPC Type: " );
    	JTextField userInNPCType = new JTextField( "", 30 );
    	
    	topPanel.add( Box.createRigidArea( new Dimension( 5, 5 ) ) );
    	topPanel.add( Box.createHorizontalGlue() );
    	topPanel.add( NPCName );
    	topPanel.add( Box.createHorizontalGlue() );
    	topPanel.add( userInName );
    	topPanel.add( Box.createHorizontalGlue() );
    	topPanel.add( NPCType );
    	topPanel.add( Box.createHorizontalGlue() );
    	topPanel.add( userInNPCType );
    	topPanel.add( Box.createHorizontalGlue() );
    	topPanel.add( Box.createRigidArea( new Dimension( 5, 5 ) ) );
    	
    	topHolder.add( Box.createRigidArea( new Dimension( 1, 1 ) ) );
    	topHolder.add( topPanel );
 
    	JLabel NPCDescription = new JLabel( "NPC Description:" );
    	JTextArea userInDescription = new JTextArea( 24, 10 );
    	JScrollPane desScroll = new JScrollPane( userInDescription );
    	
    	centerPanel.add( Box.createVerticalGlue() );
    	centerPanel.add( Box.createRigidArea( new Dimension( 0, 1 ) ) );
    	centerPanel.add( Box.createVerticalGlue() );
    	centerPanel.add( NPCDescription );
    	centerPanel.add( Box.createVerticalGlue() );
    	centerPanel.add( desScroll );
    	centerPanel.add( Box.createVerticalGlue() );
    	
    	// right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground( catapulterGray );
		rightPanel.setLayout( new BoxLayout( rightPanel, BoxLayout.Y_AXIS ) );
		
		JLabel listItem = new JLabel( "Items carried by this NPC", SwingConstants.CENTER );
		JPanel listItemLabelHolder = new JPanel(); 
		listItemLabelHolder.add( listItem );
		
		JPanel rightButtonsPanel = new JPanel();
		rightButtonsPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		rightButtonsPanel.setBackground( catapulterGray );
		
		JButton addItem = new JButton( "Add Item" );
		addItem.setBackground( catapulterGreen );
		addItem.addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent roomEvent ) {
        		
        		//TODO build add item
        	}
        } );
		
		JButton deleteItem = new JButton( "Delete Item");
		deleteItem.setBackground( catapulterRed );
		deleteItem.addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent e ) {
        		
        		//TODO build delete item

        	}
        } );
		
		rightButtonsPanel.add( addItem );
		rightButtonsPanel.add( deleteItem );
		rightPanel.add( listItemLabelHolder );
		rightPanel.add( rightButtonsPanel );
		
		// bottom pane
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		
		JButton save = new JButton( "Save" );
		save.addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent e ) {
        		
        		//TODO build add item
        		
        		buildNPC( userInName.getText(), userInDescription.getText(), npcInvetory, userInNPCType.getText() );
        		
        	}
        } );
		
		JButton exit = new JButton( "Exit" );
		exit.addActionListener( new ActionListener() {
        	
        	public void actionPerformed ( ActionEvent roomEvent ) {
        		
        		mainFrame.dispose();
        	}
        } );

		bottomPanel.add( exit );
		bottomPanel.add( save );
		
        mainFrame.add( topHolder, BorderLayout.NORTH );
    	mainFrame.add( centerPanel, BorderLayout.CENTER );
    	mainFrame.add( rightPanel, BorderLayout.WEST );
    	mainFrame.add( bottomPanel, BorderLayout.SOUTH );

    	mainFrame.setSize( 800, 500 );
    	mainFrame.setVisible( true );	
    }
    
    // for setting window location
    private static Rectangle getMaxWindowBounds(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;
        return bounds;
    }
    
    // for setting window location
    private static void setLocationToTop(JFrame frame) {
    	
        frame.setLocation(frame.getX(), getMaxWindowBounds(frame).y);
    }
    
    // for setting window location
    private static void setLocationToLeft(JFrame frame) {
    	
        frame.setLocation(getMaxWindowBounds(frame).x, frame.getY());
    }

    // for setting window location
    private static void setLocationToBottom(JFrame frame) {
    	
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(frame.getX(), bounds.y + bounds.height - frame.getHeight());
    }
    
    // for setting window location
    private static void setLocationToRight(JFrame frame) {
    	
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(bounds.x + bounds.width - frame.getWidth(), frame.getY());
    }
    
	public GameTile getTile( int tileNumber ) {
		
		return (GameTile) this.currentRoom.getTiles().get( tileNumber );
	}
	
	public int getRoomSize() {
		
		return this.currentRoom.getRoomSize();
	}
    
    // direction calculations
    public int northEquation() {
    
    	return currentTileNumber + roomLength;
    }
    
    public int southEquation() {
    	
    	return currentTileNumber - roomLength;
    }
    
    public int eastEquation() {
    	
    	return currentTileNumber + 1;
    }
    
    public int westEquation() {
    	
    	return currentTileNumber - 1;
    }
    
    public int northEastEquation() {
        
    	return northEquation() + 1;
    }
    
    public int northWestEquation() {
    	
    	return northEquation() - 1;
    }
    
    public int southEastEquation() {
    	
    	return southEquation() + 1;
    }
    
    public int southWestEquation() {
    	
    	return southEquation() - 1;
    }
    
    // map wall detection
    public boolean northWall() {
    	
    	if( northEquation() > getRoomSize() - 1 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean southWall() {
    	
    	if( southEquation() < 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean eastWall() {

    	if ( ( eastEquation() ) % ( roomLength )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean westWall() {

    	if ( ( currentTileNumber ) % ( roomLength )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    // local State methods
    private void setNotTile() {
    	
    	if( notTile == false ) {
    		
    		notTile = true;
    		tile.setBackground( catapulterRed );
    		currentTileLabel.setText( String.valueOf( "Not Tile" ) );
    		currentTileLabel.setBackground( catapulterRed );
    		roomTiles[ currentTileNumber ].setBackground( catapulterBlack );
    		currentTileDefaultTileChar = "[x]";
    		saveCurrentTile();
    		//TODO make something to handle setting default Char
    	
    		if( !northWall() ) {
    		
    			getTile( northEquation() ).setS( NOEXIT );
    		}
    		
    	} else {
    		
    		notTile = false;
    		tile.setBackground( catapulterGreen );
    		currentTileLabel.setText( String.valueOf( currentTileNumber ) );
    		currentTileLabel.setBackground( catapulterGreen );
    		roomTiles[ currentTileNumber ].setBackground( catapulterWhite );
    		currentTileDefaultTileChar = " . ";
    		saveCurrentTile();
    		if( !northWall() ) {
        		
				getTile( northEquation() ).setS( currentTileNumber );
    		}
    	}
    }
    
    // --- sets state of current room ---
    public void setCurrentTile( int tileNumber ){
    	
    	this.currentTile = getTile( tileNumber );
    	this.currentTileNumber = tileNumber;
    	this.selectedTileNumber.setText( String.valueOf( tileNumber ) ); 
    	
    	setCurrentTileName();
    	setCurrentTileDescription();
    	setCurrentTileExits();
    	setCurrentTileThingList();
    	setCurrentTileNPCList();
    	setCurrentTileLabels();
    	setCurrentTileDefaultTileChar();
    }
    
    // sets current room name
    private void setCurrentTileName() {
    	
    	this.currentTileName = currentTile.getName();
    	this.userInTileName.setText( currentTileName );
    }
    
    // sets current room description
    private void setCurrentTileDescription() {
    	
    	this.currentTileDescription = currentTile.getDescription();
    	this.userInTileDescription.setText( currentTileDescription );
    }
    
    // sets current room items
    private void setCurrentTileThingList() {
    	
    	this.currentTileThingList = currentTile.getThings();
 
		currentTileItemJList = new JList<>( itemModel );
		currentTileItemJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		currentTileItemJList.setSelectedIndex( 0 );
		
		ListSelectionModel itemListSelectionModel = currentTileItemJList.getSelectionModel();
		itemListSelectionModel.addListSelectionListener( this );
		currentTileItemJList.setVisibleRowCount( 5 );
		itemModel.clear();
		
    	for( int i = 0; i < currentTileThingList.size(); i++ ) {
    			
    		itemModel.addElement( currentTileThingList.get( i ) );
    	}
    }
    
    // sets all current room npcs
    private void setCurrentTileNPCList() {
    	
    	this.currentTileNPCList = currentTile.getNPCs();
    	
    	currentTileNPCJList = new JList<>( npcModel );
    	currentTileNPCJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
    	currentTileNPCJList.setSelectedIndex( 0 );
		
		ListSelectionModel npcListSelectionModel = currentTileNPCJList.getSelectionModel();
		npcListSelectionModel.addListSelectionListener( this );
		currentTileNPCJList.setVisibleRowCount( 5 );
		npcModel.clear();
 		
    	for(int i = 0; i < currentTileNPCList.size(); i++) {

    			npcModel.addElement( currentTileNPCList.get( i ) );	
    	}
    } 
    
    // set current room exits
    private void setCurrentTileExits() {
    	
    	this.north = this.currentTile.getN();
    	this.south = this.currentTile.getS();
    	this.east = this.currentTile.getE();
    	this.west = this.currentTile.getW();
    	this.northWest = this.currentTile.getNW();    	
    	this.northEast = this.currentTile.getNE();
    	this.southWest = this.currentTile.getSW();
    	this.southEast = this.currentTile.getSE();
    	this.up = this.currentTile.getU();
    	this.down = this.currentTile.getD();
    	this.special = this.currentTile.getSpecial();
    	this.notTile = this.currentTile.getNotTile();
    }
    
    // sets map labels
    private void setCurrentTileLabels() {
    	
    	if( notTile == true) {
    		
    		isNotTile.setSelected( true );
    		tile.setBackground( catapulterRed );
    		currentTileLabel.setText( String.valueOf( "Not Tile" ) );
    		currentTileLabel.setBackground( catapulterRed );
    		
    	} else {
    		
    		isNotTile.setSelected( false );
    		tile.setBackground( catapulterGreen );
    		currentTileLabel.setText( String.valueOf( currentTileNumber ) );
    		currentTileLabel.setBackground( catapulterGreen );
    	}
    	
    	if( north != NOEXIT ) {
    		
    		addNorthExit.setBackground( catapulterGreen );
    		northTile.setBackground( catapulterGreen );
    		northTile.setText( String.valueOf( north ) );
    		
    	} else {
    		
    		addNorthExit.setBackground( catapulterRed );
    		northTile.setBackground( catapulterBlack );
    		northTile.setText( "" );
    		
    	}
    	
    	if( south != NOEXIT ) {
    		
    		addSouthExit.setBackground( catapulterGreen );
    		southTile.setBackground( catapulterGreen );
    		southTile.setText( String.valueOf( south ) );
    		
    	} else {
    		
    		addSouthExit.setBackground( catapulterRed );
    		southTile.setBackground( catapulterBlack );
    		southTile.setText( "" );
    		
    	}
    	
    	if( east != NOEXIT ) {
    		
    		addEastExit.setBackground( catapulterGreen );
    		eastTile.setBackground( catapulterGreen );
    		eastTile.setText( String.valueOf( east ) );
    		
    	} else {
    		
    		addEastExit.setBackground( catapulterRed );
    		eastTile.setBackground( catapulterBlack );
    		eastTile.setText( "" );
    		
    	}
    	
    	if( west != NOEXIT ) {
    		
    		addWestExit.setBackground( catapulterGreen );
    		westTile.setBackground( catapulterGreen );
    		westTile.setText( String.valueOf( west ) );
    		
    	} else {
    		
    		addWestExit.setBackground( catapulterRed );
    		westTile.setBackground( catapulterBlack );
    		westTile.setText( "" );
    		
    	}
    	
    	if( northWest != NOEXIT ) {
    		
    		addNorthWestExit.setBackground( catapulterGreen );
    		northWestTile.setBackground( catapulterGreen );
    		northWestTile.setText( String.valueOf( northWest ) );
    		
    	} else {
    		
    		addNorthWestExit.setBackground( catapulterRed );
    		northWestTile.setBackground( catapulterBlack );
    		northWestTile.setText( "" );
    		
    	}
    	
    	if( northEast != NOEXIT ) {
    		
    		addNorthEastExit.setBackground( catapulterGreen );
    		northEastTile.setBackground( catapulterGreen );
    		northEastTile.setText( String.valueOf( northEast ) );
    		
    	} else {
    		
    		addNorthEastExit.setBackground( catapulterRed );
    		northEastTile.setBackground( catapulterBlack );
    		northEastTile.setText( "" );
    		
    	}
    	
    	if( southWest != NOEXIT ) {
    		
    		addSouthWestExit.setBackground( catapulterGreen );
    		southWestTile.setBackground( catapulterGreen );
    		southWestTile.setText( String.valueOf( southWest ) );
    		
    	} else {
    		
    		addSouthWestExit.setBackground( catapulterRed );
    		southWestTile.setBackground( catapulterBlack );
    		southWestTile.setText( "" );
    		
    	}
    	
    	if( southEast != NOEXIT ) {
    		
    		addSouthEastExit.setBackground( catapulterGreen );
    		southEastTile.setBackground( catapulterGreen );
    		southEastTile.setText( String.valueOf( southEast ) );
    		
    	} else {
    		
    		addSouthEastExit.setBackground( catapulterRed );
    		southEastTile.setBackground( catapulterBlack );
    		southEastTile.setText( "" );
    		
    	}
    }
    
    public void setCurrentTileDefaultTileChar() {
    	this.currentTileDefaultTileChar = currentTile.getDefaultTileChar();
    }
    
    // saving methods
    public void saveCurrentTile() {
    	
    	saveTileName();
    	saveTileDescription();
    	saveTileExits();
    	saveTileThingList();
    	saveTileNPCList();
    	saveDefaultTileChar();
    }
    
    public void saveTileName() {
    	
    	this.currentTile.setName( currentTileName );
    }
    
    public void saveTileDescription() {
    	
    	this.currentTile.setDescription( currentTileDescription );
    }
    
    public void saveTileExits() {
    	
    	this.currentTile.setN( this.north );
    	this.currentTile.setS( this.south );
    	this.currentTile.setE( this.east );
    	this.currentTile.setW( this.west );
    	this.currentTile.setSW( this.southWest );
    	this.currentTile.setSE( this.southEast );
    	this.currentTile.setNW( this.northWest );
    	this.currentTile.setNE( this.northEast );
    	this.currentTile.setU( this.up );
    	this.currentTile.setD( this.down );
    	this.currentTile.setSpecial( this.special );
    	this.currentTile.setNotTile( notTile );
    	
    }

    public void saveTileThingList() {
    	
    	this.currentTile.setThings( currentTileThingList );
    }
    
    public void saveTileNPCList() {
    	
    	this.currentTile.setNPCs( currentTileNPCList );
    }
    
    public void saveDefaultTileChar() {
    	
    	this.currentTile.setDefaultTileChar( currentTileDefaultTileChar );
    	this.currentTile.setTileCharToDefaultTileChar();
    }
    
	public void buildLayout( int numberOfTiles ) {
		
//		TileList tList = new TileList();
		ThingList tiles = new ThingList();
		this.currentRoom = new GameRoom( currentRoomName, currentRoomDescription, currentMap, tiles, roomLength, roomWidth );
		boolean notTile = false;
		
		
		for( int i = 0; i < numberOfTiles; i++ ) {
			
			int tileNumber = i;
			ThingList items = new ThingList();
			ThingList npcs = new ThingList();
			
			this.currentRoom.addTile( new GameTile( "", "", currentRoom, items, npcs, tileNumber, notTile, " . " ) );
			this.currentTileList = this.currentRoom.getTiles();
		}
	}
	
	// builds npc from user input and adds npc to room thinglist
	private void buildNPC( String aNPCName, String aNPCDescription, ThingList tList, String type ) {
		
		ThingGenerator tg = new ThingGenerator();
		Thing thing = tg.getThing( type );
		NonPlayerActor npc = ( NonPlayerActor ) thing;
		npc.setName( aNPCName );
		npc.setDescription( aNPCDescription );
		
		this.currentTile.addNPC( npc );
		this.npcModel.addElement( npc );

	}

	// builds item from user input and adds item to npc thinglist
	private Thing buildItem( Thing thingHolder, DefaultListModel< Thing > iModel, String itemName, String itemDescription,  String type ) {
		
		ThingGenerator tg = new ThingGenerator();
		Thing item = tg.getThing( type );
		item.setName( itemName );
		item.setDescription( itemDescription );

		if( thingHolder.isThingHolder() ) {
			ThingHolder tHolder = ( ThingHolder ) thingHolder;
				
				tHolder.getThings().add( item );
				iModel.addElement( item );
				return item;

		} else {
			System.out.println( "item: " + thingHolder + " is not a thingHolder and " + itemName + " can not be added to it." );
			return item;
		}
		
	}
	
	private void deleteItem() {
		
		if( currentSelectedIndex != -1) {
			
			this.currentTile.removeThing( currentSelectedIndex );
			this.itemModel.remove( currentSelectedIndex );
		}
	}
	
	private void deleteNPC() {
		
		if( currentSelectedIndex != -1) {
			
			this.currentTile.removeNPC( currentSelectedIndex );
			this.npcModel.remove( currentSelectedIndex );
		}
	}
	
	public void buildMainGUI() {
	
		this.setSize(1000, 700);
		
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    // main container
		mainContainer = this.getContentPane();
		mainContainer.setLayout( new BorderLayout( 8, 6 ) );
		mainContainer.setBackground( catapulterWhite );
		this.getRootPane().setBorder( BorderFactory.createMatteBorder( 4, 4, 4, 4, catapulterBlack ) );
		
		// toppanel components
		JPanel topPanel = new JPanel();
		//topPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		topPanel.setBackground( catapulterGray );
		topPanel.setLayout( new BorderLayout( 5, 5 ) );
		
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setBackground( catapulterGray );
		topLeftPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		
		JLabel selectedTile = new JLabel( "Tile Number: " );
		selectedTileNumber = new JLabel( String.valueOf( currentTileNumber ) );
		topLeftPanel.add( selectedTile );
		topLeftPanel.add( selectedTileNumber );
		
		JPanel topRightPanel = new JPanel();
		topRightPanel.setBackground( catapulterGray );
		topRightPanel.setLayout( new FlowLayout( 2 ) );
		
		JButton seeRoomLayout = new JButton( "See Room Layout" );
		seeRoomLayout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				roomLayout.setVisible( true );
			}
		});
		
		isNotTile = new JCheckBox();
		isNotTile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setNotTile();
			}
		});
		
		JLabel notTile = new JLabel( "Not Tile" );
		JButton connectAllTileExits = new JButton( "Connect All Tile Exits" );
		connectAllTileExits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				connectAllTiles();
			}
		});
		topRightPanel.add( connectAllTileExits );
		topRightPanel.add( seeRoomLayout );
		topRightPanel.add( isNotTile );
		topRightPanel.add( notTile );
		
		// adding components to top panel
		topPanel.add( topLeftPanel, BorderLayout.WEST );
		topPanel.add( topRightPanel, BorderLayout.EAST );
	
		// middle right panel components
		JPanel middleRightPanel = new JPanel();
		middleRightPanel.setBorder( new LineBorder( catapulterGray, 3 ) );
		middleRightPanel.setBackground( catapulterGray );
		middleRightPanel.setLayout( new BoxLayout( middleRightPanel, BoxLayout.Y_AXIS) );
		
		JPanel middleRightTopPanel = new JPanel();
		middleRightTopPanel.setBackground( catapulterWhite );
		middleRightTopPanel.setLayout( new BoxLayout( middleRightTopPanel, BoxLayout.Y_AXIS ) );
		
		JPanel tNameUInput = new JPanel();
		tNameUInput.setBackground( catapulterGray );
		tNameUInput.setLayout( new FlowLayout( 3, 5, 5 ) );
		
		JPanel tDes = new JPanel();
		tDes.setBackground( catapulterGray );
		tDes.setLayout( new FlowLayout( 3, 5, 5 ) );
		
		JLabel tileName = new JLabel( "Tile Name: ");
		userInTileName = new JTextField( "", 30 );
		JLabel tileDescription = new JLabel( "Tile Description: ", SwingConstants.CENTER);
	
		tNameUInput.add( tileName );
		tNameUInput.add( userInTileName );
		tDes.add( tileDescription );
		middleRightTopPanel.add( tNameUInput );
		middleRightTopPanel.add( tDes );
		
		JPanel middleRightBottomPanel = new JPanel();
		middleRightBottomPanel.setBackground( catapulterWhite );
		middleRightBottomPanel.setLayout( new BorderLayout( 5, 5 ) );
		
		// Room description input
		userInTileDescription = new JTextArea( 10, 30 );
		JScrollPane tileDescriptionScrollPane = new JScrollPane( userInTileDescription );
		tileDescriptionScrollPane.setBackground( catapulterWhite );
		middleRightBottomPanel.add( tileDescriptionScrollPane, BorderLayout.CENTER );
		
		middleRightPanel.add( middleRightTopPanel );
		middleRightPanel.add( middleRightBottomPanel );
		
		// middle center panel components
		JPanel middleCenterPanel = new JPanel();
		middleCenterPanel.setBackground( catapulterWhite );
		middleCenterPanel.setLayout( new GridLayout( 2, 1, 5, 5 ) );
		
		// middle center top
		JPanel middleCenterTopPanel = new JPanel();
		middleCenterTopPanel.setBackground( catapulterWhite );
		middleCenterTopPanel.setLayout( new GridLayout( 1, 1, 5, 5 ) );
		
		JPanel connections = new JPanel();
		connections.setBackground( catapulterWhite );
		connections.setLayout( new GridLayout( 4, 3, 5, 5 ) );
		
		northTile = new JLabel();
		northTile.setHorizontalAlignment( SwingConstants.CENTER );
		northTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		northTile.setBackground( catapulterBlack );
		northTile.setOpaque( true );
		
		southTile = new JLabel();
		southTile.setHorizontalAlignment( SwingConstants.CENTER );
		southTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		southTile.setBackground( catapulterBlack );
		southTile.setOpaque( true );
		
		eastTile = new JLabel();
		eastTile.setHorizontalAlignment( SwingConstants.CENTER );
		eastTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		eastTile.setBackground( catapulterBlack );
		eastTile.setOpaque( true );
		
		westTile = new JLabel();
		westTile.setHorizontalAlignment( SwingConstants.CENTER );
		westTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		westTile.setBackground( catapulterBlack );
		westTile.setOpaque( true );
		
		northEastTile = new JLabel();
		northEastTile.setHorizontalAlignment( SwingConstants.CENTER );
		northEastTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		northEastTile.setBackground( catapulterBlack );
		northEastTile.setOpaque( true );
		
		northWestTile = new JLabel();
		northWestTile.setHorizontalAlignment( SwingConstants.CENTER );
		northWestTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		northWestTile.setBackground( catapulterBlack );
		northWestTile.setOpaque( true );
		
		southEastTile = new JLabel();
		southEastTile.setHorizontalAlignment( SwingConstants.CENTER );
		southEastTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		southEastTile.setBackground( catapulterBlack );
		southEastTile.setOpaque( true );
		
		southWestTile = new JLabel();
		southWestTile.setHorizontalAlignment( SwingConstants.CENTER );
		southWestTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		southWestTile.setBackground( catapulterBlack );
		southWestTile.setOpaque( true );
		
		upTile = new JLabel();
		upTile.setHorizontalAlignment( SwingConstants.CENTER );
		upTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		upTile.setBackground( catapulterBlack );
		upTile.setOpaque( true );
		
		downTile = new JLabel();
		downTile.setHorizontalAlignment( SwingConstants.CENTER );
		downTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		downTile.setBackground( catapulterBlack );
		downTile.setOpaque( true );
		
		specialTile = new JLabel();
		specialTile.setHorizontalAlignment( SwingConstants.CENTER );
		specialTile.setBorder( new LineBorder( catapulterBlack, 1 ) );
		specialTile.setBackground( catapulterBlack );
		specialTile.setOpaque( true );
		
		currentTileLabel = new JLabel( String.valueOf( currentTileNumber ), SwingConstants.CENTER );
		currentTileLabel.setOpaque( true );
		currentTileLabel.setBackground( catapulterGreen );
		currentTileLabel.setBorder( new LineBorder( catapulterBlack, 1 ) );
		
		connections.add( northWestTile );
		connections.add( northTile );
		connections.add( northEastTile );
		connections.add( westTile );
		connections.add( currentTileLabel );
		connections.add( eastTile );
		connections.add( southWestTile );
		connections.add( southTile );
		connections.add( southEastTile );
		connections.add( downTile );
		connections.add( upTile );
		connections.add( specialTile );
		
		middleCenterTopPanel.add( connections );
		
		// middle center middle
		JPanel middleCenterMiddlePanel = new JPanel();
		middleCenterMiddlePanel.setBorder( new LineBorder( catapulterGray, 3 ) );
		middleCenterMiddlePanel.setBackground( catapulterGray );
		middleCenterMiddlePanel.setLayout( new BorderLayout( 5, 5 ) );
	
		tile = new JLabel( "Tile", SwingConstants.CENTER );
		tile.setOpaque( true );
		tile.setBorder( new LineBorder( catapulterBlack, 3 ) );
		tile.setBackground( catapulterGreen );
		middleCenterMiddlePanel.add( tile, BorderLayout.CENTER);
		
		// map exit buttons
		//TODO make it so the default is that if a direction is allowed then, the direction back is automatically set up to allowed
		addNorthExit = new JButton( "n" );
		addNorthExit.setBackground( catapulterRed );
		addNorthExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
	
				if( north == NOEXIT && !northWall() && getTile( northEquation() ).getNotTile() == false ) {
					
					north = northEquation();
					addNorthExit.setBackground( catapulterGreen );
					northTile.setBackground( catapulterGreen );
					northTile.setText( String.valueOf( north ) );
					
				} else {
					
					north = NOEXIT;
					addNorthExit.setBackground( catapulterRed );
					northTile.setBackground( catapulterBlack );
					northTile.setText( "" );
				}
			}
		});
		
		addSouthExit = new JButton( "s" );
		addSouthExit.setBackground( catapulterRed );
		addSouthExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( south == NOEXIT && !southWall() && getTile( southEquation() ).getNotTile() == false ) {
					
					south = southEquation();
					addSouthExit.setBackground( catapulterGreen );
					southTile.setBackground( catapulterGreen );
					southTile.setText( String.valueOf( south ) );
					
				} else {
					
					south = NOEXIT;
					addSouthExit.setBackground( catapulterRed );
					southTile.setBackground( catapulterBlack );
					southTile.setText( "" );
				}
			}
		});
		
		addEastExit = new JButton( "e" );
		addEastExit.setBackground( catapulterRed );
		addEastExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( east == NOEXIT && !eastWall() && getTile( eastEquation() ).getNotTile() == false  ) {
					
					east = eastEquation();
					addEastExit.setBackground( catapulterGreen );
					eastTile.setBackground( catapulterGreen );
					eastTile.setText( String.valueOf( east ) );
					
				} else {
					
					east = NOEXIT;
					addEastExit.setBackground( catapulterRed );
					eastTile.setBackground( catapulterBlack );
					eastTile.setText( "" );
				}
			}
		});
		
		addWestExit = new JButton( "w" );
		addWestExit.setBackground( catapulterRed );
		addWestExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( west == NOEXIT && !westWall() && getTile( westEquation() ).getNotTile() == false  ) {
					
					west = westEquation();
					addWestExit.setBackground( catapulterGreen );
					westTile.setBackground( catapulterGreen );
					westTile.setText( String.valueOf( west ) );
					
				} else {
					
					west = NOEXIT;
					addWestExit.setBackground( catapulterRed );
					westTile.setBackground( catapulterBlack );
					westTile.setText( "" );
				}
			}
		});
		
		addUpExit = new JButton( "u" );
		addUpExit.setBackground( catapulterRed );
		addUpExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( addUpExit.getBackground().equals( catapulterRed ) ) {
					addUpExit.setBackground( catapulterGreen );
					upTile.setBackground( catapulterGreen );
					upTile.setText( "Up Tile" );
					
				} else {
					addUpExit.setBackground( catapulterRed );
					upTile.setBackground( catapulterBlack );
					upTile.setText( "" );
				}
			}
		});
		
		addDownExit = new JButton( "d" );
		addDownExit.setBackground( catapulterRed );
		addDownExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( addDownExit.getBackground().equals( catapulterRed ) ) {
					addDownExit.setBackground( catapulterGreen );
					downTile.setBackground( catapulterGreen );
					downTile.setText( "Down Tile" );
					
				} else {
					addDownExit.setBackground( catapulterRed );
					downTile.setBackground( catapulterBlack );
					downTile.setText( "" );
				}
			}
		});
		
		addSpecialExit = new JButton( "special" );
		addSpecialExit.setBackground( catapulterRed );
		addSpecialExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( addSpecialExit.getBackground().equals( catapulterRed ) ) {
					addSpecialExit.setBackground( catapulterGreen );
					specialTile.setBackground( catapulterGreen );
					specialTile.setText( "Special Tile" );
					
				} else {
					addSpecialExit.setBackground( catapulterRed );
					specialTile.setBackground( catapulterBlack );
					specialTile.setText( "" );
				}
			}
		});
		
		JButton addExternalExit = new JButton( "external" );
		addExternalExit.setBackground( catapulterRed );
		addExternalExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( addExternalExit.getBackground().equals( catapulterRed ) ) {
					addExternalExit.setBackground( catapulterGreen );
					specialTile.setBackground( catapulterGreen );
					specialTile.setText( "External Tile" );
					
				} else {
					addExternalExit.setBackground( catapulterRed );
					specialTile.setBackground( catapulterBlack );
					specialTile.setText( "" );
				}
			}
		});
		
		addNorthWestExit = new JButton( "nw" );
		addNorthWestExit.setBackground( catapulterRed );
		addNorthWestExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( northWest == NOEXIT && !westWall() && getTile( northWestEquation() ).getNotTile() == false ) {
					
					northWest = northWestEquation();
					
					addNorthWestExit.setBackground( catapulterGreen );
					northWestTile.setBackground( catapulterGreen );
					northWestTile.setText( String.valueOf( northWest ) );
					
				} else {
					
					northWest = NOEXIT;
					addNorthWestExit.setBackground( catapulterRed );
					northWestTile.setBackground( catapulterBlack );
					northWestTile.setText( "" );
				}
			}
		});
		
		addNorthEastExit = new JButton( "ne" );
		addNorthEastExit.setBackground( catapulterRed );
		addNorthEastExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( northEast == NOEXIT && !eastWall() && getTile( northEastEquation() ).getNotTile() == false ) {
					
					northEast = northEastEquation();
					

					System.out.println( northEast ); //TODO What is this?
					addNorthEastExit.setBackground( catapulterGreen );
					northEastTile.setBackground( catapulterGreen );
					northEastTile.setText( String.valueOf( northEast ) );
					
				} else {
					
					northEast = NOEXIT;
					addNorthEastExit.setBackground( catapulterRed );
					northEastTile.setBackground( catapulterBlack );
					northEastTile.setText( "" );
				}
			}
		});
		
		addSouthWestExit = new JButton( "sw" );
		addSouthWestExit.setBackground( catapulterRed );
		addSouthWestExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( southWest == NOEXIT && !westWall() && getTile( southWestEquation() ).getNotTile() == false ) {
					
					southWest = southWestEquation();
					addSouthWestExit.setBackground( catapulterGreen );
					southWestTile.setBackground( catapulterGreen );
					southWestTile.setText( String.valueOf( southWest ) );
					
				} else {
					
					southWest = NOEXIT;
					addNorthWestExit.setBackground( catapulterRed );
					southWestTile.setBackground( catapulterBlack );
					southWestTile.setText( "" );
				}
			}
		});
		
		addSouthEastExit = new JButton( "se" );
		addSouthEastExit.setBackground( catapulterRed );
		addSouthEastExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( southEast == NOEXIT && !eastWall() && getTile( southEastEquation() ).getNotTile() == false ) {
					
					southEast = southEastEquation();
					addSouthEastExit.setBackground( catapulterGreen );
					southEastTile.setBackground( catapulterGreen );
					southEastTile.setText( String.valueOf( southEast ) );
					
				} else {
					
					southEast = NOEXIT;
					addSouthEastExit.setBackground( catapulterRed );
					southEastTile.setBackground( catapulterBlack );
					southEastTile.setText( "" );
				}
			}
		});
		
		JPanel middleCenterMiddleTopPanel = new JPanel();
		//middleCenterMiddleTopPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		middleCenterMiddleTopPanel.setBackground( catapulterGray );
		middleCenterMiddleTopPanel.setLayout( new GridLayout( 1, 5, 5, 5 ) );
		middleCenterMiddleTopPanel.add( addNorthWestExit );
		middleCenterMiddleTopPanel.add( addExternalExit );
		middleCenterMiddleTopPanel.add( addNorthExit );
		middleCenterMiddleTopPanel.add( addUpExit );
		middleCenterMiddleTopPanel.add( addNorthEastExit );
		
		middleCenterMiddlePanel.add( middleCenterMiddleTopPanel, BorderLayout.NORTH );
		
		JPanel middleCenterMiddleBottomPanel = new JPanel();
		//middleCenterMiddleBottomPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		middleCenterMiddleBottomPanel.setBackground( catapulterGray );
		middleCenterMiddleBottomPanel.setLayout( new GridLayout( 1, 5, 5, 5 ) );
		middleCenterMiddleBottomPanel.add( addSouthWestExit );
		middleCenterMiddleBottomPanel.add( addDownExit );
		middleCenterMiddleBottomPanel.add( addSouthExit );
		middleCenterMiddleBottomPanel.add( addSpecialExit );
		middleCenterMiddleBottomPanel.add( addSouthEastExit );
		
		middleCenterMiddlePanel.add( middleCenterMiddleBottomPanel, BorderLayout.SOUTH );
		
		JPanel middleCenterMiddleLeftPanel = new JPanel();
		//middleCenterMiddleLeftPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		middleCenterMiddleLeftPanel.setBackground( catapulterWhite );
		middleCenterMiddleLeftPanel.setLayout( new GridLayout( 1, 1, 5, 5 ) );
		middleCenterMiddleLeftPanel.add( addWestExit );
		
		middleCenterMiddlePanel.add( middleCenterMiddleLeftPanel, BorderLayout.WEST );
		
		JPanel middleCenterMiddleRightPanel = new JPanel();
		//middleCenterMiddleRightPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		middleCenterMiddleRightPanel.setBackground( catapulterWhite );
		middleCenterMiddleRightPanel.setLayout( new GridLayout( 1, 1, 5, 5 ) );
		middleCenterMiddleRightPanel.add( addEastExit );
		
		middleCenterMiddlePanel.add( middleCenterMiddleRightPanel, BorderLayout.EAST );
		
		middleCenterPanel.add( middleCenterTopPanel );
		middleCenterPanel.add( middleCenterMiddlePanel );
		
		// middle left components
		// main left panel
		JPanel middleLeftPanel = new JPanel();
		//middleLeftPanel.setBorder( new LineBorder( catapulterBlack, 3 ) );
		middleLeftPanel.setBackground( catapulterGray );
		middleLeftPanel.setLayout( new GridLayout( 2, 1, 5, 5 ) );
		
		JPanel middleLeftTopPanel = new JPanel();
		middleLeftTopPanel.setBorder( new LineBorder( catapulterGray, 3 ) );
		//middleLeftTopPanel.setBackground( catapulterWhite );
		middleLeftTopPanel.setLayout( new BoxLayout( middleLeftTopPanel, BoxLayout.Y_AXIS ) );
		
		JPanel listItemHolder = new JPanel();
		listItemHolder.setBackground( catapulterGray );
		listItemHolder.setLayout( new BoxLayout( listItemHolder, BoxLayout.Y_AXIS ) );
		
		JLabel listItem = new JLabel( "Items in this tile", SwingConstants.CENTER );
		JPanel listItemLabelHolder = new JPanel(); 
		listItemLabelHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
		listItemLabelHolder.add( listItem );
		
		JPanel middleLeftTopButtonsPanel = new JPanel();
		middleLeftTopButtonsPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		middleLeftTopButtonsPanel.setBackground( catapulterGray );
		
		JButton addItem = new JButton( "Add Item" );
		addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				newItem( currentTile, itemModel );
			}
		});
		addItem.setBackground( catapulterGreen );
		
		JButton deleteItem = new JButton( "Delete Item");
		deleteItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				deleteItem();
			}
		});
		deleteItem.setBackground( catapulterRed );
		middleLeftTopButtonsPanel.add( addItem );
		middleLeftTopButtonsPanel.add( deleteItem );
		
		// room item list display
		
		
		this.itemModel = new DefaultListModel<>();
		currentTileItemJList = new JList<>( itemModel );
		currentTileItemJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		currentTileItemJList.setSelectedIndex( 0 );
		
		ListSelectionModel itemListSelectionModel = currentTileItemJList.getSelectionModel();
		itemListSelectionModel.addListSelectionListener( this );
		currentTileItemJList.setVisibleRowCount( 5 );
		
		JScrollPane tileItemListScrollPane = new JScrollPane( currentTileItemJList );
		
		listItemHolder.add( listItemLabelHolder );
		listItemHolder.add( tileItemListScrollPane );
		listItemHolder.add( middleLeftTopButtonsPanel );
		
		middleLeftTopPanel.add( listItemHolder ); 
		
		JPanel middleLeftBottomPanel = new JPanel();
		middleLeftBottomPanel.setBorder( new LineBorder( catapulterGray, 3 ) );
		middleLeftBottomPanel.setLayout( new BoxLayout( middleLeftBottomPanel, BoxLayout.Y_AXIS ) );
		
		JPanel listNPCHolder = new JPanel();
		listNPCHolder.setBackground( catapulterGray );
		listNPCHolder.setLayout( new BoxLayout( listNPCHolder, BoxLayout.Y_AXIS ) );
		
		JLabel listNPC = new JLabel( "NPCs in this tile", SwingConstants.CENTER );
		JPanel listNPCLabelHolder = new JPanel(); 
		listNPCLabelHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
		listNPCLabelHolder.add( listNPC );
		
		JPanel middleLeftBottomButtonsPanel = new JPanel();
		middleLeftBottomButtonsPanel.setLayout( new FlowLayout( 1, 5, 5 ) );
		middleLeftBottomButtonsPanel.setBackground( catapulterGray );
		
		JButton addNPC = new JButton( "Add NPC" );
		addNPC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				newNPC();
			}
		});
		
		addNPC.setBackground( catapulterGreen );
		JButton deleteNPC = new JButton( "Delete NPC");
		deleteNPC.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				deleteNPC();
			}
		});
		deleteNPC.setBackground( catapulterRed );
		middleLeftBottomButtonsPanel.add( addNPC );
		middleLeftBottomButtonsPanel.add( deleteNPC );
		
		// room npc list display
		npcModel = new DefaultListModel<>();
		currentTileNPCJList = new JList<>( npcModel );
		currentTileNPCJList.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		currentTileNPCJList.setSelectedIndex( 0 );
		
		ListSelectionModel npcListSelectionModel = currentTileNPCJList.getSelectionModel();
		npcListSelectionModel.addListSelectionListener( this );
		currentTileNPCJList.setVisibleRowCount( 5 );
		
		JScrollPane tileNPCListScrollPane = new JScrollPane( currentTileNPCJList );


		listNPCHolder.add( listNPCLabelHolder );
		listNPCHolder.add( tileNPCListScrollPane );
		listNPCHolder.add( middleLeftBottomButtonsPanel );
		
		middleLeftBottomPanel.add( listNPCHolder );
		
		middleLeftPanel.add( middleLeftTopPanel );
		middleLeftPanel.add( middleLeftBottomPanel );
		
		// bottom panel components
		JButton save = new JButton( "Save" );
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentTileName = userInTileName.getText();
				currentTileDescription = userInTileDescription.getText();
				
				// soft save of local working copy maproom
				saveCurrentTile();
				
				// hard save of master copy map
				currentRoomBuilder.saveRoom( currentRoomName, currentRoomDescription, currentMap, currentTileList, roomLength, roomWidth );

				
				currentRoomBuilder.buildJSON();
				 
			}
		});
		
		JButton exitMapBuilder = new JButton( "Exit Map Builder" );
		
		JButton clearAll = new JButton( "Clear All" );
		clearAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addNorthWestExit.setBackground( catapulterRed );
				addNorthExit.setBackground( catapulterRed );
				addNorthEastExit.setBackground( catapulterRed );
				addWestExit.setBackground( catapulterRed );
				addEastExit.setBackground( catapulterRed );
				addSouthWestExit.setBackground( catapulterRed );
				addSouthExit.setBackground( catapulterRed );
				addSouthEastExit.setBackground( catapulterRed );
				addUpExit.setBackground( catapulterRed );
				addDownExit.setBackground( catapulterRed );
				addSpecialExit.setBackground( catapulterRed );
				addExternalExit.setBackground( catapulterRed );
				
				northWestTile.setBackground( catapulterBlack );
				northWestTile.setText( "" );
				northTile.setBackground( catapulterBlack );
				northTile.setText( "" );
				northEastTile.setBackground( catapulterBlack );
				northEastTile.setText( "" );
				westTile.setBackground( catapulterBlack );
				westTile.setText( "" );
				eastTile.setBackground( catapulterBlack );
				eastTile.setText( "" );
				southWestTile.setBackground( catapulterBlack );
				southWestTile.setText( "" );
				southTile.setBackground( catapulterBlack );
				southTile.setText( "" );
				southEastTile.setBackground( catapulterBlack );
				southEastTile.setText( "" );
				upTile.setBackground( catapulterBlack );
				upTile.setText( "" );
				downTile.setBackground( catapulterBlack );
				downTile.setText( "" );
				specialTile.setBackground( catapulterBlack );
				specialTile.setText( "" );
				
				userInTileDescription.setText( "" );
				userInTileName.setText( "" );
				
				
			}
		});
		
		JPanel bottomPanel = new JPanel(); 
		bottomPanel.setLayout( new GridLayout( 1, 5, 5, 5 ) );
		bottomPanel.add( clearAll );
		bottomPanel.add( save );
		bottomPanel.add( exitMapBuilder );
		
		bottomPanel.setBackground( catapulterGray );
		bottomPanel.setBorder( new LineBorder( catapulterGray, 3 ) );
	
		// adding to main container
		mainContainer.add( middleLeftPanel, BorderLayout.WEST );
		mainContainer.add( middleRightPanel, BorderLayout.EAST );
		mainContainer.add( middleCenterPanel, BorderLayout.CENTER );
		mainContainer.add( topPanel, BorderLayout.NORTH );
		mainContainer.add( bottomPanel, BorderLayout.SOUTH );
		
	}
	
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) { 
		
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		
		// TODO Delete if determined that multiple selections allowed
        int firstIndex = e.getFirstIndex();
        int lastIndex = e.getLastIndex();
        boolean isAdjusting = e.getValueIsAdjusting();

        if (lsm.isSelectionEmpty()) {
        	
        	this.currentSelectedIndex = -1;
        	
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                	this.currentLSM = lsm;
                	this.currentSelectedIndex = i;
                }
            }
        }
	}
	
	public void connectAllTiles() {
		
		int numberOfTiles = this.currentRoom.getThings().size();

	    for( int i = 0; i < numberOfTiles; i++ ) {
	    		
    		GameTile tile = ( GameTile ) this.currentRoom.getThings().get( i );
    		if( tile.getTileNumber() + roomLength < numberOfTiles ) {
    			tile.setN( tile.getTileNumber() + roomLength );
    	    }
    		if( tile.getTileNumber() - roomLength >= 0 ) {
    			tile.setS( tile.getTileNumber() - roomLength );
    	    }
    		if( tile.getTileNumber() + 1 < numberOfTiles && ( tile.getTileNumber() + 1) % roomWidth != 0 ) {
    			tile.setE( tile.getTileNumber() + 1 );
    	    }
    		if( tile.getTileNumber() - 1 >= 0 &&  tile.getTileNumber() % roomWidth != 0 ) {
    			tile.setW( tile.getTileNumber() - 1 );
    	    }
    		if( tile.getTileNumber() + roomLength + 1 < numberOfTiles && ( tile.getTileNumber() + roomLength + 1) % roomWidth != 0 ) {
    			tile.setNE( tile.getTileNumber() + roomLength + 1 );
    	    }
    		if( tile.getTileNumber() + roomLength - 1 < numberOfTiles && ( tile.getTileNumber() + roomLength ) % roomWidth != 0 ) {
    			tile.setNW( tile.getTileNumber() + roomLength - 1 );
    	    }
    		if( tile.getTileNumber() - roomLength + 1 >= 0 && ( tile.getTileNumber() - roomLength + 1) % roomWidth != 0 ) {
    			tile.setSE( tile.getTileNumber() - roomLength + 1 );
    	    }
    		if( tile.getTileNumber() - roomLength - 1 >= 0 && ( tile.getTileNumber() - roomLength ) % roomWidth != 0  ) {
    			tile.setSW( tile.getTileNumber() - roomLength - 1 );
    	    }
	
    	}

	}

	
    
}

//TODO build class that handles small pop up messages by receiving a String message as a parameter
