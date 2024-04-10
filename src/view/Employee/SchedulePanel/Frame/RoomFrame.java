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
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import controller.Employee.Schedule.Frame.MovieFrController;
import controller.Employee.Schedule.Frame.RoomFrController;
import model.MovieModel;
import model.ShiftModel;
import view.Manage.EmplPanel.EmplManageJFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

public class RoomFrame extends JFrame {


	private static final long serialVersionUID = 1L;
    private JFrame frame;
	private JPanel contentPane;
	private JButton btnExit;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	public RoomFrame(JDateChooser jdcDate, ShiftModel shift,MovieModel movie) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 615, 460);
		contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;

        JPanel jPanel = new JPanel();
        jPanel.setFont(new Font("Arial", Font.BOLD, 22));
        jPanel.setPreferredSize(new Dimension(600, 420));
        jPanel.setBackground(new Color(255, 250, 250));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(null);

        contentPane.add(jPanel, gbc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(388, 86, 212, 2);
        jPanel.add(separator);
        
        JButton btnNext = new JButton("Tiếp tục");
        btnNext.setFont(new Font("Arial", Font.BOLD, 18));
        btnNext.setForeground(Color.WHITE);
        btnNext.setBackground(new Color(0, 153, 51));
        btnNext.setBounds(258, 341, 143, 35);
        jPanel.add(btnNext);
        
        JLabel lblNewLabel = new JLabel("Chọn phòng chiếu");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(201, 73, 174, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 191, 2);
        jPanel.add(separator_1);
        
        btnExit = new JButton("Hủy bỏ");
        btnExit.setIcon(null);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(411, 341, 143, 35);
        jPanel.add(btnExit);
        
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
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton();
        buttonGroup.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnNewRadioButton.setBounds(83, 176, 155, 35);
        rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(rdbtnNewRadioButton);
        
        JRadioButton rdbtnCa = new JRadioButton();
        buttonGroup.add(rdbtnCa);
        rdbtnCa.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa.setBounds(351, 176, 155, 35);
        jPanel.add(rdbtnCa);
        
        JRadioButton rdbtnCa_1 = new JRadioButton();
        buttonGroup.add(rdbtnCa_1);
        rdbtnCa_1.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa_1.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa_1.setBounds(83, 248, 155, 35);
        jPanel.add(rdbtnCa_1);
        
        JRadioButton rdbtnCa_2 = new JRadioButton();
        buttonGroup.add(rdbtnCa_2);
        rdbtnCa_2.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa_2.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa_2.setBounds(351, 248, 155, 35);
        jPanel.add(rdbtnCa_2);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
        RoomFrController roomFrController = new RoomFrController(this,btnNext,btnExit,jdcDate,shift,movie);
        roomFrController.setDataAndEvent(rdbtnNewRadioButton,rdbtnCa,rdbtnCa_1,rdbtnCa_2);
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(null);
    }
 
}
