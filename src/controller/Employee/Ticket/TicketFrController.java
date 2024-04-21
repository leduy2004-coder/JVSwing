package controller.Employee.Ticket;

import Dao.impl.MovieDAO;
import Dao.impl.TicketDAO;
import model.MovieModel;
import model.TicketModel;
import utility.SessionUtil;
import utility.SetTable;
import view.Employee.TicketPanel.TicketFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TicketFrController extends EventTicket{
    private TicketFrame ticket;
    private MovieModel movieModel;
    SetTable<MovieModel> setTable = SetTable.getInstance();

    private final String[] COLUMNS = {"Mã Phim","Tên phim"};
    String[] METHOD = {"getMaPhim", "getTenPhim"};

    private String msg;
    private JPanel jpnView;
    private JTable table = new JTable();
    private JTextField jtfSearch;
    private JButton btnRemove;
    int t=0;

    public TicketFrController(TicketFrame ticketFrame,JPanel jpnView, JTextField jtfSearch, JButton btnRemove) {
        this.ticket = ticketFrame;
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.btnRemove = btnRemove;
    }

    public void CreateOrUpdate(){
        JTable table1 = new JTable();
        List<MovieModel> listItem = MovieDAO.getInstance().selectAll();
        table1 = setTable.setDataToTable(ticket.panelTable,COLUMNS,listItem,ticket.search,METHOD);

        ticket.lbIdEmploy.setText(SessionUtil.getInstance().getValueEmpl().getHoTen());

        MovieModel movie = new MovieModel();
        movie = getData(table1,movie);
        MovieModel finalMovie = movie;
        ticket.btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if(finalMovie.getMaPhim() != null){
                        if(!check()){
                            msg = "Vui lòng nhập đầy đủ dữ liệu !!";
                            JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                        }else{
                            ticket.lbMovie.setText(finalMovie.getTenPhim());
                            TicketModel ticketModel = new TicketModel();
                            msg = "Số lượng phải là kiểu số !!";
                            ticketModel.setSoLuongToiDa(Integer.parseInt(ticket.jtfAmount.getText().trim()));
                            msg = "Giá cả phải là kiểu số!!";
                            ticketModel.setTien(Float.parseFloat(ticket.jtfMoney.getText().trim()));
                            ticketModel.setMaNV(SessionUtil.getInstance().getValueEmpl().getMaNV());
                            ticketModel.setMovieModel(finalMovie);
                            msg= "Bạn muốn thêm dữ liệu không ?";
                            if(showDialog(msg)){
                                TicketDAO.getInstance().insert(ticketModel);
                                ticket.dispose();
                                loadTable(jpnView,jtfSearch,btnRemove);
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
                    ticket.lbMovie.setText(movieModel.getTenPhim());
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

}
