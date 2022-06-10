package main.java.mapBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ButtonListener implements ActionListener {
	private JLabel mLabel;
	private String mText;
	public ButtonListener(JLabel pLabel, String pText) {
		mLabel = pLabel;
		mText = pText;
	}
	public void actionPerformed(ActionEvent pEvent) {
		mLabel.setText(mText);
		
	}
}
