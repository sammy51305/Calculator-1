package com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.control.ButtonController;
import com.model.ButtonFactory;
import com.model.Buttons;
import com.model.RectangleButtonFactory;

public class UI {

	private JFrame frame;
	private JTextField inText;
	private JButton btnChangeColor;
	private JButton[][] keyButton = new JButton[5][4];
	private static ButtonFactory btnFactory;
	private Buttons[][] keyButtons = new Buttons[5][4];

	private String[][] labelsOfBtn = { { "C", "<-", "%", "/" }, { "7", "8", "9", "*" }, { "4", "5", "6", "-" },
			{ "1", "2", "3", "+" }, { ".", "0", "=", "=" } };

	/**
	 * Create the application.
	 */
	public UI() {
		// 設定視窗
		frame = new JFrame("Calcultor");
		frame.setSize(400, 650);// Height And Width Of Window
		frame.setLocationRelativeTo(null); // Move Window To Center

		// 設定Text
		inText = new JTextField("0");
		inText.setBounds(20, 60, 350, 80);
		inText.setEditable(false);
		inText.setBackground(Color.WHITE);
		inText.setFont(new Font("Comic Sans MS", Font.PLAIN, 33));
		frame.getContentPane().add(inText);

		// initialize();
		initializeButtons();

		// 顯示視窗
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If Click into The Red Button => End The Process
		frame.setVisible(true);
	}

	/**
	 * 目前不用此版本，用下方第二版
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// 設置Button的字型、長寬
		Font fontOfBtn = new Font("Comic Sans MS", Font.PLAIN, 28);
		int width = 80;// Width Button
		int height = 80;// Height Button

		// 設定位置
		int marginX = 20;
		int marginY = 60;
		int[] x = { marginX, marginX + 90, marginX + 180, marginX + 270 };
		int[] y = { marginY + 100, marginY + 190, marginY + 280, marginY + 370, marginY + 460 };

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
		btnChangeColor = new JButton("Toggle colors");
		btnChangeColor.setBounds(200, 30, 140, 20);
		btnChangeColor.setBackground(Color.GREEN.darker());
		btnChangeColor.setForeground(Color.WHITE);
		btnChangeColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChangeColor.addActionListener(new ButtonController(btnChangeColor, keyButton));
		frame.getContentPane().add(btnChangeColor);
	}

	/**
	 * 初始化Buttons，實作Factory Pattern
	 */
	private void initializeButtons() {
		int row_len = 5;
		int col_len = 4;
		int x_initPos = 20;
		int y_initPos = 60;
		int[] x = { x_initPos, x_initPos + 90, x_initPos + 180, x_initPos + 270 };
		int[] y = { y_initPos + 100, y_initPos + 190, y_initPos + 280, y_initPos + 370, y_initPos + 460 };

		btnFactory = new RectangleButtonFactory();
		for (int row = 0; row < row_len; row++) {
			for (int col = 0; col < col_len; col++) {
				System.out.println("row:" + row + ", col:" + col);
				if (row == 4 && col == 3)
					break;
				if (row == 4 && col == 2) {
					keyButtons[row][col] = btnFactory.generateButtons("rectangle", x[col], y[row],
							labelsOfBtn[row][col]);
				} else {
					keyButtons[row][col] = btnFactory.generateButtons("square", x[col], y[row],
							labelsOfBtn[row][col]);
				}
				keyButtons[row][col].addActionListener(new ButtonController(keyButtons, inText));
				frame.add(keyButtons[row][col]);
			}
		}
		// 設定換色Button
		btnChangeColor = new JButton("Toggle colors");
		btnChangeColor.setBounds(200, 30, 140, 20);
		btnChangeColor.setBackground(Color.GREEN.darker());
		btnChangeColor.setForeground(Color.WHITE);
		btnChangeColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChangeColor.addActionListener(new ButtonController(btnChangeColor, keyButtons));
		frame.getContentPane().add(btnChangeColor);
	}
}
