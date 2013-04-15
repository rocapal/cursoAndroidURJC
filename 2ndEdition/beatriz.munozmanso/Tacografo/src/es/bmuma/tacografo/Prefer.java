package es.bmuma.tacografo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Prefer extends Activity{
	
	EditText etName, etMat;
	static SharedPreferences mSharedPreferences;
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preferenc);

		mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		etName = (EditText) this.findViewById(R.id.etName);
		etMat = (EditText) this.findViewById(R.id.etMat);

		Button btSave = (Button) this.findViewById(R.id.btSave);
		Button btDgt = (Button) this.findViewById(R.id.dgt);
		btSave.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {	
				if (etName.getText().toString().length() > 0 &&
						etMat.getText().toString().length() > 0) {
					savePreference("name", etName.getText().toString());
					savePreference("mat", etMat.getText().toString());
					Toast.makeText(getApplicationContext(), getString(R.string.save), Toast.LENGTH_SHORT).show();
				}
				else {
					Toast.makeText(getApplicationContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btDgt.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {	
				startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.fomento.es/MFOM/LANG_CASTELLANO/DIRECCIONES_GENERALES/TRANSPORTE_POR_CARRETERA/IGT/")));
				
			}
		});
	}
	 
	 
		public static void savePreference (String key, String value)
		{
			Editor editor = mSharedPreferences.edit();
			editor.putString(key,value);
			editor.commit();
			
		}

		public static String loadPreference(String key)
		{
			return mSharedPreferences.getString(key,null);
		}
}
