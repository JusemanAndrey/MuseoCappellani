package com.example.museocappellani.Activity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.museocappellani.R;
import com.example.museocappellani.Splash_screen;

public class FloorViewActivity extends Activity {
    public final static String FIRST_FLOOR_TAG = "first_floor";
    public final static String SECOND_FLOOR_TAG = "second_floor";
    ImageView floorImageView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor);
        initComponents();
        parseEvents();
    }
    private void initComponents(){
        floorImageView = (ImageView)findViewById(R.id.floorImage);
    }

    private void parseEvents(){
        boolean isFirstFloor = getIntent().getBooleanExtra(FIRST_FLOOR_TAG, false);
        boolean isSecondFloor = getIntent().getBooleanExtra(SECOND_FLOOR_TAG, false);

        if(isFirstFloor){
            setFirstFloorImage();
            return;
        }
        if(isSecondFloor){
            setSecondFloorImage();
            return;
        }
    }

    private void setFirstFloorImage(){
        String filePath = "map0_it.jpg";
       if (Splash_screen.langLocale == "en") { filePath = "map0_en.jpg";}
       if (Splash_screen.langLocale == "fr") { filePath = "map0_fr.jpg";}
        try {
            floorImageView.setImageBitmap(BitmapFactory.decodeStream(this.getAssets().open(filePath)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setSecondFloorImage(){
        String filePath = "map1_it.jpg";
        if (Splash_screen.langLocale == "en") { filePath = "map1_en.jpg";}
        if (Splash_screen.langLocale == "fr") { filePath = "map1_fr.jpg";}
        try {
            floorImageView.setImageBitmap(BitmapFactory.decodeStream(this.getAssets().open(filePath)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btn_floor_close(View view) {
        finish();
    }
}
