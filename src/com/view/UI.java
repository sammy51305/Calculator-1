package com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.control.ButtonController;

public class UI {

	private JFrame frame;
	private JButton btnChangeColor;
	private JButton[][] keyButton = new JButton[5][4];
	private JTextField inText;

	private String[][] labelsOfBtn = { { "C", "<-", "%", "/" }, { "7", "8", "9", "*" }, { "4", "5", "6", "-" },
			{ "1", "2", "3", "+" }, { ".", "0", "=", "=" } };

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Calcultor");
		frame.setSize(410, 600);// Height And Width Of Window
		frame.setLocationRelativeTo(null); // Move Window To Center

		// 設置Button的字型、長寬
		Font fontOfBtn = new Font("Comic Sans MS", Font.PLAIN, 28);
		int width = 80;// Width Button
		int height = 70;// Height Button
		int marginX = 20;
		int marginY = 60;
		int j = -1;
		int k = -1;
		int[] x = { marginX, marginX + 90, 200, 290 };
		int[] y = { marginY + 100, marginY + 180, marginY + 260, marginY + 340, marginY + 420 };

		// 設定Text
		inText = new JTextField("0");
		inText.setBounds(x[0], marginY, 350, 70);
		inText.setEditable(false);
		inText.setBackground(Color.WHITE);
		inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
		frame.getContentPane().add(inText);

		// 設定Key Buttons
		for (int row = 0; row < y.length; row++) {
			for (int col = 0; col < x.length; col++) {
				if (row == 4 && col == 3)
					break;
				keyButton[row][col] = new JButton(labelsOfBtn[row][col]);
				keyButton[row][col].setFont(fontOfBtn);
				if (row == 4 && col == 2) {
					keyButton[row][col].setBounds(x[col], y[row], 2 * width + 10, height);
				} else {
					keyButton[row][col].setBounds(x[col], y[row], width, height);
				}
				keyButton[row][col].addActionListener(new ButtonController(keyButton, inText));
				frame.add(keyButton[row][col]);
			}
		}

		// 設定換色Button
		btnChangeColor = new JButton();
		btnChangeColor.setBounds(200, 30, 140, 18);
		btnChangeColor.setText("Toggle colors");
		btnChangeColor.setBackground(Color.GREEN.darker());
		btnChangeColor.setForeground(Color.WHITE);
		btnChangeColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChangeColor.addActionListener(new ButtonController(btnChangeColor, keyButton));
		frame.getContentPane().add(btnChangeColor);

		// 顯示視窗
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If Click into The Red Button => End The Process
		frame.setVisible(true);
	}
}
