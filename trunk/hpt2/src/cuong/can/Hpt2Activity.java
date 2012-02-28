package cuong.can;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Hpt2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button but1 = (Button) findViewById(R.id.tinh);
        final TextView hien = (TextView) findViewById(R.id.hien);
        but1.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View arg0) {
				String sa = ((EditText) findViewById(R.id.a)).getText().toString();
				String sb = ((EditText) findViewById(R.id.b)).getText().toString();
				String sc = ((EditText) findViewById(R.id.c)).getText().toString();
				String sa1 = ((EditText) findViewById(R.id.a1)).getText().toString();
				String sb1 = ((EditText) findViewById(R.id.b1)).getText().toString();
				String sc1 = ((EditText) findViewById(R.id.c1)).getText().toString();
			try {
				double a = Double.parseDouble(sa);
				double b = Double.parseDouble(sb);
				double c = Double.parseDouble(sc);
				double a1 = Double.parseDouble(sa1);
				double b1 = Double.parseDouble(sb1);
				double c1 = Double.parseDouble(sc1);
				double d=a*b1 - a1*b;
				double dx=c*b1 - c1*b;
				double dy=a*c1-a1*c;
				if (d!=0) {
					hien.setText("x= "+ (dx/d)+"\n" +"y= "+(dy/d));
				}
				else if((d==0 && dx!=0) || (d==0 && dy!=0)) {
					hien.setText("He phuong trinh vo nghiem");
				}
				else if((d==0 && dx==0) && (d==0 && dy==0)) {
					hien.setText("He phuong trinh co vo so nghiem");
				}
			}catch (Exception e){
			
			hien.setText(e.toString());
				
			}
        	
        }
    });
}
}