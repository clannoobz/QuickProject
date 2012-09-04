package qp.main.gui.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import qp.main.entity.Enemy;
import qp.main.entity.Player;
import qp.main.listeners.KListener;
import qp.main.threads.PlayerIntersect;
import qp.main.threads.PlayerMovement;

public class Frame extends JFrame{
	static Frame FRAME;
	short WIDTH = 800, HEIGHT = 600;
	private static Image img;
	private static Graphics dbimg;
	static Random rand = new Random();
	public static boolean lost = false;
	String TITLE = "Woo! Random!";
	public Frame(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setLocationRelativeTo(null);
		setTitle(TITLE);
		setResizable(false);
		setVisible(true);
		addKeyListener(new KListener());
	}
	public static void init(){
		new Player(50,50,50,50);
		for(int i = 0; i < 10; i++){
			new Enemy(rand.nextInt(400) + 400, rand.nextInt(300) + 300, 50 , 50);
		}
		FRAME = new Frame();
		new Thread(new PlayerMovement()).start();
		new Thread(new PlayerIntersect()).start();
		new Thread(){
			public void run(){
				try{
					byte interval = 15; 
					long lastDraw=0;
					while(true){
						while(System.currentTimeMillis()-lastDraw<interval)
							   Thread.sleep(1);
							 
							 lastDraw=System.currentTimeMillis();
							 FRAME.repaint();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
	}
	public static void restart(){
		lost = false;
		new Thread(new PlayerMovement()).start();
		new Thread(new PlayerIntersect()).start();
		Enemy.enemies.clear();
		new Player(50,50,50,50);
		for(int i = 0; i < 10; i++){
			new Enemy(rand.nextInt(400) + 400, rand.nextInt(300) + 300, 50 , 50);
		}
	}
	public void paint(Graphics g){
		img = createImage(WIDTH,HEIGHT);
		dbimg = img.getGraphics();
		paintComponent(dbimg);
		g.drawImage(img, 0, 0, this);
	}
	private void paintComponent(Graphics g){
		if(!lost){
			//Player
			g.setColor(Color.GREEN);
			g.fillRect((int)Player.x, (int)Player.y, Player.width, Player.height);
			g.setColor(Color.BLACK);
			g.drawRect((int)Player.x, (int)Player.y, Player.width, Player.height);
			//Enemies
			for(Enemy e: (ArrayList<Enemy>) Enemy.getEnemies().clone()){
				g.setColor(Color.RED);
				g.fillRect((int)e.x, (int)e.y, e.width, e.height);
				g.setColor(Color.BLACK);
				g.drawRect((int)e.x, (int)e.y, e.width, e.height);
		}
		}else{
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.WHITE);
			g.drawString("LOSER...Press R to restart", 50, 50);
		}
	}
}
