package controller.Manage.Statistic;

import Service.impl.StatisticService;
import bean.ChartStatisticBean;
import bean.DataStatisticBean;
import bean.TableStatisticBean;
import model.MovieModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utility.SetTable;
import view.Manage.StatisticPanel.StatisticPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class StatisticController {
    private StatisticPanel statistic;
    StatisticService statisticService = new StatisticService();
    ChartStatisticBean chart;
    DataStatisticBean data;

    TableStatisticBean table;
    private final String[] COLUMNS = {"Tên phim","Số buổi chiếu","Số vé bán ra","Tổng doanh thu"};
    String[] methodNames = {"getMovieName", "getTotalDate", "getTotalTicket","getTotalTurnover"};
    private String movieName;
    private int totalDate;
    private int totalTicket;
    private double totalTurnover;
    SetTable<MovieModel> setTable = SetTable.getInstance();


    public StatisticController(StatisticPanel statisticPanel) {
        this.statistic =statisticPanel;
        chart = new ChartStatisticBean();
        data = new DataStatisticBean();
        table = new TableStatisticBean();
    }

    public void setData(){
        data = statisticService.getData();
        statistic.jlbTotalMovie.setText(String.valueOf(data.getTotalMovie()));
        statistic.jlbTotalCus.setText(String.valueOf(data.getTotalCustomer()));
        statistic.jlbTotalEmpl.setText(String.valueOf(data.getTotalEmployee()));
    }
    public void setChart(){
        List<ChartStatisticBean> list;
        list = statisticService.getAllYear();
        for (ChartStatisticBean chart:list) {
            statistic.jcbYear.addItem(chart.getYear());
        }
        statistic.btnSeenChart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                statistic.jpnChart.removeAll();
                statistic.jpnChart.validate();
                statistic.jpnChart.repaint();
                if(statistic.jcbYear != null && !(String.valueOf(statistic.jcbYear.getItemAt(statistic.jcbYear.getSelectedIndex()))).equals("")){
                    List<ChartStatisticBean> charts;
                    charts = statisticService.getChart((int)statistic.jcbYear.getItemAt(statistic.jcbYear.getSelectedIndex()));
                    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                    if (charts.size()>0) {
                        for (ChartStatisticBean item : charts) {
                            dataset.addValue(item.getTotalTicket(), "Học viên", String.valueOf(item.getMonthOfYear()));
                        }
                    }
                    JFreeChart barChart = ChartFactory.createBarChart(
                            "Biểu đồ thống kê số lượng vé từng tháng trong năm".toUpperCase(),
                            "Tháng", "Số lượng",
                            dataset, PlotOrientation.VERTICAL, false, true, false);

                    ChartPanel chartPanel = new ChartPanel(barChart);
                    chartPanel.setPreferredSize(new Dimension(statistic.jpnChart.getWidth(), 321));
                    statistic.jpnChart.removeAll();
                    statistic.jpnChart.setLayout(new CardLayout());
                    statistic.jpnChart.add(chartPanel);
                    statistic.jpnChart.validate();
                    statistic.jpnChart.repaint();
                }else {
                    JOptionPane.showMessageDialog(null,"Phải chọn năm !!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
    }
    public void setTable() {
        statistic.btnSeenTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                statistic.jpnTable.removeAll();
                statistic.jpnTable.validate();
                statistic.jpnTable.repaint();
                if(statistic.jdcBegin.getDate()!=null&&statistic.jdcEnd.getDate()!=null){
                    if((statistic.jdcBegin.getDate()).compareTo(statistic.jdcEnd.getDate())<=0) {
                        List<TableStatisticBean> listItem = statisticService.getTable(covertDateToDateSql(statistic.jdcBegin.getDate()),covertDateToDateSql(statistic.jdcEnd.getDate()));
                        if(listItem.size()>0){
                            setTable.setDataToTable(statistic.jpnTable,COLUMNS,listItem,statistic.jtfSearch,methodNames);
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Không tìm thấy dữ liệu !!","Thông báo",JOptionPane.ERROR_MESSAGE);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"Ngày bắt đầu phải nhỏ hơn ngày kết thúc !!","Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Phải chọn đầy đủ ngày bắt đầu và kết thúc !!","Thông báo",JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });

    }
    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
}
