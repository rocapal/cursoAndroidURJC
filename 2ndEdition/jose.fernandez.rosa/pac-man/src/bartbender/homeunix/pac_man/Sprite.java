package bartbender.homeunix.pac_man;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
	protected int BMP_ROWS = 4;
	protected int BMP_COLUMNS = 3;
	protected int x = 1;
	protected int y = 1;
	protected int xSpeed = 0;
	protected int ySpeed = 0;
	protected int currentFrame = 0;
	protected int currentModel = 0;
	protected int width;
	protected int height;	
	protected GameView view;
	
	private Bitmap bmp;
	private Rect rSrc;
	private Rect rDst;
	
	public enum eDirection {
		RIGHT,LEFT,DOWN,UP;
		
		public int toInt(){
			return this.ordinal();
		}
		
		
	}
	
	public Sprite(GameView gameView, int drawableResource,int rows,int columns) {
		this.view = gameView;
		this.bmp = BitmapFactory.decodeResource(view.getResources(),drawableResource );;
		this.BMP_ROWS=rows;
		this.BMP_COLUMNS=columns;
		this.width = bmp.getWidth() / BMP_COLUMNS;
		this.height = bmp.getHeight() / BMP_ROWS;
		rSrc = new Rect(0, 0, 0, 0);
		rDst = new Rect(0, 0, 0, 0);
	}
	
	protected int getCurrentFrame() {
		return currentFrame;
	}
	protected void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}
	protected int getCurrentModel() {
		return currentModel;
	}
	protected void setCurrentModel(int currentModel) {
		this.currentModel = currentModel;
	}	

	protected void updateGraphics()	{
		int srcX = getCurrentFrame() * width;
		int srcY = getCurrentModel() * height;
		
		rSrc.set(srcX, srcY, srcX + width, srcY + height);
		rDst.set(x-width/2, y-height/2,x + width/2, y + height/2);
	}
	
	public void onDraw(Canvas canvas) {
		updateGraphics();
		canvas.drawBitmap(bmp, rSrc, rDst, null);
	}

	
}