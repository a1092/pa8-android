package com.efrei.pa8.kylin.beta.loans.controler;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.loans.model.Loan;

public class LoanListAdapter extends BaseAdapter{
	private ArrayList<Loan> loanList;
	private Context context;
	private LayoutInflater layoutInflater;
	
	public LoanListAdapter(Context context, ArrayList<Loan> loanList){
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.loanList = loanList;
	}
	
	@Override
	public int getCount() {
		return loanList.size();
	}

	@Override
	public Object getItem(int position) {
		return loanList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LinearLayout layoutItem;
		
		if (convertView == null) {
			layoutItem = (LinearLayout) layoutInflater.inflate(
					R.layout.prompt_loan, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}


		TextView textView_layout_loan_lender_value = (TextView) layoutItem.findViewById(R.id.textView_layout_loan_lender_value);
		textView_layout_loan_lender_value.setText(loanList.get(position).getNameLender());
		
		TextView textView_layout_loan_object_value = (TextView) layoutItem.findViewById(R.id.textView_layout_loan_object_value);
		textView_layout_loan_object_value.setText(loanList.get(position).getObject());
		
		TextView textView_layout_loan_borrower_value = (TextView) layoutItem.findViewById(R.id.textView_layout_loan_borrower_value);
		textView_layout_loan_borrower_value.setText(loanList.get(position).getNameBorrower());
	
		return layoutItem;
	}
}
