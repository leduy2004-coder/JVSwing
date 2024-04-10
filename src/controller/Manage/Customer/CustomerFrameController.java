package controller.Manage.Customer;

import Service.impl.CustomerService;
import com.toedter.calendar.JDateChooser;
import model.CustomerModel;
import view.Manage.CustomerPanel.CustomerManageJFrame;
import view.Manage.ManagementView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerFrameController {
    private JFrame frame;
    private CustomerManageJFrame customer;
    private CustomerService customerService;
    private CustomerModel customerModel;
    private String msg;

    public CustomerFrameController(JFrame frame, CustomerManageJFrame customerManageJFrame){
        this.frame = frame;
        this.customer = customerManageJFrame;
        customerService = new CustomerService();
    }
    public void setView(CustomerModel customerModel) {
        this.customerModel = customerModel;
        customer.jlbId.setText("Mã: "+customerModel.getMaKH());
        customer.jtfName.setText(customerModel.getHoTen());
        customer.jdcDateOfBirth.setDate(customerModel.getNgaySinh());
        customer.jtfPhone.setText(customerModel.getSdt());
        customer.jtfEmail.setText(customerModel.getEmail());
        customer.jtfUser.setText(customerModel.getTentk());
        customer.jtfPass.setText(customerModel.getMatKhau());
        customer.jcbStatus.setSelected(customerModel.isTinhTrang());
        CreateOrUpdate();
    }

    public void CreateOrUpdate() {
        customer.btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(!check()){
                        msg = "Vui lòng nhập đầy đủ dữ liệu !!";
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        if(customerModel == null) {
                            customerModel = new CustomerModel();
                        }
                        customerModel.setSdt(customer.jtfPhone.getText().trim());
                        customerModel.setHoTen(customer.jtfName.getText().trim());
                        customerModel.setEmail(customer.jtfEmail.getText().trim());
                        customerModel.setTinhTrang(customer.jcbStatus.isSelected());
                        customerModel.setTentk(customer.jtfUser.getText().trim());
                        customerModel.setMatKhau(customer.jtfPass.getText().trim());
                        customerModel.setNgaySinh(covertDateToDateSql(customer.jdcDateOfBirth.getDate()));

                        if(customerModel.getMaKH() == null){
                            msg = "Bạn muốn thêm dữ liệu không ?";
                            if(showDialog(msg)){
                                customerService.save(customerModel);
                                frame.dispose();
                                ManagementView managementView = new ManagementView();
                                managementView.CustomerPage();
                                managementView.setVisible(true);
                                customer.dispose();
                            }
                        }else {
                            msg = "Bạn muốn cập nhật dữ liệu không ?";
                            if(showDialog(msg)){
                                customerService.update(customerModel);
                                frame.dispose();
                                ManagementView managementView = new ManagementView();
                                managementView.CustomerPage();
                                managementView.setVisible(true);
                                customer.dispose();
                            }
                        }
                    }
                }catch (Exception ex) {
                        msg = ex.toString();
                        if (msg.contains("CK_kh_sdt")||customer.jtfPhone.getText().length()>10) {
                            msg = "Số điện thoại phải có 10 số !!";
                        }else if(msg.contains("U_kh_sdt")){
                            msg = "Dữ liệu số điện thoại đã có !!";
                        }else if(msg.contains("U_kh_KH")){
                            msg = "Số điện thoại đã đăng kí !!";
                        }else if (msg.contains("U_kh_user")) {
                            msg = "Tài khoản và mật khẩu đã có !!";
                        }
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                customer.btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                customer.btnSave.setBackground(new Color(6, 199, 73));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                customer.btnSave.setBackground(new Color(0, 153, 51));
            }
        });
        exit();
    }
    private boolean showDialog(String msg) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                msg, "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    private boolean check(){
        return customer.jtfName.getText() != null && !customer.jtfName.getText().equalsIgnoreCase("") &&
                customer.jdcDateOfBirth.getDate() != null && !convertDatetoString(customer.jdcDateOfBirth.getDate()).equalsIgnoreCase("") &&
                customer.jtfPhone.getText() != null && !customer.jtfPhone.getText().equalsIgnoreCase("") &&
                customer.jtfUser.getText() != null && !customer.jtfUser.getText().equalsIgnoreCase("") &&
                customer.jtfPass.getText() != null && !customer.jtfPass.getText().equalsIgnoreCase("");
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

    private void exit() {
        customer.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customer.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                customer.btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                customer.btnExit.setBackground(new Color(232, 54, 54));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                customer.btnExit.setBackground(new Color(255, 0, 0));
            }
        });
    }
    private String convertDatetoString (Date d){
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        return date = sp.format(d);
    }

}
