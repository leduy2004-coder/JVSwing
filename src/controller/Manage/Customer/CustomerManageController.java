package controller.Manage.Customer;

import Service.impl.CustomerService;
import model.CustomerModel;
import utility.ClassTableModel;
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
import java.sql.Date;
import java.util.List;

public class CustomerManageController {
    private JFrame frame;
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;

    private ClassTableModel classTableModel = null;
    private CustomerModel customerModel;

    private final String[] COLUMNS = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Ngày sinh", "Email", "Trạng thái", "Tài khoản", "Mật khẩu"};

    private CustomerService customerService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    public CustomerManageController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch, JFrame frame) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.frame =frame;
        this.classTableModel = new ClassTableModel();
        this.customerService = new CustomerService();
    }
    public void setDataToTable() {
        List<CustomerModel> listItem = customerService.selectAll();
        DefaultTableModel model = classTableModel.setTableCustomer(listItem, COLUMNS);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter); //sort

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 29));
        table.setRowHeight(39);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();

        eventTable(table);
        remove(table);
    }
    private void applyFilter() {
        String text = jtfSearch.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            RowFilter<Object, Object> rowFilter = RowFilter.regexFilter("(?i)" + text, 0);
            rowSorter.setRowFilter(rowFilter);
        }
    }

    public void displayView() {
        btnAdd.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerManageJFrame customerManageJFrame = new CustomerManageJFrame(customerModel,frame);
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

                    CustomerManageJFrame jframe = new CustomerManageJFrame(customerModel,frame);
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
                int check = 0;
                if(showDialog()) {
                    check = customerService.delete(customerModel);
                    if (customerModel.getMaKH() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else {
                        setDataToTable();
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

}
