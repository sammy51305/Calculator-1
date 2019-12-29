package com.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ButtonController extends JFrame implements ActionListener{

	private JButton btnChangeColor;
	private JButton[][] keyButton;
	private JTextField inText;

	private static char opt = ' ';// Storage Oparator
	private static boolean go = true, // Faire Calcule Avec Opt != (=)
			addWrite = true; // Racordé des Nombres dans l'Affichage
	private static double val = 0; // Storage Values For Calcule
	private static boolean isChanged = false;

	// 針對keyButton[][]
	public ButtonController(JButton[][] keyButton, JTextField inText) {
		this.keyButton = keyButton;
		this.inText = inText;
	}

	// 針對改變顏色的按鈕
	public ButtonController(JButton btnChangeColor, JButton[][] keyButton) {
		this.btnChangeColor = btnChangeColor;
		this.keyButton = keyButton;
	}

	/**
	 * 監聽按鈕事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChangeColor) {
			handleColorChange();
		} else if (e.getSource() == keyButton[0][0]) {// C
			handleOpt("C");
		} else if (e.getSource() == keyButton[0][1]) {// <-
			handleOpt("<-");
		} else if (e.getSource() == keyButton[0][2]) {// %
			handleOpt("%");
		} else if (e.getSource() == keyButton[0][3]) {// /
			handleOpt("/");
		} else if (e.getSource() == keyButton[1][0]) {// 7
			handleNumber("7");
		} else if (e.getSource() == keyButton[1][1]) {// 8
			handleNumber("8");
		} else if (e.getSource() == keyButton[1][2]) {// 9
			handleNumber("9");
		} else if (e.getSource() == keyButton[1][3]) {// *
			handleOpt("*");
		} else if (e.getSource() == keyButton[2][0]) {// 4
			handleNumber("4");
		} else if (e.getSource() == keyButton[2][1]) {// 5
			handleNumber("5");
		} else if (e.getSource() == keyButton[2][2]) {// 6
			handleNumber("6");
		} else if (e.getSource() == keyButton[2][3]) {// -
			handleOpt("-");
		} else if (e.getSource() == keyButton[3][0]) {// 1
			handleNumber("1");
		} else if (e.getSource() == keyButton[3][1]) {// 2
			handleNumber("2");
		} else if (e.getSource() == keyButton[3][2]) {// 3
			handleNumber("3");
		} else if (e.getSource() == keyButton[3][3]) {// +
			handleOpt("+");
		} else if (e.getSource() == keyButton[4][0]) {// .
			handleNumber(".");
		} else if (e.getSource() == keyButton[4][1]) {// 0
			handleNumber("0");
		} else if (e.getSource() == keyButton[4][2]) {// =
			handleOpt("=");
		}
	}

	/**
	 * 處理數字按鈕
	 * 
	 * @param num：數字
	 */
	private void handleNumber(String num) {
		if (num.equals(".")) {
			repaintFont();
			if (addWrite) {
				inText.setText(inText.getText() + ".");
			} else {
				inText.setText("0.");
				addWrite = true;
			}
			go = true;
		} else {
			inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText(num);
				} else {
					inText.setText(inText.getText() + num);
				}
			} else {
				inText.setText(num);
				addWrite = true;
			}
			go = true;
		}
	}

	/**
	 * 處理運算按鈕
	 * 
	 * @param str：表+、-、*、/ 、C、->、=
	 */
	private void handleOpt(String optStr) {
		if (optStr.equals("C")) {
			inText.setText("0");
			opt = ' ';
			val = 0;
		} else if (optStr.equals("<-")) {
			repaintFont();
			String str = inText.getText();
			StringBuilder str2 = new StringBuilder();
			for (int i = 0; i < (str.length() - 1); i++) {
				str2.append(str.charAt(i));
			}
			if (str2.toString().equals("")) {
				inText.setText("0");
			} else {
				inText.setText(str2.toString());
			}

		} else if (optStr.equals("%")) {
			repaintFont();
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}
					opt = '%';
					go = false;
					addWrite = false;
				}
		} else if (optStr.equals("=")) {
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}
					opt = '=';
					addWrite = false;
				}
		} else {// +-*/
			repaintFont();
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}
					opt = optStr.charAt(0);
					go = false;
					addWrite = false;
				} else {
					opt = optStr.charAt(0);
				}
		}
	}

	/*
	 * 處理顏色轉換事件
	 */
	private void handleColorChange() {
		if (isChanged) {
			btnChangeColor.setText("Toggle colors");
			btnChangeColor.setBackground(Color.GREEN.darker());
			btnChangeColor.setForeground(Color.WHITE);
			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 4; col++) {
					if (row == 4 && col == 3)
						break;
					keyButton[row][col].setBackground(null);
					if (col == 3) {
						keyButton[row][col].setForeground(Color.BLACK);
					} else if (row == 0) {
						keyButton[row][col].setForeground(Color.BLACK);
					} else if (row == 4 && col == 2) {
						keyButton[row][col].setForeground(Color.BLACK);
					}
				}
			}
			isChanged = false;
		} else {
			btnChangeColor.setText("Untoggle colors");
			btnChangeColor.setBackground(null);
			btnChangeColor.setForeground(Color.BLACK);

			for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 4; col++) {
					if (row == 4 && col == 3)
						break;
					keyButton[row][col].setBackground(Color.WHITE);
					if (col == 3) {
						keyButton[row][col].setForeground(Color.WHITE);// *、-、+
					} else if (row == 0) {
						keyButton[row][col].setForeground(Color.WHITE);// C、<-、%、/
					} else if (row == 4 && col == 2) {
						keyButton[row][col].setForeground(Color.WHITE);// =
					}
				}
			}

			keyButton[0][0].setBackground(Color.RED);// C
			keyButton[0][1].setBackground(Color.ORANGE);// <-
			keyButton[0][2].setBackground(Color.GREEN);// %
			keyButton[0][3].setBackground(Color.PINK);// /
			keyButton[1][3].setBackground(Color.PINK);// *
			keyButton[2][3].setBackground(Color.PINK);// -
			keyButton[3][3].setBackground(Color.PINK);// +
			keyButton[4][2].setBackground(Color.BLUE);// =

			isChanged = true;
		}
	}

	/**
	 * 重設字型
	 */
	private void repaintFont() {
		inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
	}
}
