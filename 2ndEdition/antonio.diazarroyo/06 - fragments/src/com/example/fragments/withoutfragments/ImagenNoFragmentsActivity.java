package com.example.fragments.withoutfragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragments.R;

public class ImagenNoFragmentsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.no_fragments_img_activity);
		Intent intent = getIntent();
		int idImage = intent.getIntExtra(ListNoFragmentsActivity.ID_IMAGE, -1);
		TextView textView = (TextView)findViewById(R.id.text_no_fragments);
		textView.setText("Imagen seleccionada: " + ListNoFragmentsActivity.IMAGES_TXT[idImage]);
		ImageView imageView = (ImageView)findViewById(R.id.image_no_fragments);
		imageView.setImageDrawable(this.getResources().getDrawable(ListNoFragmentsActivity.IMAGES_SRC[idImage]));
		//imageView.setId();
	}	
}
