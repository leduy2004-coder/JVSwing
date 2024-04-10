package controller.Manage.Statistic;

import Service.impl.StatisticService;
import bean.ChartStatisticBean;
import bean.DataStatisticBean;
import bean.TableStatisticBean;
import com.toedter.calendar.JDateChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import utility.ClassTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;

public class StatisticController {
    private JLabel jlbTotalMovie;
    private JLabel jlbTotalCus;
    private JLabel jlbTotalEmpl;
    private JPanel jpnChart;
    private JPanel jpnTable;
    private JDateChooser jdcBegin;
    private JDateChooser jdcEnd;
    private JButton btnSeenTable;
    private JButton btnSeenChart;
    private JComboBox jcbYear;
    StatisticService statisticService = new StatisticService();
    ChartStatisticBean chart;
    DataStatisticBean data;
    private ClassTableModel classTableModel = null;
    TableStatisticBean table;
    private final String[] COLUMNS = {"STT","Tên phim","Số buổi chiếu","Số vé bán ra","Tổng doanh thu"};
    private TableRowSorter<TableModel> rowSorter = null;

    public StatisticController(JLabel jlbTotalMovie, JLabel jlbTotalCus, JLabel jlbTotalEmpl, JPanel jpnChart,
                               JPanel jpnTable,JDateChooser jdcBegin,JDateChooser jdcEnd,JButton btnSeenTable,JComboBox jcbYear,JButton btnSeenChart) {
        this.jlbTotalMovie = jlbTotalMovie;
        this.jlbTotalCus = jlbTotalCus;
        this.jlbTotalEmpl = jlbTotalEmpl;
        this.jpnChart = jpnChart;
        this.jpnTable = jpnTable;
        this.jdcBegin = jdcBegin;
        this.jdcEnd = jdcEnd;
        this.btnSeenTable = btnSeenTable;
        this.jcbYear = jcbYear;
        this.btnSeenChart = btnSeenChart;
        chart = new ChartStatisticBean();
        data = new DataStatisticBean();
        table = new TableStatisticBean();
        this.classTableModel = new ClassTableModel();
    }

    public void setData(){
        data = statisticService.getData();
        jlbTotalMovie.setText(String.valueOf(data.getTotalMovie()));
        jlbTotalCus.setText(String.valueOf(data.getTotalCustomer()));
        jlbTotalEmpl.setText(String.valueOf(data.getTotalEmployee()));
    }
    public void setChart(){
        List<ChartStatisticBean> list;
        list = statisticService.getAllYear();
        for (ChartStatisticBean chart:list) {
            jcbYear.addItem(chart.getYear());
        }
        btnSeenChart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpnChart.removeAll();
                jpnChart.validate();
                jpnChart.repaint();
                if(jcbYear != null && !(String.valueOf(jcbYear.getItemAt(jcbYear.getSelectedIndex()))).equals("")){
                    List<ChartStatisticBean> charts;
                    charts = statisticService.getChart((int)jcbYear.getItemAt(jcbYear.getSelectedIndex()));
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
                    chartPanel.setPreferredSize(new Dimension(jpnChart.getWidth(), 321));
                    jpnChart.removeAll();
                    jpnChart.setLayout(new CardLayout());
                    jpnChart.add(chartPanel);
                    jpnChart.validate();
                    jpnChart.repaint();
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
        btnSeenTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jpnTable.removeAll();
                jpnTable.validate();
                jpnTable.repaint();
                if(jdcBegin.getDate()!=null&&jdcEnd.getDate()!=null){
                    if((jdcBegin.getDate()).compareTo(jdcEnd.getDate())<=0) {
                        List<TableStatisticBean> listItem = statisticService.getTable(covertDateToDateSql(jdcBegin.getDate()),covertDateToDateSql(jdcEnd.getDate()));
                        if(listItem.size()>0){
                            DefaultTableModel model = classTableModel.setTableStatistic(listItem, COLUMNS);
                            JTable table = new JTable(model);

                            rowSorter = new TableRowSorter<>(table.getModel());
                            table.setRowSorter(rowSorter);

                            table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
                            table.getTableHeader().setPreferredSize(new Dimension(100, 29));
                            table.setRowHeight(39);
                            table.getColumnModel().getColumn(1).setPreferredWidth(200);
                            table.getColumnModel().getColumn(2).setPreferredWidth(100);
                            table.getColumnModel().getColumn(4).setPreferredWidth(100);

                            table.validate();
                            table.repaint();

                            JScrollPane scroll = new JScrollPane();
                            scroll.getViewport().add(table);
                            scroll.setPreferredSize(new Dimension(1350, 400));
                            jpnTable.removeAll();
                            jpnTable.setLayout(new CardLayout());
                            jpnTable.add(scroll);
                            jpnTable.validate();
                            jpnTable.repaint();
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
