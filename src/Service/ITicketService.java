package Service;

import model.EmployeeModel;
import model.TicketModel;

import java.util.List;

public interface ITicketService {
    public int save(TicketModel ticketModel);
    public List<TicketModel> selectAll();

    public int update(TicketModel ticketModel);
    public int delete(TicketModel ticketModel);
}
