package bartbender.homeunix.pac_man;

import java.util.ArrayList;
import java.util.List;

import bartbender.homeunix.pac_man.Phantom.PhantomStyle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
     private SurfaceHolder holder;
     private GameLoopThread gameLoopThread;
     //private Canvas bufferedCanvas;
     //private Bitmap bufferedBitmap;
   

     private long lastClick;
     private List<Sprite> sprites = new ArrayList<Sprite>();
     
     
     public GameView(Context context) {
           super(context);
           
           gameLoopThread = new GameLoopThread(this);
           
           holder = getHolder();
           holder.addCallback(new SurfaceHolder.Callback() {

                  @Override
                  public void surfaceDestroyed(SurfaceHolder holder) {
                         boolean retry = true;
                         gameLoopThread.setRunning(false);
                         while (retry) {
                                try {
                                      gameLoopThread.join();
                                      retry = false;
                                } catch (InterruptedException e) {
                                }
                         }
                  }

                  @Override
                  public void surfaceCreated(SurfaceHolder holder) {
                         gameLoopThread.setRunning(true);
                         gameLoopThread.start();
                  }

                  @Override
                  public void surfaceChanged(SurfaceHolder holder, int format,
                                int width, int height) {
                  }
           });
          InitializeGameItems();
          
     }

     private void InitializeGameItems() {
    	 //bufferedCanvas = new Canvas();
    	 
    	 sprites.add(new Phantom(this, PhantomStyle.BLUE,1,1));
    	 sprites.add(new Phantom(this, PhantomStyle.RED,33,1));
    	 sprites.add(new Phantom(this, PhantomStyle.YELLOW,66,1));
    	 sprites.add(new Phantom(this, PhantomStyle.GREEN,94,1));
    	 
     }
     
     protected void updatePositions() {
    	 for (Sprite s : sprites)
    	 {
    		 if (s instanceof Animable)
    		 {
    			 ((Animable) s).updatePosition();
    		 }
    	 }
    	 
     }
     
     
     
     @Override
     protected void onDraw(Canvas canvas) {
           canvas.drawColor(Color.BLACK);
           for (Sprite p : sprites) p.onDraw(canvas);   
     }
     
     
     @Override
     public boolean onTouchEvent(MotionEvent event) {
    	 //Gestionamos que no se gestione mas de un click cada 300 milisegundos   	 
    	 if (System.currentTimeMillis() - lastClick > 300) {
             lastClick = System.currentTimeMillis();
             synchronized (getHolder()) {
                
             }
      }
      return true;
    }
}
