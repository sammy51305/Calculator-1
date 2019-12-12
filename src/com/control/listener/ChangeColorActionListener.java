package com.control.listener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ChangeColorActionListener extends JFrame implements ActionListener {

	private JButton btnChangeColor;
	private JButton[] keyButton;
	private Boolean isChanged;

	public ChangeColorActionListener(JButton btnChangeColor, Boolean isChanged) {
		this.btnChangeColor = btnChangeColor;
		this.isChanged = isChanged;
	}

	public ChangeColorActionListener(JButton btnChangeColor, JButton[] keyButton) {
		this.btnChangeColor = btnChangeColor;
		this.keyButton = keyButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (isChanged) {
			btnChangeColor.setText("Toggle colors");
			btnChangeColor.setBackground(Color.GREEN.darker());
			btnChangeColor.setForeground(Color.WHITE);
			isChanged = false;
		} else {
			btnChangeColor.setText("Untoggle colors");
			btnChangeColor.setBackground(null);
			btnChangeColor.setForeground(Color.BLACK);
			isChanged = true;
		}
	}
}
