package com.example.museocappellani.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.util.Log;
import com.example.museocappellani.DataBase.AppDataBaseHelper;
import com.example.museocappellani.Model.MuseumStore;
import com.example.museocappellani.Model.Opera;
import com.example.museocappellani.Model.Room;

import java.util.ArrayList;

public class AppDataBaseAdapter {
    private final static String TAG = AppDataBaseAdapter.class.getSimpleName();
    private final static String ROOM_TABLE_NAME = "room";
    private final static String ROOM_SECTION_TABLE_NAME = "roomsection";
    private final static String OPERA_TABLE_NAME = "opera";



    private MuseumStore mStore;
    private Context mContext;
    SQLiteDatabase mDataBase;
    AppDataBaseHelper mDBhelper;

    public AppDataBaseAdapter(Context context){
        mStore = new MuseumStore();
        mContext = context;
        mDBhelper = new AppDataBaseHelper(context);
        fillDataBase();
        loadDataFromDB();
    }

    public MuseumStore getStore(){
        return mStore;
    }

    public void fillDataBase(){
        try{
            mDBhelper.createDataBase();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void openDB(){
        mDataBase  = SQLiteDatabase.openDatabase(mDBhelper.getPathForDataBase(), null, SQLiteDatabase.OPEN_READONLY);
    }

    private void closeDB(){
        if(mDataBase != null){
            mDataBase.close();
        }
    }

    public void loadDataFromDB(){
        openDB();
        mStore.setRoomItems(loadRoomData());
        if(mStore.getRoomItems() != null){
            Log.d(TAG, mStore.getRoomItems().size() + "");
        }
        synchOperaItems();
        closeDB();
    }

    private ArrayList<Room> loadRoomData(){
        Cursor cursor = mDataBase.query(ROOM_TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Room> resultList = new ArrayList<Room>();
        if(cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("_id");
            int itTitleColIndex = cursor.getColumnIndex("title_it");
            int enTitleColIndex = cursor.getColumnIndex("title_en");
            int frTitleColIndex = cursor.getColumnIndex("title_fr");

            int itDescrColIndex = cursor.getColumnIndex("desc_it");
            int enDescrColIndex = cursor.getColumnIndex("desc_en");
            int frDescrColIndex = cursor.getColumnIndex("desc_fr");

            do {
                Room singleRoom = new Room();

                singleRoom.setId(cursor.getInt(idColIndex));

                singleRoom.title_it = Html.fromHtml(cursor.getString(itTitleColIndex)).toString();
                singleRoom.title_en = (cursor.getString(enTitleColIndex));
                singleRoom.title_fr = (cursor.getString(frTitleColIndex));

                singleRoom.descr_it = Html.fromHtml(cursor.getString(itDescrColIndex)).toString();
                singleRoom.descr_en = (cursor.getString(enDescrColIndex));
                singleRoom.descr_fr = (cursor.getString(frDescrColIndex));

                resultList.add(singleRoom);
            } while (cursor.moveToNext());
        }else{
            Log.d(TAG, "Room table contains 0 rows");
        }
        cursor.close();
        return  resultList;
    }

    private ArrayList<Opera> loadOperaData(){
        Cursor cursor = mDataBase.query(OPERA_TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Opera> resultList = new ArrayList<Opera>();
        if(cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("_id");
            int codeColIndex = cursor.getColumnIndex("searchcode");
            int roomColIndex = cursor.getColumnIndex("id_room");
            int itTitleColIndex = cursor.getColumnIndex("title_it");
            int enTitleColIndex = cursor.getColumnIndex("title_en");
            int frTitleColIndex = cursor.getColumnIndex("title_fr");

            int itDescrColIndex = cursor.getColumnIndex("desc_it");
            int enDescrColIndex = cursor.getColumnIndex("desc_en");
            int frDescrColIndex = cursor.getColumnIndex("desc_fr");

            do {
                Opera singleOpera = new Opera();

                singleOpera.setId(cursor.getInt(idColIndex));
                singleOpera.setRoomId(cursor.getString(roomColIndex));


                singleOpera.searchCode = Html.fromHtml(cursor.getString(codeColIndex)).toString();
                singleOpera.title_it = Html.fromHtml(cursor.getString(itTitleColIndex)).toString();
                singleOpera.title_en = Html.fromHtml(cursor.getString(enTitleColIndex)).toString();
                singleOpera.title_fr = Html.fromHtml(cursor.getString(frTitleColIndex)).toString();

                singleOpera.descr_it = Html.fromHtml(cursor.getString(itDescrColIndex)).toString();
                singleOpera.descr_en = Html.fromHtml(cursor.getString(enDescrColIndex)).toString();
                singleOpera.descr_fr = Html.fromHtml(cursor.getString(frDescrColIndex)).toString();

                resultList.add(singleOpera);
            } while (cursor.moveToNext());
        }else{
            Log.d(TAG, "Room table contains 0 rows");
        }
        cursor.close();
        return  resultList;
    }

    private void synchOperaItems(){
        ArrayList<Room> rooms = mStore.getRoomItems();
        ArrayList<Opera> operas = loadOperaData();

        for(int i=0; i < rooms.size(); i++){
            Room currRoom = rooms.get(i);
            for(int j=0; j< operas.size(); j++){
                Opera currOpera = operas.get(j);
                if(currOpera.getRoomId().equals(Integer.toString(currRoom.getId()))){
                    currRoom.addOperaToList(currOpera);
                }
            }
        }
    }
}

