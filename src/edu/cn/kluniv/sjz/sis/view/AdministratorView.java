package edu.cn.kluniv.sjz.sis.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import edu.cn.kluniv.sjz.sis.model.ResultSetTableModel;

public class AdministratorView {
	private JFrame frame;
	private JButton btnAddRow;
	private JButton btnDelRow;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode rootTreeNode;
	private DefaultMutableTreeNode tnSelectStuInfo;
	private DefaultMutableTreeNode tnSelectTeaInfo;
	private DefaultMutableTreeNode tnSelectCourPlan;
	private JLabel labelBG;
	private JScrollPane scrollPane;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu logMenu;
	private JMenuItem logout;
	
	private ResultSetTableModel rstmSelectStuInfo;
	private ResultSetTableModel rstmSelectTeaInfo;
	private ResultSetTableModel rstmSelectCourPlan;
	private ResultSet rsStuInfo;
	private ResultSet rsTeaInfo;
	private ResultSet rsCourPlan;
	public AdministratorView(ResultSet rsStuInfo, ResultSet rsTeaInfo, ResultSet rsCourPlan) {
		this.rsStuInfo = rsStuInfo;
		this.rsTeaInfo = rsTeaInfo;
		this.rsCourPlan = rsCourPlan;
		initialize();
		myEvent();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	private void myEvent() {
		// student
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode mtNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (mtNode.isLeaf()) {
					// 
					String selectNode=mtNode.getUserObject().toString();
					if (selectNode.equals(tnSelectStuInfo.getUserObject().toString())) {
						// 
						if (scrollPane!=null) {
							frame.remove(scrollPane);
						}
						// 
						rstmSelectStuInfo=new ResultSetTableModel(rsStuInfo);
						// 
						table=new JTable();
						// 
						table.setModel(rstmSelectStuInfo);
						// 
						scrollPane=new JScrollPane(table);
						scrollPane.setViewportView(table);
						// 
						frame.getContentPane().add(scrollPane,BorderLayout.SOUTH);
						frame.pack();
						frame.setVisible(true);
						System.out.println(tnSelectStuInfo.getUserObject().toString());
						// 
						table.addKeyListener(new KeyAdapter() {

							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==KeyEvent.VK_DELETE) {
									int row=table.getSelectedRow();
									rstmSelectStuInfo.deleteRow(row);
								}
							}
							
						});
						// 
						table.addKeyListener(new KeyAdapter() {

							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==KeyEvent.VK_INSERT) {
									rstmSelectStuInfo.insertRowStudent();
								}
							}
							
						});
					}
				}
			}
		});
		// teacher info query
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode mtNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (mtNode.isLeaf()) {
					// 
					String selectNode=mtNode.getUserObject().toString();
					if (selectNode.equals(tnSelectTeaInfo.getUserObject().toString())) {
						// 
						if (scrollPane!=null) {
							frame.remove(scrollPane);
						}
						// 
						rstmSelectTeaInfo=new ResultSetTableModel(rsTeaInfo);
						// 
						table=new JTable();
						// 
						table.setModel(rstmSelectTeaInfo);
						// 
						scrollPane=new JScrollPane(table);
						scrollPane.setBounds(1307, 46, 2, 2);
						scrollPane.setViewportView(table);
						// 
						frame.getContentPane().add(scrollPane,BorderLayout.SOUTH);
						frame.pack();
						frame.setVisible(true);
						System.out.println(tnSelectTeaInfo.getUserObject().toString());
						// 
						table.addKeyListener(new KeyAdapter() {

							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==KeyEvent.VK_DELETE) {
									int row=table.getSelectedRow();
									rstmSelectTeaInfo.deleteRow(row);
								}
							}
							
						});
						// 
						table.addKeyListener(new KeyAdapter() {

							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==KeyEvent.VK_INSERT) {
									rstmSelectTeaInfo.insertRowTeacher();
								}
							}
							
						});
					}
				}
			}
		});
		
		
		// course info query
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode mtNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (mtNode.isLeaf()) {
					// 
					String selectNode=mtNode.getUserObject().toString();
					if (selectNode.equals(tnSelectCourPlan.getUserObject().toString())) {
						// 
						if (scrollPane!=null) {
							frame.remove(scrollPane);
						}
						// 
						rstmSelectCourPlan=new ResultSetTableModel(rsCourPlan);
						// 
						table=new JTable();
						// 
						table.setModel(rstmSelectCourPlan);
						// 
						scrollPane=new JScrollPane(table);
						scrollPane.setViewportView(table);
						// 
						frame.getContentPane().add(scrollPane,BorderLayout.SOUTH);
						frame.pack();
						frame.setVisible(true);
						System.out.println(tnSelectCourPlan.getUserObject().toString());
						// 
						table.addKeyListener(new KeyAdapter() {

							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==KeyEvent.VK_DELETE) {
									int row=table.getSelectedRow();
									rstmSelectCourPlan.deleteRow(row);
								}
							}
							
						});
						// 
						table.addKeyListener(new KeyAdapter() {

							@Override
							public void keyPressed(KeyEvent e) {
								if (e.getKeyCode()==KeyEvent.VK_INSERT) {
									rstmSelectCourPlan.insertRowCourse();
								}
							}
							
						});
					}
				}
			}
		});
		
		// si huo
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				JOptionPane.showMessageDialog(null, "已经成功注销登录!", "注销登录通知!", 1, null);
			}
		});
		
	}
	private void initialize() {
		// TODO Auto-generated method stub
		frame=new JFrame("管理员端界面");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(10, 10));
		// 塞点私货
		menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
		logMenu = new JMenu("Logout");
		menuBar.add(logMenu);
		logout = new JMenuItem("退出登录");
		logMenu.add(logout);
		// 
		tree=new JTree();
		tree.getSelectionModel()
		.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		// 
		rootTreeNode=new DefaultMutableTreeNode("管理员端功能栏");
		// 
		tnSelectStuInfo=new DefaultMutableTreeNode("学生信息管理");
		// 
		tnSelectTeaInfo=new DefaultMutableTreeNode("教师信息管理");
		//
		tnSelectCourPlan=new DefaultMutableTreeNode("培养方案管理");
		//
		rootTreeNode.add(tnSelectStuInfo);
		rootTreeNode.add(tnSelectTeaInfo);
		rootTreeNode.add(tnSelectCourPlan);
		// 
		treeModel=new DefaultTreeModel(rootTreeNode);
		// 
		tree.setModel(treeModel);
		// 
		frame.getContentPane().add(tree,BorderLayout.WEST);
		//
		labelBG = new JLabel();
		labelBG.setIcon(new ImageIcon(AdministratorView.class.getClassLoader().getResource("images/stuView.png")));
		frame.getContentPane().add(labelBG, BorderLayout.CENTER);
		frame.pack();
	}
	
	
}
