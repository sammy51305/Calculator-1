package com.model;

import java.awt.Font;

public class RectangleButton extends Buttons {
	public RectangleButton(int x, int y, String label) {
		setText(label);
		setFont(new Font("Comic Sans MS", Font.PLAIN, 2));
		width = 170;
		height = 80;
		setBounds(x, y, width, height);
	}
}
