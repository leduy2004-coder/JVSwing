package view.Employee.SchedulePanel.Frame;

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
import javax.swing.JSeparator;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import controller.Employee.Schedule.Frame.TimeFrController;
import view.Manage.EmplPanel.EmplManageJFrame;

public class TimeFrame extends JFrame {



	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnExit;


	public TimeFrame() {
        setTitle("Thêm suất chiếu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 515, 359);
		contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;

        JPanel jPanel = new JPanel();
        jPanel.setFont(new Font("Arial", Font.BOLD, 22));
        jPanel.setPreferredSize(new Dimension(500, 320));
        jPanel.setBackground(new Color(255, 250, 250));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(null);

        contentPane.add(jPanel, gbc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(321, 86, 176, 2);
        jPanel.add(separator);
        
        JButton btnNext = new JButton("Tiếp tục");
        btnNext.setFont(new Font("Arial", Font.BOLD, 18));
        btnNext.setForeground(Color.WHITE);
        btnNext.setBackground(new Color(0, 153, 51));
        btnNext.setBounds(259, 226, 143, 35);
        jPanel.add(btnNext);
        
        JLabel lblNewLabel = new JLabel("Chọn thời gian");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(176, 73, 155, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 166, 2);
        jPanel.add(separator_1);
        
        btnExit = new JButton("Hủy bỏ");
        btnExit.setIcon(null);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(85, 226, 143, 35);
        jPanel.add(btnExit);
        
        JDateChooser jdcDate = new JDateChooser();
        jdcDate.setFont(new Font("Arial", Font.PLAIN, 20));
        jdcDate.setPreferredSize(new Dimension(82, 19));
        jdcDate.setDateFormatString("dd-MM-yyyy");
        jdcDate.setBounds(99, 133, 292, 41);
        jPanel.add(jdcDate);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/personal-information.png")));
        lblNewLabel_2.setBounds(12, 12, 110, 62);
        jPanel.add(lblNewLabel_2);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        JLabel jlb_mess = new JLabel("");
        jlb_mess.setForeground(new Color(204, 0, 0));
        jlb_mess.setFont(new Font("Arial", Font.BOLD, 20));
        jlb_mess.setBounds(447, 664, 126, 35);
        jPanel.add(jlb_mess);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);

        TimeFrController timeFrController = new TimeFrController(this,btnNext,btnExit,jdcDate);
        timeFrController.setEvent();
        
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(null);
    }
}
