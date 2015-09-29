package com.example.museocappellani;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import com.example.museocappellani.Activity.MapViewActivity;

public class DescViewActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_desc_view);
		
		 // Get the message from the intent
	    Intent intent = getIntent();
	    Bundle extras = intent.getExtras();
	    String loadpage = extras.getString("LOADPAGE");
	    
	    // Localize the page to load
	    String langpage = "";
	    if (loadpage.charAt(loadpage.length()-1)=='_') {
	    	langpage = Splash_screen.langLocale;
	    }
	    
		WebView myWebView = (WebView) findViewById(R.id.webDescView);
		
		//enable javascript
		myWebView.getSettings().setJavaScriptEnabled(true);
		
		// Set Transparent Background
		//myWebView.setBackgroundColor(0xBBFFFFFF);
		
		// Load html page
		myWebView.loadUrl("file:///android_asset/www/"+loadpage+langpage+".html");  
		//myWebView.loadUrl("http://www.google.it");
	
	}
	
	
	
	/* NAV BOTTOM*/
	public void btn_nav_home_click(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(nextScreen);
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
