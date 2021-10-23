package UserInterface;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class ConsoleLogicNew {

	DisplayNew console;
	private Controller controller;
	
	boolean trace = false;

	// command memory -- will remember 100 commands
	ArrayList<String> recent_used = new ArrayList<String>();
	int recent_used_id = 0;
	int recent_used_maximum = 100; 
	
	// loop logic
	boolean loop = false;
	int loop_times = 1;
	int loop_times_temp = 1;

	
	public ConsoleLogicNew( DisplayNew con, Controller controller ) {
		console = con;
		this.controller = controller;
	}

	public void scrollTop() {
		console.getConsoleDisplay().setCaretPosition(0);
	}
	
	public void scrollBottom() {
		console.getConsoleDisplay().setCaretPosition(console.getConsoleDisplay().getDocument().getLength());
		controller.getGuiConsole().positionCaret( ( controller.getGuiConsole().getText().split("\n") ).length );
		controller.getGuiConsole().setScrollTop( Double.MAX_VALUE );
	}
	
	public void print(String s, boolean trace) {
		print(s, trace, new Color(255, 255, 255));
		scrollBottom();
	}
	
	public void print(String s, boolean trace, Color c) {
		Style style = console.getConsoleDisplay().addStyle("Style", null);
		StyleConstants.setForeground(style, c);
		
		if(trace) {
			Throwable t = new Throwable();
			StackTraceElement[] elements = t.getStackTrace();
			String caller = elements[0].getClassName();
			
			s = caller + " ==> " + s;
		}
		
		try {
			console.document.insertString(console.document.getLength(), s, style);;
		} catch (Exception ex) {}
		
		try {
			// controller
			controller.getGuiConsole().appendText(s);
		} catch (Exception ex) {}
	}
	
	public void println(String s, boolean trace) {
		println(s, trace, new Color(255, 255, 255));
		scrollBottom();
	}
	
	public void println(String s, boolean trace, Color c) {
		print(s + "\n", trace, c);
		scrollBottom();
	}
	
	public void clear() {
		try {
			console.document.remove(0, console.document.getLength());
//			controller.getGuiConsole().gey
		} catch(Exception ex) {}
	}
	
	// console do commands for action listener
	public void doCommands(String[] cmds, String s) {
		
		println(s, trace, new Color(255, 255, 255));
		
		for(int i = 0; i < loop_times; i++) {
			try {
				//if(globalCurrentState.equalsIgnoreCase("Console")) {
				// clear console
				if(cmds[0].equalsIgnoreCase("clear")) {
					
					clear();
					
				// pop up window for next input
				} else if (cmds[0].equalsIgnoreCase("popup")) {
					
					String message = "";
					
					for(int j = 1; j < cmds.length; j++) {
						message += cmds[j];
						
						if(j != cmds.length - 1) {
							message += " ";
						}		
					}
					
					JOptionPane.showMessageDialog(null, message, "message", JOptionPane.INFORMATION_MESSAGE);
				
				// loop next input
				} else if(cmds[0].equalsIgnoreCase("loop")) {
					loop_times_temp = Integer.parseInt(cmds[1]);
					loop = true;
					
					println("The next statment will loop " + loop_times_temp + " times!", trace, new Color(155, 155, 255));
				} 
				//}	
			} catch(Exception ex) {
				println("Error ==> " + ex.getMessage(), trace, new Color(255, 155, 155));
			}
		}	
		if(loop) {
			loop_times = loop_times_temp;
			loop_times_temp = 1;
		}
	}
	
    // calls FileReader to read the documents and then appends them to the text area.
//    public void printFile(String fName, String dName) {
//    	
//    	FileReader file = new FileReader(fName, dName);
//    	println(file.getText(), trace, Color.WHITE);	
//    }
    
    public void consoleFeatures( String text ) {
		recent_used.add( text );
		recent_used_id = 0;
		scrollBottom();
		console.getInputField().selectAll();
    }
    
    public void keyPressedPerform(KeyEvent e) {
    	if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(recent_used_id < (recent_used_maximum - 1) && recent_used_id < (recent_used.size() - 1)) {
				
				recent_used_id++;
			}
			console.getInputField().setText(recent_used.get(recent_used.size() - 1 - recent_used_id));
		
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(recent_used_id > 0) {
				recent_used_id--;
			}
			console.getInputField().setText(recent_used.get(recent_used.size() - 1 - recent_used_id));
		}
    }
    
    public boolean getTrace() {
    	return trace;
    }

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}

