package controller.Employee.Booking;

import Dao.impl.BookingDAO;
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
    private BookTicketModel bookTicketModel;
    private JFrame book;


    public BookFrameController(ScheduleModel sche, int money, List<BookChairModel> listChair,JButton btnSave, JButton btnExit,JFrame frame,JFrame book) {
        this.sche = sche;
        this.money = money;
        this.listChair = listChair;
        this.btnSave = btnSave;
        this.btnExit= btnExit;
        this.frame = frame;
        this.book =book;
        bookTicketModel = new BookTicketModel();
    }

    public void SaveData(){
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String maBook;
                bookTicketModel.setMaNV(SessionUtil.getInstance().getValueEmpl().getMaNV());
                bookTicketModel.setMaVe(null);
                bookTicketModel.setMaKH(null);
                bookTicketModel.setMaSuat(sche.getMaSC());
                bookTicketModel.setTongTien(money);
                bookTicketModel.setNgayMua(sche.getNgayChieu());
                if(showDialog()){
                    maBook = BookingDAO.getInstance().insertBookTicket(bookTicketModel).getMaBook();
                    for (BookChairModel bookChairModel:listChair) {
                        bookChairModel.setMaBook(maBook);
                        BookingDAO.getInstance().insertBookChair(bookChairModel);
                    }
                    JOptionPane.showMessageDialog(null,"Đặt thành công !!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                    book.dispose();
                    frame.dispose();
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
    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn thêm không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}
