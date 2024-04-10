package Service.impl;

import Dao.impl.ScheduleDAO;
import Service.IScheduleService;
import model.MovieModel;
import model.RoomModel;
import model.ScheduleModel;
import model.ShiftModel;

import java.sql.Date;
import java.util.List;

public class ScheduleService implements IScheduleService {


    @Override
    public int save(ScheduleModel scheduleModel) {
        return ScheduleDAO.getInstance().insert(scheduleModel);
    }

    @Override
    public List<ScheduleModel> selectAllDate(Date from, Date to) {
        return ScheduleDAO.getInstance().selectAllDate(from,to);
    }

    @Override
    public List<ScheduleModel> selectAllToTal(Date from, Date to, ScheduleModel s) {
        return ScheduleDAO.getInstance().selectAllToTal(from,to,s);
    }

    @Override
    public int update(ScheduleModel scheduleModel) {
        return ScheduleDAO.getInstance().update(scheduleModel);
    }

    @Override
    public int delete(ScheduleModel scheduleModel) {
        return ScheduleDAO.getInstance().delete(scheduleModel);
    }

    @Override
    public MovieModel selectByMPhim(ScheduleModel scheduleModel) {
        return ScheduleDAO.getInstance().selectByMPhim(scheduleModel);
    }

    @Override
    public ShiftModel selectByMCa(ScheduleModel scheduleModel) {
        return ScheduleDAO.getInstance().selectByMCa(scheduleModel);
    }
    public List<ShiftModel> selectAllShift(Date date) {
        return ScheduleDAO.getInstance().selectAllShift(date);
    }
    public List<RoomModel> selectAllRoom(Date date, ShiftModel maCa) {
        return ScheduleDAO.getInstance().selectAllRoom(date,maCa);
    }
}
