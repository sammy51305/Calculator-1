package com.model;

public class RectangleButtonFactory extends ButtonFactory {

	@Override
	Buttons createButtons(String type, int x, int y, String label) {
		if(type.equals("square")) {
			return new SquareButton(x, y, label);
		} else if(type.equals("rectangle")) {
			return new RectangleButton(x, y, label);
		} else {
			return null;
		}
	}

}
