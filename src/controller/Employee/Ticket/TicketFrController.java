package controller.Employee.Ticket;

import Service.impl.MovieService;
import Service.impl.TicketService;
import Service.impl.TypeMovieService;
import model.MovieModel;
import model.TicketModel;
import utility.ClassTableModel;
import utility.SessionUtil;
import view.Employee.SchedulePanel.Frame.RoomFrame;
import view.Employee.TicketPanel.TicketFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TicketFrController {
    private TicketFrame ticket;
    private JFrame frame;
    private TicketService ticketService;
    private MovieModel movieModel;
    MovieService movieService = new MovieService();
    private ClassTableModel classTableModel = null;

    private final String[] COLUMNS = {"Mã Phim","Tên phim"};
    private TableRowSorter<TableModel> rowSorter = null;
    private String msg;
    TypeMovieService typeMovieService = new TypeMovieService();
    int t=0;

    public TicketFrController(JFrame frame,TicketFrame ticketFrame) {
        this.frame = frame;
        this.ticket = ticketFrame;
        ticketService = new TicketService();
        this.classTableModel = new ClassTableModel();
    }

    public void CreateOrUpdate(){

        ticket.lbIdEmploy.setText(SessionUtil.getInstance().getValueEmpl().getHoTen());
        JTable table = new JTable();
        java.util.List<MovieModel> list = null;
        list = movieService.selectStatus();
        table = setDataToTable(list,table);
        MovieModel movie = new MovieModel();
        movie = getData(table,movie);
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
                                frame.dispose();
                                RoomFrame roomFrame = new RoomFrame( finalMovie);
                                roomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                roomFrame.setVisible(true);
                            }
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    if(msg.equalsIgnoreCase("Số lượng phải là kiểu số !!"))
                        JOptionPane.showMessageDialog(null, msg, "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else if (msg.equalsIgnoreCase("Giá cả phải là kiểu số !!"))
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
                frame.dispose();
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

    public JTable setDataToTable(List<MovieModel> listItem, JTable table) {
        DefaultTableModel model = classTableModel.setTableMovieStatus(listItem, COLUMNS);
        table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter); //sort

        // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 29));
        table.setRowHeight(39);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        ticket.panelTable.removeAll();
        ticket.panelTable.setLayout(new CardLayout());
        ticket.panelTable.add(scroll);
        ticket.panelTable.validate();
        ticket.panelTable.repaint();

        return table;
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


}
