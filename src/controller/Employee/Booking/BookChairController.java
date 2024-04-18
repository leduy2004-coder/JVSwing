package controller.Employee.Booking;

import Service.impl.BookingService;
import Service.impl.MovieService;
import bean.BookBean;
import model.BookChairModel;
import model.ScheduleModel;
import view.Employee.BookingPanel.BookingChair;
import view.Employee.BookingPanel.BookingFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class BookChairController {
    private BookingChair book;
    private ScheduleModel sche;
    private MovieService movieService;
    private List<BookBean> listItem;
    private JLabel lbName;
    String chairBook;
    int t, money,c;
    private List<BookChairModel> listChair;
    private List<BookChairModel> listChairBook;
    private BookingService bookingService;
    public BookChairController(BookingChair book, List<BookBean> list, ScheduleModel sche, JLabel lbName) {
        this.book = book;
        this.listItem = list;
        this.sche = sche;
        this.lbName = lbName;
        movieService = new MovieService();
        listChair = new ArrayList<BookChairModel>();
        listChairBook = new ArrayList<BookChairModel>();
        bookingService = new BookingService();
    }

    public void setChair(){
        listChairBook = bookingService.selectChair(sche.getMaSC());
        System.out.println(sche.getMaSC());
        t = 0;
        money = 0;
        chairBook = "";
        book.lbChair.setText("");
        book.lbMoney.setText("");
        listChair.clear();
        for (BookBean bookBean:listItem) {
            c=0;
            for (BookChairModel bookChair: listChairBook) {
                if(bookBean.getLbNameChair().getText().equals(bookChair.getMaGhe())){
                    book.setPicture(bookBean.getLbChair(),"/img/chairOld.png",90,75);
                    c++;
                    break;
                }
            }
            if(c==0){
                RemoveEvent(bookBean);
                book.setPicture(bookBean.getLbChair(),"/img/chair.png",90,75);
                bookBean.getLbChair().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                bookBean.getLbChair().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(t!=0)
                            chairBook += ", ";
                        bookBean.getLbChair().setIcon(null);
                        book.setPicture(bookBean.getLbChair(),"/img/chairBook.png",90,75);
                        chairBook += bookBean.getLbNameChair().getText();
                        book.lbChair.setText(chairBook);
                        t++;
                        BookChairModel model = new BookChairModel();
                        model.setMaGhe(bookBean.getLbNameChair().getText());
                        listChair.add(model);

                        money += 60000;
                        book.lbMoney.setText(String.valueOf(money));
                        RemoveEvent(bookBean);
                        bookBean.getLbChair().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                });
            }

        }
    }

    public void setEvent(){
        book.btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setChair();
            }
        });
        book.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                book.dispose();
            }
        });
        book.btnBookFinal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(money==0)
                    JOptionPane.showMessageDialog(null,"Vui lòng chọn ghế !!","Thông báo",JOptionPane.ERROR_MESSAGE);
                else{
                    BookingFrame bookingFrame = new BookingFrame(sche,money,chairBook,listChair,lbName);
                    bookingFrame.setLocationRelativeTo(null);
                    bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    bookingFrame.setResizable(false);
                    bookingFrame.setVisible(true);
                }
            }
        });
    }
    private void RemoveEvent(BookBean bookBean){
        MouseListener[] mouseListeners = bookBean.getLbChair().getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            bookBean.getLbChair().removeMouseListener(listener);
        }
    }
}
