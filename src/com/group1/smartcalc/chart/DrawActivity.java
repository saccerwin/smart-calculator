package com.group1.smartcalc.chart;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.group1.smartcalc.AboutUs;
import com.group1.smartcalc.R;
import com.group1.smartcalc.R.layout;
import com.group1.smartcalc.calc.CalcActivity;
import com.group1.smartcalc.equation.EquationActivity;

public class DrawActivity extends Activity {
	private static final OnItemSelectedListener OnItemSelectedListener = null;
	Button nhap;
	Button ve;
	TextView t1, e1, t2, e2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vedothi);
		Spinner sp = (Spinner) findViewById(R.id.spiner);

		ArrayAdapter<CharSequence> ap = ArrayAdapter.createFromResource(this,
				R.array.Tenbieudo, android.R.layout.simple_spinner_item);

		ap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp.setAdapter(ap);
		t1 = (TextView) findViewById(R.id.t1);
		t2 = (TextView) findViewById(R.id.t2);
		final EditText Tunglon = (EditText) findViewById(R.id.giatritung);
		final EditText Hoanhlon = (EditText) findViewById(R.id.giatrihoanh);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				parent.getItemAtPosition(pos).toString();
				pos = pos + 1;
				if (pos == 4) {
					Tunglon.setEnabled(false);
					t1.setEnabled(false);
					t2.setEnabled(false);
					Hoanhlon.setEnabled(false);
				} else {
					Tunglon.setEnabled(true);
					t1.setEnabled(true);
					t2.setEnabled(true);
					Hoanhlon.setEnabled(true);
				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	
		Button nhap = (Button) findViewById(R.id.Them);
		

		Button ve = (Button) findViewById(R.id.Ve);
		

	}


	/** -------------Menu--------------- */
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
		case R.id.equation:
			startActivity(new Intent(getApplicationContext(),
					EquationActivity.class));
			finish();
			return true;
		case R.id.calc:
			startActivity(new Intent(getApplicationContext(),
					CalcActivity.class));
			finish();
			return true;
		case R.id.about:
			startActivity(new Intent(getApplicationContext(), AboutUs.class));
			return true;

		case R.id.help:
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://code.google.com/p/smart-calculator/")));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
