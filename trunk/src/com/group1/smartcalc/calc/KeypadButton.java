package com.group1.smartcalc.calc;

public enum KeypadButton {
	
	// Advance button
	  SIN("sin",KeypadButtonCategory.ADVANCE)
	, COS("cos",KeypadButtonCategory.ADVANCE)
	, TAN("tan",KeypadButtonCategory.ADVANCE)
	, COT("cot",KeypadButtonCategory.ADVANCE)
	, ASIN("asin",KeypadButtonCategory.ADVANCE)
	, ACOS("acos",KeypadButtonCategory.ADVANCE)
	, ATAN("atan",KeypadButtonCategory.ADVANCE)
	, LOG("log",KeypadButtonCategory.ADVANCE)
	, LN("ln",KeypadButtonCategory.ADVANCE)
	
	// Constant button
	, PI("PI", KeypadButtonCategory.CONSTANT)
	, E("e", KeypadButtonCategory.CONSTANT)
	
	// Number button
	, ZERO("0",KeypadButtonCategory.NUMBER)
	, ONE("1",KeypadButtonCategory.NUMBER)
	, TWO("2",KeypadButtonCategory.NUMBER)
	, THREE("3",KeypadButtonCategory.NUMBER)
	, FOUR("4",KeypadButtonCategory.NUMBER)
	, FIVE("5",KeypadButtonCategory.NUMBER)
	, SIX("6",KeypadButtonCategory.NUMBER)
	, SEVEN("7",KeypadButtonCategory.NUMBER)
	, EIGHT("8",KeypadButtonCategory.NUMBER)
	, NINE("9",KeypadButtonCategory.NUMBER)
	
	// Operator button
	, PLUS("+",KeypadButtonCategory.OPERATOR)
	, MINUS("-",KeypadButtonCategory.OPERATOR)
	, MULTIPLY("*",KeypadButtonCategory.OPERATOR)
	, DIV("/",KeypadButtonCategory.OPERATOR)
	, MOD("Mod",KeypadButtonCategory.OPERATOR)
	
	// Other
	, DEG_RAD("DEG", KeypadButtonCategory.OTHER)
	, RECIPROC("1/x",KeypadButtonCategory.OTHER)
	, DECIMAL_SEP(".",KeypadButtonCategory.OTHER)
	, SIGN("±",KeypadButtonCategory.OTHER)
	, SQRT("\u221a",KeypadButtonCategory.OTHER)
	, SQR("^",KeypadButtonCategory.OTHER)
	, PERCENT("%",KeypadButtonCategory.OTHER)
	, OPEN("(", KeypadButtonCategory.OTHER)
	, CLOSE(")", KeypadButtonCategory.OTHER)
	
	// Calculate
	, CALCULATE("=",KeypadButtonCategory.RESULT)
	
	// Nut trang
	, DUMMY("",KeypadButtonCategory.DUMMY);

	CharSequence _text; // Display Text
	KeypadButtonCategory _category;
	
	KeypadButton(CharSequence text,KeypadButtonCategory category) {
		_text = text;
		_category = category;
	}

	public CharSequence getText() {
		return _text;
	}
	
	public void setText(String text) {
		_text = text;
	}
}