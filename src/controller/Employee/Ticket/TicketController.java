package controller.Employee.Ticket;

import Service.impl.TicketService;
import model.MovieModel;
import view.Employee.TicketPanel.TicketFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        loadTable(jpnView,jtfSearch,btnRemove);
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


    }

}
