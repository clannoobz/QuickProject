package qp.main.entity;

import java.awt.Rectangle;

public class Player extends Rectangle{
	static Player PLAYER;
	public static double x,y;
	public static int width,height;
	public Player(double x, double y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		PLAYER = this;
	}
	public static Player getPlayer(){
		return PLAYER;
	}
	public static void move(double dx, double dy){
		PLAYER.x += dx;
		PLAYER.y += dy;
	}
	public boolean intersects(double x2,double y2,double w2,double h2){
		if( y2 + h2 < y || y + height < y2 ||  x2 + w2 < x || x + width < x2 )return false;  
		else return true;  
	}
}
