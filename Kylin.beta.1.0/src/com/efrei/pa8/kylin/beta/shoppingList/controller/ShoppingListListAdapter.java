package com.efrei.pa8.kylin.beta.shoppingList.controller;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingList;

public class ShoppingListListAdapter extends BaseAdapter{
	private ArrayList<ShoppingList> shoppingListList;
	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<ShoppingListListAdapterListener> shoppingListListListener = new ArrayList<ShoppingListListAdapterListener>();

	public ShoppingListListAdapter(Context context, ArrayList<ShoppingList> shoppingListList){
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.shoppingListList = shoppingListList;	
	}

	@Override
	public int getCount() {
		return shoppingListList.size();
	}

	@Override
	public Object getItem(int position) {
		return shoppingListList.get(position);
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
					R.layout.prompt_shoppinglist, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}

		TextView textView_prompt_shoppinglist_name_data = (TextView) layoutItem.findViewById(R.id.textView_prompt_shoppinglist_name_data);
		TextView textView_prompt_shppinglist_deadline_data = (TextView) layoutItem.findViewById(R.id.textView_prompt_shppinglist_deadline_data);

		textView_prompt_shoppinglist_name_data.setText(shoppingListList.get(position).getName());
		textView_prompt_shppinglist_deadline_data.setText(shoppingListList.get(position).getDeadline().toString());

		layoutItem.setTag(position);
		layoutItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer position = (Integer) v.getTag();
				launchShoppingList(shoppingListList.get(position), position, context, shoppingListList);
			}
		});

		return layoutItem;
	}

	public void addListener(ShoppingListListAdapterListener listener) {
		shoppingListListListener.add(listener);
	}

	private void launchShoppingList(ShoppingList shoppingList, int position, Context context, ArrayList<ShoppingList> shoppingListList) {
		for(int i = shoppingListListListener.size()-1; i >= 0; i--) {
			shoppingListListListener.get(i).onClick(position, context, shoppingListList);
		}
	}

	public interface ShoppingListListAdapterListener {
		public void onClick(int position, Context context, ArrayList<ShoppingList> shoppingListList);
	}
}
