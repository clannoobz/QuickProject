package qp.main.threads;

import java.awt.event.KeyEvent;

import qp.main.entity.Player;
import qp.main.gui.frame.Frame;
import qp.main.listeners.KListener;

public class PlayerMovement implements Runnable{
	public void run(){
		double speed = 1.5;
		try{
			while(true){
				if(KListener.keys[KeyEvent.VK_W]){
					if(Player.y - speed > 0)
					Player.move(0.0, -speed);
				}if(KListener.keys[KeyEvent.VK_A]){
					if(Player.x - speed > 0)
					Player.move(-speed, 0.0);
				}if(KListener.keys[KeyEvent.VK_S]){
					if(Player.y + Player.height + speed < Frame.HEIGHT)
					Player.move(0.0, speed);
				}if(KListener.keys[KeyEvent.VK_D]){
					if(Player.x + Player.width + speed < Frame.WIDTH)
					Player.move(speed, 0.0);
				}
				Thread.sleep(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
