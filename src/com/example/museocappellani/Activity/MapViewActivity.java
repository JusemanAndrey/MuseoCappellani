package com.example.museocappellani.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.museocappellani.Adapter.AppDataBaseAdapter;
import com.example.museocappellani.CardListActivity;
import com.example.museocappellani.DescViewActivity;
import com.example.museocappellani.MainActivity;
import com.example.museocappellani.Model.Room;
import com.example.museocappellani.R;
import com.example.museocappellani.Splash_screen;

public class MapViewActivity extends Activity{
    Button buttonRoom1;
    Button buttonRoom2;
    Button buttonRoom3;
    Button buttonRoom4;
    Button buttonRoom5;
    Button buttonRoom6;
    Button buttonFirstFloor;
    Button buttonSecondFloor;
    AppDataBaseAdapter dataBaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
        initComponents();
    }
    private void initComponents(){
        dataBaseAdapter = new AppDataBaseAdapter(this);
        buttonRoom1 = (Button)findViewById(R.id.btn_room_1);
        setButtonTitle(buttonRoom1, dataBaseAdapter.getStore().findRoomById("0"));
        buttonRoom2 = (Button)findViewById(R.id.btn_room_2);
        setButtonTitle(buttonRoom2, dataBaseAdapter.getStore().findRoomById("1"));
        buttonRoom3 = (Button)findViewById(R.id.btn_room_3);
        setButtonTitle(buttonRoom3, dataBaseAdapter.getStore().findRoomById("2"));
        buttonRoom4 = (Button)findViewById(R.id.btn_room_4);
        setButtonTitle(buttonRoom4, dataBaseAdapter.getStore().findRoomById("3"));
        buttonRoom5 = (Button)findViewById(R.id.btn_room_5);
        setButtonTitle(buttonRoom5, dataBaseAdapter.getStore().findRoomById("4"));
        buttonRoom6 = (Button)findViewById(R.id.btn_room_6);
        setButtonTitle(buttonRoom6, dataBaseAdapter.getStore().findRoomById("5"));
        buttonFirstFloor = (Button)findViewById(R.id.btn_floor_1);
        buttonSecondFloor = (Button)findViewById(R.id.btn_floor_2);
    }

    private void setButtonTitle(Button button, Room room){
        String roomid;
        roomid = String.valueOf(room.getId());
        if (room.getId() == 0) roomid = "A";
        if(room != null){
            //button.setText(getString(R.string.sala)+""+roomid+ " - "+ room.title_it);
            if (Splash_screen.langLocale == "it") {button.setText(getString(R.string.sala)+""+roomid+ " - "+ room.title_it);}
            if (Splash_screen.langLocale == "en") {button.setText(getString(R.string.sala)+""+roomid+ " - "+ room.title_en);}
            if (Splash_screen.langLocale == "fr") {button.setText(getString(R.string.sala)+""+roomid+ " - "+ room.title_fr);}
        }
    }

    public void btn_room_1_click(View view) {
        Intent nextScreen = new Intent(this,RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG,"0");
        startActivity(nextScreen);
    }
    public void btn_room_2_click(View view) {
        Intent nextScreen = new Intent(this,RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG,"1");
        startActivity(nextScreen);
    }
    public void btn_room_3_click(View view) {
        Intent nextScreen = new Intent(this,RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG,"2");
        startActivity(nextScreen);
    }
    public void btn_room_4_click(View view) {
        Intent nextScreen = new Intent(this,RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG,"3");
        startActivity(nextScreen);
    }
    public void btn_room_5_click(View view) {
        Intent nextScreen = new Intent(this,RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG,"4");
        startActivity(nextScreen);
    }
    public void btn_room_6_click(View view) {
        Intent nextScreen = new Intent(this,RoomViewActivity.class);
        nextScreen.putExtra(RoomViewActivity.ROOM_ID_TAG,"5");
        startActivity(nextScreen);
    }
    public void btn_floor_1_click(View view) {
        Intent nextScreen = new Intent(this,FloorViewActivity.class);
        nextScreen.putExtra(FloorViewActivity.FIRST_FLOOR_TAG,true);
        startActivity(nextScreen);
    }
    public void btn_floor_2_click(View view) {
        Intent nextScreen = new Intent(this,FloorViewActivity.class);
        nextScreen.putExtra(FloorViewActivity.SECOND_FLOOR_TAG,true);
        startActivity(nextScreen);
    }

    public void btn_nav_home_click(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(nextScreen);
    }
    public void btn_nav_cards_click(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), CardListActivity.class);
        startActivity(nextScreen);
    }
    public void btn_nav_back_click(View view) {
        finish();
    }
}
