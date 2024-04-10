package controller.Employee.Schedule.Frame;

import Service.impl.ScheduleService;
import com.toedter.calendar.JDateChooser;
import controller.Employee.Schedule.ScheduleController;
import model.MovieModel;
import model.RoomModel;
import model.ShiftModel;
import utility.ShiftRadioModel;
import view.Employee.SchedulePanel.Frame.MovieFrame;
import view.Employee.SchedulePanel.ScheduleFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class RoomFrController {
    private Frame frame;
    private JButton btnNext;
    private JButton btnRemove;
    private JDateChooser jdcDate;
    private ShiftModel shift;
    private MovieModel movie;
    private JRadioButton radioSelected = null;
    ScheduleService scheduleService = new ScheduleService();
    public RoomFrController(Frame frame, JButton btnNext, JButton btnRemove, JDateChooser jdcDate,ShiftModel shift,MovieModel movie) {
        this.frame = frame;
        this.btnNext = btnNext;
        this.btnRemove = btnRemove;
        this.jdcDate = jdcDate;
        this.shift =shift;
        this.movie = movie;
    }
    public void setDataAndEvent(JRadioButton jrb1, JRadioButton jrb2, JRadioButton jrb3, JRadioButton jrb4){
        List<RoomModel> list = scheduleService.selectAllRoom(covertDateToDateSql(jdcDate.getDate()),shift);
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    jrb1.setText(list.get(i).getMaPhong());
                    break;
                case 1:
                    jrb2.setText(list.get(i).getMaPhong());
                    break;
                case 2:
                    jrb3.setText(list.get(i).getMaPhong());
                    break;
                case 3:
                    jrb4.setText(list.get(i).getMaPhong());
                    break;
                default:
                    break;
            }
        }
        JRadioButton[] jRadioButtons = {jrb1, jrb2, jrb3, jrb4};

        for (JRadioButton jrb : jRadioButtons) {
            if ((jrb.getText()).equals("")) {
                jrb.setVisible(false);
            }
        }
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (JRadioButton radioButton : jRadioButtons) {
                    if (radioButton.isSelected()) {
                        radioSelected = radioButton;
                    }
                }
                if(radioSelected != null){
                    frame.dispose();
                    ScheduleFrame scheduleFrame = new ScheduleFrame(jdcDate,shift,movie,radioSelected.getText());
                    scheduleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    scheduleFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn phòng !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
