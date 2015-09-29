package com.example.museocappellani.Model;

import java.util.ArrayList;

public class Room {
    private ArrayList<Opera> mOperaItems= new ArrayList<Opera>();
    private int Id = 0;
    public String title_en = "";
    public String title_fr = "";
    public String title_it = "";
    public String descr_it = "";
    public String descr_en = "";
    public String descr_fr = "";

    public Room(){

    }
    public void setId(int id){
        this.Id = id;
    }
    public int getId(){
        return Id;
    }

    public void addOperaToList(Opera opera){
        this.mOperaItems.add(opera);
    }

    public ArrayList<Opera> getOperasList(){
        return mOperaItems;
    }
}
