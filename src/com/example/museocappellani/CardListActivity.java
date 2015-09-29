package com.example.museocappellani;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.museocappellani.Activity.CardViewActivity;
import com.example.museocappellani.Activity.MapViewActivity;
import com.example.museocappellani.Adapter.AppDataBaseAdapter;
import com.example.museocappellani.Adapter.OperaListAdapter;
import com.example.museocappellani.Model.Opera;

import java.util.ArrayList;

public class CardListActivity extends Activity {
    AppDataBaseAdapter mDataBaseAdapter;
    ListView listView;
    EditText editCardSearch;
    ArrayList<Opera> mSortItems = new ArrayList<Opera>();
    ArrayList<Opera> mItems = new ArrayList<Opera>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_list);

        initComponents();
        setListView();
	} 

    private void initComponents(){
        /*DISABLE SOFT KEYBOARD*/
        editCardSearch = (EditText)findViewById(R.id.editCardSearch);
        editCardSearch.setInputType(0);

		/*POPULATE LIST VIEW*/
        listView = (ListView)findViewById(R.id.cards_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Opera opera = (Opera)parent.getItemAtPosition(position);
                selectItem(opera);
            }
        });
        mDataBaseAdapter = new AppDataBaseAdapter(this);
        editCardSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(after == 0){
                    setListView();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int textLength = editCardSearch.getText().length();
                mSortItems.clear();
                for(int i=0; i<mItems.size();i++){
                    int maxLength = 4;

                    if(textLength <= maxLength){
                        if(editCardSearch.getText().toString().equalsIgnoreCase((String)mItems.get(i).searchCode.subSequence(0,textLength)))
                        {
                            mSortItems.add(mItems.get(i));
                        }

                    }
                }
                listView.setAdapter(new OperaListAdapter(CardListActivity.this,R.layout.item_card_list,mSortItems));
                /*if(count == 0){
                    setListView();
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setListView(){
        mItems = mDataBaseAdapter.getStore().getOperaItems();
        listView.setAdapter(new OperaListAdapter(this,R.layout.item_card_list, mItems));
    }

    private void selectItem(Opera item){
        Intent nextScreen = new Intent(this, CardViewActivity.class);
        ArrayList<Opera> items = new ArrayList<Opera>();
        items.add(item);
        nextScreen.putExtra(CardViewActivity.ITEM_TAG,item);
        //nextScreen.putParcelableArrayListExtra(CardViewActivity.ITEM_TAG, items);
        startActivity(nextScreen);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onItemClick(AdapterView<?> adapter, View view,
	    int position, long id) {
		
		simulateKey(9);
		//Contatto c = (Contatto)adapter.getItem(position);
	}
	
	public void btn_keyb_click(View view) {
		String value = (String) view.getTag(); 
		
		simulateKey(Integer.parseInt(value));
	}
	
	public void btn_keyb_clear(View view) {
		editCardSearch.setText("");
	}
	
	public static void simulateKey(final int KeyCode) {

	    new Thread() {
	        @Override
	        public void run() {
	            try {
	                Instrumentation inst = new Instrumentation();
	                inst.sendKeyDownUpSync(KeyCode);
	            } catch (Exception e) {
	                Log.e("Exception when sendKeyDownUpSync", e.toString());
	            }
	        }

	    }.start();
	}
	
	
	/* NAV BOTTOM*/
	public void btn_nav_home_click(View view) {
		Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(nextScreen);
	}
	public void btn_nav_cards_click(View view) {
		//Intent nextScreen = new Intent(getApplicationContext(), CardListActivity.class);
		//startActivity(nextScreen);
	}
	public void btn_nav_info_click(View view) {
        Intent nextScreen = new Intent(getApplicationContext(), MapViewActivity.class);
        startActivity(nextScreen);
	}
	public void btn_nav_back_click(View view) {
		finish();
	}
}
