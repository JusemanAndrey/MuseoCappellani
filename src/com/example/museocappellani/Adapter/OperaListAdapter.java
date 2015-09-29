package com.example.museocappellani.Adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.museocappellani.Model.Opera;
import com.example.museocappellani.R;
import com.example.museocappellani.Splash_screen;

public class OperaListAdapter extends ArrayAdapter<Opera> {
	
	private final List<Opera> mItems;
	private final Context mContext;
	
 
	public OperaListAdapter(Context context, int layoutResourceId, ArrayList<Opera> items) {
		super(context, layoutResourceId, items);
	    this.mContext = context;
	    this.mItems = items;
    }
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        View row = null;
        OperaHolder mHolder = new OperaHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_card_list, null);
        }else{
            row = convertView;
        }

        mHolder.mItem = mItems.get(position);
        mHolder.mOperaImage = (ImageView)row.findViewById(R.id.logo);
        mHolder.mOperaId = (TextView)row.findViewById(R.id.textViewId);
        mHolder.mOperaTitle = (TextView)row.findViewById(R.id.textViewTitle);

        row.setTag(mHolder);
	    setupItems(mHolder);
	    return row;
	  }

    private class OperaHolder{
        Opera mItem;
        ImageView mOperaImage;
        TextView mOperaId;
        TextView mOperaTitle;
        TextView mOperaDescription;
    }

    private void setupItems(OperaHolder  holder){
        String filePath = "opera_"+Integer.toString(holder.mItem.getId())+"_clip"+".jpg";
        try {
            holder.mOperaImage.setImageBitmap(BitmapFactory.decodeStream(mContext.getAssets().open(filePath)));
            holder.mOperaId.setText(holder.mItem.searchCode);
            //holder.mOperaTitle.setText(holder.mItem.title_it);
            if (Splash_screen.langLocale == "it") {holder.mOperaTitle.setText(holder.mItem.title_it);}
            if (Splash_screen.langLocale == "en") {holder.mOperaTitle.setText(holder.mItem.title_en);}
            if (Splash_screen.langLocale == "fr") {holder.mOperaTitle.setText(holder.mItem.title_fr);}
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}