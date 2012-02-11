package com.group1.smartcalc.calc;

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
