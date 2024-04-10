package controller.Manage.Employee;

import Service.impl.EmployeeService;
import model.EmployeeModel;
import utility.ClassTableModel;
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
import java.sql.Date;
import java.util.List;

public class EmplManageController {
    private JFrame frame;
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;
    
    private ClassTableModel classTableModel = null;
    private EmployeeModel employeeModel;

    private final String[] COLUMNS = {"Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Giới tính","Ngày sinh", "Địa chỉ","CCCD", "Trạng thái", "Tài khoản", "Mật khẩu"};

    private EmployeeService employeeService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    public EmplManageController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch, JFrame frame) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.frame =frame;
        this.classTableModel = new ClassTableModel();
        this.employeeService = new EmployeeService();
    }
    public void setDataToTable() {
        List<EmployeeModel> listItem = employeeService.selectAll();
        DefaultTableModel model = classTableModel.setTableEmpl(listItem, COLUMNS);
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
				EmplManageJFrame emplManageJFrame = new EmplManageJFrame(employeeModel,frame);
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

                    EmplManageJFrame jframe = new EmplManageJFrame(employeeModel,frame);
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
                int check = 0;
                if(showDialog()) {
                    check = employeeService.delete(employeeModel);
                    if (employeeModel.getMaNV() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else{
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
