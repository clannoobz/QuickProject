package qp.main.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import qp.main.gui.frame.Frame;
import qp.main.launch.Launcher;

public class KListener implements KeyListener{
	public static boolean[] keys = new boolean[65536];
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
		if(e.getKeyCode() == KeyEvent.VK_R){
			Frame.restart();
		}else
		if(e.getKeyCode() == KeyEvent.VK_F1){
			if(!Frame.debug){
				Frame.debug = true;
			}else{
				Frame.debug = false;
			}
		}if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			Frame.FRAME.dispose();
			Launcher.init();
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
