package controller.Employee.Booking;

import Service.impl.BookingService;
import model.BookChairModel;
import model.BookTicketModel;
import model.ScheduleModel;
import utility.SessionUtil;

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
    private BookingService bookingService;
    private BookTicketModel bookTicketModel;
    private JLabel labelDate;

    public BookFrameController(ScheduleModel sche, int money, List<BookChairModel> listChair,JButton btnSave, JButton btnExit,JFrame frame,JLabel labelDate) {
        this.sche = sche;
        this.money = money;
        this.listChair = listChair;
        this.btnSave = btnSave;
        this.btnExit= btnExit;
        this.frame = frame;
        this.labelDate = labelDate;
        bookingService = new BookingService();
        bookTicketModel = new BookTicketModel();
    }

    public void SaveData(){
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String maBook;
                bookTicketModel.setMaNV(SessionUtil.getInstance().getValueEmpl().getMaNV());
                bookTicketModel.setMaKH(null);
                bookTicketModel.setMaVe(null);
                bookTicketModel.setMaSuat(sche.getMaSC());
                bookTicketModel.setTongTien(money);
                bookTicketModel.setNgayMua(sche.getNgayChieu());
                System.out.println(bookTicketModel.getNgayMua());
                maBook = bookingService.insertBookTicket(bookTicketModel);
                System.out.println(maBook);
                for (BookChairModel bookChairModel:listChair) {
                    bookChairModel.setMaBook(maBook);
                    bookingService.insertBookChair(bookChairModel);
                }
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
