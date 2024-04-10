package view.Manage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import view.Employee.HomeEmplPanel;

public class HomeManagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomeManagePanel() {
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Trang chủ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 25));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));


		Border border = BorderFactory.createLineBorder(Color.GREEN);
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 99));
		lblNewLabel_1.setText("<html><div style='text-align: center;'>Hệ thống quản lí<br>rạp chiếu phim</div></html>");
		setPicture(lblNewLabel_1, "/img/anhnen.png", 1700, 1000);
		lblNewLabel_1.setHorizontalTextPosition(JLabel.CENTER);
		lblNewLabel_1.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel_1.setForeground(new Color(255, 255, 224));
		lblNewLabel_1.setIconTextGap(-25);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBorder(new LineBorder(Color.BLACK));
		lblNewLabel_1.setVerticalAlignment(JLabel.CENTER);
		lblNewLabel_1.setHorizontalAlignment(JLabel.CENTER);

		panel_1.add(lblNewLabel_1, BorderLayout.CENTER);
		
		    

		
	}
	public void setPicture(JLabel label, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(HomeEmplPanel.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		label.setIcon(scaledIcon);
    }

}
