package Service.impl;

import Dao.impl.StatisticDAO;
import bean.ChartStatisticBean;
import bean.DataStatisticBean;
import bean.TableStatisticBean;

import java.sql.Date;
import java.util.List;

public class StatisticService {
    public DataStatisticBean getData() {
        return StatisticDAO.getInstance().getData();
    }

    public List<TableStatisticBean> getTable(Date from, Date to) {
        return StatisticDAO.getInstance().getTable(from, to);
    }

    public List<ChartStatisticBean> getAllYear() {
        return StatisticDAO.getInstance().getAllYear();
    }

    public List<ChartStatisticBean> getChart(int year) {
        return StatisticDAO.getInstance().getChart(year);
    }
}
