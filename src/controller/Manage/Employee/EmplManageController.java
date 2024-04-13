package controller.Manage.Employee;

import Service.impl.EmployeeService;
import model.CustomerModel;
import model.EmployeeModel;
import model.MovieModel;
import utility.ClassTableModel;
import utility.SetTable;
import view.Manage.EmplPanel.EmplManageJFrame;
import view.Manage.ManagementView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

public class EmplManageController {
    private JFrame frame;
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;

    private EmployeeModel employeeModel;
    private MouseListener[] mouseListeners;
    private JTable table = new JTable();
    SetTable<MovieModel> setTable = SetTable.getInstance();

    private final String[] COLUMNS = {"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Giới tính","Ngày sinh", "Địa chỉ","CCCD", "Trạng thái", "Tài khoản", "Mật khẩu"};
    String[] methodNames = {"getMaNV", "getHoTen", "getSdt","isGioiTinh","getNgaySinh","getDiaChi","getCCCD","isTinhTrang","getTentk","getMatKhau"};

//    obj[3] = employeeModel.isGioiTinh() ? "Nam" : "Nữ";


    private EmployeeService employeeService = null;


    public EmplManageController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.employeeService = new EmployeeService();
    }
    
    public void displayView() {
        List<EmployeeModel> listItem = employeeService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
    	btnAdd.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				EmplManageJFrame emplManageJFrame = new EmplManageJFrame(employeeModel,jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
				emplManageJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                emplManageJFrame.setTitle("Thông tin nhân viên");
				emplManageJFrame.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(89, 190, 89));
                btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(34, 139, 34));
			}
		});
        eventTable(table);
        remove(table);
        mouseListeners = table.getMouseListeners();

    }
    private void eventTable(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    EmployeeModel employeeModel = new EmployeeModel();
                    employeeModel.setMaNV(model.getValueAt(selectedRowIndex, 0).toString());
                    employeeModel.setHoTen(model.getValueAt(selectedRowIndex, 1).toString());
                    employeeModel.setSdt(model.getValueAt(selectedRowIndex, 2) != null
                            ? model.getValueAt(selectedRowIndex, 2).toString() : null);
                    employeeModel.setGioiTinh(model.getValueAt(selectedRowIndex, 3).toString().equalsIgnoreCase("Nam"));
                    employeeModel.setNgaySinh((Date) model.getValueAt(selectedRowIndex, 4) != null
                            ? (Date) model.getValueAt(selectedRowIndex, 4) : null);
                    employeeModel.setDiaChi(model.getValueAt(selectedRowIndex, 5) != null
                            ? model.getValueAt(selectedRowIndex, 5).toString() : null);
                    employeeModel.setCCCD((Long)model.getValueAt(selectedRowIndex, 6));
                    employeeModel.setTinhTrang((boolean) model.getValueAt(selectedRowIndex, 7));
                    employeeModel.setTentk(model.getValueAt(selectedRowIndex, 8).toString());
                    employeeModel.setMatKhau(model.getValueAt(selectedRowIndex, 9).toString());

                    EmplManageJFrame jframe = new EmplManageJFrame(employeeModel,jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
                    jframe.setLocationRelativeTo(null);
                    jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jframe.setResizable(false);
                    jframe.setTitle("Thông tin nhân viên");
                    jframe.setVisible(true);

                }
            }

        });
    }

    private void remove(JTable table) {
        EmployeeModel employeeModel = new EmployeeModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    employeeModel.setMaNV(model.getValueAt(selectedRowIndex, 0).toString());

                }
            }
        });
        btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(showDialog()) {
                    if (employeeModel.getMaNV() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else{
                        employeeService.delete(employeeModel);
                        loadTable();
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRemove.setBackground(new Color(172, 92, 92));
                btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRemove.setBackground(new Color(255, 0, 0));

            }
        });
    }
    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private void loadTable(){
        List<EmployeeModel> listItem = employeeService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }
}
