package com.efrei.pa8.kylin.beta.contacts.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;


public class CreateContactActivity extends Activity{
	Button btn_create_contact_submit;
	Button btn_create_contact_cancel;
	
	String name;
	String email;
	String address;
	String homePhoneNumber;
	String mobilePhoneNumber;
	String otherPhoneNumber;
	String remark;
	String category;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_contact);
		
		btn_create_contact_submit = (Button) findViewById(R.id.btn_create_contact_submit);
		btn_create_contact_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				final EditText editText_create_contact_name = (EditText) findViewById(R.id.editText_create_contact_name);
				final EditText editText_create_contact_email = (EditText) findViewById(R.id.editText_create_contact_email);
				final EditText editText_create_contact_address = (EditText) findViewById(R.id.editText_create_contact_address);
				final EditText editText_create_contact_homePhoneNumber = (EditText) findViewById(R.id.editText_create_contact_homePhoneNumber);
				final EditText editText_prompt_add_contact_mobilePhoneNumber = (EditText) findViewById(R.id.editText_create_contact_mobilePhoneNumber);
				final EditText editText_prompt_add_contact_otherPhoneNumber = (EditText) findViewById(R.id.editText_create_contact_otherPhoneNumber);
				final EditText editText_create_contact_category = (EditText) findViewById(R.id.editText_create_contact_category);
				final EditText editText_create_contact_remark = (EditText) findViewById(R.id.editText_create_contact_remark);
				
				// Vérifcation que le champ "name" a bien été rempli.
				if (editText_create_contact_name
						.getText().toString()
						.matches("")) {

					Toast.makeText(
							CreateContactActivity.this,
							"Vous n'avez pas entré de nom  -_-",
							Toast.LENGTH_SHORT).show();
				} else {
					// / Vérification des champs, vides
					// ou non, puis sauvegarde.
					name = editText_create_contact_name
							.getText().toString();

					email = "";
					if (!editText_create_contact_email.getText().toString().matches("")) {
						email = editText_create_contact_email.getText().toString();
					}
					address = "";
					if (!editText_create_contact_address.getText().toString().matches("")) {
						address = editText_create_contact_address.getText().toString();
					}
					homePhoneNumber = "";
					if (!editText_create_contact_homePhoneNumber.getText().toString().matches("")) {
						address = editText_create_contact_homePhoneNumber.getText().toString();
					}
					mobilePhoneNumber = "";
					if (!editText_prompt_add_contact_mobilePhoneNumber.getText().toString().matches("")) {
						address = editText_prompt_add_contact_mobilePhoneNumber.getText().toString();
					}
					otherPhoneNumber = "";
					if (!editText_prompt_add_contact_otherPhoneNumber.getText().toString().matches("")) {
						address = editText_prompt_add_contact_otherPhoneNumber.getText().toString();
					}
					category = "";
					if (!editText_create_contact_category.getText().toString().matches("")) {
						category = editText_create_contact_category.getText().toString();
					}
					remark = "";
					if (!editText_create_contact_remark.getText().toString().matches("")) {
						remark = editText_create_contact_remark.getText().toString();
					}
					
					JSONSaveCreatedContactTask jSONSaveCreatedContactTask = new JSONSaveCreatedContactTask();
					jSONSaveCreatedContactTask.execute();
				}
			}
		});
		
		btn_create_contact_cancel = (Button) findViewById(R.id.btn_create_contact_cancel);
		btn_create_contact_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CreateContactActivity.this, ContactsActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	private class JSONSaveCreatedContactTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().newContact(name, email, address, homePhoneNumber, mobilePhoneNumber, otherPhoneNumber, remark, category);
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}


		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			Intent intent = new Intent(
					CreateContactActivity.this,
					ContactsActivity.class);

			startActivity(intent);
			finish();
		}
	}
}
