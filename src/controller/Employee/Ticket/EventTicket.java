package controller.Employee.Ticket;

import Service.impl.TicketService;
import model.MovieModel;
import model.TicketModel;
import utility.SetTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public abstract class EventTicket {
    public final String[] COLUMNS = {"Mã vé", "Tên phim", "Số lượng đặt", "Số lượng bán", "Giá 1 vé", "maNV", "Trạng thái"};
    String[] methodNames = {"getMaVe", "getTenPhim", "getSoLuongToiDa","getSoLuongDaDat","getTien","getMaNV","isTinhTrang"};
    SetTable<MovieModel> setTable = SetTable.getInstance();
    private JTable table = new JTable();
     TicketService ticketService = new TicketService();

    public void remove(JTable table,JButton btnRemove,JPanel jpnView, JTextField jtfSearch) {
        TicketModel ticketModel = new TicketModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    ticketModel.setMaVe(model.getValueAt(selectedRowIndex, 0).toString());

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
        List<TicketModel> listItem = ticketService.selectAll();
        MouseListener[] listeners = btnRemove.getMouseListeners();
        for (MouseListener listener : listeners) {
            btnRemove.removeMouseListener(listener);
        }
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        remove(table,btnRemove,jpnView,jtfSearch);
        return table;
    }
}
