package com.group1.smartcalc.calc;

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
