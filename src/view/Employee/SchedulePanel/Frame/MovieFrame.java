package view.Employee.SchedulePanel.Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import controller.Employee.Schedule.Frame.MovieFrController;
import model.ShiftModel;
import view.Manage.EmplPanel.EmplManageJFrame;
import java.awt.Cursor;

public class MovieFrame extends JFrame {


	private static final long serialVersionUID = 1L;
    private JFrame frame;
	private JPanel contentPane;
	private JButton btnExit;
	private JTextField textField;


	public MovieFrame(JDateChooser jdcDate, ShiftModel shift) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 715, 660);
		contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;

        JPanel jPanel = new JPanel();
        jPanel.setFont(new Font("Arial", Font.BOLD, 22));
        jPanel.setPreferredSize(new Dimension(700, 620));
        jPanel.setBackground(new Color(255, 250, 250));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(null);

        contentPane.add(jPanel, gbc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(441, 86, 259, 2);
        jPanel.add(separator);
        
        JButton btnNext = new JButton("Tiếp tục");
        btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnNext.setFont(new Font("Arial", Font.BOLD, 18));
        btnNext.setForeground(Color.WHITE);
        btnNext.setBackground(new Color(0, 153, 51));
        btnNext.setBounds(346, 559, 143, 35);
        jPanel.add(btnNext);
        
        JLabel lblNewLabel = new JLabel("Chọn phim chiếu");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(266, 74, 177, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 250, 2);
        jPanel.add(separator_1);
        
        btnExit = new JButton("Hủy bỏ");
        btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExit.setIcon(null);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(511, 559, 143, 35);
        jPanel.add(btnExit);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/personal-information.png")));
        lblNewLabel_2.setBounds(12, 12, 110, 62);
        jPanel.add(lblNewLabel_2);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        JLabel JLabelDate = new JLabel("Tìm kiếm mã");
        JLabelDate.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabelDate.setBounds(43, 119, 110, 35);
        jPanel.add(JLabelDate);
        
        textField = new JTextField();
        textField.setBounds(163, 126, 162, 25);
        jPanel.add(textField);
        textField.setColumns(10);
        
        JPanel panelTable = new JPanel();
        panelTable.setBounds(12, 174, 678, 360);
        jPanel.add(panelTable);
        
        JLabel lblNewLabel_1 = new JLabel("Kick chuột vào 1 dòng để chọn");
        lblNewLabel_1.setForeground(new Color(255, 0, 51));
        lblNewLabel_1.setFont(new Font("Arial", Font.ITALIC, 16));
        lblNewLabel_1.setBounds(30, 557, 243, 38);
        jPanel.add(lblNewLabel_1);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);

        MovieFrController movieFrController = new MovieFrController(this,btnNext,btnExit,jdcDate,shift,panelTable,textField);
        movieFrController.setDataAndEvent();
        
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(null);
    }

}
