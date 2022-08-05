package userInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.StyledDocument;

public class TextConsoleNew {

	public JFrame mainFrame;
	public JTextPane display;
	public JTextField input;
	public JScrollPane scrollpane;
	public StyledDocument document;
	
	public TextConsoleNew( JFrame frame ) {
		
		this.mainFrame = frame;
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {}
		
//		mainFrame = new JFrame();
//		mainFrame.setTitle("Console Display");
//		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
		display = new JTextPane();
		display.setEditable(false);
		display.setFont(new Font("Courier New", Font.BOLD, 14));
		display.setOpaque(false);
		
		//graphicDisplay = 
		
		document = display.getStyledDocument();
		
		input = new JTextField();
		input.setEditable(true);
		input.setBackground(new Color(50, 50, 50));
		input.setForeground(Color.WHITE);
		input.setCaretColor(Color.WHITE);
		input.setFont(new Font("Courier New", Font.BOLD, 14));
		input.setOpaque(false);
		
		// scrollpane for output
		scrollpane = new JScrollPane(display);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		scrollpane.setBorder(null);
		
		mainFrame.add(input, BorderLayout.SOUTH);
		mainFrame.add(scrollpane, BorderLayout.CENTER);

    }

    public JTextField getInputField() {
    	return input;
    }
}
