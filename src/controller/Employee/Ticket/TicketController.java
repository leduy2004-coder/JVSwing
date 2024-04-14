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

public class TicketController extends EventTicket{
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;
    private JTable table = new JTable();

    private MovieModel movieModel;

    private TicketService ticketService = null;


    public TicketController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.ticketService = new TicketService();
    }

    public void displayView() {
        List<TicketModel> listItem = ticketService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        btnAdd.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                TicketFrame ticketFrame = new TicketFrame(jpnView,jtfSearch,btnRemove);
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

        loadTable(jpnView,jtfSearch,btnRemove);
    }

}
