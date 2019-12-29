package com.model;

import java.awt.Font;

public class SquareButton extends Buttons {
	public SquareButton( int x, int y, String label) {
		setText(label);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 28));
		width = 80;
		height = 80;
		setBounds(x, y, width, height);
	}
}
