package com.kimogi.drawview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DrawView drawView = (DrawView) findViewById(R.id.draw_view);
		//draw something by your finger then get bitmap
		Bitmap bitmap = drawView.getBitmap();
		//clear bitmap
		drawView.clear();
	}
}
