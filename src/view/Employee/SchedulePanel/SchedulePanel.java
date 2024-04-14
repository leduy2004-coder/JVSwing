package view.Employee.SchedulePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.Employee.Schedule.ScheduleController;
import model.MovieModel;
import model.ShiftModel;
import view.Employee.HomeEmplPanel;
import java.awt.Insets;
import java.awt.Cursor;

public class SchedulePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JDateChooser jdcBegin;
	public JDateChooser jdcEnd;
	public JComboBox jbxName;
	public JComboBox jbxShift;
	public JComboBox<String> jbxRoom;
	public JButton btnSearch;
	public JButton btnFilter;
	public JButton btnSave;
	public JButton btnRemove;
	public JPanel panelFrmtable;
	public JTextField jtfSearch;


	public SchedulePanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0, 5, 5, 5));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		add(panel, BorderLayout.NORTH);
		
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 10, 10, 10);
        Border topBorder = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK); 
        Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		JLabel lblNewLabel = new JLabel("Quản lí suất chiếu");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 90));
		panel_1.add(panel_2, BorderLayout.NORTH);
		
	
		JPanel table = new JPanel();
		panel_1.add(table, BorderLayout.CENTER);
		table.setLayout(new BorderLayout(0, 0));
		table.setBorder(border);
		
		JPanel panel_13 = new JPanel();
		table.add(panel_13, BorderLayout.NORTH);
		
		JPanel panel_15 = new JPanel();
		panel_13.add(panel_15);
		
		JLabel lblNewLabel_5_1 = new JLabel("Danh sách suất chiếu");
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 23));
		panel_15.add(lblNewLabel_5_1);
		
		panelFrmtable = new JPanel();
		table.add(panelFrmtable, BorderLayout.CENTER);
		
		JPanel panel_14 = new JPanel();
		panel_14.setPreferredSize(new Dimension(10, 130));
		table.add(panel_14, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_7_1 = new JLabel("Tìm mã");
		lblNewLabel_7_1.setFont(new Font("Arial", Font.PLAIN, 21));
		panel_14.add(lblNewLabel_7_1);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setPreferredSize(new Dimension(10, 0));
		horizontalStrut_9.setMinimumSize(new Dimension(10, 0));
		panel_14.add(horizontalStrut_9);
		
		jtfSearch = new JTextField();
		jtfSearch.setFont(new Font("Arial", Font.PLAIN, 16));
		jtfSearch.setMargin(new Insets(2, 15, 2, 5));
		jtfSearch.setMinimumSize(new Dimension(100, 19));
		jtfSearch.setPreferredSize(new Dimension(80, 27));
		panel_14.add(jtfSearch);
		jtfSearch.setColumns(10);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setPreferredSize(new Dimension(90, 0));
		panel_14.add(horizontalStrut_8);
		
		btnRemove = new JButton("- Xóa");
		btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_14.add(btnRemove);
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(255, 0, 0));
		btnRemove.setPreferredSize(new Dimension(130, 30));
		btnRemove.setFont(new Font("Arial", Font.BOLD, 17));
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(400, 80));
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày bắt đầu ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 17));
		panel_5.add(lblNewLabel_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut);

		jdcBegin = new JDateChooser();
		jdcBegin.setFont(new Font("Arial", Font.PLAIN, 15));
		jdcBegin.setPreferredSize(new Dimension(180, 30));
		jdcBegin.setDateFormatString("dd-MM-yyyy");
		panel_5.add(jdcBegin);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		
		JLabel lblNewLabel_4 = new JLabel("Ngày kết thúc");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 17));
		panel_7.add(lblNewLabel_4);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_3);
		
		jdcEnd = new JDateChooser();
		jdcEnd.setFont(new Font("Arial", Font.PLAIN, 15));
		jdcEnd.setPreferredSize(new Dimension(180, 30));
		jdcEnd.setDateFormatString("dd-MM-yyyy");
		panel_7.add(jdcEnd);

	
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(150, 0));
		panel_2.add(horizontalStrut_1);
		
		btnFilter = new JButton("Lọc");
		btnFilter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFilter.setPreferredSize(new Dimension(130, 30));
		btnFilter.setForeground(Color.WHITE);
		btnFilter.setFont(new Font("Arial", Font.BOLD, 17));
		btnFilter.setBackground(new Color(51, 51, 102));
		panel_2.add(btnFilter);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 35));
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("     Xóa:          Kích chuột vào 1 dòng trong table rồi nhấn nút Xóa\r\n");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setPreferredSize(new Dimension(51, 50));
		lblNewLabel_2.setFont(new Font("Arial", Font.ITALIC, 14));
		panel_3.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("     Cập nhật:   Kích đúp chuột vào 1 dòng trong table ");
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("Arial", Font.ITALIC, 14));
		panel_3.add(lblNewLabel_3, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.WEST);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		panel_8.setBorder(border);
		
		JPanel panel_12 = new JPanel();
		panel_12.setMaximumSize(new Dimension(32767, 3267));
		panel_12.setMinimumSize(new Dimension(10, 20));
		panel_8.add(panel_12);
		
		JLabel lblNewLabel_5 = new JLabel("Thông tin");
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 23));
		panel_12.add(lblNewLabel_5);
		
		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9);
		
		JPanel panel_11 = new JPanel();
		panel_9.add(panel_11);
		
		JLabel lblNewLabel_6 = new JLabel("Tên phim     ");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 21));
		panel_11.add(lblNewLabel_6);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panel_11.add(horizontalStrut_5);

		jbxName = new JComboBox();
		jbxName.setMaximumRowCount(3);
		jbxName.setPrototypeDisplayValue(" None of the above ");
		jbxName.addItem(new MovieModel());
		jbxName.setFont(new Font("Arial", Font.PLAIN, 19));
		jbxName.setPreferredSize(new Dimension(220, 30));
		panel_11.add(jbxName);
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10);


		JLabel lblNewLabel_8 = new JLabel("Ca chiếu      ");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 21));
		panel_10.add(lblNewLabel_8);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		panel_10.add(horizontalStrut_6);

		jbxShift = new JComboBox();
		jbxShift.setMaximumRowCount(3);
		jbxShift.setPrototypeDisplayValue(" None of the above ");
		jbxShift.setFont(new Font("Arial", Font.PLAIN, 19));
		jbxShift.setPreferredSize(new Dimension(220, 30));
		panel_10.add(jbxShift);
		
		JPanel panel_6 = new JPanel();
		panel_8.add(panel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Phòng chiếu");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_7);
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_7);

		jbxRoom= new JComboBox<String>();
		jbxRoom.setMaximumRowCount(3);
		jbxRoom.setPrototypeDisplayValue(" None of the above ");
		jbxRoom.setFont(new Font("Arial", Font.PLAIN, 19));
		jbxRoom.setPreferredSize(new Dimension(220, 30));
		panel_6.add(jbxRoom);
		
		JPanel panel_16 = new JPanel();
		panel_16.setMaximumSize(new Dimension(32767, 3277));
		panel_8.add(panel_16);

		btnSearch = new JButton("Tìm kiếm");
		btnSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSearch.setPreferredSize(new Dimension(200, 37));
		btnSearch.setFont(new Font("Arial", Font.BOLD, 17));
		panel_16.add(btnSearch);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 80));
		panel_8.add(verticalStrut);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		add(horizontalStrut_4, BorderLayout.WEST);
	
		
		btnSave = new JButton("+ Thêm mới");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(34, 139, 34));
		btnSave.setPreferredSize(new Dimension(130, 30));
		btnSave.setFont(new Font("Arial", Font.BOLD, 17));
		panel_2.add(btnSave);


		ScheduleController scheduleController = new ScheduleController(this);
		scheduleController.setData();
		scheduleController.setEvent();
	}
	public void setPicture(JLabel label, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(HomeEmplPanel.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label.setIcon(scaledIcon);
    }
}
