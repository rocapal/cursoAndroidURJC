package es.practicas.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import es.practicas.fragments.ControlFragment.IControlListener;

public class MainFragments extends FragmentActivity implements IControlListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_fragments);

		final Button btnShow = (Button) findViewById(R.id.btn_showFrag);
		btnShow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showFragment();
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_fragments, menu);
		return true;
	}

	private void showFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("editor");
		if (editor == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.container, new ControlFragment(), "editor");
			ft.commit();
		}
	}

	private void hideFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment editor = fm.findFragmentByTag("editor");
		if (editor != null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.remove(editor);
			ft.commit();
		}

	}

	@Override
	public void clickOk(String text) {
		Toast.makeText(this, text,Toast.LENGTH_SHORT).show();

	}

	@Override
	public void clickCancel(String text) {
		hideFragment();

	}

	@Override
	public void clickTest() {
		// TODO Auto-generated method stub
		
	}
}
