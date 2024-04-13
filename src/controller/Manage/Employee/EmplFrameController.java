package controller.Manage.Employee;

import Service.impl.EmployeeService;
import Service.impl.TypeMovieService;
import com.toedter.calendar.JDateChooser;
import model.EmployeeModel;
import model.MovieModel;
import utility.SessionUtil;
import utility.SetTable;
import view.Manage.EmplPanel.EmplManageJFrame;
import view.Manage.ManagementView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmplFrameController {
    private JFrame frame;
    private EmployeeService employeeService;
    private EmployeeModel employeeModel;
    private String msg;
    private EmplManageJFrame empl;
    private JPanel jpnView;
    private JTable table;
    private String[] COLUMNS;
    private JTextField jtfSearch;
    private String[] methodNames;
    private MouseListener[] mouseListeners;
    SetTable<MovieModel> setTable = SetTable.getInstance();
    public EmplFrameController(EmplManageJFrame emplManageJFrame,JPanel jpnView, String[] COLUMNS, JTextField jtfSearch, String[] methodNames,MouseListener[] mouseListeners){
        this.empl =emplManageJFrame;
        this.jpnView = jpnView;
        this.COLUMNS = COLUMNS;
        this.jtfSearch = jtfSearch;
        this.methodNames = methodNames;
        this.mouseListeners = mouseListeners;
        employeeService = new EmployeeService();
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
                                employeeService.save(employeeModel);
                                empl.dispose();
                                loadTable();
                            }
                        }else {
                            msg = "Bạn muốn cập nhật dữ liệu không ?";
                            if(showDialog(msg)){
                                employeeService.update(employeeModel);
                                empl.dispose();
                                loadTable();
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
    private void loadTable(){
        jpnView.removeAll();
        jpnView.validate();
        jpnView.repaint();
        List<EmployeeModel> listItem = employeeService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }
}
