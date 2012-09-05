package qp.main.launch;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import qp.main.gui.frame.Frame;
import qp.main.launch.gui.buttons.LauncherButton;

public class Launcher extends JFrame{
	private static Launcher launcher;
	private static LauncherButton start_button = new LauncherButton("Start"); //0
	private static LauncherButton instructions_button = new LauncherButton("Instructions"); //1
	private static LauncherButton exit_button = new LauncherButton("Exit"); //2
	public Launcher(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(250,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Launcher");
		setVisible(true);
	}
	public static void main(String[] args){
		init();
	}
	public static void init(){
		launcher = new Launcher();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,150,40));
		JLabel label = new JLabel("Quick Test Project");
		panel.add(label);
		panel.add(start_button);
		panel.add(instructions_button);
		panel.add(exit_button);
		launcher.add(panel);
		addActionListeners();
	}
	private static void addActionListeners(){
		start_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Frame.init();
				launcher.dispose();
			}
			
		});
		instructions_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(launcher, "This is a WIP. Use WASD to move and the arrows to shoot.","Instructions",JOptionPane.PLAIN_MESSAGE);
				
			}
		});
		exit_button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
	}
}
