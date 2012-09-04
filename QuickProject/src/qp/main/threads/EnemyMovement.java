package qp.main.threads;

import java.awt.event.KeyEvent;
import java.util.Random;

import qp.main.entity.Enemy;
import qp.main.entity.Player;
import qp.main.gui.frame.Frame;
import qp.main.listeners.KListener;

public class EnemyMovement implements Runnable{
	Enemy e;
	public EnemyMovement(Enemy e){
		this.e = e;
	}
	Random rand = new Random();
	double speed = rand.nextDouble();
	public void run(){
		try{
			while(!Frame.lost){
				if(Player.y < e.y){
					e.move(0.0, -speed);
				}if(Player.x < e.x){
					e.move(-speed, 0.0);
				}if(Player.y > e.y){
					e.move(0.0, speed);
				}if(Player.x > e.x){
					e.move(speed, 0.0);
				}
				Thread.sleep(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
