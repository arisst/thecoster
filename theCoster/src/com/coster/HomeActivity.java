package com.coster;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidhive.library.DatabaseHandler;
import com.example.androidhive.library.UserFunctions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends Activity {
	Button popupFood;
	Button popupCollege;
	Button popupFuel;
	Button popupOther;
	int Today;
	private static String KEY_SUM = "sum";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		DatabaseHandler db2 = new DatabaseHandler(getApplicationContext());
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText("Periode : " + db2.getUserDetails().get("start"));
		TextView tv2 = (TextView) findViewById(R.id.textView2);
		tv2.setText("Bulanan : " + db2.getUserDetails().get("credit"));
		TextView tv3 = (TextView) findViewById(R.id.textView3);
		int harian = Integer.valueOf(db2.getUserDetails().get("credit"));
		UserFunctions userFunction =  new UserFunctions();
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		String imel = db.getUserDetails().get("email");
		JSONObject sum = userFunction.sumToday(imel);
		System.out.println("emaillll:"+ imel);
		
		try {
			int sumToday = Integer.parseInt(sum.getString("sum"));
			Today = (harian/30)-sumToday;
			
			tv3.setText("Harian : " +Today);
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}

		// popup
		
		popupFood = (Button) findViewById(R.id.popupBtn_food);
		popupCollege = (Button) findViewById(R.id.popupBtn_college);
		popupFuel = (Button) findViewById(R.id.popupBtn_fuel);
		popupOther = (Button) findViewById(R.id.popupBtn_other);
		
		popupFood.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				showPopUpFood();
			}
		});
popupCollege.setOnClickListener(new OnClickListener() {

			
			public void onClick(View v) {
				showPopUpCollege();
			}
		});
popupFuel.setOnClickListener(new OnClickListener() {

	
	public void onClick(View v) {
		showPopUpFuel();
	}
});
popupOther.setOnClickListener(new OnClickListener() {

	
	public void onClick(View v) {
		showPopUpOther();
	}
});
	}

	private void showPopUpFood() {

		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("Food");
		helpBuilder.setMessage("Price");
		//edit text input
		final EditText input = new EditText(this);
		input.setSingleLine();
		input.setText("");
		
		helpBuilder.setView(input);
		helpBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						Date date = new Date();
						String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
						db.addLog("Makan", ""+input.getText(), dateString);
						Today = Today-Integer.parseInt(""+input.getText());
						
					}
				});

		helpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

						
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});

				// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	private void showPopUpCollege() {

		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("College");
		helpBuilder.setMessage("Price");
		//edit text input
		final EditText input = new EditText(this);
		input.setSingleLine();
		input.setText("");
		
		helpBuilder.setView(input);
		helpBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						Date date = new Date();
						String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
						db.addLog("Kuliah", ""+input.getText(), dateString);
						Today = Today-Integer.parseInt(""+input.getText());
						
					}
				});

		helpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

						
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});

				// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	private void showPopUpFuel() {

		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("Fuel");
		helpBuilder.setMessage("Price");
		//edit text input
		final EditText input = new EditText(this);
		input.setSingleLine();
		input.setText("");
		
		helpBuilder.setView(input);
		helpBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						Date date = new Date();
						String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
						db.addLog("Bensin", ""+input.getText(), dateString);
						Today = Today-Integer.parseInt(""+input.getText());
					}
				});

		helpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

						
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});

				// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	private void showPopUpOther() {

		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("Other");
		helpBuilder.setMessage("Price");
		//edit text input
		final EditText inputGood = new EditText(this);
		final EditText inputPrice = new EditText(this);
		inputGood.setSingleLine();
		inputGood.setText("");
		inputPrice.setSingleLine();
		inputPrice.setText("");
		
		helpBuilder.setView(inputGood);
		
		helpBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// Do nothing but close the dialog
					}
				});

		helpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

						
					public void onClick(DialogInterface dialog, int which) {
						// Do nothing
					}
				});

				// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	// end popup
}
