package controller.Employee.Schedule.Frame;

import Service.impl.MovieService;
import com.toedter.calendar.JDateChooser;
import model.MovieModel;
import model.ShiftModel;
import utility.ClassTableModel;
import view.Employee.SchedulePanel.Frame.RoomFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    private ClassTableModel classTableModel = null;

    private final String[] COLUMNS = {"Mã Phim","Tên phim"};
    private TableRowSorter<TableModel> rowSorter = null;

    public MovieFrController(Frame frame, JButton btnNext, JButton btnRemove, JDateChooser jdcDate,ShiftModel shift,JPanel pnTable,JTextField jtfSearch) {
        this.frame = frame;
        this.btnNext = btnNext;
        this.btnRemove = btnRemove;
        this.jdcDate = jdcDate;
        this.shift = shift;
        this.pnTable = pnTable;
        this.jtfSearch = jtfSearch;
        this.classTableModel = new ClassTableModel();
    }
    public void setDataAndEvent(){
        JTable table = new JTable();
        List<MovieModel> list = null;
        list = movieService.selectStatus();
        table = setDataToTable(list,table);
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

    public JTable setDataToTable(List<MovieModel> listItem, JTable table) {
        DefaultTableModel model = classTableModel.setTableMovieStatus(listItem, COLUMNS);
        table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter); //sort

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 29));
        table.setRowHeight(39);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        pnTable.removeAll();
        pnTable.setLayout(new CardLayout());
        pnTable.add(scroll);
        pnTable.validate();
        pnTable.repaint();

        return table;
    }
    private void applyFilter() {
        String text = jtfSearch.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            RowFilter<Object, Object> rowFilter = RowFilter.regexFilter("(?i)" + text, 0);
            rowSorter.setRowFilter(rowFilter);
        }
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
