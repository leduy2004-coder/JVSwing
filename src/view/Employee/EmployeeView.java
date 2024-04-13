package view.Employee;

import bean.ListBean;
import controller.Employee.MoveEmplController;
import model.EmployeeModel;
import utility.SessionUtil;
import view.LoginView;
import view.Manage.ManagementView;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.SwingConstants;

import javax.swing.JLabel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private MoveEmplController controller;
	private JPanel homeEmplPanel;
	private JLabel homeEmplLabel;
	private JPanel ticketEmplPanel;
	private JLabel ticketEmplLabel;
	private JPanel panel_4_1;
	private JLabel lblNewLabel_1;
	private JPanel contentPane;
	private EmployeeModel employeeModel;
	private JPanel panel;
	 public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	EmployeeView frame = new EmployeeView();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	 
	public EmployeeView() {
		setTitle("Quản lí nhân viên");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1233, 772);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu();
		mnNewMenu.setIcon(new ImageIcon(ManagementView.class.getResource("/img/user.png")));
		mnNewMenu.setMargin(new Insets(6, 2, 3, 7));
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnNewMenu.setText(SessionUtil.getInstance().getValueEmpl().getHoTen());
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Thoát");
		mntmNewMenuItem.setMargin(new Insets(2, 0, 2, 40));
		mntmNewMenuItem.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmNewMenuItem.setSize(new Dimension(15, 20));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 20));
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(new Color(1, 1, 76));

		
		JLabel lblNewLabel = new JLabel("Tài khoản - Nhân viên");
		lblNewLabel.setForeground(SystemColor.info);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 260, 42);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblNewLabel);
	
		panel.add(Box.createVerticalStrut(20));
		
		homeEmplPanel = new JPanel();
		panel.add(homeEmplPanel);
		homeEmplPanel.setPreferredSize(new Dimension(50, 50));
		panel.add(Box.createVerticalStrut(10));
		homeEmplPanel.setLayout(new BorderLayout(0, 0));
		homeEmplPanel.setBackground(new Color(44, 134, 93));
		homeEmplLabel = new JLabel("Trang chủ");
		homeEmplLabel.setHorizontalAlignment(SwingConstants.CENTER);
		homeEmplLabel.setForeground(new Color(245, 245, 245));
		homeEmplLabel.setFont(new Font("Arial", Font.BOLD, 20));
		homeEmplPanel.add(homeEmplLabel);
		panel.add(Box.createVerticalStrut(10));
		setPicture(homeEmplLabel, "/img/icon-home.png");
		
		ticketEmplPanel = new JPanel();
		panel.add(ticketEmplPanel);
		ticketEmplPanel.setPreferredSize(new Dimension(50, 50));
		panel.add(Box.createVerticalStrut(10));
		ticketEmplPanel.setLayout(new BorderLayout(0, 0));
		ticketEmplPanel.setBackground(new Color(44, 134, 93));
		ticketEmplLabel = new JLabel("Đặt vé");
		ticketEmplLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ticketEmplLabel.setForeground(new Color(245, 245, 245));
		ticketEmplLabel.setFont(new Font("Arial", Font.BOLD, 20));
		ticketEmplPanel.add(ticketEmplLabel);
		panel.add(Box.createVerticalStrut(10));
		setPicture(ticketEmplLabel, "/img/booking.png");
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		panel.add(Box.createVerticalStrut(10));
		panel_4.setLayout(new BorderLayout(0, 0));
		panel_4.setBackground(new Color(44, 134, 93));
		JLabel lblNewLabel_3 = new JLabel("Quản lí vé");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(new Color(245, 245, 245));
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 20));
		panel_4.add(lblNewLabel_3);
		panel.add(Box.createVerticalStrut(10));
		panel_4.setPreferredSize(new Dimension(50, 50));
		setPicture(lblNewLabel_3, "/img/movie-ticket.png");
		
		JPanel contentEmplPanel = new JPanel();
		contentPane.add(contentEmplPanel, BorderLayout.CENTER);
		
		panel_4_1 = new JPanel();
		panel_4_1.setPreferredSize(new Dimension(50, 50));
		panel_4_1.setBackground(new Color(44, 134, 93));
		panel.add(panel_4_1);
		panel_4_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("Quản lí phim");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		setPicture(lblNewLabel_1, "/img/video-player.png");
		panel_4_1.add(lblNewLabel_1, BorderLayout.CENTER);
		panel.add(Box.createVerticalStrut(10));
		panel.add(Box.createVerticalStrut(10));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(50, 50));
		panel_1.setBackground(new Color(44, 134, 93));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Suất chiếu");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 20));
		setPicture(lblNewLabel_2, "/img/lichchieu.png");
		panel_1.add(lblNewLabel_2, BorderLayout.CENTER);
		
		panel.add(Box.createVerticalStrut(170));
		
		List<ListBean> listItem = new ArrayList<>();
		listItem.add(new ListBean("Trang chủ", homeEmplPanel, homeEmplLabel));
		listItem.add(new ListBean("Đặt vé", ticketEmplPanel, ticketEmplLabel));
		listItem.add(new ListBean("Vé", panel_4, lblNewLabel_3));
		listItem.add(new ListBean("Phim", panel_4_1, lblNewLabel_1));
		listItem.add(new ListBean("Suất chiếu", panel_1, lblNewLabel_2));
		
		controller = new MoveEmplController(contentEmplPanel,this,mntmNewMenuItem);
		controller.setEvent(listItem);
		
		
	}
	public void setPicture(JLabel label, String fileName) {
		ImageIcon originalIcon = new ImageIcon(EmployeeView.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label.setIcon(scaledIcon);
    }
	public void HomePage(){
		controller.setView(homeEmplPanel,homeEmplLabel);
	}
}
