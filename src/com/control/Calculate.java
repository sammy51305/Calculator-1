package com.control;

import java.awt.Font;

import javax.swing.JTextField;

public class Calculate {
	public static double calc(double x, String input, char opt, JTextField inText) {
		inText.setFont(inText.getFont().deriveFont(Font.BOLD));
		double y = Double.parseDouble(input);
		if (opt == '+') {
			return x + y;
		} else if (opt == '-') {
			return x - y;
		} else if (opt == '*') {
			return x * y;
		} else if (opt == '/') {
			return x / y;
		} else if (opt == '%') {
			return x % y;
		}
		inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
		return y;
	}
}
