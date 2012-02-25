package com.group1.smartcalc.equation;

import com.group1.smartcalc.AboutUs;
import com.group1.smartcalc.R;
import com.group1.smartcalc.R.id;
import com.group1.smartcalc.R.layout;
import com.group1.smartcalc.R.menu;
import com.group1.smartcalc.calc.CalcActivity;
import com.group1.smartcalc.chart.DrawActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EquationActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.equation);
		final Button button = (Button) findViewById(R.id.tinh);
		final TextView hien = (TextView) findViewById(R.id.hien);
		button.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View view) {
				String sa = ((EditText) findViewById(R.id.a)).getText()
						.toString();
				String sb = ((EditText) findViewById(R.id.b)).getText()
						.toString();
				String sc = ((EditText) findViewById(R.id.c)).getText()
						.toString();
				try {

					double a = Double.parseDouble(sa);
					double b = Double.parseDouble(sb);
					double c = Double.parseDouble(sc);
					if (a == 0) {
						if (b == 0) {
							if (c == 0) {
								hien.setText("Phuong trinh vo so nghiem");
							} else {
								hien.setText("Phuong trinh vo nghiem");
							}
						} else
							hien.setText("x= " + (Math.sqrt((-c / b))));
					} else {
						double delta = (b * b - 4 * a * c);
						if (delta < 0) {
							hien.setText("Phuong trinh vo nghiem");
						} else if (delta == 0) {
							hien.setText("x= " + ((-b * b) / (2 * a)));
						}

						else {
							double candel = Math.sqrt(delta);
							hien.setText("x1 = " + ((b * b + candel) / (2 * a))
									+ "\n" + "x2 = "
									+ ((b * b - candel) / (2 * a)));
						}
					}

				}

				catch (Exception ex) {
					hien.setText(ex.toString());
				}

			}
		});
	}
	
    /**-------------Menu---------------*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {	
        switch (item.getItemId()) {
        case R.id.graph:
        case R.id.converter:
        	return true;
        case R.id.calc:
        	startActivity(new Intent(getApplicationContext(), CalcActivity.class));
        	finish();
        	return true;
        case R.id.chart:
        	startActivity(new Intent(getApplicationContext(), DrawActivity.class));
        	finish();
        	return true;
        case R.id.about:
        	startActivity(new Intent(getApplicationContext(), AboutUs.class));
        	return true;
        	
        case R.id.help:
        	startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://code.google.com/p/smart-calculator/")));
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}