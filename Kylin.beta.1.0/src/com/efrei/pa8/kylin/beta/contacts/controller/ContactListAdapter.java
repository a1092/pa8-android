package com.efrei.pa8.kylin.beta.contacts.controller;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.contacts.model.Contact;


public class ContactListAdapter extends BaseAdapter{
	private ArrayList<Contact> contactList;
	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<ContactListAdapterListener> contactListListener = new ArrayList<ContactListAdapterListener>();

	public ContactListAdapter(Context context, ArrayList<Contact> contactList){
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.contactList = contactList;
	}
	
	@Override
	public int getCount() {
		return contactList.size();
	}

	@Override
	public Object getItem(int position) {
		return contactList.get(position);
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
					R.layout.prompt_contact, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}
		
		TextView textView_prompt_contact_name = (TextView) layoutItem.findViewById(R.id.textView_prompt_contact_name);
		textView_prompt_contact_name.setText(contactList.get(position).getName() + "     Catégorie :");
	
		TextView textView_prompt_contact_category = (TextView) layoutItem.findViewById(R.id.textView_prompt_contact_category);
		textView_prompt_contact_category.setText(contactList.get(position).getCategory());

		
		layoutItem.setTag(position);
		layoutItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Integer position = (Integer) v.getTag();
				clickOnTheContact(contactList.get(position), position, context, contactList, true);
			}
		});
		layoutItem.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				Integer position = (Integer) v.getTag();
				clickOnTheContact(contactList.get(position), position, context, contactList, false);
				return false;
			}
		});
		return layoutItem;
	}
	
	public void addListener(ContactListAdapterListener listener) {
		contactListListener.add(listener);
	}
	
	private void clickOnTheContact(Contact contact, int position, Context context, ArrayList<Contact> contactList, Boolean show) {
    	for(int i = contactListListener.size()-1; i >= 0; i--) {
    		if(show){
    			contactListListener.get(i).onClickShow(position, context, contactList);
    		}
    		else {	// If "not show" ==> delete
    			contactListListener.get(i).onClickDelete(position, context, contactList);
    		}
    	}
    }
	
	public interface ContactListAdapterListener {
		public void onClickShow(int position, Context context, ArrayList<Contact> contactList);
		public void onClickDelete(int position, Context context, ArrayList<Contact> contactList);
	}
}
