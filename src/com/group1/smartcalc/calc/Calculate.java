package com.group1.smartcalc.calc;

public class Calculate {
	
	// cac phep toan mot ngoi khi luu vao stack phep toan se co gia tri nhu trong ham note
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

	//thu
	//ham xet do uu tien 
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
	//ham tinh gia tri cua bieu thuc 1 ngoi ko tinh bieu thuc luong giac
	private double cal(double num, char c){
		switch (c) {
		case 'l':return Math.log10(num);
		case 'L':return Math.log(num);
		case 'q':return Math.sqrt(num);
		default:
			return 0;
		}
	}
	//ham tinh gia tri cua bieu thuc luong giac
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
	
	// ham tinh gia tri bieu thuc 2 ngo^i
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
	
	//phuong thuc tinh toan gia tri bieu thuc
	public double calculate(String s,boolean isRad) {
		// TODO Auto-generated method stub
		s='('+s+')';// them dau ngoac vao dau va cuoi
		stack stk=new stack(50);//stack luu tru cac gia tri so
		String num;
		stack_char stk_c=new stack_char(50);//stack luu tru cac phep toan
		stk_c.push('~');
		int leng=s.length(),i=0;
		char k=s.charAt(0),k1;
		byte pri,pri1; Double number;
		
		// xet lan luot cac ky tu trong xau s
		while (i<leng){
			
			k=s.charAt(i);// k luu tru gia tri dang xet tai thoi diem hien tai
			pri=priority( k);//pri luu tru do uu tien cua k
			
			if (pri<=3){// tuong duong voi k la ky tu bat dau cua mot so, ta tien hanh doc so do roi luu vao stack so
				
				if (pri==1){//tuong ung voi k la ky tu la chu so
					num="";
					// doc toi khi k ko con la chu so va ko phai la dau "."
					while (i<leng && pri<3)
						{num=num+k;	i++;if(i<leng){k=s.charAt(i);	pri=priority(k);}}
					number=Double.valueOf(num);}
				else
					if (k=='p') {number=Math.PI;i+=2;} 
					else {number=Math.E;i++;}	
				
				stk.push(number);// luu so vua doc duoc vao stack	
			}
			else
				if (pri<7) { // tuong ung voi k la cac phep toan 2 ngoi + - * / ^
							//doc ky tu k1 tu stack phep toan neu thu tu uu tien cua kytu k1>k thi thuc hien phep tinh voi k1
					k1=stk_c.pull();pri1=priority(k1);
					while(k1!='('&&priority(k1)>=priority(k))
						{number=cal(stk.pull(), stk.pull(), k1);stk.push(number);k1=stk_c.pull();}
					stk_c.push(k1);stk_c.push(k);i++;}
				else
					if (pri==7){//tuog ung voi dau (. cho ( vao stack phep toan 
						stk_c.push(k);i++;k=s.charAt(i);
						// neu lien sau dau ngoac la cong hoac tru thi them 0 vao stack so
						//vi du (-2+3) ta them 0  thanh (0-2+3)
						if (k=='+'||k=='-') stk.push(0);}
					else
						if (pri==8){//tuong ung voi ngoac dong
							k1=stk_c.pull();i++;
							// lay cac phep toan trong stack phep toan ra thuc hien den khi gap dau (
							while (k1!='(')
								{number=cal(stk.pull(), stk.pull(), k1);stk.push(number);k1=stk_c.pull();}
							number=stk.pull();
							k1=stk_c.pull();pri1=priority(k1);
							
							//khi gap dau ( ta kiem tra neu phep toan truoc ( la phep toan 1 ngoi thi thuc hien
							if(pri1==10||pri1==9){
								if (pri1==9) number=cal(number,k1);
								else number=cal(number, k1, isRad);
								k1=stk_c.pull();pri=priority(k1);
							}
							stk.push(number);stk_c.push(k1);
						}
						else{//tuong ung voi phep toan 1 ngoi ta cho vao stack phep toan					
							num="";
							while ('a'<=k&&'z'>=k){num=num+k;k=s.charAt(++i);}
							stk_c.push(note(num));
						}					
					 
		}
		return stk.pull();
	}
	
	public class stack {// stack kieu double
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
	public class stack_char {//stack kieu char
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
