package edu.cn.kluniv.sjz.sis.view;

import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import edu.cn.kluniv.sjz.sis.model.ResultSetTableModel;

public class TeacherView {
	private JFrame frame;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode rootTreeNode;
	private DefaultMutableTreeNode tnSelectCourPlan;
	private DefaultMutableTreeNode tnSelectGrade;
	private DefaultMutableTreeNode tnModifyPasswd;
	private JLabel labelBG;
	private JScrollPane scrollPane;
	private JTable table;
	private ResultSetTableModel rstmSelectGrade;
	private ResultSetTableModel rstmSelectCourPlan;
	private ResultSet rsGrade;
	private ResultSet rsCourPlan;
	//
	private String account;

	public TeacherView(ResultSet rsGrade, ResultSet rsCourPlan, String account) {
		this.rsGrade = rsGrade;
		this.rsCourPlan = rsCourPlan;
		this.account = account;
		initialize();
		myEvent();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	private void myEvent() {
		// query course info
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode mtNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (mtNode.isLeaf()) {
					//
					String selectNode = mtNode.getUserObject().toString();
					if (selectNode.equals(tnSelectCourPlan.getUserObject().toString())) {
						//
						if (scrollPane != null) {
							frame.remove(scrollPane);
						}
						//
						rstmSelectCourPlan = new ResultSetTableModel(rsCourPlan);
						//
						table = new JTable();
						//
						table.setModel(rstmSelectCourPlan);
						//
						scrollPane = new JScrollPane(table);
						scrollPane.setViewportView(table);
						//
						frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
						frame.pack();
						frame.setVisible(true);
						System.out.println(tnSelectCourPlan.getUserObject().toString());
						//
						//

					}
				}
			}
		});

		// grade info query
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode mtNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (mtNode.isLeaf()) {
					//
					String selectNode = mtNode.getUserObject().toString();
					if (selectNode.equals(tnSelectGrade.getUserObject().toString())) {
						//
						if (scrollPane != null) {
							frame.remove(scrollPane);
						}
						//
						rstmSelectGrade = new ResultSetTableModel(rsGrade);
						//
						table = new JTable();
						//
						table.setModel(rstmSelectGrade);
						//
						scrollPane = new JScrollPane(table);
						scrollPane.setViewportView(table);
						//
						frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
						frame.pack();
						frame.setVisible(true);
						System.out.println(tnSelectGrade.getUserObject().toString());

					}
				}
			}
		});

		// modify password
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode mtNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (mtNode.isLeaf()) {
					//
					String selectNode = mtNode.getUserObject().toString();
					if (selectNode.equals(tnModifyPasswd.getUserObject().toString())) {
						//
						ModifyPasswordView modifyPasswordView = new ModifyPasswordView();
						modifyPasswordView.setVisible(true);
						modifyPasswordView.setLocationRelativeTo(null);

						System.out.println(tnModifyPasswd.getUserObject().toString());

					}
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame("教师端界面");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(10, 10));
		//
		tree = new JTree();
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		//
		rootTreeNode = new DefaultMutableTreeNode("教师端功能栏");
		//
		tnSelectCourPlan = new DefaultMutableTreeNode("查询培养方案");
		//
		tnSelectGrade = new DefaultMutableTreeNode("查询成绩");
		//
		tnModifyPasswd = new DefaultMutableTreeNode("修改密码");
		//
		rootTreeNode.add(tnSelectCourPlan);
		rootTreeNode.add(tnSelectGrade);
		rootTreeNode.add(tnModifyPasswd);
		//
		treeModel = new DefaultTreeModel(rootTreeNode);
		//
		tree.setModel(treeModel);
		//
		frame.getContentPane().add(tree, BorderLayout.WEST);
		//
		labelBG = new JLabel();
		labelBG.setIcon(new ImageIcon(""));
		frame.getContentPane().add(labelBG, BorderLayout.CENTER);
		frame.pack();
	}

}
