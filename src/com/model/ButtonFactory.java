package com.model;

//ButtonFactory
//generateButtons() 是被宣告在抽象的 ButtonFactory 內，因此也不知道次類別如何實際製作 Buttons。
//而當 generateButtons() 呼叫createButtons() 時，某個具象的次類別 ButtonFactory 會負責建立 Buttons。
public abstract class ButtonFactory {
	// 為求產生Buttons一致，要讓子類別不能修改此方法
	final public Buttons generateButtons(String type, int x, int y, String label) {
		System.out.println("type:" + type + ",x:" + x + ",y:" + y);
		Buttons buttons = createButtons(type, x, y, label);
		return buttons;
	}

	// 讓次類別實作此抽象Method
	abstract Buttons createButtons(String type, int x, int y, String label);
}
