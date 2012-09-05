package qp.main.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import qp.main.gui.frame.Frame;

public class KListener implements KeyListener{
	public static boolean[] keys = new boolean[65536];
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
		if(e.getKeyCode() == KeyEvent.VK_R){
			Frame.restart();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
