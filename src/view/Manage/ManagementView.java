package view.Manage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bean.ListBean;
import controller.Manage.MoveManageController;
import model.ManageModel;
import utility.SessionUtil;
import view.LoginView;
import view.Employee.EmployeeView;
import java.awt.Insets;

public class ManagementView extends JFrame {

	private static final long serialVersionUID = 1L;
	MoveManageController controller;
	private JPanel contentPane;
	private ManageModel manageModel;
	private JPanel contentManagePanel;
	private JPanel emplManagePanel;
	private JPanel homeManagePanel;
	private JLabel homeManageLabel;
	private JLabel emplManageLabel;
	private JPanel customerManagePanel;
	private JLabel customerManageLabel;
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	ManagementView frame = new ManagementView();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }


	public ManagementView() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1233, 772);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu();
		mnNewMenu.setIcon(new ImageIcon(ManagementView.class.getResource("/img/user.png")));
		mnNewMenu.setMargin(new Insets(6, 2, 3, 12));
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnNewMenu.setText((SessionUtil.getInstance().getValueManage()).getHoTen());
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Thoát");
		mntmNewMenuItem.setMargin(new Insets(2, 0, 2, 39));
		mntmNewMenuItem.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmNewMenuItem.setSize(new Dimension(15, 20));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 20));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(1, 1, 76));


		JLabel lblNewLabel = new JLabel("Tài khoản - Quản lí");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 260, 42);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);

		panel.add(Box.createVerticalStrut(20));

		homeManagePanel = new JPanel();
		panel.add(homeManagePanel);
		homeManagePanel.setPreferredSize(new Dimension(50, 50));
		panel.add(Box.createVerticalStrut(10));
		homeManagePanel.setLayout(new BorderLayout(0, 0));
		homeManagePanel.setBackground(new Color(44, 134, 93));
		homeManageLabel = new JLabel("Trang chủ");
		homeManageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeManageLabel.setForeground(new Color(245, 245, 245));
		homeManageLabel.setFont(new Font("Arial", Font.BOLD, 20));
		homeManagePanel.add(homeManageLabel);
		panel.add(Box.createVerticalStrut(10));
		setPicture(homeManageLabel, "/img/icon-home.png");

		emplManagePanel = new JPanel();
		panel.add(emplManagePanel);
		emplManagePanel.setPreferredSize(new Dimension(50, 50));
		panel.add(Box.createVerticalStrut(10));
		emplManagePanel.setLayout(new BorderLayout(0, 0));
		emplManagePanel.setBackground(new Color(44, 134, 93));
		emplManageLabel = new JLabel("Nhân viên");
		emplManageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		emplManageLabel.setForeground(new Color(245, 245, 245));
		emplManageLabel.setFont(new Font("Arial", Font.BOLD, 20));
		emplManagePanel.add(emplManageLabel);
		panel.add(Box.createVerticalStrut(10));
		setPicture(emplManageLabel, "/img/evaluation.png");

		customerManagePanel = new JPanel();
		panel.add(customerManagePanel);
		panel.add(Box.createVerticalStrut(10));
		customerManagePanel.setLayout(new BorderLayout(0, 0));
		customerManagePanel.setBackground(new Color(44, 134, 93));
		customerManageLabel = new JLabel("Khách hàng");
		customerManageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		customerManageLabel.setForeground(new Color(245, 245, 245));
		customerManageLabel.setFont(new Font("Arial", Font.BOLD, 20));
		customerManagePanel.add(customerManageLabel);
		customerManagePanel.setPreferredSize(new Dimension(50, 50));
		setPicture(customerManageLabel, "/img/customer.png");
		panel.add(Box.createVerticalStrut(10));

		JPanel statisticPanel = new JPanel();
		panel.add(statisticPanel);
		panel.add(Box.createVerticalStrut(10));
		statisticPanel.setLayout(new BorderLayout(0, 0));
		statisticPanel.setBackground(new Color(44, 134, 93));
		JLabel statisticLabel = new JLabel("Thống kê");
		statisticLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statisticLabel.setForeground(new Color(245, 245, 245));
		statisticLabel.setFont(new Font("Arial", Font.BOLD, 20));
		statisticPanel.add(statisticLabel);
		panel.add(Box.createVerticalStrut(300));
		statisticPanel.setPreferredSize(new Dimension(50, 50));
		setPicture(statisticLabel, "/img/analysis.png");

		contentManagePanel = new JPanel();
		contentPane.add(contentManagePanel, BorderLayout.CENTER);


		controller = new MoveManageController(contentManagePanel,this,mntmNewMenuItem);


		List<ListBean> listItem = new ArrayList<>();
		listItem.add(new ListBean("Trang chủ", homeManagePanel, homeManageLabel));
		listItem.add(new ListBean("Nhân viên", emplManagePanel, emplManageLabel));
		listItem.add(new ListBean("Khách hàng", customerManagePanel, customerManageLabel));
		listItem.add(new ListBean("Thống kê", statisticPanel, statisticLabel));

		controller.setEvent(listItem);
	}
	public void setPicture(JLabel label, String fileName) {
		ImageIcon originalIcon = new ImageIcon(ManagementView.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label.setIcon(scaledIcon);
    }

	public void HomePage(){
		controller.setView(homeManagePanel,homeManageLabel);
	}
	public void EmplPage(){
		controller.setViewEmpl(emplManagePanel,emplManageLabel);
	}
	public void CustomerPage(){
		controller.setViewCustomer(customerManagePanel,customerManageLabel);
	}
}
