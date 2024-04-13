package controller.Employee.Ticket;

import Service.impl.TicketService;
import model.MovieModel;
import model.TicketModel;
import utility.ClassTableModel;
import utility.SetTable;
import view.Employee.TicketPanel.TicketFrame;

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
import java.util.List;

public class TicketController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;
    private JTable table = new JTable();

    private MouseListener[] mouseListeners;
    private ClassTableModel classTableModel = null;
    private MovieModel movieModel;

    private final String[] COLUMNS = {"Mã vé", "Tên phim", "Số lượng đặt", "Số lượng bán", "Giá 1 vé", "maNV", "Trạng thái"};
    String[] methodNames = {"getMaVe", "getTenPhim", "getSoLuongToiDa","getSoLuongDaBan","getTien","getMaNV","isTinhTrang"};

    private TicketService ticketService = null;

    SetTable<MovieModel> setTable = SetTable.getInstance();

    public TicketController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.classTableModel = new ClassTableModel();
        this.ticketService = new TicketService();
    }

    public void displayView() {
        List<TicketModel> listItem = ticketService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        btnAdd.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                TicketFrame ticketFrame = new TicketFrame(jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
                ticketFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ticketFrame.setTitle("Thông tin vé");
                ticketFrame.setVisible(true);
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

        remove(table);
        mouseListeners = table.getMouseListeners();
    }
    private void remove(JTable table) {
        TicketModel ticketModel = new TicketModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    ticketModel.setMaVe(model.getValueAt(selectedRowIndex, 1).toString());

                }
            }
        });
        btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(showDialog()) {
                    if (ticketModel.getMaVe() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else{
                        ticketService.delete(ticketModel);
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
        List<TicketModel> listItem = ticketService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }

}
