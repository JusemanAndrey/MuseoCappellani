package com.example.museocappellani.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Opera implements Parcelable {
    private int Id = 0;
    public String searchCode = "";
    public String title_en = "";
    public String title_fr = "";
    public String title_it = "";
    public String descr_it = "";
    public String descr_en = "";
    public String descr_fr = "";

    private String room_id = "";
    private String room_section_id = "";

    public Opera(){

    }
    public Opera(Parcel parcel){
        Id = parcel.readInt();
        searchCode = parcel.readString();
        title_en = parcel.readString();
        title_fr = parcel.readString();
        title_it = parcel.readString();
        descr_it = parcel.readString();
        descr_en = parcel.readString();
        descr_fr = parcel.readString();
        room_id = parcel.readString();
        room_section_id = parcel.readString();
    }
    public void setId(int id){
       this.Id = id;
    }
    public int getId(){
        return Id;
    }

    public void setRoomId(String id){
        this.room_id = id;
    }
    public String getRoomId(){
        return room_id;
    }

    public void writeToParcel(Parcel parcel, int flags){
        parcel.writeInt(Id);
        parcel.writeString(searchCode);
        parcel.writeString(title_en);
        parcel.writeString(title_fr);
        parcel.writeString(title_it);

        parcel.writeString(descr_it);
        parcel.writeString(descr_en);
        parcel.writeString(descr_fr);
        parcel.writeString(room_id);
        parcel.writeString(room_section_id);
    }

    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<Opera> CREATOR = new Parcelable.Creator<Opera>() {
        // распаковываем объект из Parcel
        public Opera createFromParcel(Parcel in) {
            return new Opera(in);
        }

        public Opera[] newArray(int size) {
            return new Opera[size];
        }
    };
}
