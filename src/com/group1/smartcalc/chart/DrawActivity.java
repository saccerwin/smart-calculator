package com.group1.smartcalc.chart;

import com.group1.smartcalc.AboutUs;
import com.group1.smartcalc.R;
import com.group1.smartcalc.R.id;
import com.group1.smartcalc.R.menu;
import com.group1.smartcalc.calc.CalcActivity;
import com.group1.smartcalc.equation.EquationActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class DrawActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Drawview(this) );
	}
	
	private class Drawview extends View {
		Paint paint = new Paint();
		public Drawview(Context context) {
			super(context);
			paint.setColor(Color.GREEN);
			paint.setStrokeWidth(2);
		}
		public void onDraw(Canvas canvas){
			canvas.drawLine(0, 350, 460, 350, paint); //ox
			canvas.drawText(">", 460, 355, paint);

			canvas.drawLine(230, 10, 230, 800, paint); //oy
			canvas.drawText("^",227,13,paint);

			canvas.drawText("0", 233, 347, paint); //0}
		}

	
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
        case R.id.equation:
        	startActivity(new Intent(getApplicationContext(), EquationActivity.class));
        	finish();
        	return true;
        case R.id.calc:
        	startActivity(new Intent(getApplicationContext(), CalcActivity.class));
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

