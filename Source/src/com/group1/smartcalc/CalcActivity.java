package com.group1.smartcalc;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.group1.smartcalc.calc.Calculate;
import com.group1.smartcalc.calc.KeypadAdapter;
import com.group1.smartcalc.calc.KeypadButton;

public class CalcActivity extends Activity {   
	GridView _keypadGrid;
	KeypadAdapter _keypadAdapter;
	TextView _txtInput;
	TextView _txtResult;
	ImageButton _btnClear;
	private boolean isRad = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		_txtInput = (TextView) findViewById(R.id.txtInput);
		_txtResult = (TextView)	findViewById(R.id.txtResult);
		
		/** ------------- Clear button ----------- */
		_btnClear = (ImageButton) findViewById(R.id.btnClear);		
		_btnClear.setOnClickListener(new OnClickListener() {				
			
		
			public void onClick(View v) {
				String currentInput = _txtInput.getText().toString();
				int currentInputLen = currentInput.length();
				int endIndex = currentInputLen - 1;
				
				//kiểm tra nếu chuỗi có <= 1 phần tử
				if (endIndex < 1)
				{
					_txtInput.setText("");
					return;
				}
				if (!Character.isDigit(currentInput.charAt(endIndex))) {	// kiểm tra xem ký tự hiện tại có phải là số không				
					while (!Character.isDigit(currentInput.charAt(endIndex - 1)))  { // check ký tự tiếp theo có phải là số không
						endIndex--;	// nếu không phải thì xóa luôn số đó
						if (endIndex < 1) // //kiểm tra nếu chuỗi có <= 1 phần tử
						{
							_txtInput.setText("");
							return;
						}
					}					
				}

				_txtInput.setText(currentInput.subSequence(0, endIndex));

			}
		});

		_btnClear.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				_txtInput.setText("");
				_txtResult.setText("");
				return false;
			}
		});
		
		/**---------- GridView keypad ------------*/
		_keypadGrid = (GridView) findViewById(R.id.grdButtons);
		_keypadAdapter = new KeypadAdapter(this);
		_keypadGrid.setAdapter(_keypadAdapter);
		_keypadGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			}
		});
		
		_keypadAdapter.setOnButtonClickListener(new OnClickListener() {
			public void onClick(View v) {
				Button btn = (Button) v;
				KeypadButton keypadButton = (KeypadButton) btn.getTag();
				processKeypadInput(keypadButton);
			}
		});
	}
	

	
	/**-------------------Keypad------------------ */
	private void processKeypadInput(KeypadButton keypadButton) {
		String text = keypadButton.getText().toString();
		String currentInput = _txtInput.getText().toString();
		Double result = 0.0;
		int currentInputLen = currentInput.length();
		switch (keypadButton) {
		case DEG_RAD:
			isRad = !isRad;
			if (text == "RAD")
				keypadButton.setText("DEG");
			else
				keypadButton.setText("RAD");
			_keypadGrid.setAdapter(_keypadAdapter);
			_keypadAdapter.notifyDataSetChanged();			
			break;
		case SIGN:
			if (currentInputLen > 0 && currentInput != "0") {
				// nếu có dấu trừ thì bỏ đi
				if (currentInput.charAt(0) == '-') {
					_txtInput.setText(currentInput.subSequence(1,
							currentInputLen));
				}
				// nếu không thì thêm vào
				else {
					_txtInput.setText("-" + currentInput.toString());
				}
			}
			break;
		case DECIMAL_SEP: //cần hoàn thiện thêm
			if(currentInput.contains("."))
				return;
			else
				_txtInput.setText(currentInput.toString() + ".");
			break;		
		case CALCULATE:
			Calculate calc = new Calculate();
			result = calc.calculate(currentInput, isRad);
			_txtResult.setText(result.toString());
			break;
		case RECIPROC:
			Calculate calc1 = new Calculate();
			result = calc1.calculate("1/(" +  currentInput + ")", isRad);
			_txtResult.setText(result.toString());
			break;
		case SQRT:
			_txtInput.setText(currentInput.toString() + "sqrt(");
			break;	
		case PI:
			_txtInput.setText(currentInput.toString() + "pi");
			break;
			
		case SIN:
		case COS:
		case TAN:
		case COT:
		case ASIN:
		case ACOS:
		case ATAN:
		case LN:
		case LOG:
			_txtInput.setText(currentInput.toString() + text + "(");
			break;
		
		case PERCENT:
		case SQR:
		case E:
		case OPEN:
		case CLOSE:		
		case DIV:
		case MULTIPLY:
		case PLUS:
		case MINUS:
		case ONE:
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
		case SIX:
		case SEVEN:
		case EIGHT:
		case NINE:
		case ZERO:
			_txtInput.setText(currentInput.toString() + text);
			break;
		
		default:
			return;
			
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
        	startActivity(new Intent(getApplicationContext(), TestdrawActivity.class));
        	return true;
        case R.id.converter:
        	return true;
        case R.id.equation:
        	startActivity(new Intent(getApplicationContext(), EquationActivity.class));
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