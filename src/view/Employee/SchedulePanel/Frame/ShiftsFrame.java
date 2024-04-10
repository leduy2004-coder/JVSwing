package view.Employee.SchedulePanel.Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import controller.Employee.Schedule.Frame.ShiftFrController;
import model.ShiftModel;
import utility.ShiftRadioModel;
import view.Manage.EmplPanel.EmplManageJFrame;

public class ShiftsFrame extends JFrame {

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	TimeFrame frame = new TimeFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	private static final long serialVersionUID = 1L;
    private JFrame frame;
	private JPanel contentPane;
	private JButton btnExit;
	private final ButtonGroup buttonGroup = new ButtonGroup();


	public ShiftsFrame(List<ShiftModel> list, JDateChooser jdcDate) {

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
        separator.setBounds(388, 86, 212, 2);
        jPanel.add(separator);
        
        JButton btnNext = new JButton("Tiếp tục");
        btnNext.setFont(new Font("Arial", Font.BOLD, 18));
        btnNext.setForeground(Color.WHITE);
        btnNext.setBackground(new Color(0, 153, 51));
        btnNext.setBounds(258, 341, 143, 35);
        jPanel.add(btnNext);
        
        JLabel lblNewLabel = new JLabel("Chọn ca chiếu");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(233, 73, 155, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 223, 2);
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
        
        JLabel JLabelDate = new JLabel(convertDatetoString(covertDateToDateSql(jdcDate.getDate())));
        JLabelDate.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabelDate.setBounds(12, 119, 165, 35);
        jPanel.add(JLabelDate);
        
        ShiftRadioModel rdbtnNewRadioButton = new ShiftRadioModel();
        buttonGroup.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnNewRadioButton.setBounds(28, 176, 155, 35);
        rdbtnNewRadioButton.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(rdbtnNewRadioButton);

        ShiftRadioModel rdbtnCa = new ShiftRadioModel();
        buttonGroup.add(rdbtnCa);
        rdbtnCa.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa.setBounds(218, 176, 155, 35);
        jPanel.add(rdbtnCa);

        ShiftRadioModel rdbtnCa_5 = new ShiftRadioModel();
        buttonGroup.add(rdbtnCa_5);
        rdbtnCa_5.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa_5.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa_5.setBounds(411, 176, 155, 35);
        jPanel.add(rdbtnCa_5);

        ShiftRadioModel rdbtnCa_1 = new ShiftRadioModel();
        buttonGroup.add(rdbtnCa_1);
        rdbtnCa_1.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa_1.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa_1.setBounds(28, 248, 155, 35);
        jPanel.add(rdbtnCa_1);

        ShiftRadioModel rdbtnCa_2 = new ShiftRadioModel();
        buttonGroup.add(rdbtnCa_2);
        rdbtnCa_2.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa_2.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa_2.setBounds(218, 248, 155, 35);
        jPanel.add(rdbtnCa_2);

        ShiftRadioModel rdbtnCa_3 = new ShiftRadioModel();
        buttonGroup.add(rdbtnCa_3);
        rdbtnCa_3.setHorizontalAlignment(SwingConstants.CENTER);
        rdbtnCa_3.setFont(new Font("Arial", Font.PLAIN, 15));
        rdbtnCa_3.setBounds(411, 248, 155, 35);
        jPanel.add(rdbtnCa_3);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);

        ShiftFrController shiftFrController = new ShiftFrController(this, btnNext,btnExit,jdcDate,list);
        shiftFrController.setData(rdbtnNewRadioButton,rdbtnCa,rdbtnCa_5,rdbtnCa_1,rdbtnCa_2,rdbtnCa_3);
        
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
}
