package cuong.can.a;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Hpt3Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button but1 = (Button) findViewById(R.id.tinh);
        final TextView hien = (TextView) findViewById(R.id.hien);
        but1.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				String sa = ((EditText) findViewById(R.id.a)).getText().toString();
				String sb = ((EditText) findViewById(R.id.b)).getText().toString();
				String sc = ((EditText) findViewById(R.id.c)).getText().toString();
				String sd = ((EditText) findViewById(R.id.d)).getText().toString();
				String sa1 = ((EditText) findViewById(R.id.a1)).getText().toString();
				String sb1 = ((EditText) findViewById(R.id.b1)).getText().toString();
				String sc1 = ((EditText) findViewById(R.id.c1)).getText().toString();
				String sd1 = ((EditText) findViewById(R.id.d1)).getText().toString();
				String sa2= ((EditText) findViewById(R.id.a2)).getText().toString();
				String sb2= ((EditText) findViewById(R.id.b2)).getText().toString();
				String sc2= ((EditText) findViewById(R.id.c2)).getText().toString();
				String sd2 = ((EditText) findViewById(R.id.d2)).getText().toString();
				try {
					double a = Double.parseDouble(sa);
					double b = Double.parseDouble(sb);
					double c = Double.parseDouble(sc);
					double d= Double.parseDouble(sd);
					double a1 = Double.parseDouble(sa1);
					double b1 = Double.parseDouble(sb1);
					double c1 = Double.parseDouble(sc1);
					double d1= Double.parseDouble(sd1);
					double a2 = Double.parseDouble(sa2);
					double b2 = Double.parseDouble(sb2);
					double c2 = Double.parseDouble(sc2);
					double d2= Double.parseDouble(sd2);
						double	b3 = b - b1 * (a / a1);
						double	c3 = c - c1 * (a / a1);
						double	d3 = d - d1 * (a / a1);
						double	b4 = b1 - b2 * (a1 / a2);
						double	c4 = c1 - c2 * (a1 / a2);
						double	d4 = d1 - d2 * (a1 / a2);
						double	c5 = c3 - c4 * (b3 / b4);
						double	d5 = d3 - d4 * (b3 / b4);
						double nz= d5/c5;
						double ny = (d3-c3*nz)/b3;
						double nx=(d-b*ny - c*nz)/a;
						hien.setText("x= "+(nx) +"\n"+"Y= "+(ny)+"\n"+"Z= "+(nz));
				
			}catch (Exception e){
				hien.setText(e.toString());
			}
    }
});
    }
}