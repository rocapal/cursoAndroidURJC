package bartbender.homeunix.pac_man;

import java.util.Random;

import android.util.Log;

public class Phantom extends Sprite implements Animable {
	static final int ROWS = 4;
	static final int COLS = 3;

	public enum PhantomStyle {
		RED(0), BLUE(1), GREEN(2), YELLOW(3);

		private int value;

		private PhantomStyle(int c) {
			value = c;
		}

		public int toInt() {
			return value;
		}

	}
	
	public Phantom(GameView gameView, int style, int startX, int startY) {
		super(gameView, R.drawable.fantasma, 4, 3);
		InitializePhantom(gameView,style,startX,startY);
	}
	
	public Phantom(GameView gameView, PhantomStyle style, int startX, int startY) {
		super(gameView, R.drawable.fantasma, 4, 3);
		InitializePhantom(gameView,style.toInt(),startX,startY);
	}

	private void InitializePhantom(GameView gameView, int style, int startX, int startY) {		
		setCurrentModel(style);
		this.x=startX;
		this.y=startY;
		Random rnd = new Random();
		setDirection(eDirection.values()[rnd.nextInt(4)]);
		
		Log.d("Phantom " + style + "Speeds", "ySpeed:" + ySpeed + " xSpeed:" + xSpeed);
	}
	

	public void setDirection(eDirection dir){
		switch (dir){
		case RIGHT:
			xSpeed = 10;
			ySpeed = 0;
			break;
		case LEFT:
			xSpeed = -10;
			ySpeed = 0;
			break;
		case DOWN:
			xSpeed = 0;
			ySpeed = 10;
			break;
		case UP:
			xSpeed = 0;
			ySpeed = -10;
			break;		
		}
		
	}
	

	public void checkCollision() {

	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub
		checkCollision();
		if (x >= view.getWidth() - width /2- xSpeed || x - width/2+ xSpeed <= 0) {
			xSpeed = -xSpeed;
		}
		x = x + xSpeed;
		
		if (y >= view.getHeight() - height /2 - ySpeed || y - height/2 + ySpeed <= 0) {
			ySpeed = -ySpeed;
		}
		y = y + ySpeed;
		
		currentFrame = ++currentFrame % BMP_COLUMNS;
	}

	@Override
	public void resetPosition() {
		// TODO Auto-generated method stub
		x = 0;
		y = 0;

	}

	@Override
	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;

	}

}
