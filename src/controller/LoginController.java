package controller;

import Service.impl.EmployeeService;
import Service.impl.ManageService;
import model.EmployeeModel;
import model.ManageModel;
import utility.SessionUtil;
import view.Employee.EmployeeView;
import view.LoginView;
import view.Manage.ManagementView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginController {
    private LoginView dialog;
    private JButton btnLogin;
    private JButton btnExit;
    private JTextField txtUsername;
    private JPasswordField txtpassword;
    private JLabel jlbMess;
    private EmployeeService employeeService;
    private ManageService manageService;

    public LoginController(LoginView dialog, JButton btnLogin, JButton btnExit, JTextField txtUsername, JPasswordField txtpassword, JLabel jlbMess) {
        this.dialog = dialog;
        this.btnLogin = btnLogin;
        this.btnExit = btnExit;
        this.txtUsername = txtUsername;
        this.txtpassword = txtpassword;
        this.jlbMess = jlbMess;

        employeeService = new EmployeeService();
        manageService = new ManageService();
    }

    public void LoginEvent() {
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnLogin.setBackground(new Color(180, 221, 127));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(Color.WHITE);
            }
        });
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dialog.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnExit.setBackground(new Color(180, 221, 127));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnExit.setBackground(Color.WHITE);
            }
        });
    }

    public void keyEvent() {
        txtpassword.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    login();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });
        txtUsername.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    login();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void login() {
        String password = new String(txtpassword.getPassword());
        try {
            if (txtUsername.getText().length() == 0 || password.length() == 0) {
                jlbMess.setText("Phải nhập đầy đủ dữ liệu!");
            } else {
                EmployeeModel employeeModel = employeeService.selectByUserNameAndPassword(txtUsername.getText(), password);
                ManageModel manageModel = manageService.selectByUserNameAndPassword(txtUsername.getText(), password);
                if (employeeModel == null && manageModel == null) {
                    jlbMess.setText("Tài khoản hoặc mật khẩu không đúng!");
                } else {
                    if (employeeModel != null && manageModel == null && employeeModel.isTinhTrang()) {
                        dialog.dispose();
                        SessionUtil.getInstance().putValueEmpl(employeeModel);
                        EmployeeView frame = new EmployeeView();
                        frame.HomePage();
                        frame.setVisible(true);
                    } else if (manageModel != null && employeeModel == null && manageModel.isTinhTrang()) {
                        dialog.dispose();
                        SessionUtil.getInstance().putValueManage(manageModel);
                        ManagementView frame = new ManagementView();
                        frame.HomePage();
                        frame.setVisible(true);
                    } else {
                        jlbMess.setText("Tài khoản của bạn đang bị tạm khóa!");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
