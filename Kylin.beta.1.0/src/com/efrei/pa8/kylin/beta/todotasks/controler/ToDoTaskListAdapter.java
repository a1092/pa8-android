package com.efrei.pa8.kylin.beta.todotasks.controler;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.efrei.pa8.kylin.beta.R;
import com.efrei.pa8.kylin.beta.todotasks.model.ToDoTask;
import com.efrei.pa8.kylin.beta.tools.Data;

public class ToDoTaskListAdapter extends BaseAdapter{
	private ArrayList<ToDoTask> toDoTaskList;
	private Context context;
	private LayoutInflater layoutInflater;
	private ArrayList<ToDoTaskListAdapterListener> toDoTaskListAdapterListener = new ArrayList<ToDoTaskListAdapterListener>();
	
	public ToDoTaskListAdapter(Context context, ArrayList<ToDoTask> toDoTaskList){
		this.context = context;
		layoutInflater = LayoutInflater.from(this.context);
		this.toDoTaskList = toDoTaskList;
	}
	
	@Override
	public int getCount() {
		return toDoTaskList.size();
	}

	@Override
	public Object getItem(int position) {
		return toDoTaskList.get(position);
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
					R.layout.prompt_todotask, parent, false);
		} else {
			layoutItem = (LinearLayout) convertView;
		}


		TextView textView_prompt_todotask_name_value = (TextView) layoutItem.findViewById(R.id.textView_prompt_todotask_name_value);
		textView_prompt_todotask_name_value.setText(toDoTaskList.get(position).getName());
		
		TextView textView_prompt_todotask_users_value = (TextView) layoutItem.findViewById(R.id.textView_prompt_todotask_users_value);
		String string = "";
		for(int i = 0 ; i < Data.getInstance().getTodoTaskList().get(position).getUsers().size() ; i++){
			for(int j = 0 ; j < Data.getInstance().getUserList().size() ; j ++){
				if(Data.getInstance().getUserList().get(j).getId() == Data.getInstance().getTodoTaskList().get(position).getUsers().get(i).getId()){
					string += Data.getInstance().getUserList().get(j).getUsername() + ";";
				}
			}
		}
		textView_prompt_todotask_users_value.setText(string);
		
		TextView textView_prompt_todotask_deadline_value = (TextView) layoutItem.findViewById(R.id.textView_prompt_todotask_deadline_value);
		textView_prompt_todotask_deadline_value.setText(toDoTaskList.get(position).getDeadline().toString());

		layoutItem.setTag(position);
		layoutItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				Integer position = (Integer) v.getTag();
				clickOnTheToDoTask(toDoTaskList.get(position), position, context, toDoTaskList);
			}
		});
		
		return layoutItem;
	}
	
	public void addListener(ToDoTaskListAdapterListener listener) {
		toDoTaskListAdapterListener.add(listener);
	}
	
	private void clickOnTheToDoTask(ToDoTask toDoTask, int position, Context context, ArrayList<ToDoTask> toDoTaskList) {
    	for(int i = toDoTaskListAdapterListener.size()-1; i >= 0; i--) {
    		toDoTaskListAdapterListener.get(i).onClickShow(position, context, toDoTaskList);
    		}
    }
	
	public interface ToDoTaskListAdapterListener {
		public void onClickShow(int position, Context context, ArrayList<ToDoTask> toDoTaskList);
	}
}
