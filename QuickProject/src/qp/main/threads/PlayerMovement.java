package qp.main.threads;

import java.awt.event.KeyEvent;

import qp.main.entity.Player;
import qp.main.gui.frame.Frame;
import qp.main.listeners.KListener;

public class PlayerMovement implements Runnable{
	public void run(){
		double speed = 1.5;
		try{
			while(!Frame.lost){
				if(KListener.keys[KeyEvent.VK_W]){
					Player.move(0.0, -speed);
				}if(KListener.keys[KeyEvent.VK_A]){
					Player.move(-speed, 0.0);
				}if(KListener.keys[KeyEvent.VK_S]){
					Player.move(0.0, speed);
				}if(KListener.keys[KeyEvent.VK_D]){
					Player.move(speed, 0.0);
				}
				Thread.sleep(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
