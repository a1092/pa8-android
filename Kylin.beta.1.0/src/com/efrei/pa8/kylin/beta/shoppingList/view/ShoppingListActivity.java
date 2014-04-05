package com.efrei.pa8.kylin.beta.shoppingList.view;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.shoppingList.controller.ShoppingListAdapter;
import com.efrei.pa8.kylin.beta.shoppingList.controller.ShoppingListAdapter.ShoppingListAdapterListener;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingItem;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;

public class ShoppingListActivity extends Activity implements ShoppingListAdapterListener{
	ListView listView_shoppinglist_shoppingitem_list;
	Button btn_shoppinglist_add_shoppingitem;
	Button btn_shoppinglist_backtoshoppinglistlists;
	ShoppingListAdapter shoppingListAdapter;
	TextView textView_shoppinglist_listname;
	TextView textView_shoppinglist_listdeadline;

	int shoppingListPosition;
	int shoppingItemToDeletePosition;
	
	String shoppingItemName;
	String shoppingItemQuantity;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoppinglist);

		this.shoppingListPosition = Data.getInstance().getListPosition();
		Log.d("listPosition: ", String.valueOf(shoppingListPosition));

		textView_shoppinglist_listname = (TextView) findViewById(R.id.textView_shoppinglist_listname);
		textView_shoppinglist_listname.setText(Data.getInstance().getShoppingListList().get(shoppingListPosition).getName());

		textView_shoppinglist_listdeadline = (TextView) findViewById(R.id.textView_shoppinglist_listdeadline);
		textView_shoppinglist_listdeadline.setText(Data.getInstance().getShoppingListList().get(shoppingListPosition).getDeadline().toString());

		shoppingListAdapter = new ShoppingListAdapter(this, Data.getInstance().getShoppingListList().get(shoppingListPosition).getShoppingItems());
		shoppingListAdapter.addListener(this);

		listView_shoppinglist_shoppingitem_list = (ListView) findViewById(R.id.listView_shoppinglist_shoppingitem_list);
		listView_shoppinglist_shoppingitem_list.setAdapter(shoppingListAdapter);

		btn_shoppinglist_backtoshoppinglistlists = (Button) findViewById(R.id.btn_shoppinglist_backtoshoppinglistlists);
		btn_shoppinglist_backtoshoppinglistlists.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				JSONRetrieveShoppingListsTask jSONRetrieveShoppingListsTask = new JSONRetrieveShoppingListsTask();
				jSONRetrieveShoppingListsTask.execute();
			}
		});
		
		btn_shoppinglist_add_shoppingitem = (Button) findViewById(R.id.btn_shoppinglist_add_shoppingitem);
		btn_shoppinglist_add_shoppingitem.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(ShoppingListActivity.this);
				View promptsView = li.inflate(R.layout.prompt_modifyshoppingitem, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						ShoppingListActivity.this);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText editText_prompt_modifyshoppingitem_name = (EditText) promptsView.findViewById(R.id.editText_prompt_modifyshoppingitem_name);
				final EditText editText_prompt_modifyshoppingitem_quantity = (EditText) promptsView.findViewById(R.id.editText_prompt_modifyshoppingitem_quantity);

				// set dialog message
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						// get user input and set it to result
						
						shoppingItemName = editText_prompt_modifyshoppingitem_name.getText().toString();
						
						if (shoppingItemName.matches("")) {
							Toast.makeText(
									ShoppingListActivity.this,
									"Vous n'avez pas entré de nom  -_-",
									Toast.LENGTH_SHORT).show();
						} else {
							if (editText_prompt_modifyshoppingitem_quantity.getText()
									.toString().matches("")) {
								shoppingItemQuantity = "1";
							} else {
								shoppingItemQuantity = editText_prompt_modifyshoppingitem_quantity
												.getText()
												.toString();
							}
							
							JSONSaveShoppingItemTask jSONSaveShoppingItemTask = new JSONSaveShoppingItemTask();
							jSONSaveShoppingItemTask.execute();
						}
					}
				})
				.setNegativeButton("Annuler",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dialog.cancel();
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
			}
		});

	}

	@Override
	public void onClickEdit(final int position, Context context,
			ArrayList<ShoppingItem> shoppingItemList) {

		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(ShoppingListActivity.this);
		View promptsView = li.inflate(R.layout.prompt_modifyshoppingitem, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ShoppingListActivity.this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText editText_prompt_modifyshoppingitem_name = (EditText) promptsView.findViewById(R.id.editText_prompt_modifyshoppingitem_name);
		final EditText editText_prompt_modifyshoppingitem_quantity = (EditText) promptsView.findViewById(R.id.editText_prompt_modifyshoppingitem_quantity);

		editText_prompt_modifyshoppingitem_name.setText(Data.getInstance().getShoppingListList().get(shoppingListPosition).getShoppingItems().get(position).getName());
		editText_prompt_modifyshoppingitem_quantity.setText(String.valueOf(Data.getInstance().getShoppingListList().get(shoppingListPosition).getShoppingItems().get(position).getQuantity()));

		// set dialog message
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int id) {
				
				
				shoppingItemName = editText_prompt_modifyshoppingitem_name.getText().toString();
				
				if (shoppingItemName.matches("")) {
					Toast.makeText(
							ShoppingListActivity.this,
							"Vous n'avez pas entré de nom  -_-",
							Toast.LENGTH_SHORT).show();
				} else {
					if (editText_prompt_modifyshoppingitem_quantity.getText()
							.toString().matches("")) {
						shoppingItemQuantity = "1";
					} else {
						shoppingItemQuantity = editText_prompt_modifyshoppingitem_quantity
										.getText()
										.toString();
					}
					
					Data.getInstance().setShoppingItemPosition(position);
					
					JSONEditShoppingItemTask jSONEditShoppingItemTask = new JSONEditShoppingItemTask();
					jSONEditShoppingItemTask.execute();
					
				}
			}
		})
		.setNegativeButton("Annuler",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int id) {
				dialog.cancel();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();		
	}
	
	@Override
	public void onClickDelete(final int position, Context context,
			ArrayList<ShoppingItem> shoppingItemList) {
		
		LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompt_delete, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);

		alertDialogBuilder.setView(promptsView);

		final TextView textView_prompt_delete_message = (TextView) promptsView.findViewById(R.id.textView_prompt_delete_message);
		textView_prompt_delete_message.setText("Voulez-vous vraiment supprimer " + Data.getInstance().getShoppingListList().get(shoppingListPosition).getShoppingItems().get(shoppingListPosition).getName() + " de la liste ?");

		alertDialogBuilder.setCancelable(false).setPositiveButton("Supprimer le contact", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				shoppingItemToDeletePosition = position;
				
				JSONDeleteShoppingItemTask jSONDeleteShoppingItemTask = new JSONDeleteShoppingItemTask();
				jSONDeleteShoppingItemTask.execute();
			}
		}).setNegativeButton("Annuler la suppression",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
	}
	
	private class JSONRetrieveShoppingListsTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().getShoppingLists();
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
					ShoppingListActivity.this,
					ChooseAShoppingListActivity.class);

			startActivity(intent);
			finish();
		}
	}
	
	private class JSONSaveShoppingItemTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().addShoppingItem(shoppingItemName, shoppingItemQuantity);
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
					ShoppingListActivity.this,
					ShoppingListActivity.class);

			startActivity(intent);
			finish();
		}
	}
	
	private class JSONEditShoppingItemTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().editShoppingItem(shoppingItemName, shoppingItemQuantity);
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
					ShoppingListActivity.this,
					ShoppingListActivity.class);

			startActivity(intent);
			finish();
		}
	}
	
	private class JSONDeleteShoppingItemTask extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().deleteShoppingItem(shoppingItemToDeletePosition);
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
					ShoppingListActivity.this,
					ShoppingListActivity.class);

			startActivity(intent);
			finish();
		}
	}
}
