package com.control;

import java.awt.Font;

import javax.swing.JTextField;

public class Calculate {

	/**
	 * 四則運算
	 * @param x：第一個運算元
	 * @param input：第二個運算元
	 * @param opt：運算子
	 * @param inText：文字框
	 * @return 運算結果
	 */
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
