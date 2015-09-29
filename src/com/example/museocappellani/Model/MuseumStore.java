package com.example.museocappellani.Model;

import java.util.ArrayList;

public class MuseumStore {
    private ArrayList<Room> mRoomItems = new ArrayList<Room>();

    public ArrayList<Room> getRoomItems(){
        return  mRoomItems;
    }

    public void setRoomItems(ArrayList<Room> items){
        mRoomItems = items;
    }

    public ArrayList<Opera> getOperaItems(){
        //return  mOperaItems;
        ArrayList<Opera> res = new ArrayList<Opera>();
        for(int i=0; i<mRoomItems.size(); i++){
            Room currRoom = mRoomItems.get(i);
            res.addAll(currRoom.getOperasList());
        }
        return res;
    }

    public Room findRoomById(String id){
        for(int i=0; i<mRoomItems.size(); i++){
            Room currRoom = mRoomItems.get(i);
            if(Integer.toString(currRoom.getId()).equals(id)){
                return currRoom;
            }
        }
        return null;
    }

    public Opera findOperaAtTheList(Opera item, boolean flag){
        Room room = findRoomById(item.getRoomId());
        int position = calculatePositionForItem(item, room.getOperasList());
            if(flag){
                return getOperaByPosition(room, ++position);
            }
            else{
                return getOperaByPosition(room, --position);
            }
    }

    private int calculatePositionForItem(Opera item, ArrayList<Opera> operaItems){
        int res = 0;
        for(int i=0; i<operaItems.size();i++){
            if(operaItems.get(i).getId() == item.getId()){
                res = i;
                return res;
            }
        }
        return res;
    }

    private Opera getOperaByPosition(Room room, int position){
        ArrayList<Opera> operaItems = room.getOperasList();
        if(position > operaItems.size()-1){
            position = operaItems.size()-1;
        }
        if(position < 0){
            position = 0;
        }
        return operaItems.get(position);
    }
}
