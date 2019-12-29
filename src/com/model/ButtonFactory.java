package com.model;

public abstract class ButtonFactory {
	
	final public Buttons generateButtons(String type, int x, int y, String label) {
		System.out.println("type:"+type +",x:"+ x + ",y:" + y);
		Buttons buttons = createButtons(type, x, y, label);
		return buttons;
	}
	
	abstract Buttons createButtons(String type, int x, int y, String label);
}
