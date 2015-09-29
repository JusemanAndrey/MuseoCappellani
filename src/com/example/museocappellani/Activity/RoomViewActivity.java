package com.example.museocappellani.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.example.museocappellani.Adapter.AppDataBaseAdapter;
import com.example.museocappellani.Adapter.OperaListAdapter;
import com.example.museocappellani.CardListActivity;
import com.example.museocappellani.DescViewActivity;
import com.example.museocappellani.MainActivity;
import com.example.museocappellani.Model.Opera;
import com.example.museocappellani.Model.Room;
import com.example.museocappellani.R;
import com.example.museocappellani.Splash_screen;

public class RoomViewActivity extends Activity {
    public static final String ROOM_ID_TAG = "room_tag";
    private final static String TAG = RoomViewActivity.class.getSimpleName();
    TextView roomViewTitle;
    TextView roomViewDescr;
    ListView roomViewList;
    AppDataBaseAdapter dataBaseAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_view);
        initComponents();
        parseEvent();
    }

    private void initComponents(){
        roomViewTitle = (TextView)findViewById(R.id.roomViewTitleText);
        roomViewDescr = (TextView)findViewById(R.id.roomViewDescr);
        roomViewList = (ListView)findViewById(R.id.roomViewList);
        dataBaseAdapter = new AppDataBaseAdapter(this);

        roomViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Opera item = (Opera)parent.getItemAtPosition(position);
                selectOperaItem(item);
            }
        });
    }

    private void parseEvent(){
        String room_id = getIntent().getStringExtra(ROOM_ID_TAG);
        if(room_id.equals("")){
            Log.e(TAG, "Room doesn't came on");
        }
        else{
            Room currRoom = dataBaseAdapter.getStore().findRoomById((room_id));
            setInfo(currRoom);
        }
    }

    private void setInfo(Room room){
        String roomid;
        roomid = String.valueOf(room.getId());
        if (room.getId() == 0) roomid = "A";
        if (Splash_screen.langLocale == "it") {
            roomViewTitle.setText(getString(R.string.sala) + "" + roomid + " " + room.title_it);
            roomViewList.setAdapter(new OperaListAdapter(this, R.layout.item_card_list, room.getOperasList()));
            roomViewDescr.setText(room.descr_it);
        } else if (Splash_screen.langLocale == "en") {
            roomViewTitle.setText(getString(R.string.sala) + "" + roomid + " " + room.title_en);
            roomViewList.setAdapter(new OperaListAdapter(this, R.layout.item_card_list, room.getOperasList()));
            roomViewDescr.setText(room.descr_en);
        } else if (Splash_screen.langLocale == "fr") {
            roomViewTitle.setText(getString(R.string.sala) + "" + roomid + " " + room.title_fr);
            roomViewList.setAdapter(new OperaListAdapter(this, R.layout.item_card_list, room.getOperasList()));
            roomViewDescr.setText(room.descr_fr);
        }
    }

    private void selectOperaItem(Opera item){
        Intent nextScreen = new Intent(this, CardViewActivity.class);
        nextScreen.putExtra(CardViewActivity.ITEM_TAG,item);
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
    public void btn_nav_info_click(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), MapViewActivity.class);
        startActivity(nextScreen);
    }
    public void btn_nav_back_click(View view) {
        finish();
    }
}
