package Service;

import bean.FilterBean;
import model.BookChairModel;
import model.BookTicketModel;
import model.CustomerModel;

import java.util.List;

public interface IBookingService {
    public int insertBookChair(BookChairModel b);
    public BookTicketModel insertBookTicket(BookTicketModel b);
    public List<BookChairModel> selectChair(String maSC);
    public List<FilterBean> selectByPhone(String phone);

}
