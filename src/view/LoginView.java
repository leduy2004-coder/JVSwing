package view;

import controller.LoginController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField JtextField_user;
	private JPasswordField JpasswordField_pass;
	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 160, 635, 660);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(620, 620));
        jPanel.setBackground(new Color(255, 250, 240));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(null);

        contentPane.add(jPanel, gbc);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 231, 620);
        panel.setBackground(new Color(180, 221, 127));
        jPanel.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Hệ thống quản lí ");
        lblNewLabel_1.setForeground(new Color(51, 0, 153));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel_1.setBounds(10, 311, 206, 43);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("rạp chiếu phim");
		lblNewLabel.setForeground(new Color(51, 0, 153));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 347, 196, 43);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Trang Đăng Nhập");
		lblNewLabel_5.setForeground(new Color(0, 0, 102));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel_5.setBounds(0, 52, 231, 43);
		panel.add(lblNewLabel_5);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(54, 473, 67, 0);
		panel.add(separator_3);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-66, 115, 366, 0);
		panel.add(separator_1);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(0, 137, 389, 2);
		panel.add(separator_2_1);

		JLabel lblNewLabel_2 = new JLabel("");
		setPicture(lblNewLabel_2, "/img/login.png", 100, 100);
		lblNewLabel_2.setBounds(380, 10, 127, 112);
		jPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tài khoản");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel_3.setBounds(241, 165, 189, 41);
		jPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Mật khẩu");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel_4.setBounds(241, 296, 189, 41);
		jPanel.add(lblNewLabel_4);
		
		JtextField_user = new JTextField();
		JtextField_user.setForeground(Color.DARK_GRAY);
		JtextField_user.setFont(new Font("Arial", Font.PLAIN, 22));
		JtextField_user.setBounds(236, 210, 381, 52);
		jPanel.add(JtextField_user);
		JtextField_user.setColumns(10);
		
		JpasswordField_pass = new JPasswordField();
		JpasswordField_pass.setForeground(Color.DARK_GRAY);
		JpasswordField_pass.setFont(new Font("Arial", Font.PLAIN, 22));
		JpasswordField_pass.setBounds(236, 338, 381, 52);
		jPanel.add(JpasswordField_pass);
		
		JButton Btn_login = new JButton("Đăng nhập");
		Btn_login.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		Btn_login.setBounds(333, 497, 174, 41);
		jPanel.add(Btn_login);
		
		JButton Btn_exit = new JButton("Thoát");
		Btn_exit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 22));
		Btn_exit.setBounds(333, 548, 173, 41);
		jPanel.add(Btn_exit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(227, 423, 389, 2);
		jPanel.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(228, 137, 389, 2);
		jPanel.add(separator_2);
		
		JLabel messLabel = new JLabel();
		messLabel.setForeground(new Color(255, 0, 0));
		messLabel.setFont(new Font("Arial", Font.ITALIC, 17));
		messLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messLabel.setBounds(269, 435, 324, 34);
		jPanel.add(messLabel);
		LoginController login = new LoginController(this,Btn_login,Btn_exit, JtextField_user,JpasswordField_pass,messLabel);
		login.LoginEvent();
		login.keyEvent();
    }
    public void setPicture(JLabel label, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(LoginView.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label.setIcon(scaledIcon);
    }
}

