package controller.Manage.Customer;

import Service.impl.CustomerService;
import model.CustomerModel;
import model.MovieModel;
import utility.ClassTableModel;
import utility.SetTable;
import view.Manage.CustomerPanel.CustomerManageJFrame;
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

public class CustomerManageController {
    private JFrame frame;
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;

    private CustomerModel customerModel;
    private MouseListener[] mouseListeners;
    private JTable table = new JTable();
    SetTable<MovieModel> setTable = SetTable.getInstance();


    private final String[] COLUMNS = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Ngày sinh", "Email", "Trạng thái", "Tài khoản", "Mật khẩu"};
    String[] methodNames = {"getMaKH", "getHoTen", "getSdt","getNgaySinh","getEmail","isTinhTrang","getTentk","getMatKhau"};

    private CustomerService customerService;


    public CustomerManageController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.customerService = new CustomerService();
    }

    public void displayView() {
        List<CustomerModel> listItem = customerService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);

        btnAdd.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerManageJFrame customerManageJFrame = new CustomerManageJFrame(customerModel,jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
                customerManageJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerManageJFrame.setTitle("Thông tin khách hàng");
                customerManageJFrame.setVisible(true);
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

                    CustomerModel customerModel = new CustomerModel();
                    customerModel.setMaKH(model.getValueAt(selectedRowIndex, 0).toString());
                    customerModel.setHoTen(model.getValueAt(selectedRowIndex, 1).toString());
                    customerModel.setSdt(model.getValueAt(selectedRowIndex, 2) != null
                            ? model.getValueAt(selectedRowIndex, 2).toString() : null);
                    customerModel.setNgaySinh((Date) model.getValueAt(selectedRowIndex, 3) != null
                            ? (Date) model.getValueAt(selectedRowIndex, 3) : null);
                    customerModel.setEmail(model.getValueAt(selectedRowIndex, 4).toString());
                    customerModel.setTinhTrang((boolean) model.getValueAt(selectedRowIndex, 5));
                    customerModel.setTentk(model.getValueAt(selectedRowIndex, 6).toString());
                    customerModel.setMatKhau(model.getValueAt(selectedRowIndex, 7).toString());

                    CustomerManageJFrame jframe = new CustomerManageJFrame(customerModel,jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
                    jframe.setLocationRelativeTo(null);
                    jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jframe.setResizable(false);
                    jframe.setTitle("Thông tin khách hàng");
                    jframe.setVisible(true);

                }
            }

        });
    }

    private void remove(JTable table) {
        CustomerModel customerModel = new CustomerModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    customerModel.setMaKH(model.getValueAt(selectedRowIndex, 0).toString());

                }
            }
        });
        btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(showDialog()) {

                    if (customerModel.getMaKH() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else {
                        customerService.delete(customerModel);
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
        List<CustomerModel> listItem = customerService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }

}
