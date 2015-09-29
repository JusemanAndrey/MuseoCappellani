package com.example.museocappellani.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.*;
import java.sql.SQLException;

public class AppDataBaseHelper extends SQLiteOpenHelper {
    private static String DB_PATH = "/data/data/com.example.museocappellani/databases/";
    private static String DB_NAME = "museojudica.sqlite3";
    private SQLiteDatabase mDataBase;

    private final Context mContext;

    public AppDataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.mContext = context;
    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //ничего не делать - база уже есть
        } else {

            //вызывая этот метод создаем пустую базу, позже она будет перезаписана
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {
                e.printStackTrace();

                throw new Error("Error copying database");

            }
        }

    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException {

        //Открываем локальную БД как входящий поток
        InputStream myInput = mContext.getAssets().open(DB_NAME);

        //Путь ко вновь созданной БД
        String outFileName = DB_PATH + DB_NAME;

        //Открываем пустую базу данных как исходящий поток
        OutputStream myOutput = new FileOutputStream(outFileName);

        //перемещаем байты из входящего файла в исходящий
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //закрываем потоки
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public String getPathForDataBase(){
        return DB_PATH + DB_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}