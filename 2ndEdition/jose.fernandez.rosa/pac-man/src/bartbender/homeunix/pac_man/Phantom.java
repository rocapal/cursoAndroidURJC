package bartbender.homeunix.pac_man;

import java.util.Random;

import android.util.Log;

public class Phantom extends Sprite implements Animable {
	static final int ROWS = 4;
	static final int COLS = 3;

	public Phantom(GameView gameView, PhantomStyle style, int xStart, int yStart) {
		super(gameView, R.drawable.fantasma, 4, 3);
		setCurrentModel(style.toInt());
		this.x=xStart;
		this.y=yStart;
		Random rnd = new Random();
		if (rnd.nextInt(2) < 1)
			xSpeed = 5;
		else
			xSpeed = -5;
		if (rnd.nextInt(2) < 1)
			ySpeed = 5;
		else
			ySpeed = -5;
		Log.d("Phantom " + style.toString() + "Speeds", "ySpeed:" + ySpeed + " xSpeed:" + xSpeed);

	}

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

	public void checkCollision() {

	}

	@Override
	public void updatePosition() {
		// TODO Auto-generated method stub
		checkCollision();
		if (x >= view.getWidth() - width - xSpeed || x + xSpeed <= 0) {
			xSpeed = -xSpeed;
		}
		x = x + xSpeed;
		if (y >= view.getHeight() - height - ySpeed || y + ySpeed <= 0) {
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
