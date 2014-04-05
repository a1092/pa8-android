package com.efrei.pa8.kylin.beta.contacts.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;

public class ModifyContactActivity extends Activity{
	int contactPosition;
	Button btn_modify_contact_submit;
	Button btn_modify_contact_cancel;

	String name;
	String email;
	String address;
	String homePhoneNumber;
	String mobilePhoneNumber;
	String otherPhoneNumber;
	String remark;
	String category;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifycontact);

		Bundle bundle = getIntent().getExtras();
		this.contactPosition = bundle.getInt("contactPosition");

		EditText editText_modify_contact_name_value = (EditText) findViewById(R.id.editText_modify_contact_name_value);
		EditText editText_modify_contact_email_value = (EditText) findViewById(R.id.editText_modify_contact_email_value);
		EditText editText_modify_contact_address_value = (EditText) findViewById(R.id.editText_modify_contact_address_value);
		EditText editText_modify_contact_homePhoneNumber_value = (EditText) findViewById(R.id.editText_modify_contact_homePhoneNumber_value);
		EditText editText_modify_contact_mobilePhoneNumber_value = (EditText) findViewById(R.id.editText_modify_contact_mobilePhoneNumber_value);
		EditText editText_mofidy_contact_otherPhoneNumber_value = (EditText) findViewById(R.id.editText_modify_contact_otherPhoneNumber_value);
		EditText editText_modify_contact_category_value = (EditText) findViewById(R.id.editText_modify_contact_category_value);
		EditText editText_modify_contact_remark_value = (EditText) findViewById(R.id.editText_modify_contact_remark_value);

		editText_modify_contact_name_value.setText(Data.getInstance().getContactList().get(contactPosition).getName());
		editText_modify_contact_email_value.setText(Data.getInstance().getContactList().get(contactPosition).getEmail());
		editText_modify_contact_address_value.setText(Data.getInstance().getContactList().get(contactPosition).getAdress());
		editText_modify_contact_homePhoneNumber_value.setText(Data.getInstance().getContactList().get(contactPosition).getHomePhoneNumber());
		editText_modify_contact_mobilePhoneNumber_value.setText(Data.getInstance().getContactList().get(contactPosition).getMobilePhoneNumber());
		editText_mofidy_contact_otherPhoneNumber_value.setText(Data.getInstance().getContactList().get(contactPosition).getOtherPhoneNumber());
		editText_modify_contact_category_value.setText(Data.getInstance().getContactList().get(contactPosition).getCategory());
		editText_modify_contact_remark_value.setText(Data.getInstance().getContactList().get(contactPosition).getRemark());


		btn_modify_contact_submit = (Button) findViewById(R.id.btn_modify_contact_submit);
		btn_modify_contact_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText editText_modify_contact_name_value = (EditText) findViewById(R.id.editText_modify_contact_name_value);
				EditText editText_modify_contact_email_value = (EditText) findViewById(R.id.editText_modify_contact_email_value);
				EditText editText_modify_contact_address_value = (EditText) findViewById(R.id.editText_modify_contact_address_value);
				EditText editText_modify_contact_homePhoneNumber_value = (EditText) findViewById(R.id.editText_modify_contact_homePhoneNumber_value);
				EditText editText_modify_contact_mobilePhoneNumber_value = (EditText) findViewById(R.id.editText_modify_contact_mobilePhoneNumber_value);
				EditText editText_mofidy_contact_otherPhoneNumber_value = (EditText) findViewById(R.id.editText_modify_contact_otherPhoneNumber_value);
				EditText editText_modify_contact_category_value = (EditText) findViewById(R.id.editText_modify_contact_category_value);
				EditText editText_modify_contact_remark_value = (EditText) findViewById(R.id.editText_modify_contact_remark_value);


				// / Vérification des champs, vides
				// ou non, puis sauvegarde.
				name = editText_modify_contact_name_value
						.getText().toString();

				email = "";
				if (!editText_modify_contact_email_value.getText().toString().matches("")) {
					email = editText_modify_contact_email_value.getText().toString();
				}
				address = "";
				if (!editText_modify_contact_address_value.getText().toString().matches("")) {
					address = editText_modify_contact_address_value.getText().toString();
				}
				homePhoneNumber = "";
				if (!editText_modify_contact_homePhoneNumber_value.getText().toString().matches("")) {
					address = editText_modify_contact_homePhoneNumber_value.getText().toString();
				}
				mobilePhoneNumber = "";
				if (!editText_modify_contact_mobilePhoneNumber_value.getText().toString().matches("")) {
					address = editText_modify_contact_mobilePhoneNumber_value.getText().toString();
				}
				otherPhoneNumber = "";
				if (!editText_mofidy_contact_otherPhoneNumber_value.getText().toString().matches("")) {
					address = editText_mofidy_contact_otherPhoneNumber_value.getText().toString();
				}
				category = "";
				if (!editText_modify_contact_category_value.getText().toString().matches("")) {
					category = editText_modify_contact_category_value.getText().toString();
				}
				remark = "";
				if (!editText_modify_contact_remark_value.getText().toString().matches("")) {
					remark = editText_modify_contact_remark_value.getText().toString();
				}
				
				JSONSaveModifiedContactTask jSONSaveModifiedContactTask = new JSONSaveModifiedContactTask();
				jSONSaveModifiedContactTask.execute();
			}
		});

		btn_modify_contact_cancel = (Button) findViewById(R.id.btn_modify_contact_cancel);
		btn_modify_contact_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ModifyContactActivity.this, ContactsActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
	private class JSONSaveModifiedContactTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().modifyContact(contactPosition, name, email, address, homePhoneNumber, mobilePhoneNumber, otherPhoneNumber, remark, category);
				
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
					ModifyContactActivity.this,
					ContactsActivity.class);

			startActivity(intent);
			finish();
		}
	}
}
