package at.ex;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestcalActivity extends Activity {
	/** Called when the activity is first created. */
	String tag=new String("test");
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		String s = "(3+sin(pi+2))";
		TextView tw = (TextView) findViewById(R.id.textview);
		Log.i(tag,"djhdiw");
		//tw.setText(s);
		tw.setText(((Object) caltulate(s,true)).toString());
	}

	private char note(String s){
    	if (s.equals("sin"))return 's';
    	if (s.equals("cos"))return 'c';
    	if (s.equals("tan")) return 't';
    	if (s.equals("cot")) return 'o';
    	if (s.equals("asin")) return 'S';
      	if (s.equals("acos"))return 'C';
    	if (s.equals("atan")) return 'T';
    	if (s.equals("acot")) return 'O';
    	//if (s=="pi") return 'p';
    	if (s.equals("log")) return 'l';
    	if (s.equals("ln")) return 'L';
    	if (s.equals("sqrt")) return 'q';
    	
		//return 's';
    	return (Character) null;
    }

	private byte priority(char x){
		if ('0'<=x && x<='9') return 1;
    	switch (x) {
		case '.': return 2;
		case 'p': return 3;	
		case 'e': return 3;
		case '+': return 4;
		case '-': return 4;
		case '*': return 5;
		case '/': return 5;
		case '^': return 6;
		case '(': return 7;
		case ')': return 8;
		case 'L': return 9;
		case 'l': return 9;
		case 'q': return 9;
		case '~': return 0;
		default:
			return 10;
		}	
    }
	private double cal(double num, char c){
		switch (c) {
		case 'l':return Math.log10(num);
		case 'L':return Math.log(num);
		case 'q':return Math.sqrt(num);
		default:
			return 0;
		}
	}
	private double cal(double num,char c,boolean isRad){
		if (isRad==false) num=num/180*Math.PI;
		switch (c) {
		case 's':return Math.sin(num);
		case 'c':return Math.cos(num);
		case 't':return Math.tan(num);
		case 'o':return ((double)1/Math.tan(num));
		
		case 'S':return Math.asin(num);
		case 'C':return Math.acos(num);
		case 'T':return Math.atan(num);
		//case 'O':return Math.sin(num);
		
		default:
			return 0;
		}
	}
	private double cal(double num1, double num, char c){
		switch (c) {
		case '+':return(num+num1);
		case '-':return(num-num1);
		case '*':return(num*num1);
		case '/':return(num/num1);
		case '^':return Math.pow(num,num1);
		default:
			return 0;
		}
	}
	public double caltulate(String s,boolean isRad) {
		// TODO Auto-generated method stub
		s='('+s+')';
		stack stk=new stack(10);
		String num;
		stack_char stk_c=new stack_char(10);Log.i(tag, "cal3");
		stk_c.push('~');Log.i(tag, "cal4");
		int leng=s.length(),i=0;
		char k=s.charAt(0),k1;
		byte pri,pri1; Double number;
		
		if (k=='+'||k=='-') stk.push(0);
		while (i<leng){
			
			k=s.charAt(i);
			pri=priority( k);
			Log.i(tag,""+k);
			if (pri<=3){
				
				if (pri==1){
					num="";
					while (i<leng && pri<3){num=num+k;	i++;if(i<leng){k=s.charAt(i);	pri=priority(k);}}
					number=Double.valueOf(num);}
				else
					if (k=='p') {number=Math.PI;i+=2;} else {number=Math.E;i++;}	
				stk.push(number);
	
			}
			else
				if (pri<7) {
					k1=stk_c.pull();pri1=priority(k1);
					while(k1!='('&&priority(k1)>=priority(k))
						{number=cal(stk.pull(), stk.pull(), k1);stk.push(number);Log.i(tag,number.toString());k1=stk_c.pull();}
					stk_c.push(k1);stk_c.push(k);i++;}
				else
					if (pri==7){stk_c.push(k);i++;}
					else
						if (pri==8){
							k1=stk_c.pull();i++;
							while (k1!='('){number=cal(stk.pull(), stk.pull(), k1);stk.push(number);k1=stk_c.pull();}
							number=stk.pull();Log.i(tag,"cal 1");
							k1=stk_c.pull();pri1=priority(k1);
							if(pri1==10||pri1==9){
								if (pri1==9) number=cal(number,k1);
								else number=cal(number, k1, isRad);
								k1=stk_c.pull();pri=priority(k1);
							}
							stk.push(number);stk_c.push(k1);
						}
						else{					
							num="";
							while ('a'<=k&&'z'>=k){num=num+k;k=s.charAt(++i);Log.i(tag,""+k);}
							stk_c.push(note(num));
						}
						
							
					
					 
		}
		return stk.pull();
	}

}