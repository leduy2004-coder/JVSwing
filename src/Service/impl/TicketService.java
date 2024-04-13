package Service.impl;

import Dao.impl.TicketDAO;
import Service.ITicketService;
import model.TicketModel;

import java.util.List;

public class TicketService implements ITicketService {

    @Override
    public int save(TicketModel ticketModel) {
        return TicketDAO.getInstance().insert(ticketModel);
    }

    @Override
    public List<TicketModel> selectAll() {
        return TicketDAO.getInstance().selectAll();
    }

    @Override
    public int update(TicketModel ticketModel) {
        return TicketDAO.getInstance().update(ticketModel);
    }

    @Override
    public int delete(TicketModel ticketModel) {
       return TicketDAO.getInstance().delete(ticketModel);
    }
}
