package view.Employee.TicketPanel;

import controller.Employee.Ticket.TicketFrController;
import view.Manage.EmplPanel.EmplManageJFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;

public class TicketFrame extends JFrame {


	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    public JButton btnSave;
    public JButton btnExit;
    public JTextField jtfAmount;
    public JCheckBox jcbStatus;
    public JTextField jtfMoney;
    public JPanel panelTable;
    public JLabel lbIdTicket;
    public JLabel lbMovie;
    public JLabel lbIdEmploy;
    public JTextField search;

	public TicketFrame(JPanel jpnView, JTextField jtfSearch, JButton btnRemove) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1026, 778);
		contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1000, 720));
        jPanel.setBackground(new Color(255, 250, 250));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(null);

        contentPane.add(jPanel, gbc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(231, 86, 769, 2);
        jPanel.add(separator);
        
        btnSave = new JButton("Lưu dữ liệu");
        setPicture(btnSave, "/img/save.png", 25, 25);
        btnSave.setFont(new Font("Arial", Font.BOLD, 18));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(new Color(0, 153, 51));
        btnSave.setBounds(592, 22, 176, 41);
        jPanel.add(btnSave);
        
        JLabel lblNewLabel = new JLabel("Thông tin phim");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(92, 73, 202, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 88, 2);
        jPanel.add(separator_1);
        
        btnExit = new JButton("Thoát");
        setPicture(btnExit, "/img/exit.png", 25, 25);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(792, 22, 176, 41);
        jPanel.add(btnExit);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Số lượng");
        lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1.setBounds(52, 481, 110, 41);
        jPanel.add(lblNewLabel_1_2_1);
        
        JLabel lblNewLabel_1_2_1_1 = new JLabel("Trạng thái");
        lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_1.setBounds(535, 481, 110, 41);
        jPanel.add(lblNewLabel_1_2_1_1);
        
        jcbStatus = new JCheckBox("Kích hoạt");
        jcbStatus.setHorizontalAlignment(SwingConstants.CENTER);
        jcbStatus.setFont(new Font("Arial", Font.BOLD, 17));
        jcbStatus.setBounds(636, 481, 292, 41);
        jPanel.add(jcbStatus);
        
        JLabel lblBat = new JLabel(": Dữ liệu yêu cầu bắt buộc");
        lblBat.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        lblBat.setForeground(new Color(255, 0, 0));
        lblBat.setFont(new Font("Arial", Font.ITALIC, 17));
        lblBat.setBounds(24, 682, 281, 28);
        jPanel.add(lblBat);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/personal-information.png")));
        lblNewLabel_2.setBounds(12, 12, 110, 62);
        jPanel.add(lblNewLabel_2);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        

        JLabel label_4_1 = new JLabel("");
        label_4_1.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_4_1.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_1.setBounds(482, 487, 55, 24);
        jPanel.add(label_4_1);
        
        jtfAmount = new JTextField();
        jtfAmount.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfAmount.setBounds(176, 481, 292, 41);
        jPanel.add(jtfAmount);
        jtfAmount.setColumns(10);
        
        JLabel lblNewLabel_1_2_1_2 = new JLabel("Tên phim:");
        lblNewLabel_1_2_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_2.setBounds(559, 613, 110, 41);
        jPanel.add(lblNewLabel_1_2_1_2);
        
        JLabel lblNewLabel_1_2_1_2_1 = new JLabel("Mã nhân viên:");
        lblNewLabel_1_2_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_2_1.setBounds(156, 613, 126, 41);
        jPanel.add(lblNewLabel_1_2_1_2_1);
        
        JLabel lblNewLabel_1_2_1_3 = new JLabel("Giá 1 vé");
        lblNewLabel_1_2_1_3.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_3.setBounds(52, 542, 110, 41);
        jPanel.add(lblNewLabel_1_2_1_3);
        
        jtfMoney = new JTextField();
        jtfMoney.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfMoney.setColumns(10);
        jtfMoney.setBounds(176, 542, 292, 41);
        jPanel.add(jtfMoney);
        
        JLabel label_4_1_1 = new JLabel("");
        label_4_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_1_1.setBounds(482, 553, 55, 24);
        jPanel.add(label_4_1_1);
        
        lbIdTicket = new JLabel("");
        lbIdTicket.setForeground(new Color(0, 0, 128));
        lbIdTicket.setFont(new Font("Arial", Font.PLAIN, 20));
        lbIdTicket.setBounds(125, 613, 126, 41);
        jPanel.add(lbIdTicket);
        
        lbIdEmploy = new JLabel("");
        lbIdEmploy.setForeground(new Color(0, 0, 128));
        lbIdEmploy.setFont(new Font("Arial", Font.PLAIN, 20));
        lbIdEmploy.setBounds(292, 613, 176, 41);
        jPanel.add(lbIdEmploy);
        
        lbMovie = new JLabel("");
        lbMovie.setForeground(new Color(0, 0, 128));
        lbMovie.setFont(new Font("Arial", Font.PLAIN, 20));
        lbMovie.setBounds(651, 613, 339, 41);
        jPanel.add(lbMovie);
        
        panelTable = new JPanel();
        panelTable.setBounds(10, 153, 980, 264);
        jPanel.add(panelTable);
        
        JLabel lblKchChutVo = new JLabel("Kích chuột vào 1 dòng để chọn");
        lblKchChutVo.setForeground(Color.RED);
        lblKchChutVo.setFont(new Font("Arial", Font.ITALIC, 17));
        lblKchChutVo.setBounds(12, 426, 281, 28);
        jPanel.add(lblKchChutVo);
        
        JLabel label_4_1_2 = new JLabel("");
        label_4_1_2.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_1_2.setBounds(482, 542, 55, 24);
        jPanel.add(label_4_1_2);
        
        JLabel lblNewLabel_1_2_1_4 = new JLabel("Tìm mã");
        lblNewLabel_1_2_1_4.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_4.setBounds(559, 98, 110, 41);
        jPanel.add(lblNewLabel_1_2_1_4);
        
        search = new JTextField();
        search.setFont(new Font("Arial", Font.PLAIN, 16));
        search.setColumns(10);
        search.setBounds(651, 98, 292, 41);
        jPanel.add(search);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);

        TicketFrController ticketFrController = new TicketFrController( this,jpnView,jtfSearch,btnRemove);
        ticketFrController.CreateOrUpdate();
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(scaledIcon);
    }
}

