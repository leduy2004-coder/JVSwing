package controller.Employee.Schedule.Frame;

import Service.impl.MovieService;
import com.toedter.calendar.JDateChooser;
import model.MovieModel;
import model.ShiftModel;
import utility.SetTable;
import view.Employee.SchedulePanel.Frame.RoomFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MovieFrController {
    private Frame frame;
    private JButton btnNext;
    private JButton btnRemove;
    private JDateChooser jdcDate;
    private ShiftModel shift;
    private JPanel pnTable;
    private JTextField jtfSearch;
    MovieService movieService = new MovieService();
    SetTable<MovieModel> setTable = SetTable.getInstance();

    private final String[] COLUMNS = {"Mã Phim","Tên phim"};
    String[] methodNames = {"getMaPhim", "getTenPhim"};


    public MovieFrController(Frame frame, JButton btnNext, JButton btnRemove, JDateChooser jdcDate,ShiftModel shift,JPanel pnTable,JTextField jtfSearch) {
        this.frame = frame;
        this.btnNext = btnNext;
        this.btnRemove = btnRemove;
        this.jdcDate = jdcDate;
        this.shift = shift;
        this.pnTable = pnTable;
        this.jtfSearch = jtfSearch;

    }
    public void setDataAndEvent(){
        JTable table = new JTable();
        List<MovieModel> list = movieService.selectStatus();
        table = setTable.setDataToTable(pnTable,COLUMNS,list,jtfSearch,methodNames);
        MovieModel movie = new MovieModel();
        movie = getData(table,movie);
        MovieModel finalMovie = movie;
        btnNext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(finalMovie.getMaPhim() != null){
                    frame.dispose();
                    RoomFrame roomFrame = new RoomFrame(jdcDate,shift, finalMovie);
                    roomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    roomFrame.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
        btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
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


    public MovieModel getData(JTable table, MovieModel movieModel){
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    movieModel.setMaPhim(model.getValueAt(selectedRowIndex, 0).toString());
                    movieModel.setTenPhim(model.getValueAt(selectedRowIndex,1).toString());
                }
            }
        });
        return movieModel;
    }
}
