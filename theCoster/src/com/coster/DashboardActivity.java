package com.coster;

import org.json.JSONObject;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.example.androidhive.library.DatabaseHandler;
import com.example.androidhive.library.UserFunctions;

public class DashboardActivity extends TabActivity {
	UserFunctions userFunctions;
	Button btnLogout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        /**
         * Dashboard Screen for the application
         * */        
        // Check login status in database
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext())){
        	setContentView(R.layout.dashboard);
        	
        	/* start tab */
        	Resources res = getResources();
            TabHost tabHost = getTabHost();
            TabHost.TabSpec spec;
            Intent inten;
            
            //tab home
            inten = new Intent().setClass(this, HomeActivity.class);
            spec = tabHost.newTabSpec("tab1").setIndicator("", res.getDrawable(R.drawable.home_icon)).setContent(inten);
            tabHost.addTab(spec);
            
          //tab log
            inten = new Intent().setClass(this, LogActivity.class);
            spec = tabHost.newTabSpec("tab2").setIndicator("", res.getDrawable(R.drawable.log_icon)).setContent(inten);
            tabHost.addTab(spec);
            
          //tab profile
            inten = new Intent().setClass(this, ProfileActivity.class);
            spec = tabHost.newTabSpec("tab3").setIndicator("", res.getDrawable(R.drawable.profile_icon)).setContent(inten);
            tabHost.addTab(spec);
            
        	btnLogout = (Button) findViewById(R.id.btnLogout);
        	tabHost.setCurrentTab(0);
            
        	/* end tab*/
        	
        	btnLogout.setOnClickListener(new View.OnClickListener() {
    			
    			public void onClick(View arg0) {
    				// TODO Auto-generated method stub
    				//userFunctions.logoutUser(getApplicationContext());
    				DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    				userFunctions.deleteLog(db.getUserDetails().get("email"));   
    				System.out.println("Size : "+db.getLogDetails().size());
    				for(int i = 0; i<(db.getLogDetails().size()/3); i++){
    				String goods = ""+db.getLogDetails().get("goods"+i+"");
    				String price = ""+db.getLogDetails().get("price"+i+"");
        			String time = ""+db.getLogDetails().get("time"+i+""); 			      			
        			userFunctions.storeLog(db.getUserDetails().get("email"), goods, price, time);
        			}
    				db.resetTables();
    				Intent login = new Intent(getApplicationContext(), LoginActivity.class);
    	        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        	startActivity(login);
    	        	// Closing dashboard screen
    	        	finish();
    			}
    		});
        	
        }else{
        	// user is not logged in show login screen
        	Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(login);
        	// Closing dashboard screen
        	finish();
        }
       
        
        
        
    }
   
    
    
}