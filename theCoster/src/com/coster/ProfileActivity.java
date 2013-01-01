package com.coster;
	
import org.json.JSONObject;

import com.example.androidhive.library.DatabaseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		//TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);
		DatabaseHandler db2 = new DatabaseHandler(getApplicationContext());
		
		TextView tvName = (TextView)findViewById(R.id.profileName);
		TextView tvEmail = (TextView)findViewById(R.id.profileEmail);
		tvName.setText("Name :"+db2.getUserDetails().get("name"));
		tvEmail.setText("Email :"+db2.getUserDetails().get("email"));
		
		
	}

}