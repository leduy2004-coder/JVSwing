package Service.impl;

import Dao.impl.BookingDAO;
import Service.IBookingService;
import bean.FilterBean;
import model.BookChairModel;
import model.BookTicketModel;

import java.util.List;

public class BookingService implements IBookingService {

    @Override
    public int insertBookChair(BookChairModel b) {
        return BookingDAO.getInstance().insertBookChair(b);
    }

    @Override
    public BookTicketModel insertBookTicket(BookTicketModel b) {
        return BookingDAO.getInstance().insertBookTicket(b);
    }

    @Override
    public List<BookChairModel> selectChair(String maSC) {
        return BookingDAO.getInstance().selectChair(maSC);
    }

    @Override
    public List<FilterBean> selectByPhone(String phone) {
        return BookingDAO.getInstance().selectByPhone(phone);
    }
}
