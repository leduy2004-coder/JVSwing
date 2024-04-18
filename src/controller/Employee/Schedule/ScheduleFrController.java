package controller.Employee.Schedule;

import Service.impl.ScheduleService;
import com.toedter.calendar.JDateChooser;
import model.MovieModel;
import model.ScheduleModel;
import model.ShiftModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class ScheduleFrController {
    private Frame frame;
    private JButton btnSave;
    private JButton btnRemove;
    private JDateChooser jdcDate;
    private ShiftModel shift;
    private MovieModel movie;
    private String maPhong;
    private JRadioButton radioSelected = null;
    ScheduleService scheduleService = new ScheduleService();
    public ScheduleFrController(Frame frame, JButton btnSave, JButton btnRemove, JDateChooser jdcDate,ShiftModel shift,MovieModel movie,String maPhong) {
        this.frame = frame;
        this.btnSave = btnSave;
        this.btnRemove = btnRemove;
        this.jdcDate = jdcDate;
        this.shift =shift;
        this.movie = movie;
        this.maPhong= maPhong;
    }
    public void setDataAndEvent(){
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ScheduleModel scheduleModel = new ScheduleModel();
                scheduleModel.setMaCa(shift.getMaCa());
                scheduleModel.setMaPhim(movie.getMaPhim());
                scheduleModel.setMaPhong(maPhong);
                scheduleModel.setNgayChieu(covertDateToDateSql(jdcDate.getDate()));
                scheduleModel.setTinhTrang(true);
                if(showDialog()){
                    scheduleService.save(scheduleModel);
                    JOptionPane.showMessageDialog(null, "Đã thêm thành công !!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
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
        btnRemove.addMouseListener(new MouseAdapter() {
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
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn thêm không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}
