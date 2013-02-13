package bartbender.homeunix.pac_man;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

public class Pacman extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_pacman, menu);
        return true;
    }
    
}
