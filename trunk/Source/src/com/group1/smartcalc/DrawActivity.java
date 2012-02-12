package com.group1.smartcalc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
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

}

