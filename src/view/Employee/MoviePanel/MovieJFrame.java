package view.Employee.MoviePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.security.PublicKey;

import javax.swing.*;
import javax.swing.border.Border;

import Service.impl.TypeMovieService;
import controller.Employee.Movie.MovieFrameController;

import com.toedter.calendar.JDateChooser;

import model.MovieModel;
import model.TypeMovieModel;
import view.Manage.EmplPanel.EmplManageJFrame;

public class MovieJFrame extends JFrame {


	private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JPanel contentPane;
    public JButton btnSave;
    public JButton btnExit;
    public JTextField jtfDirector;
    public JComboBox jbxType;
    public JDateChooser jdcDate;
    public JTextArea jtaDes;
    public JTextArea jtaVideo;
    public JTextField jtfName;
    public JTextField jtfThumbnail;
    public JTextField jtfYear;
    public JTextField jtfDura;
    public JCheckBox jcbStatus;
    public JLabel jlbId;


	public MovieJFrame(MovieModel movieModel, JPanel jpnView, String[] COLUMNS, JTextField jtfSearch, String[] methodNames, MouseListener[] mouseListeners) {

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
                JLabel lblNewLabel_1 = new JLabel("Loại phim");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(33, 139, 110, 41);
        jPanel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Đạo diễn");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(33, 211, 126, 41);
        jPanel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Ngày chiếu");
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2.setBounds(33, 283, 110, 41);
        jPanel.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("Tên phim");
        lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_3.setBounds(516, 140, 110, 41);
        jPanel.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_5 = new JLabel("Độ tuổi");
        lblNewLabel_1_5.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_5.setBounds(516, 211, 110, 41);
        jPanel.add(lblNewLabel_1_5);
        
        jtfDirector = new JTextField();
        jtfDirector.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfDirector.setColumns(10);
        jtfDirector.setBounds(157, 212, 292, 41);
        jPanel.add(jtfDirector);
        
        btnExit = new JButton("Thoát");
        setPicture(btnExit, "/img/exit.png", 25, 25);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Arial", Font.BOLD, 18));
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setBounds(792, 22, 176, 41);
        jPanel.add(btnExit);
        
        jtfName = new JTextField();
        jtfName.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfName.setColumns(10);
        jtfName.setBounds(617, 140, 292, 41);
        jPanel.add(jtfName);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Hình");
        lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1.setBounds(33, 371, 110, 41);
        jPanel.add(lblNewLabel_1_2_1);
        
        JLabel lblNewLabel_1_2_1_1 = new JLabel("Trạng thái");
        lblNewLabel_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_2_1_1.setBounds(516, 371, 110, 41);
        jPanel.add(lblNewLabel_1_2_1_1);
        
        jcbStatus = new JCheckBox("Kích hoạt");
        jcbStatus.setHorizontalAlignment(SwingConstants.CENTER);
        jcbStatus.setFont(new Font("Arial", Font.BOLD, 17));
        jcbStatus.setBounds(617, 371, 292, 41);
        jPanel.add(jcbStatus);
        
        jdcDate = new JDateChooser();
        jdcDate.setDateFormatString("dd-MM-yyyy");
        jdcDate.setBounds(157, 283, 292, 41);
        jPanel.add(jdcDate);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Video");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(33, 468, 126, 41);
        jPanel.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Mô tả");
        lblNewLabel_1_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(516, 468, 126, 41);
        jPanel.add(lblNewLabel_1_1_2);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label.setFont(new Font("Dialog", Font.BOLD, 18));
        label.setBounds(463, 156, 55, 24);
        jPanel.add(label);
        
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
        
        JLabel label_4 = new JLabel("");
        label_4.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_4.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4.setBounds(919, 225, 55, 24);
        jPanel.add(label_4);
        
        jlbId = new JLabel("");
        jlbId.setForeground(new Color(204, 0, 0));
        jlbId.setFont(new Font("Arial", Font.BOLD, 20));
        jlbId.setBounds(447, 664, 126, 35);
        jPanel.add(jlbId);

        JLabel label_4_1 = new JLabel("");
        label_4_1.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_4_1.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_1.setBounds(463, 297, 55, 24);
        jPanel.add(label_4_1);
        
        JLabel label_3 = new JLabel("");
        label_3.setIcon(new ImageIcon(EmplManageJFrame.class.getResource("/img/key.png")));
        label_3.setFont(new Font("Dialog", Font.BOLD, 18));
        label_3.setBounds(919, 156, 55, 24);
        jPanel.add(label_3);

        jbxType = new JComboBox();
        jbxType.addItem(new TypeMovieModel());
        jbxType.setBounds(157, 139, 292, 41);
        jPanel.add(jbxType);
        
        jtfThumbnail = new JTextField();
        jtfThumbnail.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfThumbnail.setBounds(157, 371, 292, 41);
        jPanel.add(jtfThumbnail);
        jtfThumbnail.setColumns(10);
        
        jtfYear = new JTextField();
        jtfYear.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfYear.setBounds(617, 211, 292, 41);
        jPanel.add(jtfYear);
        jtfYear.setColumns(10);
        
        jtfDura = new JTextField();
        jtfDura.setFont(new Font("Arial", Font.PLAIN, 16));
        jtfDura.setColumns(10);
        jtfDura.setBounds(617, 283, 292, 41);
        jPanel.add(jtfDura);
        
        JLabel lblNewLabel_1_5_1 = new JLabel("Thời lượng");
        lblNewLabel_1_5_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_5_1.setBounds(516, 283, 110, 41);
        jPanel.add(lblNewLabel_1_5_1);
        
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
        
        jtaVideo = new JTextArea();
        jtaVideo.setFont(new Font("Arial", Font.PLAIN, 15));
        jtaVideo.setBounds(157, 479, 292, 160);
        jtaVideo.setBorder(border1);
        
        JLabel lblNewLabel_3 = new JLabel("Phút");
        lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 15));
        lblNewLabel_3.setBounds(923, 300, 45, 13);
        jPanel.add(lblNewLabel_3);
      
        
        jtaDes = new JTextArea();
        jtaDes.setBounds(617, 479, 350, 231);
        jtaDes.setBorder(border1);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(617, 479, 357, 231);
        scroll.setViewportView(jtaDes);
        scroll.setPreferredSize(new Dimension(350, 231));
        
        jPanel.add(scroll);
        
        JScrollPane scroll1 = new JScrollPane();
        scroll1.setBounds(157, 479, 292, 160);
        scroll1.setViewportView(jtaVideo);
        scroll1.setPreferredSize(new Dimension(292, 160));
        
        jPanel.add(scroll1);
        
        JLabel label_4_1_1 = new JLabel("");
        label_4_1_1.setIcon(new ImageIcon(MovieJFrame.class.getResource("/img/key.png")));
        label_4_1_1.setFont(new Font("Dialog", Font.BOLD, 18));
        label_4_1_1.setBounds(463, 548, 55, 24);
        jPanel.add(label_4_1_1);

        MovieFrameController movieFrameController = new MovieFrameController(this,jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
	    if(movieModel == null) movieFrameController.CreateOrUpdate();
        else movieFrameController.setView(movieModel);
    }
	public void setPicture(JButton button, String fileName, int width, int height) {
		ImageIcon originalIcon = new ImageIcon(EmplManageJFrame.class.getResource(fileName));
		Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		button.setIcon(scaledIcon);
    }
}
