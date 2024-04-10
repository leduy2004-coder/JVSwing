package view.Employee.SchedulePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

import controller.Employee.Schedule.ScheduleFrController;
import model.MovieModel;
import model.ShiftModel;
import view.Manage.EmplPanel.EmplManageJFrame;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ScheduleFrame extends JFrame {


	private static final long serialVersionUID = 1L;
    private JFrame frame;
	private JPanel contentPane;
	private JButton btnExit;


	public ScheduleFrame(JDateChooser jdcDate, ShiftModel shift,MovieModel movie,String maPhong) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 250, 620, 460);
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
        separator.setBounds(371, 86, 229, 2);
        jPanel.add(separator);
        
        JButton btnSave = new JButton("Thêm");
        btnSave.setFont(new Font("Arial", Font.BOLD, 18));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(new Color(0, 153, 51));
        btnSave.setBounds(277, 362, 143, 35);
        jPanel.add(btnSave);
        
        JLabel lblNewLabel = new JLabel("Lên lịch chiếu");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(227, 73, 174, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 217, 2);
        jPanel.add(separator_1);
        
        btnExit = new JButton("Hủy bỏ");
        btnExit.setIcon(null);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(430, 362, 143, 35);
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
        
        JLabel JLabelDate = new JLabel(convertDatetoString(covertDateToDateSql(jdcDate.getDate())));
        JLabelDate.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabelDate.setBounds(30, 109, 165, 35);
        jPanel.add(JLabelDate);
        
        JLabel JLabelShift = new JLabel(shift.getTenCa());
        JLabelShift.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabelShift.setBounds(100, 167, 117, 35);
        jPanel.add(JLabelShift);
        
        JLabel JLabelTime = new JLabel(convertTimetoString((shift.getTgBD())));
        JLabelTime.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabelTime.setBounds(227, 172, 346, 28);
        jPanel.add(JLabelTime);
        
        JLabel lblNewLabel_3_2 = new JLabel("Phim");
        lblNewLabel_3_2.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_3_2.setBounds(100, 225, 117, 35);
        jPanel.add(lblNewLabel_3_2);
        
        JLabel lblNewLabel_3_3 = new JLabel("Phòng chiếu");
        lblNewLabel_3_3.setFont(new Font("Arial", Font.PLAIN, 17));
        lblNewLabel_3_3.setBounds(100, 291, 117, 35);
        jPanel.add(lblNewLabel_3_3);
        
        JLabel JLabelMovie = new JLabel(movie.getTenPhim());
        JLabelMovie.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabelMovie.setBounds(227, 228, 346, 28);
        jPanel.add(JLabelMovie);
        
        JLabel JLabelRoom = new JLabel(maPhong);
        JLabelRoom.setFont(new Font("Arial", Font.PLAIN, 17));
        JLabelRoom.setBounds(227, 294, 346, 28);
        jPanel.add(JLabelRoom);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);

        ScheduleFrController scheduleFrController = new ScheduleFrController(this,btnSave,btnExit,jdcDate,shift,movie,maPhong);
        scheduleFrController.setDataAndEvent();
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(null);
    }
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
    private String convertDatetoString (Date d){
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        return date = sp.format(d);
    }
    private String convertTimetoString(Time d){
        LocalTime localTime = d.toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = localTime.format(formatter);
        return timeString;
    }

}
