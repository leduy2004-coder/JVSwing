package controller.Employee.Schedule.Frame;

import com.toedter.calendar.JDateChooser;
import model.ShiftModel;
import utility.ShiftRadioModel;
import view.Employee.SchedulePanel.Frame.MovieFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class ShiftFrController {
    private Frame frame;
    private JButton btnNext;
    private JButton btnRemove;
    private JDateChooser jdcDate;
    private ShiftRadioModel radioSelected = null;
    private List<ShiftModel> list;

    public ShiftFrController(Frame frame, JButton btnNext, JButton btnRemove, JDateChooser jdcDate,List<ShiftModel> list) {
        this.frame = frame;
        this.btnNext = btnNext;
        this.btnRemove = btnRemove;
        this.jdcDate = jdcDate;
        this.list = list;
    }
    public void setData(ShiftRadioModel jrb1, ShiftRadioModel jrb2, ShiftRadioModel jrb3, ShiftRadioModel jrb4, ShiftRadioModel jrb5, ShiftRadioModel jrb6){
        for (int i = 0; i < list.size(); i++) {
            switch (i) {
                case 0:
                    jrb1.setShiftModel(list.get(i));
                    break;
                case 1:
                    jrb2.setShiftModel(list.get(i));
                    break;
                case 2:
                    jrb3.setShiftModel(list.get(i));
                    break;
                case 3:
                    jrb4.setShiftModel(list.get(i));
                    break;
                case 4:
                    jrb5.setShiftModel(list.get(i));
                    break;
                case 5:
                    jrb6.setShiftModel(list.get(i));
                    break;
                default:
                    break;
            }
        }
        ShiftRadioModel[] jRadioButtons = {jrb1, jrb2, jrb3, jrb4, jrb5, jrb6};

        for (ShiftRadioModel jrb : jRadioButtons) {
            if ((jrb.getText()).equals("")) {
                jrb.setVisible(false);
            }
        }
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (ShiftRadioModel radioButton : jRadioButtons) {
                    if (radioButton.isSelected()) {
                        radioSelected = radioButton;
                    }
                }
                if(radioSelected != null){
                    frame.dispose();
                    MovieFrame movieFrame = new MovieFrame(jdcDate,((ShiftRadioModel) radioSelected).getShiftModel());
                    movieFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    movieFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ca chiếu !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
