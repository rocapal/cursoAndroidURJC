package es.bmuma.tacografo;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TimesActivity extends Activity {

	private static boolean state;
	private static boolean driving;
	private static int type;;
	//static SharedPreferences mSharedPreferences;
	//Editor editor = mSharedPreferences.edit();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gestion_times);
		
        ImageButton btStartStop = (ImageButton) this.findViewById(R.id.startstop);
        ImageButton btDriving = (ImageButton) this.findViewById(R.id.driving);
        ImageButton btDispose= (ImageButton) this.findViewById(R.id.dispose);
        ImageButton btRelax= (ImageButton) this.findViewById(R.id.relax);
        ImageButton btWork = (ImageButton) this.findViewById(R.id.work);
        
        restaurar(); 
                

        btStartStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!state){
					Toast.makeText(getApplicationContext(), getString(R.string.inicio), Toast.LENGTH_SHORT).show();
					mostrar();
					startService(new Intent(getApplicationContext(), TimeService.class));
				}else{
					driving=false;
					Toast.makeText(getApplicationContext(), getString(R.string.fin), Toast.LENGTH_SHORT).show();
					stopService(new Intent(getApplicationContext(), TimeService.class));
				}
				state = !state;
			}

		});
        
        btDriving.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(state && (!driving)){
					type=1;
					Toast.makeText(getApplicationContext(), getString(R.string.drivingTime), 
							Toast.LENGTH_SHORT).show();
					driving = true;
					TimeService.setType(type);
						
				}else{
					driving = false;
					if (!state){
						Toast.makeText(getApplicationContext(), getString(R.string.initDay), 
								Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getApplicationContext(), getString(R.string.drivingYet), 
								Toast.LENGTH_SHORT).show();
					}
			        
				}
				
			}
		});
        
        btDispose.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(state && driving && (type!=2)){
					type = 2;
					driving = false;

					Toast.makeText(getApplicationContext(), getString(R.string.disposeTime), 
							Toast.LENGTH_SHORT).show();
					TimeService.setType(type);
						
				}else{
					if(!state){
						Toast.makeText(getApplicationContext(), getString(R.string.initDay), 
							Toast.LENGTH_SHORT).show();
					}else if(!driving){
						Toast.makeText(getApplicationContext(), getString(R.string.noDriving), 
								Toast.LENGTH_SHORT).show();
					}else if (type ==2){
						Toast.makeText(getApplicationContext(), getString(R.string.disposeYet), 
								Toast.LENGTH_SHORT).show();
					}
			        
				}
				
			}
		});
        
        btRelax.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(state&& (type!=3)){
					type = 3;
					driving = false;

					Toast.makeText(getApplicationContext(), getString(R.string.relaxTime), 
							Toast.LENGTH_SHORT).show();
					TimeService.setType(3);
						
				}else{
					if(!state){
						Toast.makeText(getApplicationContext(), getString(R.string.initDay), 
							Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getApplicationContext(), getString(R.string.relaxYet), 
								Toast.LENGTH_SHORT).show();
					}
			        
				}
				
			}
		});
        
        btWork.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(state&& (type!=4)){
					type = 4;
					driving = false;

					Toast.makeText(getApplicationContext(), getString(R.string.workTime), 
							Toast.LENGTH_SHORT).show();
					TimeService.setType(4);
						
				}else{
					if(!state){
						Toast.makeText(getApplicationContext(), getString(R.string.initDay), 
							Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getApplicationContext(), getString(R.string.relaxYet), 
								Toast.LENGTH_SHORT).show();
					}
			        
				}
				
			}
		});
        
        if(state)
        	mostrar();
        
	}
	
	
	
	@Override
	protected void onStop() {
		guardar();
		super.onStop();
	}
	
	
	@Override
	protected void onPause() {
		guardar();
		super.onPause();
	}

	
	@Override
	protected void onDestroy() {
		guardar();
		super.onDestroy();
	}
	
	@Override
	protected void onRestart() {
		restaurar();
		super.onRestart();
	}
	
	@Override
	protected void onResume() {
		restaurar();
		super.onResume();
	}
	
	@Override
	protected void onStart() {
		restaurar();
		super.onStart();
	}
	
	private void restaurar(){
		state = TimeService.getSavedState();
        driving = TimeService.getSavedDriving();
        type = TimeService.getSavedType();      
	}
	
	private void guardar(){
		TimeService.setSavedState(state);
		TimeService.setSavedDriving(driving);
		TimeService.setSavedType(type);
	}
	private void mostrar() {
        final TextView tvTemp = (TextView) this.findViewById(R.id.time);
        final TextView tvName = (TextView) this.findViewById(R.id.name);
        final TextView tvMat = (TextView) this.findViewById(R.id.mostratMat);
		TimeService.regListener(new ITimeService() {
			
			
			@Override
			public void updateTime(String time) {
				tvTemp.setText(time);						
			}

			@Override
			public void updateName() {
				try{
					String nombre = Prefer.loadPreference("name");
					if(nombre==null || nombre.equals(null))
						tvName.setText(" ");
					else
						tvName.setText(getString(R.string.name) + " " + nombre);
				}catch(Exception e){
					tvName.setText(" ");
				}
				
			}

			@Override
			public void updateMat() {
				try{
					String mat = Prefer.loadPreference("mat");
					if(mat==null || mat.equals(null))
						tvMat.setText(" ");
					else
						tvMat.setText(getString(R.string.matirc) + " " + mat);
				}catch(Exception e){
					tvMat.setText(" ");
				}
				
			}


		});

		
	}

}
