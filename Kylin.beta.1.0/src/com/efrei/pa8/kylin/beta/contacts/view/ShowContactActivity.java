package com.efrei.pa8.kylin.beta.contacts.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.tools.Data;

public class ShowContactActivity extends Activity{
	int contactPosition;
	Button btn_show_contact_modifycontact;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_contact);

		Bundle bundle = getIntent().getExtras();
		this.contactPosition = bundle.getInt("contactPosition");
		
		TextView textView_show_contact_name_value = (TextView) findViewById(R.id.textView_show_contact_name_value);
		TextView textView_show_contact_email_value = (TextView) findViewById(R.id.textView_show_contact_email_value);
		TextView textView_show_contact_address_value = (TextView) findViewById(R.id.textView_show_contact_address_value);
		TextView textView_show_contact_homePhoneNumber_value = (TextView) findViewById(R.id.textView_show_contact_homePhoneNumber_value);
		TextView textView_show_contact_mobilePhoneNumber_value = (TextView) findViewById(R.id.textView_show_contact_mobilePhoneNumber_value);
		TextView textView_show_contact_otherPhoneNumber_value = (TextView) findViewById(R.id.textView_show_contact_otherPhoneNumber_value);
		TextView textView_show_contact_category_value = (TextView) findViewById(R.id.textView_show_contact_category_value);
		TextView textView_show_contact_remark_value = (TextView) findViewById(R.id.textView_show_contact_remark_value);
		
		textView_show_contact_name_value.setText(Data.getInstance().getContactList().get(contactPosition).getName());
		textView_show_contact_email_value.setText(Data.getInstance().getContactList().get(contactPosition).getEmail());
		textView_show_contact_address_value.setText(Data.getInstance().getContactList().get(contactPosition).getAdress());
		textView_show_contact_homePhoneNumber_value.setText(Data.getInstance().getContactList().get(contactPosition).getHomePhoneNumber());
		textView_show_contact_mobilePhoneNumber_value.setText(Data.getInstance().getContactList().get(contactPosition).getMobilePhoneNumber());
		textView_show_contact_otherPhoneNumber_value.setText(Data.getInstance().getContactList().get(contactPosition).getOtherPhoneNumber());
		textView_show_contact_category_value.setText(Data.getInstance().getContactList().get(contactPosition).getCategory());
		textView_show_contact_remark_value.setText(Data.getInstance().getContactList().get(contactPosition).getRemark());
		
		btn_show_contact_modifycontact = (Button) findViewById(R.id.btn_show_contact_modifycontact);
		btn_show_contact_modifycontact.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent(ShowContactActivity.this, ModifyContactActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("contactPosition", contactPosition);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});
	}
}
