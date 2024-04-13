package controller.Employee.Ticket;

import Service.impl.MovieService;
import Service.impl.TicketService;
import Service.impl.TypeMovieService;
import model.MovieModel;
import model.TicketModel;
import utility.SessionUtil;
import utility.SetTable;
import view.Employee.TicketPanel.TicketFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class TicketFrController {
    private TicketFrame ticket;
    private TicketService ticketService;
    private MovieModel movieModel;
    MovieService movieService = new MovieService();
    SetTable<MovieModel> setTable = SetTable.getInstance();

    private final String[] COLUMNS = {"Mã Phim","Tên phim"};
    String[] METHOD = {"getMaPhim", "getTenPhim"};

    private String msg;
    private JPanel jpnView;
    private JTable table = new JTable();
    private String[] columns;
    private JTextField jtfSearch;
    private String[] methodNames;
    private MouseListener[] mouseListeners;
    TypeMovieService typeMovieService = new TypeMovieService();
    int t=0;

    public TicketFrController(TicketFrame ticketFrame,JPanel jpnView, String[] COLUMNS, JTextField jtfSearch, String[] methodNames, MouseListener[] mouseListeners) {
        this.ticket = ticketFrame;
        this.jpnView = jpnView;
        this.columns = COLUMNS;
        this.jtfSearch = jtfSearch;
        this.methodNames = methodNames;
        this.mouseListeners = mouseListeners;
        ticketService = new TicketService();
    }

    public void CreateOrUpdate(){
        JTable table1 = new JTable();
        List<MovieModel> listItem = movieService.selectAll();
        table1 = setTable.setDataToTable(ticket.panelTable,COLUMNS,listItem,ticket.search,METHOD);

        ticket.lbIdEmploy.setText(SessionUtil.getInstance().getValueEmpl().getHoTen());

        MovieModel movie = new MovieModel();
        movie = getData(table1,movie);
        MovieModel finalMovie = movie;
        if(finalMovie.getMaPhim() != null){
            ticket.lbMovie.setText(finalMovie.getTenPhim());
        }
        ticket.btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(finalMovie.getMaPhim() != null){
                        if(!check()){
                            msg = "Vui lòng nhập đầy đủ dữ liệu !!";
                            JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                        }else{
                            TicketModel ticketModel = new TicketModel();
                            msg = "Số lượng phải là kiểu số !!";
                            ticketModel.setSoLuongToiDa(Integer.parseInt(ticket.jtfAmount.getText().trim()));
                            msg = "Giá cả phải là kiểu số!!";
                            ticketModel.setTien(Float.parseFloat(ticket.jtfMoney.getText().trim()));
                            ticketModel.setMaNV(ticket.lbIdEmploy.getText());
                            ticketModel.setMovieModel(finalMovie);

                            if(showDialog(msg)){
                                ticketService.save(ticketModel);
                                ticket.dispose();
                                loadTable();
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, msg, "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
        ticket.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ticket.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
    }


    public MovieModel getData(JTable table, MovieModel movieModel){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    movieModel.setMaPhim(model.getValueAt(selectedRowIndex, 0).toString());
                    movieModel.setTenPhim(model.getValueAt(selectedRowIndex,1).toString());
                }
            }
        });
        return movieModel;
    }

    private boolean showDialog(String msg) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                msg, "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    private boolean check(){
        return ticket.jtfAmount.getText() != null && !ticket.jtfAmount.getText().equalsIgnoreCase("") &&
                ticket.jtfMoney.getText() != null && !ticket.jtfMoney.getText().equalsIgnoreCase("") ;
    }
    private void loadTable(){
        jpnView.removeAll();
        jpnView.validate();
        jpnView.repaint();
        SetTable<MovieModel> setTable = SetTable.getInstance();
        List<MovieModel> listItem = movieService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }

}
