package com.group1.smartcalc;

import com.group1.smartcalc.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {	
    	Dialog dialog;
        switch (item.getItemId()) {
        
        case R.id.about:
        	dialog = new Dialog(MainActivity.this);
            
            dialog.setContentView(R.layout.aboutus);
            dialog.setTitle("About us");
            dialog.setCancelable(true);
            dialog.show();
    
        	return true;
        	
        case R.id.help:
        	dialog = new Dialog(MainActivity.this);
            
            dialog.setContentView(R.layout.helplayout);
            dialog.setTitle("Help");
            dialog.setCancelable(true);
            dialog.show();

            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
}