package Service;

import model.CustomerModel;
import model.MovieModel;
import model.ScheduleModel;
import model.ShiftModel;

import java.sql.Date;
import java.util.List;

public interface IScheduleService {
    public int save(ScheduleModel scheduleModel);
    public List<ScheduleModel> selectAllByDate(Date date);
    public List<ScheduleModel> selectAllDate(Date from, Date to);
    public List<ScheduleModel> selectAllToTal(Date from, Date to,ScheduleModel s);

    public int update(ScheduleModel scheduleModel);
    public int delete(ScheduleModel scheduleModel);

    public MovieModel selectByMPhim(ScheduleModel scheduleModel);
    public ShiftModel selectByMCa(ScheduleModel scheduleModel);
}
