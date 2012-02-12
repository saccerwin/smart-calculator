package com.group1.smartcalc.calc;

public class Calculate {
	private char note(String s){
    	if (s.equals("sin"))return 's';
    	if (s.equals("cos"))return 'c';
    	if (s.equals("tan")) return 't';
    	if (s.equals("cot")) return 'o';
    	if (s.equals("asin")) return 'S';
      	if (s.equals("acos"))return 'C';
    	if (s.equals("atan")) return 'T';
    	if (s.equals("acot")) return 'O';
    	
    	if (s.equals("log")) return 'l';
    	if (s.equals("ln")) return 'L';
    	if (s.equals("sqrt")) return 'q';
    	
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
	public double calculate(String s,boolean isRad) {
		// TODO Auto-generated method stub
		s='('+s+')';
		stack stk=new stack(50);
		String num;
		stack_char stk_c=new stack_char(50);
		stk_c.push('~');
		int leng=s.length(),i=0;
		char k=s.charAt(0),k1;
		byte pri,pri1; Double number;
		
		
		while (i<leng){
			
			k=s.charAt(i);
			pri=priority( k);
			
			if (pri<=3){
				
				if (pri==1){
					num="";
					while (i<leng && pri<3)
						{num=num+k;	i++;if(i<leng){k=s.charAt(i);	pri=priority(k);}}
					number=Double.valueOf(num);}
				else
					if (k=='p') {number=Math.PI;i+=2;} else {number=Math.E;i++;}	
				stk.push(number);	
			}
			else
				if (pri<7) {
					k1=stk_c.pull();pri1=priority(k1);
					while(k1!='('&&priority(k1)>=priority(k))
						{number=cal(stk.pull(), stk.pull(), k1);stk.push(number);k1=stk_c.pull();}
					stk_c.push(k1);stk_c.push(k);i++;}
				else
					if (pri==7){
						stk_c.push(k);i++;k=s.charAt(i);
						if (k=='+'||k=='-') stk.push(0);}
					else
						if (pri==8){
							k1=stk_c.pull();i++;
							while (k1!='(')
								{number=cal(stk.pull(), stk.pull(), k1);stk.push(number);k1=stk_c.pull();}
							number=stk.pull();
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
							while ('a'<=k&&'z'>=k){num=num+k;k=s.charAt(++i);}
							stk_c.push(note(num));
						}					
					 
		}
		return stk.pull();
	}
	public class stack {
		private double[] data;
		private int top;
		public stack(int size){
			top=-1;
			data= new double[size];
		}
		public double pull(){
			return(data[top--]);
		}
		public void push(double x){
			data[++top]=x;
		}
		public boolean isEmpty(){
			if (top>-1)return false;
			return true;
		}
		
		
	}
	public class stack_char {
		private char[] data;
		private int top;
		public stack_char(int size){
			top=-1;
			data= new char[size];
		}
		public char pull(){
			return(data[top--]);
		}
		public void push(char x){
			data[++top]=x;
		}
		public boolean isEmpty(){
			if (top>-1)return false;
			return true;
		}
	}
}
