package controller.Employee.Booking;

import model.BookChairModel;
import model.ScheduleModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BookFrameController {
    private ScheduleModel sche;
    private int money;
    private List<BookChairModel> listChair;
    private JButton btnSave;
    private JButton btnExit;
    private JFrame frame;

    public BookFrameController(ScheduleModel sche, int money, List<BookChairModel> listChair,JButton btnSave, JButton btnExit,JFrame frame) {
        this.sche = sche;
        this.money = money;
        this.listChair = listChair;
        this.btnSave = btnSave;
        this.btnExit= btnExit;
        this.frame = frame;
    }

    public void SaveData(){
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });
    }
}
