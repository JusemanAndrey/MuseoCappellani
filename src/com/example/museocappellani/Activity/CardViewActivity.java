package com.example.museocappellani.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.museocappellani.Adapter.AppDataBaseAdapter;
import com.example.museocappellani.CardListActivity;
import com.example.museocappellani.MainActivity;
import com.example.museocappellani.Model.Opera;
import com.example.museocappellani.Model.Room;
import com.example.museocappellani.R;
import com.example.museocappellani.Splash_screen;

public class CardViewActivity extends Activity {
    public final static String ITEM_TAG = "item";
    private TextView cardViewTitle;
    private TextView cardViewCode;
    private ImageView cardViewImage;
    private TextView cardViewDescr;
    private AppDataBaseAdapter dataBaseAdapter;
    Button roomClickButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        initComponents();
        setInfo();
    }
    private void initComponents(){
        cardViewImage = (ImageView)findViewById(R.id.cardViewImage);
        cardViewTitle = (TextView)findViewById(R.id.cardViewTitleText);
        cardViewCode = (TextView)findViewById(R.id.cardViewCodeText);
        cardViewDescr = (TextView)findViewById(R.id.cardViewDescr);
        roomClickButton = (Button)findViewById(R.id.btn_room_click);
        dataBaseAdapter = new AppDataBaseAdapter(this);
    }


    private void setInfo(){

        Opera item = (Opera)getIntent().getParcelableExtra(ITEM_TAG);
        //cardViewTitle.setText(item.title_it);
        if (Splash_screen.langLocale == "it") {cardViewTitle.setText(item.title_it);}
        if (Splash_screen.langLocale == "en") {cardViewTitle.setText(item.title_en);}
        if (Splash_screen.langLocale == "fr") {cardViewTitle.setText(item.title_fr);}
        cardViewCode.setText(item.searchCode);
        //cardViewDescr.setText(item.descr_it);
        if (Splash_screen.langLocale == "it") {cardViewDescr.setText(item.descr_it);}
        if (Splash_screen.langLocale == "en") {cardViewDescr.setText(item.descr_en);}
        if (Splash_screen.langLocale == "fr") {cardViewDescr.setText(item.descr_fr);}

        try{
            String filePath = "opera_"+Integer.toString(item.getId())+".jpg";
            cardViewImage.setImageBitmap(BitmapFactory.decodeStream(this.getAssets().open(filePath)));
        }catch (Exception e){
            e.printStackTrace();
        }
        Room room = dataBaseAdapter.getStore().findRoomById(item.getRoomId());
        if(room != null){
            //roomClickButton.setText(room.title_it);
            if (Splash_screen.langLocale == "it") {roomClickButton.setText(room.title_it);}
            if (Splash_screen.langLocale == "en") {roomClickButton.setText(room.title_en);}
            if (Splash_screen.langLocale == "fr") {roomClickButton.setText(room.title_fr);}
        }
    }

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

    public void btn_nav_prev_click(View view){
        Opera item = getIntent().getParcelableExtra(ITEM_TAG);
        Opera nextItem = dataBaseAdapter.getStore().findOperaAtTheList(item,false);
        Intent nextScreen = new Intent(this, CardViewActivity.class);
        nextScreen.putExtra(CardViewActivity.ITEM_TAG,nextItem);
        startActivity(nextScreen);
        finish();
    }
    public void btn_nav_to_room_click(View view){
        Opera item = getIntent().getParcelableExtra(ITEM_TAG);
        Room room = dataBaseAdapter.getStore().findRoomById(item.getRoomId());
        if(room != null){
            Log.d("", room.getId() + "");
        }
        Intent nextScreen = new Intent(this, RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG, item.getRoomId());
        startActivity(nextScreen);
    }
    public void btn_nav_next_click(View view){
        Opera item = getIntent().getParcelableExtra(ITEM_TAG);
        Opera nextItem = dataBaseAdapter.getStore().findOperaAtTheList(item,true);
        Intent nextScreen = new Intent(this, CardViewActivity.class);
        nextScreen.putExtra(CardViewActivity.ITEM_TAG,nextItem);
        startActivity(nextScreen);
        finish();
    }
}
