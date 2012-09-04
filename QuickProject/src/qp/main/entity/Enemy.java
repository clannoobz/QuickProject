package qp.main.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import qp.main.threads.EnemyMovement;

public class Enemy extends Rectangle{
	public double x,y;
	public int width,height;
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public Enemy(double x, double y, int w, int h){
		this.x = x;
		this.y = y;
		this.height = h;
		this.width = w;
		enemies.add(this);
		new Thread(new EnemyMovement(this)).start();
	}
	public static ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public boolean intersects(double x2,double y2,double w2,double h2){
		if( y2 + h2 < y || y + height < y2 ||  x2 + w2 < x || x + width < x2 )return false;  
		else return true;  
	}
	public void move(double dx, double dy){
		x += dx;
		y += dy;
	}
}
