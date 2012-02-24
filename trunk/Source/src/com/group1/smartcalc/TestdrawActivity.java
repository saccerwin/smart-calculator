package com.group1.smartcalc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TestdrawActivity extends Activity {
	protected static final Uri DrawActivity = null;
	/** Called when the activity is first created. */
	EditText a, b, c, d;
	String a1, b1, c1, d1;
	public static Float a2, b2, c2, d2;
	Button draw;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testdraw);

		a = (EditText) findViewById(R.id.a);
		b = (EditText) findViewById(R.id.b);
		c = (EditText) findViewById(R.id.c);
		d = (EditText) findViewById(R.id.d);
		draw = (Button) findViewById(R.id.draw);
		draw.setOnClickListener(click);

	}

	public OnClickListener click = new OnClickListener() {

		public void onClick(View v) {
			
			a1 = a.getText().toString();
			b1 = b.getText().toString();
			c1 = c.getText().toString();
			d1 = d.getText().toString();

			if (a1.equals("") || b1.equals("") || c1.equals("")
					|| d1.equals("")) {
				
				AlertDialog.Builder build = new AlertDialog.Builder(
						TestdrawActivity.this);
				
				build.setTitle("Info Missing");
				build.setMessage("Please input value of a,b,c and d!");
				build.setPositiveButton("Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
							}
						});
				build.show();
			
			} else {
				
				a2 = Float.valueOf(a1);
				b2 = Float.valueOf(b1);
				c2 = Float.valueOf(c1);
				d2 = Float.valueOf(d1);
				
				
				if (a2 == 0 && b2 == 0 && c2 == 0 && d2 == 0) {
					
					AlertDialog.Builder build = new AlertDialog.Builder(
							TestdrawActivity.this);
					build.setTitle("Info Missing");
					build.setMessage("Value of a,b,c and d is invalid!");
					build.setPositiveButton("Close",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
								}
							});
					build.show();
				
				} else {
					
					startActivity(new Intent(TestdrawActivity.this,
							Draw1Activity.class));
					a.setText("");
					b.setText("");
					c.setText("");
					d.setText("");
				}
			}

		}
	};
}