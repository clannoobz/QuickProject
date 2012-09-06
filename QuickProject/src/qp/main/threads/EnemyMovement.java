package qp.main.threads;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import qp.main.entity.Enemy;
import qp.main.entity.Player;
import qp.main.entity.Projectile;
import qp.main.gui.frame.Frame;

public class EnemyMovement implements Runnable{
	Enemy e;
	public EnemyMovement(Enemy e){
		this.e = e;
	}
	Random rand = new Random();
	double speed = rand.nextDouble();
	public void run(){
		try{
			while(!Frame.lost && e.exists){
				if(Player.y < e.y){
					e.move(0.0, -speed);
				}if(Player.x < e.x){
					e.move(-speed, 0.0);
				}if(Player.y > e.y){
					e.move(0.0, speed);
				}if(Player.x > e.x){
					e.move(speed, 0.0);
				}
				try{
				if(!Projectile.getProjectiles().isEmpty()){
					for(Projectile p: (ArrayList<Projectile>) Projectile.getProjectiles().clone()){
						if(e.intersects(p.x,p.y,p.width,p.height)){
							e.remove();
							p.remove();
						}
					}
				}
				}catch(Exception e){
					if(!(e instanceof NullPointerException || e instanceof NoSuchElementException))
					e.printStackTrace();
				}
				Thread.sleep(2);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
