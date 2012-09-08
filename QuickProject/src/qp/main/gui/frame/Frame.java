package qp.main.gui.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import qp.main.entity.Enemy;
import qp.main.entity.Player;
import qp.main.entity.Projectile;
import qp.main.gui.ImageLoader;
import qp.main.listeners.KListener;
import qp.main.threads.FPSUpdater;
import qp.main.threads.PlayerIntersect;
import qp.main.threads.PlayerMovement;
import qp.main.threads.ProjectileLauncher;

public class Frame extends JFrame{
	public static Frame FRAME;
	public static short WIDTH = 800;
	public static short HEIGHT = 600;
	public static int fps;
	public static String bufferedfps = "Please wait...";
	private static Image img;
	private static Graphics dbimg;
	public static boolean debug = false;
	static Random rand = new Random();
	public static boolean lost = false;
	static Thread PLAYERINTERSECT = new Thread(new PlayerIntersect());
	static Thread PROLAUNCHER = new Thread(new ProjectileLauncher());
	static Thread PLAYERMOVEMENT = new Thread(new PlayerMovement());
	static Image PlayerImage = ImageLoader.getImageFrom("awesomecharacter.png");
	static Image BackgroundImage = ImageLoader.getImageFrom("background.png");
	static Image EnemyImage = ImageLoader.getImageFrom("enemy.png");
	static Image LoserImage = ImageLoader.getImageFrom("loser.png");
	static Thread FPSUPDATER = new Thread(new FPSUpdater());
	static Thread FRAMELIMITER = new Thread(){
		public void run(){
			try{
				byte interval = 10; 
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
	};
	static JPanel panel = new JPanel();
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
		clearEntities();
		for(int i = 0; i < 10; i++){
			new Enemy(rand.nextInt(WIDTH/2) + WIDTH/2, rand.nextInt(HEIGHT/2) + HEIGHT/2, 50 , 50);
		}
		if(FRAME==null){
		FRAME = new Frame();}else{
			FRAME.setVisible(true);
		}
		initThreads();
	}
	private static void clearEntities(){
		Enemy.getEnemies().clear();
		for(Projectile p: Projectile.getProjectiles()){
			p.exists = false;
		}
		Projectile.getProjectiles().clear();
	}
	public static void restart(){
		lost = false;
		clearEntities();
		new Player(50,50,50,50);
		for(int i = 0; i < 10; i++){
			new Enemy(rand.nextInt(WIDTH/2) + WIDTH/2, rand.nextInt(HEIGHT/2) + HEIGHT/2, 50 , 50);
		}
	}
	private static void initThreads(){
		try{
		if(!PLAYERMOVEMENT.isAlive()){
			PLAYERMOVEMENT.start();
		}
		if(!PLAYERINTERSECT.isAlive()){
			PLAYERINTERSECT.start();
		}
		if(!PROLAUNCHER.isAlive()){
			PROLAUNCHER.start();
		}
		if(!FRAMELIMITER.isAlive()){
			FRAMELIMITER.start();
		}
		if(!FPSUPDATER.isAlive()){
			FPSUPDATER.start();
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		img = createImage(WIDTH,HEIGHT);
		dbimg = img.getGraphics();
		paintComponent(dbimg);
		g.drawImage(img, 0, 0, this);
		fps++;
	}
	private void paintComponent(Graphics g){
		if(!lost){
			g.drawImage(BackgroundImage,0,0,WIDTH,HEIGHT,this);
			//Player
			if(PlayerImage != null){
			g.drawImage(PlayerImage,(int)Player.x, (int)Player.y, Player.width, Player.height,this);
			}else{
				g.fillRect((int)Player.x, (int)Player.y, Player.width, Player.height);
			}
			//Enemies
			try{
			for(Enemy e: (ArrayList<Enemy>) Enemy.getEnemies().clone()){
				g.drawImage(EnemyImage,(int)e.x, (int)e.y, e.width, e.height,this);
			}
			}catch(Exception e){
				if(!(e instanceof NullPointerException))
				e.printStackTrace();
			}
			//Projectiles
			for(Projectile p: (ArrayList<Projectile>) Projectile.getProjectiles().clone()){
				try{
					if(p!=null){
					g.setColor(Color.YELLOW);
					g.fillRect((int)p.x, (int)p.y, p.width, p.height);
					g.setColor(Color.BLACK);
					g.drawRect((int)p.x, (int)p.y, p.width, p.height);
					}
				}catch(Exception e){
					if (!(e instanceof ConcurrentModificationException))
					e.printStackTrace();
				}
			}
		}else{
		g.drawImage(LoserImage,0,0,WIDTH,HEIGHT,this);
		}
		//Debug
		if(debug){
		g.setColor(Color.BLACK);
		g.drawString("FPS: " + bufferedfps + " Projectiles: " + Projectile.getProjectiles().size() + " Enemies: " + Enemy.getEnemies().size() , 10, HEIGHT-10);
		}
	}
}
