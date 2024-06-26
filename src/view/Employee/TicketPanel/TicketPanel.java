package view.Employee.TicketPanel;

import controller.Employee.Ticket.TicketController;
import view.Employee.HomeEmplPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TicketPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TicketPanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(0, 5, 5, 5));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		add(panel, BorderLayout.NORTH);
		
        Border emptyBorder = BorderFactory.createEmptyBorder(20, 10, 10, 10);
		
		JLabel lblNewLabel = new JLabel("Quản lí vé");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 50));
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm mã");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 21));
		panel_2.add(lblNewLabel_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 17));
		textField.setSize(new Dimension(100, 0));
		textField.setMinimumSize(new Dimension(34, 19));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setPreferredSize(new Dimension(10, 25));
		panel_2.add(textField);
		textField.setColumns(15);
		
	
		JPanel panel_table = new JPanel();
		panel_1.add(panel_table, BorderLayout.CENTER);

		Border border = BorderFactory.createLineBorder(Color.GREEN);

	
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(230, 0));
		panel_2.add(horizontalStrut_1);
		
		JButton btnNewButton = new JButton("+ Thêm mới");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(34, 139, 34));
		btnNewButton.setPreferredSize(new Dimension(130, 30));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 17));
		panel_2.add(btnNewButton);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_2.add(horizontalStrut_2);
		
		JButton btnNewButton_1 = new JButton("- Xóa");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setPreferredSize(new Dimension(130, 30));
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 17));
		panel_2.add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 35));
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("     Xóa:          Kích chuột vào 1 dòng trong table rồi nhấn nút Xóa\r\n");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setPreferredSize(new Dimension(51, 50));
		lblNewLabel_2.setFont(new Font("Arial", Font.ITALIC, 14));
		panel_3.add(lblNewLabel_2);


		TicketController ticketController = new TicketController(panel_table,btnNewButton,btnNewButton_1,textField);
		ticketController.displayView();
		
	}
	public void setPicture(JLabel label, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(HomeEmplPanel.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label.setIcon(scaledIcon);
    }
}
