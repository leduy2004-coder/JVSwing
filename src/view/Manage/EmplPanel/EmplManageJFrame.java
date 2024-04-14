package view.Manage.EmplPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.EmployeeModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;

import controller.Manage.Employee.EmplFrameController;

import javax.swing.JTextArea;

public class EmplManageJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
    public JFrame frame;
    public JPanel contentPane;
    public final ButtonGroup buttonGroup = new ButtonGroup();
    public JButton btnExit;
    public JButton btnSave;
    public JTextField jtfName;
    public JTextField jtfPhone;
    public JDateChooser jdcDateOfBirth;
    public JTextField jtfCccd;
    public JTextArea jtaAddress;
    public JRadioButton jtfMale;
    public JRadioButton jtfFemale;
    public JCheckBox jcbStatus;
    public JTextField jtfUser;
    public JTextField jtfPass;
    public JLabel jlbId;

    private EmplFrameController emplFrameController;

	public EmplManageJFrame(EmployeeModel employeeModel, JPanel jpnView, JTextField jtfSearch, JButton btnRemove) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 996, 663);
		contentPane = new JPanel();
        setContentPane(contentPane);
		contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(980, 620));
        jPanel.setBackground(new Color(255, 250, 250));
        jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jPanel.setLayout(null);

        contentPane.add(jPanel, gbc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(251, 86, 729, 2);
        jPanel.add(separator);
        
        btnSave = new JButton("Lưu dữ liệu");
        setPicture(btnSave, "/img/save.png", 25, 25);
        btnSave.setFont(new Font("Arial", Font.BOLD, 18));
        btnSave.setForeground(Color.WHITE);
        btnSave.setBackground(new Color(0, 153, 51));
        btnSave.setBounds(592, 22, 176, 41);
        jPanel.add(btnSave);
        
        JLabel lblNewLabel = new JLabel("Thông tin nhân viên");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setBounds(67, 73, 202, 28);
        jPanel.add(lblNewLabel);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 86, 60, 2);
        jPanel.add(separator_1);
        
        JLabel lblNewLabel_1 = new JLabel("Họ tên");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(33, 139, 110, 41);
        jPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Số điện thoại");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(33, 211, 126, 41);
        jPanel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Ngày sinh");
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(33, 283, 110, 41);
        jPanel.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("CCCD");
        lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(516, 140, 110, 41);
        jPanel.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_5 = new JLabel("Địa chỉ");
        lblNewLabel_1_5.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_5.setBounds(516, 211, 110, 41);
        jPanel.add(lblNewLabel_1_5);
        
        jtfName = new JTextField();
        jtfName.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfName.setBounds(157, 140, 264, 41);
        jPanel.add(jtfName);
        jtfName.setColumns(10);
        
        jtfPhone = new JTextField();
        jtfPhone.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfPhone.setColumns(10);
        jtfPhone.setBounds(157, 212, 264, 41);
        jPanel.add(jtfPhone);
        
        btnExit = new JButton("Thoát");
        setPicture(btnExit, "/img/exit.png", 25, 25);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(792, 22, 176, 41);
        jPanel.add(btnExit);
        
        jtfCccd = new JTextField();
        jtfCccd.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfCccd.setColumns(10);
        jtfCccd.setBounds(617, 140, 264, 41);
        jPanel.add(jtfCccd);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Giới tính");
        lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1.setBounds(33, 371, 110, 41);
        jPanel.add(lblNewLabel_1_2_1);
        
        jtfMale = new JRadioButton("Nam");
        jtfMale.setHorizontalAlignment(SwingConstants.CENTER);
        buttonGroup.add(jtfMale);
        jtfMale.setFont(new Font("Arial", Font.BOLD, 17));
        jtfMale.setBounds(157, 376, 126, 32);
        jPanel.add(jtfMale);
        
        jtfFemale = new JRadioButton("Nữ");
        jtfFemale.setHorizontalAlignment(SwingConstants.CENTER);
        buttonGroup.add(jtfFemale);
        jtfFemale.setFont(new Font("Arial", Font.BOLD, 17));
        jtfFemale.setBounds(295, 376, 126, 32);
        jPanel.add(jtfFemale);
        
        JLabel lblNewLabel_1_2_1_1 = new JLabel("Trạng thái");
        lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_1.setBounds(516, 371, 110, 41);
        jPanel.add(lblNewLabel_1_2_1_1);
        
        jcbStatus = new JCheckBox("Kích hoạt");
        jcbStatus.setHorizontalAlignment(SwingConstants.CENTER);
        jcbStatus.setFont(new Font("Arial", Font.BOLD, 17));
        jcbStatus.setBounds(617, 378, 264, 28);
        jPanel.add(jcbStatus);

        jdcDateOfBirth = new JDateChooser();
        jdcDateOfBirth.setDateFormatString("dd-MM-yyyy");
        jdcDateOfBirth.setBounds(157, 283, 264, 41);
        jPanel.add(jdcDateOfBirth);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Tài khoản");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(33, 468, 126, 41);
        jPanel.add(lblNewLabel_1_1_1);
        
        jtfUser = new JTextField();
        jtfUser.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfUser.setColumns(10);
        jtfUser.setBounds(157, 469, 264, 41);
        jPanel.add(jtfUser);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Mật khẩu");
        lblNewLabel_1_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(516, 468, 126, 41);
        jPanel.add(lblNewLabel_1_1_2);
        
        jtfPass = new JTextField();
        jtfPass.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfPass.setColumns(10);
        jtfPass.setBounds(617, 469, 264, 41);
        jPanel.add(jtfPass);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        label.setBounds(439, 153, 55, 24);
        jPanel.add(label);
        
        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon(Objects.requireNonNull(EmplManageJFrame.class.getResource("/img/key.png"))));
        label_1.setFont(new Font("Dialog", Font.BOLD, 18));
        label_1.setBounds(439, 482, 55, 24);
        jPanel.add(label_1);
        
        JLabel label_2 = new JLabel("");
        label_2.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_2.setFont(new Font("Dialog", Font.BOLD, 18));
        label_2.setBounds(891, 482, 55, 24);
        jPanel.add(label_2);
        
        JLabel lblBat = new JLabel(": Dữ liệu yêu cầu bắt buộc");
        lblBat.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        lblBat.setForeground(new Color(255, 0, 0));
        lblBat.setFont(new Font("Arial", Font.ITALIC, 17));
        lblBat.setBounds(33, 564, 281, 28);
        jPanel.add(lblBat);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/personal-information.png")));
        lblNewLabel_2.setBounds(12, 12, 110, 62);
        jPanel.add(lblNewLabel_2);
        
        jtaAddress = new JTextArea();
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        jtaAddress.setBorder(border);
        jtaAddress.setBounds(617, 225, 264, 99);
        jPanel.add(jtaAddress);
        
        JLabel label_4 = new JLabel("");
        label_4.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_4.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4.setBounds(439, 225, 55, 24);
        jPanel.add(label_4);
        
        jlbId = new JLabel("");
        jlbId.setForeground(new Color(204, 0, 0));
        jlbId.setFont(new Font("Arial", Font.BOLD, 20));
        jlbId.setBounds(742, 557, 126, 35);
        jPanel.add(jlbId);

        JLabel label_4_1 = new JLabel("");
        label_4_1.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_4_1.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_1.setBounds(439, 297, 55, 24);
        jPanel.add(label_4_1);
        
        JLabel label_4_2 = new JLabel("");
        label_4_2.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_4_2.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_2.setBounds(439, 380, 55, 24);
        jPanel.add(label_4_2);

        JLabel label_3 = new JLabel("");
        label_3.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_3.setFont(new Font("Dialog", Font.BOLD, 18));
        label_3.setBounds(891, 153, 55, 24);
        jPanel.add(label_3);
        emplFrameController = new EmplFrameController(this,jpnView,jtfSearch,btnRemove);
	    if(employeeModel == null) emplFrameController.CreateOrUpdate();
        else emplFrameController.setView(employeeModel);
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(scaledIcon);
    }
}
