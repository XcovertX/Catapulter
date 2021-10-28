package mapBuilder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import game.WorldReader;
import gameObjects.Thing;
import gameObjects.ThingHolder;
import gameObjects.ThingList;
import globals.CatapulterColor;
import globals.TileChar;
import res.ResourceLoader;
import world.BuildJSON;
import world.BuildRoomLayout;
import world.GameMap;
import world.GameRoom;
import world.GameTile;
import world.GameWorld;

import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;

import java.io.File;
import java.util.LinkedList;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.layout.RowLayout;

public class WorldBuilder {
	
	public static WorldBuilder wb;
	public static GameWorld gw;
	
	private GameMap gm;
	private GameRoom gr;
	private GameTile gt;

	protected Shell shlWorldBuilder;
	private Text roomName_txt;
	private Text roomLength_txt;
	private Text roomWidth_txt;
	private Text roomDescrip_txt;
	private Button inside_btn;
	private Button outside_btn;
	private Text tileDes_txt;
	private Label tileNum_lbl;
	private int mapViewerSelectedIndex = -1;
	private int roomViewerSelectedIndex = -1;
	private boolean newMap = false;
	private boolean newRoom = false;
	
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
	private boolean notTile;
	private boolean exitRoom;
	private boolean exitMap;
	
	private Button n_btn;
	private Button s_btn;
	private Button w_btn;
	private Button e_btn;
	private Button ne_btn;
	private Button nw_btn;
	private Button se_btn;
	private Button sw_btn;
	private Button u_btn;
	private Button d_btn;
	private Button special_btn;
	private Button notTile_btn;
	private Button exitRoom_btn;
	private Button exitMap_btn;
	
	private static final int NOEXIT = -1;
	
	private LinkedList<Button> buttonlist = new LinkedList<Button>();
	private Button[] roomTiles;
	
	private Composite tileLayout_cpt;
	
	private String savePath;

	private ListViewer mapViewer;
	private ListViewer roomViewer;
	private Table exits_tbl;
	private Text tileName_txt;
	private Text defTileChar_txt;
	
	private Tree items_tree;
	private Tree npcs_tree;
	
	public WorldBuilder( String path, String worldName ) {
		wb = this;
		this.savePath = path;
		loadWorld( path, worldName );
		wb.open();

	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new WorldBuilder( "", "" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlWorldBuilder.open();
		shlWorldBuilder.layout();
		while (!shlWorldBuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlWorldBuilder = new Shell();
		shlWorldBuilder.setSize(1353, 717);
		shlWorldBuilder.setLocation( 5, 5 );
		shlWorldBuilder.setText("World Builder -- " + gw.getName() );
		
		Label roomsIn_lbl = new Label(shlWorldBuilder, SWT.NONE);
		roomsIn_lbl.setText("XXXXXXXXXXXX");
		roomsIn_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		roomsIn_lbl.setBounds(125, 176, 193, 30);
		
		mapViewer = new ListViewer(shlWorldBuilder, SWT.BORDER | SWT.V_SCROLL);
		updateMapViewer();
		List mapList = mapViewer.getList();
		mapList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = mapList.getSelectionIndex();
				mapViewerSelectedIndex = index;
				
				gm = ( GameMap ) gw.getMaps().get( mapViewerSelectedIndex ); 
				
				roomViewerSelectedIndex = -1;
				roomsIn_lbl.setText( mapViewer.getList().getItem(index) );
				updateRoomViewer();
				clearRoomAttributes();
			}
		});
		mapList.setBounds(10, 46, 308, 124);
		
		Button newMap_btn = new Button(shlWorldBuilder, SWT.NONE);
		newMap_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				setNewMap(true);
				MapDetails md = new MapDetails();
				md.open();
				setNewMap(false);
			}
		});
		newMap_btn.setText("New Map");
		newMap_btn.setBounds(98, 13, 62, 25);
		
		Button deleteMap_btn = new Button(shlWorldBuilder, SWT.NONE);
		deleteMap_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if( mapViewerSelectedIndex >= 0 ) {;
					Confirmation confirm = new Confirmation( "Are you sure you want to delete this map?" );
					if( confirm.getAnswer() ) {
						gw.getMaps().remove( mapViewerSelectedIndex );
						updateMapViewer();
					}
				}
			}
		});
		deleteMap_btn.setText("Delete Map");
		deleteMap_btn.setBounds(234, 13, 84, 25);
		
		Button editMap_btn = new Button(shlWorldBuilder, SWT.NONE);
		editMap_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {	
			}
		});
		editMap_btn.setBounds(166, 13, 62, 25);
		editMap_btn.setText("Edit Map");
		
		Label rooms_lbl = new Label(shlWorldBuilder, SWT.NONE);
		rooms_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		rooms_lbl.setAlignment(SWT.CENTER);
		rooms_lbl.setBounds(10, 176, 109, 30);
		rooms_lbl.setText("Rooms in :");
		
		Label maps_lbl = new Label(shlWorldBuilder, SWT.NONE);
		maps_lbl.setText("Maps");
		maps_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		maps_lbl.setAlignment(SWT.CENTER);
		maps_lbl.setBounds(10, 10, 82, 30);

		Label label = new Label(shlWorldBuilder, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(324, 10, 2, 650);
		
		Label roomName_lbl = new Label(shlWorldBuilder, SWT.NONE);
		roomName_lbl.setBounds(10, 415, 75, 18);
		roomName_lbl.setText("Room Name");
		
		roomName_txt = new Text(shlWorldBuilder, SWT.BORDER);
		roomName_txt.setBounds(91, 412, 227, 21);
		roomName_txt.setEnabled( false );
		
		Label roomLength_lbl = new Label(shlWorldBuilder, SWT.NONE);
		roomLength_lbl.setText("Room Length");
		roomLength_lbl.setBounds(10, 442, 75, 18);
		
		roomLength_txt = new Text(shlWorldBuilder, SWT.BORDER);
		roomLength_txt.setBounds(91, 439, 227, 21);
		roomLength_txt.setEnabled( false );
		
		Label roomWidth_lbl = new Label(shlWorldBuilder, SWT.NONE);
		roomWidth_lbl.setText("Room Width");
		roomWidth_lbl.setBounds(10, 469, 75, 18);
		
		roomWidth_txt = new Text(shlWorldBuilder, SWT.BORDER);
		roomWidth_txt.setBounds(91, 466, 227, 21);
		roomWidth_txt.setEnabled( false );
		
		Label label_1 = new Label(shlWorldBuilder, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(10, 368, 308, 2);
		
		Label roomInfo_lbl = new Label(shlWorldBuilder, SWT.NONE);
		roomInfo_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		roomInfo_lbl.setAlignment(SWT.CENTER);
		roomInfo_lbl.setBounds(65, 373, 199, 25);
		roomInfo_lbl.setText("Room Information");
		
		Label label_1_1 = new Label(shlWorldBuilder, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		label_1_1.setBounds(10, 404, 308, 2);
		
		Label roomDescription_lbl = new Label(shlWorldBuilder, SWT.NONE);
		roomDescription_lbl.setText("Room Description");
		roomDescription_lbl.setBounds(10, 493, 109, 18);
		
		roomDescrip_txt = new Text(shlWorldBuilder, SWT.BORDER);
		roomDescrip_txt.setBounds(10, 517, 308, 98);
		roomDescrip_txt.setEnabled( false );
		
		inside_btn = new Button(shlWorldBuilder, SWT.RADIO);
		inside_btn.setBounds(198, 495, 52, 16);
		inside_btn.setText("Inside");
		inside_btn.setEnabled( false );
		
		outside_btn = new Button(shlWorldBuilder, SWT.RADIO);
		outside_btn.setBounds(256, 495, 62, 16);
		outside_btn.setText("Outside");
		outside_btn.setEnabled( false );
		
		Button newRoom_btn = new Button(shlWorldBuilder, SWT.NONE);
		newRoom_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( mapViewerSelectedIndex >= 0 ) {
					
					newRoom = true;
					
					roomName_txt.setEnabled( true );
					roomWidth_txt.setEnabled( true );
					roomLength_txt.setEnabled( true );
					outside_btn.setEnabled( true );
					inside_btn.setEnabled( true );
					roomDescrip_txt.setEnabled( true );
					
					roomName_txt.setText("");
					roomWidth_txt.setText("");
					roomLength_txt.setText("");
					outside_btn.setSelection( false );
					inside_btn.setSelection( false );
					roomDescrip_txt.setText("");
					
				} else {
					
					new Message( "You must select a map where the new room will be." );
				
				}	
			}
		});
		newRoom_btn.setText("New Room");
		newRoom_btn.setBounds(10, 337, 82, 25);
		
		roomViewer = new ListViewer(shlWorldBuilder, SWT.BORDER | SWT.V_SCROLL);
		List roomList = roomViewer.getList();
		roomList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clearRoomAttributes();
				int index = roomList.getSelectionIndex();
				roomViewerSelectedIndex = index;
				
				gr = ( GameRoom ) gm.getRooms().get( index );
				
				new BuildRoomLayout();
				
				
				roomName_txt.setText( gr.getName() );
				roomDescrip_txt.setText( gr.getDescription() );
				roomLength_txt.setText( Integer.toString( gr.getRoomLength() ) );
				roomWidth_txt.setText( Integer.toString( gr.getRoomWidth() ) );
				if( gr.isInside() ) {
					inside_btn.setSelection( true );
				}
				if( gr.isOutside() ) {
					outside_btn.setSelection( true );
				}
				
				updateTileViewer();
			}
		});
		roomList.setBounds(10, 207, 308, 124);
		
		Button deleteRoom_btn = new Button(shlWorldBuilder, SWT.NONE);
		deleteRoom_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if( roomViewerSelectedIndex >= 0 ) {;
					Confirmation confirm = new Confirmation( "Are you sure you want to delete this room?" );
					if( confirm.getAnswer() ) {
						roomViewer.getList().remove( roomViewerSelectedIndex );
						roomViewer.refresh( false );
					}
				}
			}
		});
		deleteRoom_btn.setText("Delete Room");
		deleteRoom_btn.setBounds(209, 337, 109, 25);
		
		Button editRoom_btn = new Button(shlWorldBuilder, SWT.NONE);
		editRoom_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( roomViewerSelectedIndex >= 0 ) {
					
					roomName_txt.setEnabled( true );
					roomWidth_txt.setEnabled( true );
					roomLength_txt.setEnabled( true );
					outside_btn.setEnabled( true );
					inside_btn.setEnabled( true );
					roomDescrip_txt.setEnabled( true );
					
					clearRoomAttributes();
					
					int index = roomList.getSelectionIndex();
					roomViewerSelectedIndex = index;
					GameMap gm = ( GameMap ) gw.getMaps().get( mapViewerSelectedIndex );
					GameRoom gr = ( GameRoom ) gm.getRooms().get( index );
					roomName_txt.setText( gr.getName() );
					roomDescrip_txt.setText( gr.getDescription() );
					roomLength_txt.setText( Integer.toString( gr.getRoomLength() ) );
					roomWidth_txt.setText( Integer.toString( gr.getRoomWidth() ) );
					if( gr.isInside() ) {
						inside_btn.setSelection( true );
					}
					if( gr.isOutside() ) {
						outside_btn.setSelection( true );
					}
					
				} else {
					
					new Message( "You must select a room before it can be edited." );
				
				}	
			}
		});
		editRoom_btn.setText("Edit Room");
		editRoom_btn.setBounds(98, 337, 105, 25);
		
		Button saveRoom_btn = new Button(shlWorldBuilder, SWT.NONE);
		saveRoom_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
			
				if( newRoom ) {
					
					GameRoom gr = new GameRoom();
				
					if( roomName_txt.getText() != "" ) {
						gr.setName( roomName_txt.getText() );
					}
					if( roomDescrip_txt.getText() != "" ) {
						gr.setDescription( roomDescrip_txt.getText() );
					}
					if( outside_btn.getSelection() == true ) {
						gr.setOutside( true );
					}
					if( inside_btn.getSelection() == true ) {
						gr.setInside( true );
					}
					if( roomLength_txt.getText() != "" ) {
						gr.setRoomLength( Integer.parseInt( roomLength_txt.getText() ) );
					}
					if( roomWidth_txt.getText() != "" ) {
						gr.setRoomWidth( Integer.parseInt( roomWidth_txt.getText() ) );
					}
			
					addNewRoom( gr );
					setGr( gr );
					
					new BuildRoomLayout();
					
					setGt( ( GameTile ) gr.getTiles().get( 0 ) );

					updateTileViewer();
					
					roomViewer.getList().setSelection( roomViewer.getList().getItems().length - 1 );
					
					newRoom = false;
					
				} else {
					
					GameMap gm = ( GameMap ) gw.getMaps().get( mapViewerSelectedIndex );
					GameRoom gr = ( GameRoom ) gm.getRooms().get( roomViewerSelectedIndex );
					roomViewer.getList().remove( roomViewerSelectedIndex );

					if( roomName_txt.getText() != "" ) {
						gr.setName( roomName_txt.getText() );
					}
					if( roomDescrip_txt.getText() != "" ) {
						gr.setDescription( roomDescrip_txt.getText() );
					}
					if( outside_btn.getSelection() == true ) {
						gr.setOutside( true );
					}
					if( inside_btn.getSelection() == true ) {
						gr.setInside( true );
					}
					if( roomLength_txt.getText() != "" ) {
						gr.setRoomLength( Integer.parseInt( roomLength_txt.getText() ) );
					}
					if( roomWidth_txt.getText() != "" ) {
						gr.setRoomWidth( Integer.parseInt( roomWidth_txt.getText() ) );
					}
					
					updateRoomViewer();

					setGt( ( GameTile ) gr.getTiles().get( 0 ) );
					
					updateTileViewer();
				}
				
				roomName_txt.setEnabled( false );
				roomWidth_txt.setEnabled( false );
				roomLength_txt.setEnabled( false );
				outside_btn.setEnabled( false );
				inside_btn.setEnabled( false );
				roomDescrip_txt.setEnabled( false );

			}
		});
		saveRoom_btn.setText("Save Room");
		saveRoom_btn.setBounds(167, 621, 151, 39);
		
		Button cancelRoom_btn = new Button(shlWorldBuilder, SWT.NONE);
		cancelRoom_btn.setText("Cancel");
		cancelRoom_btn.setBounds(9, 621, 151, 39);
		
		Label tileNumber_lbl = new Label(shlWorldBuilder, SWT.NONE);
		tileNumber_lbl.setText("Tile Number :");
		tileNumber_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		tileNumber_lbl.setBounds(332, 10, 118, 30);
		
		tileNum_lbl = new Label(shlWorldBuilder, SWT.NONE);
		tileNum_lbl.setText("XXX");
		tileNum_lbl.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		tileNum_lbl.setBounds(456, 10, 59, 30);
		
		Button enableExits_btn = new Button(shlWorldBuilder, SWT.NONE);
		enableExits_btn.setBounds(333, 576, 101, 39);
		enableExits_btn.setText("Enable All Exits");
		
		Button disableAllExits_lbl = new Button(shlWorldBuilder, SWT.NONE);
		disableAllExits_lbl.setText("Disable All Exits");
		disableAllExits_lbl.setBounds(333, 621, 101, 39);
		
		items_tree = new Tree(shlWorldBuilder, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.VIRTUAL);
		items_tree.setForeground(SWTResourceManager.getColor(0, 0, 0));
		items_tree.setHeaderBackground(SWTResourceManager.getColor(230, 230, 250));
		items_tree.setHeaderForeground(SWTResourceManager.getColor(0, 0, 128));
		items_tree.setHeaderVisible(true);
		items_tree.setToolTipText("");
		items_tree.setTouchEnabled(true);
		items_tree.setLinesVisible(true);
		items_tree.setBounds(723, 10, 296, 381);

		TreeColumn trclmnItem = new TreeColumn(items_tree, SWT.NONE);
		trclmnItem.setWidth(220);
		trclmnItem.setText("Item");
		
		npcs_tree = new Tree(shlWorldBuilder, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.VIRTUAL);
		npcs_tree.setTouchEnabled(true);
		npcs_tree.setToolTipText("");
		npcs_tree.setLinesVisible(true);
		npcs_tree.setHeaderVisible(true);
		npcs_tree.setHeaderForeground(SWTResourceManager.getColor(0, 0, 128));
		npcs_tree.setHeaderBackground(SWTResourceManager.getColor(230, 230, 250));
		npcs_tree.setForeground(SWTResourceManager.getColor(253, 245, 230));
		npcs_tree.setBounds(1025, 10, 302, 381);
		
		TreeColumn trclmnNpc = new TreeColumn(npcs_tree, SWT.NONE);
		trclmnNpc.setWidth(220);
		trclmnNpc.setText("NPC");
		
		tileDes_txt = new Text(shlWorldBuilder, SWT.BORDER);
		tileDes_txt.setBounds(970, 517, 357, 98);
		
		Label tileDes_lbl = new Label(shlWorldBuilder, SWT.NONE);
		tileDes_lbl.setText("Tile Description");
		tileDes_lbl.setBounds(970, 496, 109, 18);
		
		Button setPerimeter_btn = new Button(shlWorldBuilder, SWT.NONE);
		setPerimeter_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				for( int i = 0; i < gr.getTiles().size(); i++ ) {
					
					gt = ( GameTile ) gr.getTiles().get( i );
					
					if( southWall() ) {
						
						setNotTile();
						
					} else if( northWall() ) {
						
						setNotTile();
						
					} else if( eastWall() ) {
						
						setNotTile();
						
					} else if( westWall() ) {
						
						setNotTile();
					}
				}
			}
		});
		setPerimeter_btn.setText("Set Perimeter");
		setPerimeter_btn.setBounds(440, 621, 94, 39);
		
		Button connectAllTiles_btn_1 = new Button(shlWorldBuilder, SWT.NONE);
		connectAllTiles_btn_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				for( int i = 0; i < gr.getTiles().size(); i++ ) {
					gt = ( GameTile ) gr.getTiles().get( i );
					
					if( !gt.getNotTile() ) {
						
						connectExits();
					}
				}
			}
		});
		connectAllTiles_btn_1.setText("Connect All Tiles");
		connectAllTiles_btn_1.setBounds(440, 576, 94, 39);
		
		Button save_btn = new Button(shlWorldBuilder, SWT.NONE);
		save_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println( savePath );
				saveTile();
				new BuildJSON().construct( gw, savePath );
			}
		});
		save_btn.setText("Save");
		save_btn.setBounds(1154, 621, 173, 39);
		
		Button exit_btn = new Button(shlWorldBuilder, SWT.NONE);
		exit_btn.setText("Exit");
		exit_btn.setBounds(970, 621, 178, 39);
		
		Menu menu = new Menu(shlWorldBuilder, SWT.BAR);
		shlWorldBuilder.setMenuBar(menu);
		
		n_btn = new Button(shlWorldBuilder, SWT.FLAT);
		n_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getN() == NOEXIT 
						&& !northWall() 
						&& ( ( GameTile ) gr.getTiles().get( northEquation() ) ).getNotTile() == false ) {
					
					gt.setN( northEquation() );
					n_btn.setBackground( new Color( null, 153, 255, 153) );
					
				} else {
					
					gt.setN( NOEXIT );
					n_btn.setBackground( new Color( null, 255, 153, 153) );
				}
			}
		});
		n_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		n_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		n_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		n_btn.setBounds(382, 438, 52, 40);
		n_btn.setText("n");
		
		nw_btn = new Button(shlWorldBuilder, SWT.FLAT);
		nw_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getNW() == NOEXIT 
						&& !northWall()
						&& !westWall()
						&& ( ( GameTile ) gr.getTiles().get( northWestEquation() ) ).getNotTile() == false ) {
					
					gt.setNW( northWestEquation() );
					nw_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setNW( NOEXIT );
					nw_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		nw_btn.setText("nw");
		nw_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		nw_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		nw_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		nw_btn.setBounds(332, 438, 44, 40);
		
		ne_btn = new Button(shlWorldBuilder, SWT.FLAT);
		ne_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getNE() == NOEXIT 
						&& !northWall()
						&& !eastWall()
						&& ( ( GameTile ) gr.getTiles().get( northEastEquation() ) ).getNotTile() == false ) {
					
					gt.setNE( northEastEquation() );
					ne_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setNE( NOEXIT );
					ne_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		ne_btn.setText("ne");
		ne_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		ne_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		ne_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		ne_btn.setBounds(440, 438, 44, 40);
		
		w_btn = new Button(shlWorldBuilder, SWT.FLAT);
		w_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getW() == NOEXIT 
						&& !westWall()
						&& ( ( GameTile ) gr.getTiles().get( westEquation() ) ).getNotTile() == false ) {
					
					gt.setW( westEquation() );
					w_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setW( NOEXIT );
					w_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		w_btn.setText("w");
		w_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		w_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		w_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		w_btn.setBounds(332, 484, 44, 40);
		
		e_btn = new Button(shlWorldBuilder, SWT.FLAT);
		e_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getE() == NOEXIT 
						&& !eastWall()
						&& ( ( GameTile ) gr.getTiles().get( eastEquation() ) ).getNotTile() == false ) {
					
					gt.setE( eastEquation() );
					e_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setE( NOEXIT );
					e_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		e_btn.setText("e");
		e_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		e_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		e_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		e_btn.setBounds(440, 484, 44, 40);
		
		sw_btn = new Button(shlWorldBuilder, SWT.FLAT);
		sw_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getSW() == NOEXIT 
						&& !southWall() 
						&& !westWall()
						&& ( ( GameTile ) gr.getTiles().get( southWestEquation() ) ).getNotTile() == false ) {
					
					gt.setSW( southWestEquation() );
					sw_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setSW( NOEXIT );
					sw_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		sw_btn.setText("sw");
		sw_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		sw_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		sw_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		sw_btn.setBounds(332, 530, 44, 40);
		
		s_btn = new Button(shlWorldBuilder, SWT.FLAT);
		s_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getS() == NOEXIT 
						&& !southWall()
						&& ( ( GameTile ) gr.getTiles().get( southEquation() ) ).getNotTile() == false ) {
					
					gt.setS( southEquation() );
					s_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setS( NOEXIT );
					s_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		s_btn.setText("s");
		s_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		s_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		s_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		s_btn.setBounds(382, 530, 52, 40);
		
		se_btn = new Button(shlWorldBuilder, SWT.FLAT);
		se_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if( gt.getSE() == NOEXIT 
						&& !southWall() 
						&& !eastWall()
						&& ( ( GameTile ) gr.getTiles().get( southEastEquation() ) ).getNotTile() == false ) {
					
					gt.setSE( southEastEquation() );
					se_btn.setBackground( new Color( null, 153, 255, 153) );

					
				} else {
					
					gt.setSE( NOEXIT );
					se_btn.setBackground( new Color( null, 255, 153, 153) );

				}
			}
		});
		se_btn.setText("se");
		se_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		se_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		se_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		se_btn.setBounds(440, 530, 44, 40);
		
		d_btn = new Button(shlWorldBuilder, SWT.FLAT);
		d_btn.setText("d");
		d_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		d_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		d_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		d_btn.setBounds(490, 530, 44, 40);
		
		u_btn = new Button(shlWorldBuilder, SWT.FLAT);
		u_btn.setText("u");
		u_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		u_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		u_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		u_btn.setBounds(490, 438, 44, 40);
		
		exitRoom_btn = new Button(shlWorldBuilder, SWT.FLAT);
		exitRoom_btn.setText("Exit Room");
		exitRoom_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		exitRoom_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		exitRoom_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		exitRoom_btn.setBounds(540, 485, 118, 39);
		
		tileLayout_cpt = new Composite(shlWorldBuilder, SWT.BORDER);
		tileLayout_cpt.setBounds(332, 46, 387, 387);
		
		exitMap_btn = new Button(shlWorldBuilder, SWT.FLAT);
		exitMap_btn.setText("Exit Map");
		exitMap_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		exitMap_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		exitMap_btn.setBackground(SWTResourceManager.getColor(255, 99, 71));
		exitMap_btn.setBounds(540, 530, 118, 40);
		
		Button disableUpExits_btn = new Button(shlWorldBuilder, SWT.NONE);
		disableUpExits_btn.setText("Disable Up Exits");
		disableUpExits_btn.setBounds(540, 576, 118, 39);
		
		Button disableDownExits_btn = new Button(shlWorldBuilder, SWT.NONE);
		disableDownExits_btn.setText("Disable Down Exits");
		disableDownExits_btn.setBounds(540, 621, 118, 39);
		
		Button currentTile_btn = new Button(shlWorldBuilder, SWT.FLAT);
		currentTile_btn.setText("x");
		currentTile_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		currentTile_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		currentTile_btn.setBackground(SWTResourceManager.getColor(32, 178, 170));
		currentTile_btn.setBounds(382, 484, 52, 40);
		
		notTile_btn = new Button(shlWorldBuilder, SWT.FLAT);
		notTile_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				setNotTile();
			}
		});
		notTile_btn.setText("Not Tile");
		notTile_btn.setForeground(SWTResourceManager.getColor(0, 0, 0));
		notTile_btn.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		notTile_btn.setBackground( new Color( null, 255, 153, 153) );
		notTile_btn.setBounds(540, 439, 118, 40);
		
		Button addItem_btn = new Button(shlWorldBuilder, SWT.NONE);
		addItem_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("sssss");
				new ItemBuilder( gt );
			}
		});
		addItem_btn.setText("Add Item");
		addItem_btn.setBounds(723, 394, 94, 39);
		
		Button editItem_btn = new Button(shlWorldBuilder, SWT.NONE);
		editItem_btn.setText("Edit Item");
		editItem_btn.setBounds(823, 394, 96, 39);
		
		Button deleteItem_btn = new Button(shlWorldBuilder, SWT.NONE);
		deleteItem_btn.setText("Delete Item");
		deleteItem_btn.setBounds(925, 394, 94, 39);
		
		Button addNPC_btn = new Button(shlWorldBuilder, SWT.NONE);
		addNPC_btn.setText("Add NPC");
		addNPC_btn.setBounds(1025, 394, 94, 39);
		 
		Button editNPC_btn = new Button(shlWorldBuilder, SWT.NONE);
		editNPC_btn.setText("Edit NPC");
		editNPC_btn.setBounds(1125, 394, 102, 39);
		
		Button deleteNPC_btn = new Button(shlWorldBuilder, SWT.NONE);
		deleteNPC_btn.setText("Delete NPC");
		deleteNPC_btn.setBounds(1233, 394, 94, 39);
		
		exits_tbl = new Table(shlWorldBuilder, SWT.BORDER | SWT.FULL_SELECTION);
		exits_tbl.setBounds(664, 442, 300, 218);
		exits_tbl.setHeaderVisible(true);
		exits_tbl.setLinesVisible(true);
		
		TableColumn tblclmnDirection = new TableColumn(exits_tbl, SWT.NONE);
		tblclmnDirection.setMoveable(true);
		tblclmnDirection.setWidth(75);
		tblclmnDirection.setText("Direction");
		
		TableColumn tblclmnMap = new TableColumn(exits_tbl, SWT.NONE);
		tblclmnMap.setWidth(75);
		tblclmnMap.setText("Map");
		
		TableColumn tblclmnRoom = new TableColumn(exits_tbl, SWT.NONE);
		tblclmnRoom.setWidth(75);
		tblclmnRoom.setText("Room");
		
		TableColumn tblclmnNewColumn = new TableColumn(exits_tbl, SWT.NONE);
		tblclmnNewColumn.setWidth(75);
		tblclmnNewColumn.setText("Tile");
		
		tileName_txt = new Text(shlWorldBuilder, SWT.BORDER);
		tileName_txt.setBounds(970, 457, 289, 30);
		
		Label tileName_lbl = new Label(shlWorldBuilder, SWT.NONE);
		tileName_lbl.setText("Tile Name");
		tileName_lbl.setBounds(970, 439, 109, 18);
		
		defTileChar_txt = new Text(shlWorldBuilder, SWT.BORDER);
		defTileChar_txt.setBounds(1265, 457, 62, 30);
		
		Label defTileChar_lbl = new Label(shlWorldBuilder, SWT.NONE);
		defTileChar_lbl.setText("Default Tile Char");
		defTileChar_lbl.setBounds(1218, 439, 109, 18);
		

	}
	
	public void addNewMap( GameMap gm ) {
		gw.addMap( gm );
		updateMapViewer();	
	}
	
	public void addNewRoom( GameRoom gr ) {
		
		GameMap map = ( GameMap ) gw.getMaps().get( mapViewerSelectedIndex );
		map.addRoom( gr );
		updateRoomViewer();	
	}
	
	public void addNewTile( GameTile gt ) {
		
		GameMap map = ( GameMap ) gw.getMaps().get( mapViewerSelectedIndex );
		GameRoom room = ( GameRoom ) map.getRooms().get( roomViewerSelectedIndex );
		room.addTile( gt );
	} 
	
	public void loadWorld( String path, String worldName ) {
		
		WorldReader worldReader = new WorldReader();
		
		gw =  worldReader.getWorld( path + worldName, worldName + ".json" );	
	}
	
	public void updateMapViewer() {
		mapViewer.getList().removeAll();
		for( int i = 0; i < gw.getThings().size(); i++ ) {
			mapViewer.add( gw.getMaps().get( i ) );
		}
	}
	
	public void updateRoomViewer() {
		roomViewer.getList().removeAll();
		GameMap map = ( GameMap ) gw.getThings().get( mapViewerSelectedIndex );
		for( int i = 0; i < map.getRooms().size(); i++ ) {
			roomViewer.add( map.getRooms().get( i ) );
		}
	}
	
	public void updateTileViewer() {
		
		for( int i = 0; i < roomTiles.length; i++ ) {
			
			if( roomTiles[ i ].getSelection() ) {
				
				gt = ( GameTile ) gr.getTiles().get( i );
				break;
			}
		}
		
		tileName_txt.setText( gt.getName() );
		tileNum_lbl.setText( Integer.toString( gt.getTileNumber() ) );
		tileDes_txt.setText( gt.getDescription() );
		defTileChar_txt.setText( gt.getDefaultTileChar() );
		updateItemsTree();
//		npcs_tree.removeAll();
		updateTileExits();
		
	}
	
	public void connectExits() {
		
		if( !southWall() && ( ( GameTile ) gr.getTiles().get( southEquation() ) ).getNotTile() == false ) {
			gt.setS( southEquation() );
		}
		if( !northWall() && ( ( GameTile ) gr.getTiles().get( northEquation() ) ).getNotTile() == false ) {
			gt.setN( northEquation() );
		}
		if( !eastWall() && ( ( GameTile ) gr.getTiles().get( eastEquation() ) ).getNotTile() == false  ) {
			gt.setE( eastEquation() );
		}
		if( !westWall() && ( ( GameTile ) gr.getTiles().get( westEquation() ) ).getNotTile() == false ) {
			gt.setW( westEquation() );
		}
		if( !southWall() && !westWall() 
				&& ( ( GameTile ) gr.getTiles().get( southWestEquation() ) ).getNotTile() == false ) {
			gt.setSW( southWestEquation() );
		}
		if( !southWall() && !eastWall() 
				&& ( ( GameTile ) gr.getTiles().get( southEastEquation() ) ).getNotTile() == false ) {
			gt.setSE( southEastEquation() );
		}
		if( !northWall() && !westWall() 
				&& ( ( GameTile ) gr.getTiles().get( northWestEquation() ) ).getNotTile() == false ) {
			gt.setNW( northWestEquation() );
		}
		if( !northWall() && !eastWall() 
				&& ( ( GameTile ) gr.getTiles().get( northEastEquation() ) ).getNotTile() == false ) {
			gt.setNE( southEastEquation() );
		}

	}
	
	public void updateTileExits() {
//		
//		this.north = gt.getN();
//		this.south = gt.getS();
//		this.east = gt.getE();
//		this.west = gt.getW();
//		this.northEast = gt.getNE();
//		this.northWest = gt.getNW();
//		this.southEast = gt.getSE();
//		this.southWest = gt.getSW();
//		this.up = gt.getU();
//		this.down = gt.getD();
//		this.special = gt.getSpecial();
//		this.notTile = gt.getNotTile();
//		this.exitRoom = gt.isExitRoom();
//		this.exitMap = gt.isExitMap();
		
		if( gt.getN() < 0 ) {
			n_btn.setBackground( new Color( null, 255, 153, 153) );
		} else {
			n_btn.setBackground( new Color( null, 153, 255, 153) );
		}
		if( gt.getS() < 0 ) {
			s_btn.setBackground( new Color( null, 255, 153, 153) );
		} else {
			s_btn.setBackground( new Color( null, 153, 255, 153) );
		}
		if( gt.getE() < 0 ) {
			e_btn.setBackground( new Color( null, 255, 153, 153) );
		} else {
			e_btn.setBackground( new Color( null, 153, 255, 153) );
		}
		if( gt.getW() < 0 ) {
			w_btn.setBackground( new Color( null, 255, 153, 153) );
		} else {
			w_btn.setBackground( new Color( null, 153, 255, 153) );
		}
		if( gt.getNE() < 0 ) {
			ne_btn.setBackground(  new Color( null, 255, 153, 153) );
		} else {
			ne_btn.setBackground(  new Color( null, 153, 255, 153) );
		}
		if( gt.getNW() < 0 ) {
			nw_btn.setBackground(  new Color( null, 255, 153, 153) );
		} else {
			nw_btn.setBackground(  new Color( null, 153, 255, 153) );
		}
		if( gt.getSE() < 0 ) {
			se_btn.setBackground(  new Color( null, 255, 153, 153) );
		} else {
			se_btn.setBackground(  new Color( null, 153, 255, 153) );
		}
		if( gt.getSW() < 0 ) {
			sw_btn.setBackground(  new Color( null, 255, 153, 153) );
		} else {
			sw_btn.setBackground(  new Color( null, 153, 255, 153) );
		}
		if( gt.getNotTile() ) {
			notTile_btn.setBackground( new Color( null, 153, 255, 153 ) );
		} else {
			notTile_btn.setBackground( new Color( null, 255, 153, 153 ) );
		}
	}
	
	public void clearRoomAttributes() {
		
		roomName_txt.setText( "" );
		roomDescrip_txt.setText( "" );
		roomLength_txt.setText( "" );
		roomWidth_txt.setText( "" );
		inside_btn.setSelection( false );
		outside_btn.setSelection( false );
		
		for( int i = 0; i < buttonlist.size(); i++ ) {
			buttonlist.get( i ).dispose();
		}
        tileLayout_cpt.layout();
		
	}
	
	public void clearTileAttributes() {
		
		tileName_txt.setText( "" );
		tileDes_txt.setText( "" );
		defTileChar_txt.setText( "" );
		notTile_btn.setBackground( new Color( null, 255, 153, 153 ) );
		
		items_tree.getChildren();
//		npcs_tree.removeAll();
		this.north = NOEXIT;
		this.south = NOEXIT;
		this.east = NOEXIT;
		this.west = NOEXIT;
		this.northEast = NOEXIT;
		this.northWest = NOEXIT;
		this.southEast = NOEXIT;
		this.southWest = NOEXIT;
		this.up = NOEXIT;
		this.down = NOEXIT;
		this.special = NOEXIT;
		this.notTile = false;
		this.exitRoom = false;
		this.exitMap = false;
	}
	
	 // saving methods
    public void saveTile() {
    	
    	saveTileName();
    	saveTileDescription();
//    	saveTileExits();
    	saveTileThingList();
    	saveTileNPCList();
    	saveDefaultTileChar();
    }
    
    public void saveTileName() {
    	
    	this.gt.setName( tileName_txt.getText() );
    }
    
    public void saveTileDescription() {
    	
    	this.gt.setDescription( tileDes_txt.getText() );
    }
    
//    public void saveTileExits() {
//    	
//    	this.gt.setN( this.north );
//    	this.gt.setS( this.south );
//    	this.gt.setE( this.east );
//    	this.gt.setW( this.west );
//    	this.gt.setSW( this.southWest );
//    	this.gt.setSE( this.southEast );
//    	this.gt.setNW( this.northWest );
//    	this.gt.setNE( this.northEast );
//    	this.gt.setU( this.up );
//    	this.gt.setD( this.down );
//    	this.gt.setSpecial( this.special );
//    	this.gt.setNotTile( notTile );
//    	
//    }

    public void saveTileThingList() {
    	
//    	this.gt.setThings( currentTileThingList );
    }
    
    public void saveTileNPCList() {
    	
//    	this.gt.setNPCs( currentTileNPCList );
    }
    
    public void saveDefaultTileChar() {
    	
    	this.gt.setDefaultTileChar( defTileChar_txt.getText() );
    	this.gt.setTileCharToDefaultTileChar();
    }
    
 // direction calculations
    public int northEquation() {
    
    	return gt.getTileNumber() + gr.getRoomLength();
    }
    
    public int southEquation() {
    	
    	return gt.getTileNumber() - gr.getRoomLength();
    }
    
    public int eastEquation() {
    	
    	return gt.getTileNumber() + 1;
    }
    
    public int westEquation() {
    	
    	return gt.getTileNumber() - 1;
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
    	System.out.println( gt.getTileNumber() + " : " + northEquation() + " : " + gr.getRoomSize() + " : " + ( northEquation() > gr.getRoomSize() - 1));
    	
    	if( northEquation() > gr.getRoomSize() - 1 ) {
    		
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

    	if ( ( eastEquation() ) % ( gr.getRoomLength() )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    public boolean westWall() {

    	if ( ( gt.getTileNumber() ) % ( gr.getRoomLength() )  == 0 ) {
    		
    		return true;
    		
    	} else {
    		
    		return false;
    	}
    }
    
    // local State methods
    private void setNotTile() {
    	
    	if( gt.getNotTile() == false ) {
    		
    		gt.setNotTile( true );
    		notTile_btn.setBackground( new Color( null, 153, 255, 153) );
    		Button tileButton = roomTiles[ gt.getTileNumber() ];
    		tileButton.setBackground( new Color( null, 36, 36, 36 ) );
    		
    		if( gr.getRoomSize() < 500 ) {
    			roomTiles[ gt.getTileNumber() ].setText( "nt" );
    		}
    		
    		gt.setDefaultTileChar( TileChar.wall );
    		gt.setTileCharToDefaultTileChar();
    		defTileChar_txt.setText( gt.getDefaultTileChar() );
    		saveTile();
    	
    		if( !northWall() ) {
    		
    			GameTile nTile = ( GameTile ) gr.getTiles().get( northEquation() );
    			nTile.setS( NOEXIT );
    		}
    		if( !southWall() ) {
        		
    			GameTile sTile = ( GameTile ) gr.getTiles().get( southEquation() );
    			sTile.setN( NOEXIT );
    		}
    		if( !eastWall() ) {
        		
    			GameTile eTile = ( GameTile ) gr.getTiles().get( eastEquation() );
    			eTile.setW( NOEXIT );
    		}
    		if( !westWall() ) {
        		
    			GameTile wTile = ( GameTile ) gr.getTiles().get( westEquation() );
    			wTile.setE( NOEXIT );
    		}
    		if( !northWall() && !eastWall() ) {
        		
    			GameTile neTile = ( GameTile ) gr.getTiles().get( northEastEquation() );
    			neTile.setSW( NOEXIT );
    		}
    		if( !northWall() && !westWall() ) {
        		
    			GameTile nwTile = ( GameTile ) gr.getTiles().get( northWestEquation() );
    			nwTile.setSE( NOEXIT );
    		}
    		if( !southWall() && !eastWall() ) {
        		
    			GameTile seTile = ( GameTile ) gr.getTiles().get( southEastEquation() );
    			seTile.setNW( NOEXIT );
    		}
    		if( !southWall() && !westWall() ) {
        		
    			GameTile swTile = ( GameTile ) gr.getTiles().get( southWestEquation() );
    			swTile.setNE( NOEXIT );
    		}
    		
    	} else {
    		
    		gt.setNotTile( false );
    		notTile_btn.setBackground( new Color( null, 255, 153, 153) );
    		roomTiles[ gt.getTileNumber() ].setBackground( new Color( null, 210, 214, 217 ) );
    		
    		if( gr.getRoomSize() < 500 ) {
    			roomTiles[ gt.getTileNumber() ].setText( Integer.toString( gt.getTileNumber() ) );
    		}
    		
    		gt.setDefaultTileChar( TileChar.floor );
    		gt.setTileCharToDefaultTileChar();
    		defTileChar_txt.setText( gt.getDefaultTileChar() );
    		saveTile();

    		if( !northWall() ) {
        		
    			GameTile nTile = ( GameTile ) gr.getTiles().get( northEquation() );
    			nTile.setS( gt.getTileNumber() );
    		}
    		if( !southWall() ) {
        		
    			GameTile sTile = ( GameTile ) gr.getTiles().get( southEquation() );
    			sTile.setN( gt.getTileNumber() );
    		}
    		if( !eastWall() ) {
        		
    			GameTile eTile = ( GameTile ) gr.getTiles().get( eastEquation() );
    			eTile.setW( gt.getTileNumber() );
    		}
    		if( !westWall() ) {
        		
    			GameTile wTile = ( GameTile ) gr.getTiles().get( westEquation() );
    			wTile.setE( gt.getTileNumber() );
    		}
    		if( !northWall() && !eastWall() ) {
        		
    			GameTile neTile = ( GameTile ) gr.getTiles().get( northEastEquation() );
    			neTile.setSW( gt.getTileNumber() );
    		}
    		if( !northWall() && !westWall() ) {
        		
    			GameTile nwTile = ( GameTile ) gr.getTiles().get( northWestEquation() );
    			nwTile.setSE( gt.getTileNumber() );
    		}
    		if( !southWall() && !eastWall() ) {
        		
    			GameTile seTile = ( GameTile ) gr.getTiles().get( southEastEquation() );
    			seTile.setNW( gt.getTileNumber() );
    		}
    		if( !southWall() && !westWall() ) {
        		
    			GameTile swTile = ( GameTile ) gr.getTiles().get( southWestEquation() );
    			swTile.setNE( gt.getTileNumber() );
    		}
    	}
    }
    
    // --- sets state of current room ---
//    public void setCurrentTile( int tileNumber ){
//    	
//    	this.currentTile = getTile( tileNumber );
//    	this.currentTileNumber = tileNumber;
//    	this.selectedTileNumber.setText( String.valueOf( tileNumber ) ); 
//    	
//    	setCurrentTileName();
//    	setCurrentTileDescription();
//    	setCurrentTileExits();
//    	setCurrentTileThingList();
//    	setCurrentTileNPCList();
//    	setCurrentTileLabels();
//    	setCurrentTileDefaultTileChar();
//    }
  

	public boolean isNewMap() {
		return newMap;
	}

	public void setNewMap(boolean newMap) {
		this.newMap = newMap;
	}
	
	public Composite getTileLayout() {
		return tileLayout_cpt;
	}
	
	public void setTileLayout( Composite tileLayout ) {
		this.tileLayout_cpt = tileLayout;
	}

	public LinkedList<Button> getButtonlist() {
		return buttonlist;
	}

	public void setButtonlist(LinkedList<Button> buttonlist) {
		this.buttonlist = buttonlist;
	}

	public Button[] getRoomTiles() {
		return roomTiles;
	}

	public void setRoomTiles(Button[] roomTiles) {
		this.roomTiles = roomTiles;
	}
	
	public void setRoomTiles(int roomTileSize ) {
		this.roomTiles = new Button[roomTileSize];
	}

	public GameMap getGm() {
		return gm;
	}

	public void setGm(GameMap gm) {
		this.gm = gm;
	}

	public GameRoom getGr() {
		return gr;
	}

	public void setGr(GameRoom gr) {
		this.gr = gr;
	}

	public GameTile getGt() {
		return gt;
	}

	public void setGt(GameTile gt) {
		this.gt = gt;
	}

	public Button getS_btn() {
		return s_btn;
	}

	public void setS_btn(Button s_btn) {
		this.s_btn = s_btn;
	}

	public Button getW_btn() {
		return w_btn;
	}

	public void setW_btn(Button w_btn) {
		this.w_btn = w_btn;
	}

	public Button getE_btn() {
		return e_btn;
	}

	public void setE_btn(Button e_btn) {
		this.e_btn = e_btn;
	}

	public Button getNe_btn() {
		return ne_btn;
	}

	public void setNe_btn(Button ne_btn) {
		this.ne_btn = ne_btn;
	}

	public Button getNw_btn() {
		return nw_btn;
	}

	public void setNw_btn(Button nw_btn) {
		this.nw_btn = nw_btn;
	}

	public Button getSe_btn() {
		return se_btn;
	}

	public void setSe_btn(Button se_btn) {
		this.se_btn = se_btn;
	}

	public Button getSw_btn() {
		return sw_btn;
	}

	public void setSw_btn(Button sw_btn) {
		this.sw_btn = sw_btn;
	}

	public Button getU_btn() {
		return u_btn;
	}

	public void setU_btn(Button u_btn) {
		this.u_btn = u_btn;
	}

	public Button getD_btn() {
		return d_btn;
	}

	public void setD_btn(Button d_btn) {
		this.d_btn = d_btn;
	}

	public Button getSpecial_btn() {
		return special_btn;
	}

	public void setSpecial_btn(Button special_btn) {
		this.special_btn = special_btn;
	}

	public Button getNotTile_btn() {
		return notTile_btn;
	}

	public void setNotTile_btn(Button notTile_btn) {
		this.notTile_btn = notTile_btn;
	}

	public Button getExitRoom_btn() {
		return exitRoom_btn;
	}

	public void setExitRoom_btn(Button exitRoom_btn) {
		this.exitRoom_btn = exitRoom_btn;
	}

	public Button getExitMap_btn() {
		return exitMap_btn;
	}

	public void setExitMap_btn(Button exitMap_btn) {
		this.exitMap_btn = exitMap_btn;
	}

	public Shell getShell() {
		return shlWorldBuilder;
		
	}
	
	public boolean isNewRoom() {
		return this.newRoom;
	}
	
	//tree methods. move to new object
	
	public void updateItemsTree(){
		items_tree.removeAll();
		ThingList things = gt.getThings();
		for( int i = 0; i < things.size(); i++ ) {
			Thing thing = things.get( i );
			TreeItem treeItem = new TreeItem( items_tree, 0 );
			treeItem.setText( thing.getName() );
			if( thing.isThingHolder() ) {
				getAll( thing, treeItem );
			}
		}
	}
	
	public void updateNpcsTree(){
		ThingList things = gt.getNPCs();
		for( int i = 0; i < things.size(); i++ ) {
			Thing thing = things.get( i );
			TreeItem treeItem = new TreeItem( npcs_tree, 0 );
			treeItem.setText( thing.getName() );
			if( thing.isThingHolder() ) {
				getAll( thing, treeItem );
			}
		}
	}
	
	private void getAll( Thing thing, TreeItem treeItem ) {
		
		ThingHolder thingHolder = ( ThingHolder ) thing;
		ThingList things = thingHolder.getThings();
		for( int i = 0; i < things.size(); i++ ) {
			Thing t = things.get( i );
			TreeItem ti = new TreeItem( treeItem, 0 );
			ti.setText( t.getName() );
			if( t.isThingHolder() ) {
				getAll( t, ti );
			}
		}
	}
}
