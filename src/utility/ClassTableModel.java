package utility;

import bean.TableStatisticBean;
import model.CustomerModel;
import model.EmployeeModel;
import model.MovieModel;
import model.ScheduleModel;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClassTableModel {

    public DefaultTableModel setTableEmpl(List<EmployeeModel> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            } //cells are not edited

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        EmployeeModel employeeModel = null;
        for (int i = 0; i < listItem.size(); i++) {
            employeeModel = listItem.get(i);
            obj = new Object[columns];
            obj[0] = employeeModel.getMaNV();
            obj[1] = employeeModel.getHoTen();
            obj[2] = employeeModel.getSdt();
            obj[3] = employeeModel.isGioiTinh() ? "Nam" : "Nữ";
            obj[4] = employeeModel.getNgaySinh();
            obj[5] = employeeModel.getDiaChi();
            obj[6] = employeeModel.getCCCD();
            obj[7] = employeeModel.isTinhTrang();
            obj[8] = employeeModel.getTentk();
            obj[9] = employeeModel.getMatKhau();
            dtm.addRow(obj);
        }
        return dtm;

    }
    public DefaultTableModel setTableCustomer(List<CustomerModel> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            } //cells are not edited

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        CustomerModel customerModel = null;
        for (int i = 0; i < listItem.size(); i++) {
            customerModel = listItem.get(i);
            obj = new Object[columns];
            obj[0] = customerModel.getMaKH();
            obj[1] = customerModel.getHoTen();
            obj[2] = customerModel.getSdt();
            obj[3] = customerModel.getNgaySinh();
            obj[4] = customerModel.getEmail();
            obj[5] = customerModel.isTinhTrang();
            obj[6] = customerModel.getTentk();
            obj[7] = customerModel.getMatKhau();
            dtm.addRow(obj);
        }
        return dtm;
    }
    public DefaultTableModel setTableMovie(List<MovieModel> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            } //cells are not edited

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        MovieModel movieModel = null;
        for (int i = 0; i < listItem.size(); i++) {
            movieModel = listItem.get(i);
            obj = new Object[columns];
            obj[0] = movieModel.getMaPhim();
            obj[1] = movieModel.getTenPhim();
            obj[2] = movieModel.getTypeMovieModel().getTenLPhim();
            obj[3] = movieModel.getDaoDien();
            obj[4] = movieModel.getDoTuoi();
            obj[5] = movieModel.getNgayKhoiChieu();
            obj[6] = movieModel.getThoiLuong();
            obj[7] = movieModel.isTinhTrang();
            dtm.addRow(obj);
        }
        return dtm;
    }

    public DefaultTableModel setTableMovieStatus(List<MovieModel> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            } //cells are not edited

        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        MovieModel movieModel = null;
        for (int i = 0; i < listItem.size(); i++) {
            movieModel = listItem.get(i);
            obj = new Object[columns];
            obj[0] = movieModel.getMaPhim();
            obj[1] = movieModel.getTenPhim();
            dtm.addRow(obj);
        }
        return dtm;
    }
    public DefaultTableModel setTableSchedule(List<ScheduleModel> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        ScheduleModel scheduleModel = null;
        for (int i = 0; i < listItem.size(); i++) {
            scheduleModel = listItem.get(i);
            obj = new Object[columns];
            obj[0] = i+1;
            obj[1] = scheduleModel.getMaSC();
            obj[2] = scheduleModel.getNgayChieu();
            dtm.addRow(obj);
        }
        return dtm;
    }
    public DefaultTableModel setTableStatistic(List<TableStatisticBean> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        TableStatisticBean table = null;
        for (int i = 0; i < listItem.size(); i++) {
            table = listItem.get(i);
            obj = new Object[columns];
            obj[0] = i+1;
            obj[1] = table.getMovieName();
            obj[2] = table.getTotalDate();
            obj[3] = table.getTotalTicket();
            obj[4] = table.getTotalTurnover();
            dtm.addRow(obj);
        }
        return dtm;
    }
}
