package edu.cn.kluniv.sjz.sis.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AboutPage extends JFrame {
	private String infoString;
	private JTextArea infoArea;
	
	public AboutPage() {
		infoString="版本: 0.0.1 \r\n"
				+ "Author: TapuFini\r\n"
				+ "日期: 2022-06-20T20:10:58.283Z\r\n"
				+ "Github: https://github.com/itqihou/DBSLearningCode\r\n";
		add(new JLabel(
				"<html><h1>About Page</h1></html>"),
				BorderLayout.NORTH);
		setTitle("Student Infomation Management System");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		JButton okbtn = new JButton("确认");
		infoArea=new JTextArea(infoString);
		infoArea.setFont(new Font("宋体", Font.PLAIN, 22));
		infoArea.setEditable(false);
		okbtn.addActionListener(event -> {
			dispose();
			});		
		panel.add(okbtn);
		add(infoArea);
		add(panel, BorderLayout.SOUTH);
		setSize(600, 400);
		setLocationRelativeTo(null);
	}
	public static void main(String[] args) {
		new AboutPage().setVisible(true);
	}
}
