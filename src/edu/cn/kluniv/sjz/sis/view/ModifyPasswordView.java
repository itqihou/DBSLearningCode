package edu.cn.kluniv.sjz.sis.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.dao.UserDAO;
import edu.cn.kluniv.sjz.sis.model.User;
import edu.cn.kluniv.sjz.sis.util.StringUtil;

public class ModifyPasswordView extends JFrame {
	private JPanel contentPane;
	private JTextField oldPasswordtextField;
	private JTextField newPasswordtextField;
	private JTextField confirmPasswordtextField;
	private UserDAO userDao;
	private DBConnection dbc;
	private String account;
	private String account1;//
	private User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifyPasswordView frame = new ModifyPasswordView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModifyPasswordView(String account) {
		account1 = account;
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		oldPasswordtextField = new JTextField();
		oldPasswordtextField.setColumns(10);

		newPasswordtextField = new JTextField();
		newPasswordtextField.setColumns(10);

		confirmPasswordtextField = new JTextField();
		confirmPasswordtextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("旧密码");
		lblNewLabel.setForeground(Color.BLACK);

		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 19));
		lblNewLabel.setBackground(Color.BLUE);

		JLabel lblNewLabel_1 = new JLabel("新密码");
		lblNewLabel_1.setForeground(Color.BLACK);

		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 19));

		JLabel lblNewLabel_2 = new JLabel("确认密码");
		lblNewLabel_2.setForeground(Color.BLACK);

		lblNewLabel_2.setBackground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 19));

		JButton ConfirmButton = new JButton("确认");

		ConfirmButton.setBackground(Color.CYAN);
		ConfirmButton.setFont(new Font("楷体", Font.PLAIN, 19));
		// 监听确认按钮
		ConfirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				confirmPasswordValue(evt);
			}
		});

		JButton ResetButton = new JButton("重置");

		ResetButton.setBackground(Color.RED);
		ResetButton.setFont(new Font("楷体", Font.PLAIN, 19));
		// 监听重置按钮
		ResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				resetValue(evt);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane
				.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(91, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel).addComponent(lblNewLabel_1)
														.addComponent(lblNewLabel_2))
												.addGap(30)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(newPasswordtextField, GroupLayout.DEFAULT_SIZE,
																92, Short.MAX_VALUE)
														.addComponent(confirmPasswordtextField)
														.addComponent(oldPasswordtextField, GroupLayout.PREFERRED_SIZE,
																127, GroupLayout.PREFERRED_SIZE))
												.addGap(128))
										.addGroup(Alignment.TRAILING,
												gl_contentPane.createSequentialGroup().addComponent(ConfirmButton)
														.addGap(63).addComponent(ResetButton).addGap(101)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(36)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel).addComponent(
						oldPasswordtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addGap(27)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(newPasswordtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
				.addGap(31)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(confirmPasswordtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
				.addGap(36).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(ResetButton)
						.addComponent(ConfirmButton))
				.addContainerGap(35, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

		dbc = new DBConnection();
		dbc.connect();
		userDao = new UserDAO(dbc);

	}

	public ModifyPasswordView() {
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);

		oldPasswordtextField = new JTextField();
		oldPasswordtextField.setColumns(10);

		newPasswordtextField = new JTextField();
		newPasswordtextField.setColumns(10);

		confirmPasswordtextField = new JTextField();
		confirmPasswordtextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("旧密码");
		lblNewLabel.setForeground(Color.BLACK);

		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 19));
		lblNewLabel.setBackground(Color.BLUE);

		JLabel lblNewLabel_1 = new JLabel("新密码");
		lblNewLabel_1.setForeground(Color.BLACK);

		lblNewLabel_1.setBackground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 19));

		JLabel lblNewLabel_2 = new JLabel("确认密码");
		lblNewLabel_2.setForeground(Color.BLACK);

		lblNewLabel_2.setBackground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 19));

		JButton ConfirmButton = new JButton("确认");

		ConfirmButton.setBackground(Color.CYAN);
		ConfirmButton.setFont(new Font("楷体", Font.PLAIN, 19));
		JButton ResetButton = new JButton("重置");

		ResetButton.setBackground(Color.RED);
		ResetButton.setFont(new Font("楷体", Font.PLAIN, 19));
	}

	protected void confirmPasswordValue(ActionEvent evt) {
		String oldPassword = oldPasswordtextField.getText().toString();
		String newPassword = newPasswordtextField.getText().toString();
		String confirmPassword = confirmPasswordtextField.getText().toString();

		if (StringUtil.isEmpty(oldPassword)) {
			JOptionPane.showMessageDialog(this, "请输入旧密码");
		} else if (StringUtil.isEmpty(newPassword)) {
			JOptionPane.showMessageDialog(this, "请输入新密码");
		} else if (StringUtil.isEmpty(confirmPassword)) {
			JOptionPane.showMessageDialog(this, "确认密码");
		} else if (!(newPassword.equals(confirmPassword))) {
			JOptionPane.showMessageDialog(this, "两次输入的密码不一致");
			resetValue();
			return;
		} else if (oldPassword.equals(newPassword)) {
			JOptionPane.showMessageDialog(this, "新旧密码一样请重新输入");
			resetValue();
		} else {
			user = new User(account, newPassword);

			String reStr = userDao.updatePasswd(user, account1, oldPassword);

			JOptionPane.showMessageDialog(this, reStr);
			resetValue();
			if (reStr.equals("密码修改成功")) {
				this.dispose();
			}
		}
	}

	private void resetValue() {
		oldPasswordtextField.setText("");
		newPasswordtextField.setText("");
		confirmPasswordtextField.setText("");
	}

	protected void resetValue(ActionEvent evt) {
		resetValue();
	}

}