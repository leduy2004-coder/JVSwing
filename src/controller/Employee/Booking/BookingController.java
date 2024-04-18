package controller.Employee.Booking;

import Service.impl.ScheduleService;
import bean.BookBean;
import model.MovieModel;
import model.ScheduleModel;
import utility.SetTable;
import view.Employee.BookingPanel.BookingChair;
import view.Employee.BookingPanel.BookingPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingController {
    private BookingPanel book;
    private ScheduleModel sche;
    private JTable table = new JTable();

    public final String[] COLUMNS = {"Mã suất chiếu", "Mã phim", "Mã phòng", "Mã ca"};
    String[] methodNames = {"getMaSC", "getMaPhim", "getMaPhong","getMaCa"};

    SetTable<MovieModel> setTable = SetTable.getInstance();
    private ScheduleService scheduleService;
    public BookingController(BookingPanel book) {
        this.book = book;
        scheduleService = new ScheduleService();
    }

    public void setDataTable(){
        book.btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                book.lbName.setText("");
                book.lbShift.setText("");
                book.lbDate.setText("");
                book.lbRoom.setText("");
                MouseListener[] mouseListeners = book.btnBook.getMouseListeners();
                for (MouseListener listener : mouseListeners) {
                    book.btnBook.removeMouseListener(listener);
                }
                if(book.jdDate.getDate() != null){
                    List<ScheduleModel> listItem = scheduleService.selectAllByDate(covertDateToDateSql(book.jdDate.getDate()));
                    if(listItem.size() > 0 ){
                        table = setTable.setDataToTable(book.pnTable,COLUMNS,listItem,book.jtfSearch,methodNames);
                        sche = new ScheduleModel();
                        sche = getData(table,sche);
                        setEvent(sche);
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Không tìm thấy suất chiếu !!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }else
                    JOptionPane.showMessageDialog(null,"Chọn ngày hiển thị !!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setEvent(ScheduleModel sche){
        book.btnBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(sche != null){
                    BookingChair bookingFrame = new BookingChair(sche,book.lbName);
                    bookingFrame.setLocationRelativeTo(null);
                    bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    bookingFrame.setResizable(false);
                    bookingFrame.setVisible(true);
                }
            }
        });
    }
    public ScheduleModel getData(JTable table, ScheduleModel sche){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    sche.setMaPhim(model.getValueAt(selectedRowIndex, 1).toString());
                    sche.setMaSC(model.getValueAt(selectedRowIndex,0).toString());
                    sche.setMaCa(model.getValueAt(selectedRowIndex,3).toString());
                    sche.setMaPhong(model.getValueAt(selectedRowIndex,2).toString());
                    sche.setNgayChieu(covertDateToDateSql(book.jdDate.getDate()));
                    book.lbName.setText(scheduleService.selectByMPhim(sche).getTenPhim());
                    book.lbShift.setText(scheduleService.selectByMCa(sche).getTenCa());
                    book.lbDate.setText(covertDateToDateSql(book.jdDate.getDate()).toString());
                    book.lbRoom.setText(sche.getMaPhong());
                }
            }
        });
        return sche;
    }
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

    private String convertDatetoString(Date d) {
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        return date = sp.format(d);
    }

}
