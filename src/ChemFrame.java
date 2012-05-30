import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChemFrame {

	public static void main(String s[]) {
		JFrame frame = new JFrame("ChemFrame");
		// Add a window listner for close button
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// This is an empty content area in the frame
		Global.work_string = Global.input_string;
		Global.input_type = 1;
		frame.getContentPane().setLayout(null);
		JLabel substance_label = new JLabel("gggg");
		substance_label.setLocation(30, 10);
		frame.getContentPane().add(substance_label);
		ChemPanel chempanel = new ChemPanel();
		chempanel.init();
		System.out.println("setloc");
		chempanel.setLocation(1, 1);
		System.out.println("setprefsize");
		frame.setPreferredSize(new Dimension(500, 550));
		System.out.println("add panel");
		frame.getContentPane().add(chempanel);
		System.out.println("pack");
		frame.pack();
		frame.setVisible(true);
		//frame.repaint();

	}
}
