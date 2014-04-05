package com.efrei.pa8.kylin.beta.contacts.view;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.contacts.controller.ContactListAdapter;
import com.efrei.pa8.kylin.beta.contacts.controller.ContactListAdapter.ContactListAdapterListener;
import com.efrei.pa8.kylin.beta.contacts.model.Contact;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;

public class ContactsActivity extends Activity implements
ContactListAdapterListener {

	ListView listView_contacts_contact_list;
	Button btn_contacts_create_contact;
	ContactListAdapter contactListAdapter;
	int contactToDeletePosition;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);

		Log.d("taille: ", String.valueOf(Data.getInstance().getContactList().size()));
		Log.d("taille: ", Data.getInstance().getContactList().get(2).getName());
		
		contactListAdapter = new ContactListAdapter(this, Data.getInstance().getContactList());
		contactListAdapter.addListener(this);

		listView_contacts_contact_list = (ListView) findViewById(R.id.listView_contacts_contact_list);
		listView_contacts_contact_list.setAdapter(contactListAdapter);

		btn_contacts_create_contact = (Button) findViewById(R.id.btn_contacts_create_contact);
		btn_contacts_create_contact.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ContactsActivity.this, CreateContactActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onClickShow(int position, Context context, ArrayList<Contact> contactList) {
		Intent intent = new Intent(ContactsActivity.this, ShowContactActivity.class);
		Bundle bundle = new Bundle();
		bundle.putInt("contactPosition", position);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	@Override
	public void onClickDelete(final int position, Context context,ArrayList<Contact> contactList) {
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompt_delete, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder.setView(promptsView);

		final TextView textView_prompt_delete_message = (TextView) promptsView.findViewById(R.id.textView_prompt_delete_message);
		textView_prompt_delete_message.setText("Voulez-vous vraiment supprimer le contact " + contactList.get(position).getName() + "?");

		alertDialogBuilder.setCancelable(false).setPositiveButton("Supprimer le contact", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				contactToDeletePosition = position;
				JSONDeleteContactTask jSONDeleteContactTask = new JSONDeleteContactTask();
				jSONDeleteContactTask.execute();
			}
		}).setNegativeButton("Annuler la suppression",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
	}

	private class JSONDeleteContactTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().deleteContact(contactToDeletePosition);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}


		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			// The app launches the dashboard and passes the
			// username and password of the user.
			Intent intent = new Intent(
					ContactsActivity.this,
					ContactsActivity.class);

			startActivity(intent);
			finish();
		}
	}
}
