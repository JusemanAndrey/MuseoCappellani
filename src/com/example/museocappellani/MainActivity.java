package com.example.museocappellani;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.museocappellani.Activity.MapViewActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void btn_infomuseo_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), DescViewActivity.class);
		nextScreen.putExtra("LOADPAGE", "museo_it");
		startActivity(nextScreen);
	}
	
	public void btn_info_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), DescViewActivity.class);
		nextScreen.putExtra("LOADPAGE", "info_");
		startActivity(nextScreen);
	}
	
	public void btn_akrai_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), DescViewActivity.class);
		nextScreen.putExtra("LOADPAGE", "akrai_");
		startActivity(nextScreen);
	}
	
	public void btn_judica_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), DescViewActivity.class);
		nextScreen.putExtra("LOADPAGE", "judica_");
		startActivity(nextScreen);
	}
	
	public void btn_cardlist_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), CardListActivity.class);
		startActivity(nextScreen);
	}
	
	public void btn_palazzolo_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), DescViewActivity.class);
		nextScreen.putExtra("LOADPAGE", "palazzolo_it");
		startActivity(nextScreen);
	}
	
	public void btn_language_click(View view) {
	    // Do something in response to button
		Intent nextScreen = new Intent(getApplicationContext(), Splash_screen.class);
		startActivity(nextScreen);
	}
	
	
	
	/* NAV BOTTOM*/
	public void btn_nav_home_click(View view) {
		//Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		//startActivity(nextScreen);
	}
	public void btn_nav_cards_click(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), CardListActivity.class);
		startActivity(nextScreen);
	}
	public void btn_nav_info_click(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), MapViewActivity.class);
        startActivity(nextScreen);
	}
	public void btn_nav_back_click(View view) {
		finish();
	}
}
