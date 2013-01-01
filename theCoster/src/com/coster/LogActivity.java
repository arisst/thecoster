package com.coster;

import com.example.androidhive.library.DatabaseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class LogActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log_layout);
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		TextView tv = (TextView)findViewById(R.id.textView1);
		
		for(int i = 0; i<(db.getLogDetails().size()/3); i++){
	
			tv.setText("Goods :"+db.getLogDetails().get("goods"+i+"")+ "Prices :"+db.getLogDetails().get("price"+i+"")+ "Time :"+db.getLogDetails().get("time"+i+""));
			
			
			
		}
		
		
	}
	
	
	
	
	
	
	

}
