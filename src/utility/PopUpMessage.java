package utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PopUpMessage {
	
	String message;
	
	JFrame mainFrame;
	
	JPanel borderPanel;
	
	JLabel topBorder;
	JLabel bottomBorder;
	JTextArea rightBorder;
	JTextArea leftBorder;
	
	public PopUpMessage( String aMessage ){
		
		this.message = aMessage;
		
		mainFrame = new JFrame();

        borderPanel = new JPanel();
        borderPanel.setLayout( new BorderLayout( 10, 10 ) );
        borderPanel.setBackground( Color.WHITE );

        JPanel topBorderHolder = new JPanel();
        topBorderHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
        
        JPanel bottomBorderHolder = new JPanel();
        bottomBorderHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
        
        JPanel rightBorderHolder = new JPanel();
        rightBorderHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
        
        JPanel leftBorderHolder = new JPanel();
        leftBorderHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
        
        JPanel centerMessageHolder = new JPanel();
        centerMessageHolder.setLayout( new GridLayout( 1, 1, 5, 5 ) );
        
        JLabel centerMessage = new JLabel( message );
        centerMessage.setLayout( new FlowLayout( 2, 5, 5 ) );
        centerMessage.setHorizontalAlignment( SwingConstants.CENTER );
		
        topBorder = new JLabel();
        topBorder.setLayout( new FlowLayout( 1, 5, 5 ) );
        
        bottomBorder = new JLabel();
        bottomBorder.setLayout( new FlowLayout( 1, 5, 5 ) );
        
        rightBorder = new JTextArea();
        rightBorder.setLayout( new FlowLayout( 1, 5, 5 ) );
        
        leftBorder = new JTextArea();
        leftBorder.setLayout( new FlowLayout( 1, 5, 5 ) );
        
        topBorderHolder.add( topBorder );
        bottomBorderHolder.add( bottomBorder );
        rightBorderHolder.add( rightBorder );
        leftBorderHolder.add( leftBorder );
        centerMessageHolder.add( centerMessage );
        
        borderPanel.add( centerMessageHolder, BorderLayout.CENTER );
        
        mainFrame.add( borderPanel );
        
        if( message.length() <= 30) {
        	
        	borderMaker( 40 );
        	mainFrame.setSize( 305, 200 );
        	
        } else if( message.length() > 30 && message.length() < 60 ) {
        	
        	borderMaker( message.length() + 10 );
        	
        } else {
        	
        	borderMaker( 70 );
        }
        
        
        mainFrame.setLocationRelativeTo( null );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setVisible( true );
        
	}
	
	private void borderMaker( int size ) {
		
		// top border
		for( int i = 0; i <= size; i++ ) {
			
			topBorder.setText( topBorder.getText() + "=" );	
		}
		borderPanel.add( topBorder, BorderLayout.NORTH );
		
		// bottom border
		for( int i = 0; i <= size; i++ ) {
			
			bottomBorder.setText( bottomBorder.getText() + "=" );
		}
		borderPanel.add( bottomBorder, BorderLayout.SOUTH );
		
		if( size == 70 ) {
			
			
			
		} else {
			
			// right border
			for( int i = 0; i <= 7; i++ ) {
				
				rightBorder.setText( rightBorder.getText() + "||\n" );
			}
			rightBorder.setText( rightBorder.getText() + "||" );
			borderPanel.add( rightBorder, BorderLayout.EAST );
			
			// left border
			for( int i = 0; i <= 7; i++ ) {
				
				leftBorder.setText( leftBorder.getText() + "||\n" );
			}
			leftBorder.setText( leftBorder.getText() + "||" );
			borderPanel.add( leftBorder, BorderLayout.WEST );
		}
	}
}
