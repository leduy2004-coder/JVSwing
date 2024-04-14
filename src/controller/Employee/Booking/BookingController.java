package controller.Employee.Booking;

import Service.impl.ScheduleService;
import model.MovieModel;
import model.ScheduleModel;
import utility.SetTable;
import view.Employee.BookingPanel.BookingPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingController {
    private BookingPanel book;
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
                if(book.jdDate.getDate() != null){
                    List<ScheduleModel> listItem = scheduleService.selectAllByDate(covertDateToDateSql(book.jdDate.getDate()));
                    if(listItem.size() > 0 )
                        setTable.setDataToTable(book.pnTable,COLUMNS,listItem,book.jtfSearch,methodNames);
                    else
                        JOptionPane.showMessageDialog(null,"Không tìm thấy suất chiếu !!","Thông báo",JOptionPane.ERROR_MESSAGE);

                }else
                    JOptionPane.showMessageDialog(null,"Chọn ngày hiển thị !!","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
        });
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
