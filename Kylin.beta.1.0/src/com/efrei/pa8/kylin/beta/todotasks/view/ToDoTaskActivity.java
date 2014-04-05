package com.efrei.pa8.kylin.beta.todotasks.view;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.todotasks.controler.ToDoTaskListAdapter;
import com.efrei.pa8.kylin.beta.todotasks.controler.ToDoTaskListAdapter.ToDoTaskListAdapterListener;
import com.efrei.pa8.kylin.beta.todotasks.model.ToDoTask;
import com.efrei.pa8.kylin.beta.tools.Data;

public class ToDoTaskActivity extends Activity implements ToDoTaskListAdapterListener{

	ListView listView_todolist_create_todotask;
	Button btn_todolist_create_todotask;
	ToDoTaskListAdapter toDoTaskListAdapter;
	String name;
	String description;
	int userPosition;
	Date deadline;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todotask);
		

		toDoTaskListAdapter = new ToDoTaskListAdapter(this,Data.getInstance().getTodoTaskList());

		listView_todolist_create_todotask = (ListView) findViewById(R.id.listView_todotask_create_todotask);
		listView_todolist_create_todotask.setAdapter(toDoTaskListAdapter);

		btn_todolist_create_todotask = (Button) findViewById(R.id.btn_todotask_create_todotask);
		btn_todolist_create_todotask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ToDoTaskActivity.this);
				
				alertDialogBuilder.setTitle("Attention")
				  .setMessage(Html.fromHtml("This functionality has not been implemented yet."))
				  .setCancelable(false)
				  .setPositiveButton("OK", null);

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		});		
	}

	@Override
	public void onClickShow(int position, Context context, ArrayList<ToDoTask> toDoTaskList) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ToDoTaskActivity.this);
		
		alertDialogBuilder.setTitle("Attention")
		  .setMessage(Html.fromHtml("This functionality has not been implemented yet."))
		  .setCancelable(false)
		  .setPositiveButton("OK", null);

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
		
		/*
		LayoutInflater li = LayoutInflater.from(ToDoTaskActivity.this);
		View promptsView = li.inflate(R.layout.prompt_todotaskdetails, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ToDoTaskActivity.this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		final EditText textView_prompt_todotask_name = (EditText) promptsView.findViewById(R.id.textView_prompt_todotask_name);
		final EditText textView_prompt_todotask_users = (EditText) promptsView.findViewById(R.id.textView_prompt_todotask_users);
		final EditText textView_prompt_todotask_deadline = (EditText) promptsView.findViewById(R.id.textView_prompt_todotask_deadline);
		final EditText textView_prompt_todotask_description = (EditText) promptsView.findViewById(R.id.textView_prompt_todotask_description);

		// set dialog message
		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								// get user input and set it to result

								if (textView_prompt_todotask_name.getText().toString().matches("") || textView_prompt_todotask_users.getText().toString().matches("") || textView_prompt_todotask_description.getText().toString().matches("")|| textView_prompt_todotask_deadline.getText().toString().matches("")){
									Toast.makeText(
											ToDoTaskActivity.this,
											"Vous n'avez pas rempli tous les champs  -_-",
											Toast.LENGTH_SHORT).show();
								} else {
									/// FAIRE L'AJOUT DE TODOTASK 
								}
							}
						})
				.setNegativeButton("Cancel",
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
		*/
	}
}
