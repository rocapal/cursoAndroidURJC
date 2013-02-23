package bartbender.homeunix.pac_man;

import android.util.Log;
import android.view.MotionEvent;

public class Pacman extends Sprite implements Animable {

	
	private int startX,startY;
	int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 2, 0 };
	
	public Pacman(GameView gameView, int startX, int startY){		
		super(gameView, R.drawable.pacman, 4,3);
		this.startX=startX;
		this.startY=startY;
		resetPosition();
		
	}

	@Override
	public void updatePosition() {
		if (x >= view.getWidth() - width /2- xSpeed || x - width/2+ xSpeed <= 0) {
			xSpeed = 0;
		}
		x = x + xSpeed;
		
		if (y >= view.getHeight() - height /2 - ySpeed || y - height/2 + ySpeed <= 0) {
			ySpeed = 0;
		}
		y = y + ySpeed;
		
		currentFrame = ++currentFrame % BMP_COLUMNS;
	}

	@Override
	public void resetPosition() {

		this.x = startX;
		this.y = startY;
		this.setDirection(eDirection.RIGHT);
		
	}

	@Override
	public void setPosition(int x, int y) {
	
		this.x = x;
		this.y = y;
	}

	public void setDirection(eDirection dir){
		setCurrentModel(dir.toInt());
		switch (dir){
		case RIGHT:
			this.xSpeed=10;
			this.ySpeed=0;
			break;
		case LEFT:
			this.xSpeed=-10;
			this.ySpeed=0;
			break;
		case UP:
			this.xSpeed=0;
			this.ySpeed=-10;
			break;
		case DOWN:
			this.xSpeed=0;
			this.ySpeed=10;
			break;
		}
		 
	}


	 

    private int calculateDirection(int x2,int y2) {
          double dirDouble = (Math.atan2(x2-x, y2-y) / (Math.PI / 2) + 2);
          int direction = (int) Math.round(dirDouble) % BMP_ROWS;
          Log.d("direccion","dirección:" + direction );
          return DIRECTION_TO_ANIMATION_MAP[direction];
          
    }
    
	public boolean onTouchEvent(MotionEvent event) {
		this.setDirection(eDirection.values()[calculateDirection((int) event.getX(),(int) event.getY())]);
		return true;
	}
}
