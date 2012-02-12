package com.group1.smartcalc.calc;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.group1.smartcalc.R;

public class KeypadAdapter extends BaseAdapter {
	private Context _context;
	
	// Declare button click listener variable
	private OnClickListener _onButtonClick;
	
	public KeypadAdapter(Context c) {
		_context = c;
	}

	// Method to set button click listener variable
	public void setOnButtonClickListener(OnClickListener listener) {
		_onButtonClick = listener;
	}

	public int getCount() {
		return _buttons.length;
	}

	public Object getItem(int position) {
		return _buttons[position];
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ButtonView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		Button btn;
		if (convertView == null) { 
			btn = new Button(_context);
			KeypadButton keypadButton = _buttons[position];
	//		Log.d("Test", String.format("Position: %dd, name: %s", position,_buttons[position].getText()));
			switch(keypadButton._category)
			{
			case ADVANCE:
				btn.setBackgroundResource(R.drawable.keypad_advance);
				break;	
			case NUMBER:
				btn.setBackgroundResource(R.drawable.keypad);
				break;
			case OPERATOR:
				btn.setBackgroundResource(R.drawable.keypad_op);
				break;
			case CONSTANT:
				btn.setBackgroundResource(R.drawable.keypad_const);
				break;
			case OTHER:
				btn.setBackgroundResource(R.drawable.keypad_other);
				break;
			case RESULT:
				btn.setBackgroundResource(R.drawable.keypad_result);
				break;
			case DUMMY:
				btn.setBackgroundResource(R.drawable.app_vertical);
				break;
			default:
				btn.setBackgroundResource(R.drawable.keypad);
				break;
			}
			
			// Set OnClickListener của button cho _onButtonClick
			if(keypadButton != KeypadButton.DUMMY)
				btn.setOnClickListener(_onButtonClick);
			else
				btn.setClickable(false);
			// Set tag cho button
			btn.setTag(keypadButton);
		} else {
			btn = (Button) convertView;
		}
		
		btn.setText(_buttons[position].getText());
		return btn;
	}

	// Tạo mảng chứa các button bên trong (theo thứ tự)
	private KeypadButton[] _buttons = { 
			KeypadButton.SQRT, KeypadButton.SQR,
			KeypadButton.OPEN, KeypadButton.CLOSE, KeypadButton.SEVEN,
			KeypadButton.EIGHT, KeypadButton.NINE, KeypadButton.RECIPROC,
			KeypadButton.SIGN, KeypadButton.SIN, KeypadButton.COS,
			KeypadButton.TAN, KeypadButton.COT, KeypadButton.FOUR,
			KeypadButton.FIVE, KeypadButton.SIX, KeypadButton.PLUS,
			KeypadButton.MINUS, KeypadButton.ASIN, KeypadButton.ACOS,
			KeypadButton.ATAN, KeypadButton.MOD, KeypadButton.ONE,
			KeypadButton.TWO, KeypadButton.THREE, KeypadButton.MULTIPLY,
			KeypadButton.DIV, KeypadButton.LOG, KeypadButton.LN,
			KeypadButton.E, KeypadButton.DEG_RAD, KeypadButton.PI,
			KeypadButton.ZERO, KeypadButton.DECIMAL_SEP,
			KeypadButton.CALCULATE, 
		};

}
