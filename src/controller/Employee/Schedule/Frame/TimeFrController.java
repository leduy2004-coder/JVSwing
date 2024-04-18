package controller.Employee.Schedule.Frame;

import Service.impl.ScheduleService;
import com.toedter.calendar.JDateChooser;
import model.ShiftModel;
import view.Employee.SchedulePanel.Frame.ShiftsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class TimeFrController {
    private Frame frame;
    private JButton btnNext;
    private JButton btnRemove;
    private JDateChooser jdcDate;
    ScheduleService scheduleService = new ScheduleService();

    public TimeFrController(Frame frame,JButton btnNext, JButton btnRemove,JDateChooser jdcDate) {
        this.frame = frame;
        this.btnNext = btnNext;
        this.btnRemove = btnRemove;
        this.jdcDate = jdcDate;
    }
    public void setEvent(){
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                List<ShiftModel> list = null;
                if(jdcDate.getDate()!=null){
                    list = scheduleService.selectAllShift(covertDateToDateSql(jdcDate.getDate()));
                    if(list.size()>0){
                        frame.dispose();
                        ShiftsFrame shiftsFrame = new ShiftsFrame(list,jdcDate);
                        shiftsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        shiftsFrame.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Ngày chiếu đã hết ca !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày chiếu !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
}
