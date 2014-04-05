package com.efrei.pa8.kylin.beta.shoppingList.view;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.shoppingList.controller.ShoppingListListAdapter;
import com.efrei.pa8.kylin.beta.shoppingList.controller.ShoppingListListAdapter.ShoppingListListAdapterListener;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingList;
import com.efrei.pa8.kylin.beta.tools.Data;

public class ChooseAShoppingListActivity extends Activity implements ShoppingListListAdapterListener{
	
	ListView listView_chooseashoppinglist_shoppinglist_list;
	Button btn_chooseashoppinglist_add_shoppinglist;
	ShoppingListListAdapter shoppingListListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooseashoppinglist);

		shoppingListListAdapter = new ShoppingListListAdapter(this, Data.getInstance().getShoppingListList());
		shoppingListListAdapter.addListener(this);

		listView_chooseashoppinglist_shoppinglist_list = (ListView) findViewById(R.id.listView_chooseashoppinglist_shoppinglist_list);
		listView_chooseashoppinglist_shoppinglist_list.setAdapter(shoppingListListAdapter);

		btn_chooseashoppinglist_add_shoppinglist = (Button) findViewById(R.id.btn_chooseashoppinglist_add_shoppinglist);
		btn_chooseashoppinglist_add_shoppinglist.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			Intent intent = new Intent(ChooseAShoppingListActivity.this, CreateAShoppingListActivity.class);
			startActivity(intent);
			}
		});
	}
	
	@Override
	public void onClick(int position, Context context,ArrayList<ShoppingList> shoppingListList) {
		Data.getInstance().setListPosition(position);
		Intent intent = new Intent(ChooseAShoppingListActivity.this, ShoppingListActivity.class);
		startActivity(intent);
	}
}
