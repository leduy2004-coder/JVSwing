package Service;

import model.BookChairModel;
import model.BookTicketModel;
import model.CustomerModel;

import java.util.List;

public interface IBookingService {
    public int insertBookChair(BookChairModel b);
    public String insertBookTicket(BookTicketModel b);
    public List<BookChairModel> selectChair(String maSC);

}
