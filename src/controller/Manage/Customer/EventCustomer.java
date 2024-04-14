package controller.Manage.Customer;

import Service.impl.CustomerService;
import model.CustomerModel;
import model.MovieModel;
import utility.SetTable;
import view.Manage.CustomerPanel.CustomerManageJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;

public abstract class EventCustomer {
    private final String[] COLUMNS = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Ngày sinh", "Email", "Trạng thái", "Tài khoản", "Mật khẩu"};
    String[] methodNames = {"getMaKH", "getHoTen", "getSdt","getNgaySinh","getEmail","isTinhTrang","getTentk","getMatKhau"};
    private JTable table = new JTable();
    SetTable<MovieModel> setTable = SetTable.getInstance();

    CustomerService customerService = new CustomerService();

    private void eventTable(JTable table,JPanel jpnView, JTextField jtfSearch,JButton btnRemove) {
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

                    CustomerManageJFrame jframe = new CustomerManageJFrame(customerModel,jpnView,jtfSearch,btnRemove);
                    jframe.setLocationRelativeTo(null);
                    jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jframe.setResizable(false);
                    jframe.setTitle("Thông tin khách hàng");
                    jframe.setVisible(true);

                }
            }

        });
    }

    private void remove(JTable table,JPanel jpnView, JTextField jtfSearch,JButton btnRemove) {
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
                        loadTable(jpnView,jtfSearch,btnRemove);
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
    public JTable loadTable(JPanel jpnView, JTextField jtfSearch,JButton btnRemove){
        jpnView.removeAll();
        jpnView.validate();
        jpnView.repaint();
        List<CustomerModel> listItem = customerService.selectAll();
        MouseListener[] listeners = btnRemove.getMouseListeners();
        for (MouseListener listener : listeners) {
            btnRemove.removeMouseListener(listener);
        }
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        eventTable(table,jpnView,jtfSearch,btnRemove);
        remove(table,jpnView,jtfSearch,btnRemove);
        return table;
    }
}
