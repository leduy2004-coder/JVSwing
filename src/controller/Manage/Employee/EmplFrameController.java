package controller.Manage.Employee;

import Dao.impl.EmployeeDAO;
import model.EmployeeModel;
import utility.SessionUtil;
import view.Manage.EmplPanel.EmplManageJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmplFrameController extends EventEmpl{
    private EmployeeModel employeeModel;
    private String msg;
    private EmplManageJFrame empl;
    private JPanel jpnView;
    private JTextField jtfSearch;
    private JButton btnRemove;
    public EmplFrameController(EmplManageJFrame emplManageJFrame,JPanel jpnView, JTextField jtfSearch,JButton btnRemove){
        this.empl =emplManageJFrame;
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.btnRemove = btnRemove;
    }

    public void setView(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
        empl.jlbId.setText("Mã: "+employeeModel.getMaNV());
        empl.jtfName.setText(employeeModel.getHoTen());
        empl.jdcDateOfBirth.setDate(employeeModel.getNgaySinh());
        empl.jtfPhone.setText(employeeModel.getSdt());
        empl.jtaAddress.setText(employeeModel.getDiaChi());
        if (employeeModel.isGioiTinh()) {
            empl.jtfMale.setSelected(true);
        } else {
            empl.jtfFemale.setSelected(true);
        }
        empl.jtfCccd.setText(String.valueOf(employeeModel.getCCCD()));
        empl.jtfUser.setText(employeeModel.getTentk());
        empl.jtfPass.setText(employeeModel.getMatKhau());
        empl.jcbStatus.setSelected(employeeModel.isTinhTrang());
        CreateOrUpdate();
    }

    public void CreateOrUpdate() {
        empl.btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(!check()){
                        msg = "Vui lòng nhập đầy đủ dữ liệu !!";
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        if(employeeModel == null) {
                            employeeModel = new EmployeeModel();
                        }
                        employeeModel.setSdt(empl.jtfPhone.getText().trim());
                        employeeModel.setHoTen(empl.jtfName.getText().trim());
                        msg = "Dữ liệu CCCD sai !!";
                        employeeModel.setCCCD(Long.parseLong(empl.jtfCccd.getText().trim()));
                        msg= "";
                        employeeModel.setDiaChi(empl.jtaAddress.getText().trim());
                        employeeModel.setTinhTrang(empl.jcbStatus.isSelected());
                        employeeModel.setGioiTinh(empl.jtfMale.isSelected());
                        employeeModel.setTentk(empl.jtfUser.getText().trim());
                        employeeModel.setMatKhau(empl.jtfPass.getText().trim());
                        employeeModel.setNgaySinh(covertDateToDateSql(empl.jdcDateOfBirth.getDate()));
                        employeeModel.setMaQL(SessionUtil.getInstance().getValueManage().getMaQL());
                        if(employeeModel.getMaNV() == null){
                            msg = "Bạn muốn thêm dữ liệu không ?";
                            if(showDialog(msg)){
                                EmployeeDAO.getInstance().insert(employeeModel);
                                empl.dispose();
                                loadTable(jpnView,jtfSearch,btnRemove);
                            }
                        }else {
                            msg = "Bạn muốn cập nhật dữ liệu không ?";
                            if(showDialog(msg)){
                                EmployeeDAO.getInstance().update(employeeModel);
                                empl.dispose();
                                loadTable(jpnView,jtfSearch,btnRemove);
                            }
                        }
                    }
                }catch (Exception ex) {
                    if(msg.equalsIgnoreCase("Dữ liệu CCCD sai !!")){
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                    }else {
                        msg = ex.toString();
                        if (msg.contains("CK_nv_sdt")||empl.jtfPhone.getText().length()>10) {
                            msg = "Số điện thoại phải có 10 số !!";
                        }else if(msg.contains("U_nv_sdt")){
                            msg = "Dữ liệu số điện thoại đã có !!";
                        }else if(msg.contains("U_nv_cccd")){
                            msg = "Dữ liệu CCCD đã có !!";
                        }else if (msg.contains("U_nv_user")) {
                            msg = "Tài khoản và mật khẩu đã có !!";
                        }
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                empl.btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                empl.btnSave.setBackground(new Color(6, 199, 73));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                empl.btnSave.setBackground(new Color(0, 153, 51));
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
        return empl.jtfName.getText() != null && !empl.jtfName.getText().equalsIgnoreCase("") &&
                empl.jtfCccd.getText() != null && !empl.jtfCccd.getText().equalsIgnoreCase("") &&
                empl.jdcDateOfBirth.getDate() != null && !convertDatetoString(empl.jdcDateOfBirth.getDate()).equalsIgnoreCase("") &&
                empl.jtfPhone.getText() != null && !empl.jtfPhone.getText().equalsIgnoreCase("") &&
                !(!empl.jtfFemale.isSelected()&&!empl.jtfMale.isSelected()) &&
                empl.jtfUser.getText() != null && !empl.jtfUser.getText().equalsIgnoreCase("") &&
                empl.jtfPass.getText() != null && !empl.jtfPass.getText().equalsIgnoreCase("");
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

    private void exit() {
        empl.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                empl.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                empl.btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                empl.btnExit.setBackground(new Color(232, 54, 54));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                empl.btnExit.setBackground(new Color(255, 0, 0));
            }
        });
    }
    private String convertDatetoString (Date d){
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        return date = sp.format(d);
    }
}
