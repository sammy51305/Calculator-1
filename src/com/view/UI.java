package com.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.control.Calculate;
import com.control.listener.ChangeColorActionListener;

public class UI implements ActionListener {

	private JFrame frame;
	private JButton btnChangeColor;
	private JButton[][] keyButton;
	private JTextField inText;

	private String[][] labelsOfBtn = { { "C", "<-", "%", "/" }, { "7", "8", "9", "*" }, { "4", "5", "6", "-" },
			{ "1", "2", "3", "+" }, { ".", "0", "=", "=" } };
	private Boolean isChanged = false;
	private char opt = ' ';// Storage Oparator
	private boolean go = true, // Faire Calcule Avec Opt != (=)
			addWrite = true; // Racordé des Nombres dans l'Affichage
	private double val = 0; // Storage Values For Calcule

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

		// 設定換色Button
		btnChangeColor = new JButton();
		btnChangeColor.setBounds(200, 30, 140, 18);
		btnChangeColor.setText("Toggle colors");
		btnChangeColor.setBackground(Color.GREEN.darker());
		btnChangeColor.setForeground(Color.WHITE);
		btnChangeColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChangeColor.addActionListener(new ChangeColorActionListener(btnChangeColor, isChanged));
		frame.getContentPane().add(btnChangeColor);

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
		keyButton = new JButton[5][4];
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
				keyButton[row][col].addActionListener(this);
				frame.add(keyButton[row][col]);
			}
		}

		// 顯示視窗
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If Click into The Red Button => End The Process
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == keyButton[0][0]) {// C
			System.out.println("C");
			inText.setText("0");
			opt = ' ';
			val = 0;
		} else if (e.getSource() == keyButton[0][1]) {// <-
			System.out.println("<-");
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
		} else if (e.getSource() == keyButton[0][2]) {// %
			System.out.println("%");
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
		} else if (e.getSource() == keyButton[0][3]) {// /
			System.out.println("/");
			repaintFont();
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}
					opt = '/';
					go = false;
					addWrite = false;
				} else {
					opt = '/';
				}
		} else if (e.getSource() == keyButton[1][0]) {// 7
			System.out.println("7");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("7");
				} else {
					inText.setText(inText.getText() + "7");
				}
			} else {
				inText.setText("7");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[1][1]) {// 8
			System.out.println("8");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("8");
				} else {
					inText.setText(inText.getText() + "8");
				}
			} else {
				inText.setText("8");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[1][2]) {// 9
			System.out.println("9");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("9");
				} else {
					inText.setText(inText.getText() + "9");
				}
			} else {
				inText.setText("9");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[1][3]) {// *
			System.out.println("*");
			repaintFont();
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}
					opt = '*';
					go = false;
					addWrite = false;
				} else {
					opt = '*';
				}
		} else if (e.getSource() == keyButton[2][0]) {// 4
			System.out.println("4");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("4");
				} else {
					inText.setText(inText.getText() + "4");
				}
			} else {
				inText.setText("4");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[2][1]) {// 5
			System.out.println("5");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("5");
				} else {
					inText.setText(inText.getText() + "5");
				}
			} else {
				inText.setText("5");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[2][2]) {// 6
			System.out.println("6");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("6");
				} else {
					inText.setText(inText.getText() + "6");
				}
			} else {
				inText.setText("6");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[2][3]) {// -
			System.out.println("-");
			repaintFont();
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}

					opt = '-';
					go = false;
					addWrite = false;
				} else {
					opt = '-';
				}
		} else if (e.getSource() == keyButton[3][0]) {// 1
			System.out.println("1");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("1");
				} else {
					inText.setText(inText.getText() + "1");
				}
			} else {
				inText.setText("1");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[3][1]) {// 2
			System.out.println("2");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("2");
				} else {
					inText.setText(inText.getText() + "2");
				}
			} else {
				inText.setText("2");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[3][2]) {// 3
			System.out.println("3");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("3");
				} else {
					inText.setText(inText.getText() + "3");
				}
			} else {
				inText.setText("3");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[3][3]) {// +
			System.out.println("+");
			repaintFont();
			if (Pattern.matches("([-]?\\d+[.]\\d*)|(\\d+)", inText.getText()))
				if (go) {
					val = Calculate.calc(val, inText.getText(), opt, inText);
					if (Pattern.matches("[-]?[\\d]+[.][0]*", String.valueOf(val))) {
						inText.setText(String.valueOf((int) val));
					} else {
						inText.setText(String.valueOf(val));
					}
					opt = '+';
					go = false;
					addWrite = false;
				} else {
					opt = '+';
				}
		} else if (e.getSource() == keyButton[4][0]) {// .
			System.out.println(".");
			repaintFont();
			if (addWrite) {
				inText.setText(inText.getText() + ".");
			} else {
				inText.setText("0.");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[4][1]) {// 0
			System.out.println("0");
			repaintFont();
			if (addWrite) {
				if (Pattern.matches("[0]*", inText.getText())) {
					inText.setText("0");
				} else {
					inText.setText(inText.getText() + "0");
				}
			} else {
				inText.setText("0");
				addWrite = true;
			}
			go = true;
		} else if (e.getSource() == keyButton[4][2]) {// =
			System.out.println("=");
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
		}
	}

	private void repaintFont() {
		inText.setFont(inText.getFont().deriveFont(Font.PLAIN));
	}
}
