package com.group1.smartcalc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Draw1Activity extends Activity {

	float a=TestdrawActivity.a2;
	float b=TestdrawActivity.b2;
	float c=TestdrawActivity.c2;
	float d=TestdrawActivity.d2;
	Paint paint, paintText, paintGraph;
	float ppx = 1, ppy = 1;
	int xx, yy, px, py, i;
	int array[] = { -2, -1, 2, 1 };

	// Int height
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("test", "wefw");
		setContentView(new DrawView(this));

	}

	private class DrawView extends View {

		public DrawView(Context context) {
			super(context);
			Log.i("test", "dd");
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onDraw(Canvas canvas) {

			paint = new Paint();
			paintText = new Paint();
			paintGraph = new Paint();
			paint.setColor(Color.GRAY);
			paint.setStrokeWidth(1);
			paintText.setColor(Color.WHITE);
			paintText.setStrokeWidth(1);
			paintGraph.setColor(Color.GREEN);
			paintGraph.setStrokeWidth(1);

			xx = getWidth() / 2;
			yy = getHeight() / 2;
			if (xx < yy)
				yy = xx;
			else
				xx = yy;

			px = xx * 2 / 5;
			py = xx * 2 / 5;

			canvas.drawLine(0, yy, xx * 2, yy, paint); // ox
			canvas.drawText(">", xx * 2 - 2, yy + 5, paint);

			canvas.drawLine(xx, 0, xx, yy * 2, paint); // oy
			canvas.drawText("^", xx - 3, 7, paint);

			if (a == 0 && b == 0)
				draw1(canvas);
			else if (a == 0)
				draw2(canvas);
			else
				draw3(canvas);

			drawtext(canvas);
		}

		private void draw1(Canvas canvas) {

			float d1 = Math.abs(d);
			if (d1 > 0) {
				while (2 * ppy < d1)
					ppy *= 10;
				while (0.2 * ppy >= d1)
					ppy = ppy / 10;
			}

			Log.i("test", "csv" + String.valueOf(ppy));
			ppx = ppy;
			float x1 = (float) (-2.5 * ppx), y1 = x1 * c + d;
			float x2 = (float) (2.5 * ppx), y2 = x2 * c + d;
			x1 = calx(x1);
			y1 = caly(y1);
			x2 = calx(x2);
			y2 = caly(y2);
			canvas.drawLine(x1, y1, x2, y2, paintGraph);
		}

		private void draw2(Canvas canvas) {
		}

		private void draw3(Canvas canvas) {
		}

		private void drawtext(Canvas canvas) {
			for (i = 0; i < 4; i++) {
				float x1 = ppx * array[i];
				float x2 = calx(x1);
				canvas.drawText("+", x2-3, yy + 4 + 1, paint);
				canvas.drawText(String.valueOf(x1), x2 - 5, yy + 20, paint);
			}
			for (i = 0; i < 4; i++) {
				float y1 = ppy * array[i];
				float y2 = caly(y1);
				canvas.drawText("+", xx - 3, y2+3, paint);
				canvas.drawText(String.valueOf(y1), xx - 25, y2+3, paint);
			}
		}

		private float calx(float x) {
			float x1 = xx + x * px / ppx;
			return x1;
		}

		private float caly(float y) {
			float y1 = (-y / ppy * py + yy);
			return y1;
		}
	}
}
