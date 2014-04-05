package com.efrei.pa8.kylin.beta.shoppingList.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingList;
import com.efrei.pa8.kylin.beta.tools.Data;

public class CreateAShoppingListActivity extends Activity{

	Button btn_createashoppinglist_submit;
	Button btn_createashoppinglist_cancel;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_createashoppinglist);

		btn_createashoppinglist_submit = (Button) findViewById(R.id.btn_createashoppinglist_submit);
		btn_createashoppinglist_submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText editText_createashoppinglist_name = (EditText) findViewById(R.id.editText_createashoppinglist_name);
				EditText editText_createashoppinglist_deadline = (EditText) findViewById(R.id.editText_createashoppinglist_deadline);

				if((editText_createashoppinglist_name.getText().toString().matches("")) || (editText_createashoppinglist_deadline.getText().toString().matches(""))){
					Toast.makeText(CreateAShoppingListActivity.this, "Vous n'avez pas rempli tous les champs", Toast.LENGTH_SHORT).show();
				}
				else{
					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
					Date deadline = null;
					try {
						deadline = df.parse(editText_createashoppinglist_deadline.getText().toString());
					} catch (java.text.ParseException e) {
						e.printStackTrace();
					}
					Data.getInstance().addShoppingList(new ShoppingList(editText_createashoppinglist_name.getText().toString(), deadline));
				}
			}
		});

		btn_createashoppinglist_cancel = (Button) findViewById(R.id.btn_createashoppinglist_cancel);
		btn_createashoppinglist_cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(CreateAShoppingListActivity.this, "**TODO** Not Implemented yet ! **TODO**", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
