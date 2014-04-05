package com.efrei.pa8.kylin.beta.loans.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.loans.controler.LoanListAdapter;
import com.efrei.pa8.kylin.beta.tools.Data;
import com.efrei.pa8.kylin.beta.web.CustomHttpClientSG;

public class LoansActivity extends Activity{

	ListView listView_loans_loan_list;
	Button btn_loans_create_loan;
	LoanListAdapter loanListAdapter;
	String loanObject;
	int positionLoanLender;
	int positionLoanBorrower;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loans);

		loanListAdapter = new LoanListAdapter(this, Data.getInstance().getLoanList());

		listView_loans_loan_list = (ListView) findViewById(R.id.listView_loans_loan_list);
		listView_loans_loan_list.setAdapter(loanListAdapter);

		btn_loans_create_loan = (Button) findViewById(R.id.btn_loans_create_loan);
		btn_loans_create_loan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				LayoutInflater li = LayoutInflater.from(LoansActivity.this);
				View promptsView = li.inflate(R.layout.prompt_addloan, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						LoansActivity.this);

				alertDialogBuilder.setView(promptsView);

				List<String> listUsernames = new ArrayList<String>();

				for(int i = 0 ; i < Data.getInstance().getUserList().size() ; i++){
					listUsernames.add(Data.getInstance().getUserList().get(i).getUsername());
				}				

				final Spinner spinner_create_loan_lender = (Spinner) promptsView.findViewById(R.id.spinner_create_loan_lender);
				ArrayAdapter<String> spinner_create_loan_lender_Adapter = new ArrayAdapter<String>(LoansActivity.this, android.R.layout.simple_spinner_item, listUsernames);
				spinner_create_loan_lender_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner_create_loan_lender.setAdapter(spinner_create_loan_lender_Adapter);

				final Spinner spinner_create_loan_borrower = (Spinner) promptsView.findViewById(R.id.spinner_create_loan_borrower);
				ArrayAdapter<String> spinner_create_loan_borrower_Adapter = new ArrayAdapter<String>(LoansActivity.this, android.R.layout.simple_spinner_item, listUsernames);
				spinner_create_loan_borrower_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinner_create_loan_borrower.setAdapter(spinner_create_loan_borrower_Adapter);

				final EditText editText_create_loan_object = (EditText) promptsView.findViewById(R.id.editText_create_loan_object);

				// set dialog message
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {

						if (editText_create_loan_object.getText().toString()
								.matches("")) {
							Toast.makeText(
									LoansActivity.this,
									"Vous n'avez pas entré d'objet  -_-",
									Toast.LENGTH_SHORT).show();
						} else {
							loanObject = editText_create_loan_object.getText().toString();
							positionLoanLender = spinner_create_loan_lender.getSelectedItemPosition();
							positionLoanBorrower = spinner_create_loan_borrower.getSelectedItemPosition();
							
							JSONcreateLoan jSONcreateLoan = new JSONcreateLoan();
							jSONcreateLoan.execute();
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

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		});
	}
	
	private class JSONcreateLoan extends AsyncTask<Void, Integer, Boolean>{

		@Override
		protected Boolean doInBackground(Void... arg0) {
			try {
				CustomHttpClientSG.getInstance().newLoan(loanObject, positionLoanLender, positionLoanBorrower);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}

		@Override
		protected void onPostExecute(Boolean result) {			
			super.onPostExecute(result);
			Intent intent = new Intent(LoansActivity.this, LoansActivity.class);
			startActivity(intent);
			finish();
		}
	}
}
