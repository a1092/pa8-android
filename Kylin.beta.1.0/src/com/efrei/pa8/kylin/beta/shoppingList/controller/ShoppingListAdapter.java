package com.efrei.pa8.kylin.beta.shoppingList.controller;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingItem;

public class ShoppingListAdapter extends BaseAdapter{
	private ArrayList<ShoppingItem> shoppingItemList;
	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<ShoppingListAdapterListener> shoppingListListener = new ArrayList<ShoppingListAdapterListener>();

	public ShoppingListAdapter(Context context, ArrayList<ShoppingItem> shoppingItemList){
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.shoppingItemList = shoppingItemList;	
	}
	
	@Override
	public int getCount() {
		return shoppingItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return shoppingItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		LinearLayout layoutItem;
		
		if (convertView == null) {
			layoutItem = (LinearLayout) layoutInflater.inflate(
					R.layout.prompt_shoppingitem, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}
		
		TextView textView_prompt_shoppingitem_name_data = (TextView) layoutItem.findViewById(R.id.textView_prompt_shoppingitem_name_data);
		TextView textView_prompt_shoppingitem_quantity_data = (TextView) layoutItem.findViewById(R.id.textView_prompt_shoppingitem_quantity_data);
		textView_prompt_shoppingitem_name_data.setText(shoppingItemList.get(position).getName());
		textView_prompt_shoppingitem_quantity_data.setText(String.valueOf(shoppingItemList.get(position).getQuantity()));

		layoutItem.setTag(position);
		layoutItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer position = (Integer) v.getTag();
				clickOnArticle(shoppingItemList.get(position), position, context, shoppingItemList, true);
			}
		});
		layoutItem.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Integer position = (Integer) v.getTag();
				clickOnArticle(shoppingItemList.get(position), position, context, shoppingItemList, false);
				return false;
			}
		});
		
		return layoutItem;
	}
	
	public void addListener(ShoppingListAdapterListener listener) {
		shoppingListListener.add(listener);
	}
	
	private void clickOnArticle(ShoppingItem shoppingItem, int position, Context context, ArrayList<ShoppingItem> shoppingItemList, boolean edit) {
    	for(int i = shoppingListListener.size()-1; i >= 0; i--) {
    		if(edit){
    		Log.d("position: ", String.valueOf(position));
    		Log.d("position: ", String.valueOf(shoppingItemList));
    		shoppingListListener.get(i).onClickEdit(position, context, shoppingItemList);
    		}
    		else{
        		shoppingListListener.get(i).onClickDelete(position, context, shoppingItemList);
    		}
    	}
    }
	
	public interface ShoppingListAdapterListener {
		public void onClickEdit(int position, Context context, ArrayList<ShoppingItem> shoppingItemList);
		public void onClickDelete(int position, Context context, ArrayList<ShoppingItem> shoppingItemList);
	}
}
