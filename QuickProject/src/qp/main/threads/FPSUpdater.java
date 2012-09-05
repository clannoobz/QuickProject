package qp.main.threads;

import qp.main.gui.frame.Frame;

public class FPSUpdater implements Runnable{
	public void run(){
		try{
			while(true){
				Thread.sleep(1000);
				Frame.bufferedfps = String.valueOf(Frame.fps);
				Frame.fps = 0;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
