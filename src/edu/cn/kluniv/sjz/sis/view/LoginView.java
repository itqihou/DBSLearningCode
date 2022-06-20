package edu.cn.kluniv.sjz.sis.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.cn.kluniv.sjz.sis.dao.BaseDAO;
import edu.cn.kluniv.sjz.sis.dao.CourseDAO;
import edu.cn.kluniv.sjz.sis.dao.DBConnection;
import edu.cn.kluniv.sjz.sis.dao.SCDAO;
import edu.cn.kluniv.sjz.sis.dao.StudentDAO;
import edu.cn.kluniv.sjz.sis.dao.TeacherDAO;
import edu.cn.kluniv.sjz.sis.dao.UserDAO;
import edu.cn.kluniv.sjz.sis.model.User;
import edu.cn.kluniv.sjz.sis.util.StringUtil;

public class LoginView {
	private JFrame frame;
	private JPanel panel;
	private JLabel labelAccount;// 有点匈牙利命名法那味
	private JLabel labelPwd;
	private JLabel labelBg;
	private JLabel labelGroupMemberDescription;
	private JTextField txtAccount;
	private JPasswordField txtPasswd;
	private JButton btnLogin;
	private String account;
	private String passwd;
	private JMenuBar menuBar;
	private JMenu helpMenu;
	private JMenuItem aboutPage;
	/**
	 * 以上是GUI控件
	 */
	private DBConnection dbc;
	private UserDAO userDAO;
	private CourseDAO courseDAO;
	private StudentDAO studentDAO;
	private TeacherDAO teacherDAO;
	private SCDAO scDAO;
	private ResultSet rs;
	private User user;
	private StudentView stuView;
	private TeacherView teaView;
	private AdministratorView adminView;

	public LoginView() {
		dbc = new DBConnection();
		dbc.connect();
		userDAO = new UserDAO(dbc);
		studentDAO = new StudentDAO(dbc);
		teacherDAO = new TeacherDAO(dbc);
		courseDAO = new CourseDAO(dbc);
		scDAO = new SCDAO(dbc);
		initialize();
		myEvent();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	private void myEvent() {
		// TODO Auto-generated method stub
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setAccount(txtAccount.getText());
				//
				setPasswd(String.valueOf(txtPasswd.getPassword()));
				//
				if (!StringUtil.isEmpty(account) && !StringUtil.isEmpty(passwd)) {
					try {
						//
						rs = userDAO.selectByAccountAndPwd(account, passwd);
						//
						if (rs.next()) {
							//
							user = userDAO.saveUser(rs);
							//
							if (user.getRole() == BaseDAO.userRoleStudent) {
								//
								frame.dispose();
								//
								stuView = new StudentView(studentDAO.selectByNo(user.getAccount()),
										courseDAO.selectAll(BaseDAO.userRoleStudent),
										scDAO.selectByNo(user.getAccount()), user.getAccount());

							}
							//
							if (user.getRole() == BaseDAO.userRoleTeacher) {
								//
								frame.dispose();
								//
								teaView = new TeacherView(courseDAO.selectAll(BaseDAO.userRoleTeacher),
										scDAO.selectByNo(user.getAccount()), user.getAccount());
							}
							//
							if (user.getRole() == BaseDAO.userRoleAdmin) {
								//
								frame.dispose();
								//
								adminView = new AdministratorView(studentDAO.selectAll(BaseDAO.userRoleAdmin),
										teacherDAO.selectAll(BaseDAO.userRoleAdmin),
										courseDAO.selectAll(BaseDAO.userRoleAdmin));
							}
						} else {
							JOptionPane.showMessageDialog(null, "用户名及密码错误!");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名及密码不能为空!");
				}
			}
		});
		
		// si huo
		aboutPage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutPage aboutPage=new AboutPage();
				aboutPage.setVisible(true);
			}
		});
	}

	private void initialize() {
		// 添加frame
		frame = new JFrame();
		frame.setTitle("学生信息管理系统");
		frame.setBounds(100, 100, 891, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		// 塞点私货
		menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		aboutPage = new JMenuItem("About");
		helpMenu.add(aboutPage);
		//
		panel = new JPanel();
		panel.setBounds(0, 0, 875, 405);
		panel.setLayout(null);
		//
		frame.getContentPane().add(panel);
		//
		txtAccount = new JTextField();
		txtAccount.setBounds(373, 315, 154, 20);
		txtAccount.setColumns(10);
		txtAccount.setText(null);
		panel.add(txtAccount);
		//
		txtPasswd = new JPasswordField();
		txtPasswd.setBounds(590, 315, 154, 20);
		txtPasswd.setText(null);
		panel.add(txtPasswd);
		//
		labelAccount = new JLabel("账号");
		labelAccount.setBounds(333, 315, 36, 20);
		panel.add(labelAccount);
		//
		labelPwd = new JLabel("密码");
		labelPwd.setBounds(550, 315, 36, 19);
		panel.add(labelPwd);
		//
		btnLogin = new JButton("登录");
		btnLogin.setBounds(761, 314, 89, 23);
		panel.add(btnLogin);
		//
		labelGroupMemberDescription = new JLabel("合作成员:");
		labelGroupMemberDescription.setBounds(10, 346, 840, 48);
		panel.add(labelGroupMemberDescription);
		//
		labelBg = new JLabel();
		labelBg.setIcon(new ImageIcon(LoginView.class.getClassLoader().getResource("images/loginView.png")));
		labelBg.setBounds(0, 0, 875, 405);
		panel.add(labelBg);
		//
		frame.setVisible(true);
	}

}
