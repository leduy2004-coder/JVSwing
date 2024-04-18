package controller.Employee.Booking;

import Service.impl.BookingService;
import bean.FilterBean;
import model.MovieModel;
import utility.SetTable;
import view.Employee.BookingPanel.FilterFrame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FilterController {
    private FilterFrame filter;
    public final String[] COLUMNS = {"Họ tên","Tên phim", "Ngày mua", "Phòng","Ca", "Ghế"};
    String[] methodNames = {"getHoTen", "getTenPhim", "getNgayMua","getMaPhong","getTenCa","getMaGhe"};
    private BookingService bookingService;
    private List<FilterBean> list;
    SetTable<MovieModel> setTable = SetTable.getInstance();
    String msg;
    int c;
    public FilterController(FilterFrame filter) {
        this.filter = filter;
        bookingService = new BookingService();
        list = new ArrayList<>();
        c=0;
    }

    public void getDataTable(){
        filter.btnFilter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!check())
                    msg = "Vui lòng nhập đầy đủ dữ liệu !!";
                else{
                    if(!kiemTraChuoi(filter.jtfPhone.getText()))
                        msg = "Số điện thoại phải là 10 số !!";
                    else{
                        list = bookingService.selectByPhone(filter.jtfPhone.getText());
                        if(list.size()>0){
                            setTable.setDataToTable(filter.pnTable,COLUMNS,list,filter.jtfSearch,methodNames);
                            c++;
                        }else
                            msg = "Không tìm thấy dữ liệu !!";
                    }
                }
                if(c==0)
                    JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
        });

        filter.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filter.dispose();
            }
        });
    }
    private boolean check(){
        return filter.jtfPhone.getText() != null && !filter.jtfPhone.getText().equalsIgnoreCase("") ;
    }
    private boolean kiemTraChuoi(String chuoi) {
        if (chuoi.matches("[0-9]+")) {
            if (chuoi.length() == 10) {
                return true;
            }
        }
        return false;
    }
}
