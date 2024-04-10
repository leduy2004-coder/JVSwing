package view.Manage.StatisticPanel;

import controller.Manage.Statistic.StatisticController;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;

public class StatisticPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StatisticPanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 85));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 3, 10, 10));
		
		JPanel panel_3 = new JPanel();
		panel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		panel_3.setBackground(new Color(192, 192, 192));
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		panel_3.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(80, 70));
		lblNewLabel.setIcon(new ImageIcon(StatisticPanel.class.getResource("/img/movie-strip.png")));
		panel_3.add(lblNewLabel, BorderLayout.WEST);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(192, 192, 192));
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_1 = new JLabel("30");
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 30));
		panel_6.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Số phim đã chiếu");
		lblNewLabel_2.setForeground(new Color(47, 79, 79));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 17));
		panel_6.add(lblNewLabel_2);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setFont(new Font("Arial", Font.PLAIN, 15));
		panel_3_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_3_1.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_3_1);
		panel_3_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setForeground(new Color(192, 192, 192));
		panel_3_1.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_5 = new JLabel("50");
		lblNewLabel_5.setForeground(new Color(47, 79, 79));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 30));
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Khách từ trước đến nay");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_6.setForeground(new Color(47, 79, 79));
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setPreferredSize(new Dimension(80, 70));
		lblNewLabel_3.setIcon(new ImageIcon(StatisticPanel.class.getResource("/img/customer-service.png")));
		panel_3_1.add(lblNewLabel_3, BorderLayout.WEST);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setFont(new Font("Arial", Font.PLAIN, 15));
		panel_3_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_3_2.setBackground(Color.LIGHT_GRAY);
		panel.add(panel_3_2);
		panel_3_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setPreferredSize(new Dimension(80, 70));
		lblNewLabel_4.setIcon(new ImageIcon(StatisticPanel.class.getResource("/img/new-arrival.png")));
		panel_3_2.add(lblNewLabel_4, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setForeground(new Color(192, 192, 192));
		panel_3_2.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel_7 = new JLabel("70");
		lblNewLabel_7.setForeground(new Color(47, 79, 79));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 30));
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Nhân viên đang làm việc");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_8.setForeground(new Color(47, 79, 79));
		panel_2.add(lblNewLabel_8);
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(10, 300));
		add(bottomPanel, BorderLayout.SOUTH);

		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		bottomPanel.add(panel_4, BorderLayout.NORTH);
		
		JLabel lblNewLabel_9 = new JLabel("Từ");
		lblNewLabel_9.setFont(new Font("Arial", Font.BOLD, 19));
		panel_4.add(lblNewLabel_9);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut);
		
		JDateChooser beginDate = new JDateChooser();
		beginDate.setDateFormatString("dd-MM-yyyy");
		beginDate.setPreferredSize(new Dimension(180, 30));
		beginDate.setFont(new Font("Arial", Font.PLAIN, 15));
		panel_4.add(beginDate);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(30, 0));
		panel_4.add(horizontalStrut_2);
		
		JLabel lblNewLabel_10 = new JLabel("Đến");
		lblNewLabel_10.setFont(new Font("Arial", Font.BOLD, 19));
		panel_4.add(lblNewLabel_10);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_1);
		
		JDateChooser endDate = new JDateChooser();
		endDate.setDateFormatString("dd-MM-yyyy");
		endDate.setFont(new Font("Arial", Font.PLAIN, 15));
		endDate.setPreferredSize(new Dimension(180, 30));
		panel_4.add(endDate);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_3);
		
		JButton btnSeen = new JButton("Xem");
		btnSeen.setPreferredSize(new Dimension(90, 30));
		btnSeen.setFont(new Font("Arial", Font.PLAIN, 19));
		panel_4.add(btnSeen);
		
		JPanel tablePanel = new JPanel();
		bottomPanel.add(tablePanel, BorderLayout.CENTER);

		topPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		topPanel.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel_11 = new JLabel("Năm");
		lblNewLabel_11.setFont(new Font("Arial", Font.BOLD, 19));
		panel_5.add(lblNewLabel_11);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setPreferredSize(new Dimension(20, 0));
		panel_5.add(horizontalGlue);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBox.setPreferredSize(new Dimension(180, 30));
		comboBox.addItem("");
		panel_5.add(comboBox);

		JPanel chartPanel = new JPanel();
		topPanel.add(chartPanel, BorderLayout.CENTER);
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(30, 0));
		panel_5.add(horizontalStrut_4);
		
		JButton btnXem = new JButton("Xem");
		btnXem.setFont(new Font("Arial", Font.PLAIN, 19));
		btnXem.setPreferredSize(new Dimension(90, 30));
		panel_5.add(btnXem);

		StatisticController statisticController = new StatisticController(lblNewLabel_1,lblNewLabel_5,lblNewLabel_7,chartPanel,tablePanel,beginDate,endDate,btnSeen,comboBox,btnXem);
		
		statisticController.setData();
		statisticController.setChart();
		statisticController.setTable();
	}

}
