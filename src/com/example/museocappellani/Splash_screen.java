package com.example.museocappellani;

import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;



public class Splash_screen extends Activity {
	
	public static String langLocale = "";
	
	Locale myLocale;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

	}
	
	public void btn_it_click(View view) {
	    // Do something in response to button
		setLocale("it");
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(nextScreen);
	}
	
	public void btn_en_click(View view) {
	    // Do something in response to button
		setLocale("en");
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(nextScreen);
	}
	
	public void btn_fr_click(View view) {
	    // Do something in response to button
		setLocale("fr");
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(nextScreen);
	}
	
	public void setLocale(String lang) { 
		
		
		myLocale = new Locale(lang); 
		Resources res = getResources(); 
		DisplayMetrics dm = res.getDisplayMetrics(); 
		Configuration conf = res.getConfiguration(); 
		conf.locale = myLocale; 
		res.updateConfiguration(conf, dm); 
		//Intent refresh = new Intent(this, Splash_screen.class); 
		//startActivity(refresh);
		
		Splash_screen.langLocale = lang;
		
		Context context = getApplicationContext();
		CharSequence text = getString(R.string.language_sel);
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
}
