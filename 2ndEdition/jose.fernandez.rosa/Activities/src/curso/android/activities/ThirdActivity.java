package curso.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import curso.android.activities.Global.Messages;

public class ThirdActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_second);
		
		Intent inputIntent  = getIntent();
		if (inputIntent != null)
		{
			String name = inputIntent.getStringExtra(Messages.MSG.toString());
			int value = inputIntent.getIntExtra(Messages.INT.toString(), -1);
			
			TextView txtView = (TextView) findViewById(R.id.txtview_getted);
			txtView.setText("name:" +  name + "  value:" +value);
		}
		
		
		Intent returnIntent = new Intent();
		returnIntent.putExtra(Messages.INT.toString(), 30);
		setResult(RESULT_OK,returnIntent);

		Button btnCloseMe = (Button) findViewById(R.id.btn_close_me);
		btnCloseMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Log.d("closeme","invocando a finishActivity");
					finishActivity(Global.ACT_THIRD);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();					
				}
			}
		});

		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("onDestroy3","pasa");
		
	}

}
