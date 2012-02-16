package pt2.a;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PhuongTrinhBac2Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button button = (Button) findViewById(R.id.tinh);
        final TextView hien = (TextView) findViewById(R.id.hien);
        button.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View view) {
				String sa = ((EditText) findViewById(R.id.a)).getText().toString();
				String sb = ((EditText) findViewById(R.id.b)).getText().toString();
				String sc = ((EditText) findViewById(R.id.c)).getText().toString();
				try 
				{
			
				double a = Double.parseDouble(sa);
				double b = Double.parseDouble(sb);
				double c = Double.parseDouble(sc);
				if(a==0)
				{
					if(b==0)
					{
						if(c==0)
						{
							hien.setText("Phuong trinh vo so nghiem");
						}
						else {
							hien.setText("Phuong trinh vo nghiem");
					}
					}
					else
						hien.setText("x= "+(Math.sqrt((-c/b))));
				}
					else {
						double delta=(b*b-4*a*c);
						if(delta<0){
							hien.setText("Phuong trinh vo nghiem");
						}
						else
							if(delta==0)
							{
								hien.setText("x= "+ ((-b*b)/(2*a)));
							}
						
						else
						{
							double candel=Math.sqrt(delta);
							hien.setText("x1 = " + ((b*b + candel)/(2 * a)) + "\n" + "x2 = " + ((b*b-candel)/(2 * a)));
						}
					}
						
				}
			
				catch (Exception ex){
					hien.setText(ex.toString());
				}
				
			}
        });
    }
}
				
			
        	
        
    
