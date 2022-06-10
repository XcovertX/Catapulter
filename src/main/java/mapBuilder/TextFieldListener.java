package main.java.mapBuilder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class TextFieldListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent pEvent) {
		JTextField tf = (JTextField)pEvent.getSource();
		tf.setText("*******");
		System.out.println(pEvent.getActionCommand());
	}
}
