package controller.Employee.Movie;

import Dao.impl.MovieDAO;
import model.MovieModel;
import utility.SetTable;
import view.Employee.MoviePanel.MovieJFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public abstract class EventMovie {
    private JTable table = new JTable();
    public final String[] COLUMNS = {"Mã phim", "Tên phim", "Loại phim", "Đạo diễn","Độ tuổi", "Ngày chiếu","Thời lượng", "Trạng thái"};
    String[] methodNames = {"getMaPhim", "getTenPhim", "getTenLoaiPhim","getDaoDien","getDoTuoi","getNgayKhoiChieu","getThoiLuong","isTinhTrang"};

    SetTable<MovieModel> setTable = SetTable.getInstance();

    public void eventTable(JTable table,JPanel jpnView, JTextField jtfSearch,JButton btnRemove) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    MovieModel movieModel1 = new MovieModel();
                    movieModel1.setMaPhim(model.getValueAt(selectedRowIndex, 0).toString());
                    MovieJFrame jframe = new MovieJFrame(MovieDAO.getInstance().selectById(movieModel1),jpnView,jtfSearch,btnRemove);
                    jframe.setLocationRelativeTo(null);
                    jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jframe.setResizable(false);
                    jframe.setTitle("Thông tin phim");
                    jframe.setVisible(true);

                }
            }
        });
    }
    public void remove(JTable table,JPanel jpnView, JTextField jtfSearch,JButton btnRemove) {
        MovieModel movieModel = new MovieModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(model.getValueAt(selectedRowIndex, 0).toString());
                    movieModel.setMaPhim(model.getValueAt(selectedRowIndex, 0).toString());
                }
            }
        });
        btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(showDialog()) {
                    if (movieModel.getMaPhim() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else{
                        MovieDAO.getInstance().delete(movieModel);
                        loadTable(jpnView,jtfSearch,btnRemove);
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRemove.setBackground(new Color(172, 92, 92));
                btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnRemove.setBackground(new Color(255, 0, 0));
            }
        });
    }
    public boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    public JTable loadTable(JPanel jpnView, JTextField jtfSearch,JButton btnRemove){
        jpnView.removeAll();
        jpnView.validate();
        jpnView.repaint();
        List<MovieModel> listItem = MovieDAO.getInstance().selectAll();
        MouseListener[] listeners = btnRemove.getMouseListeners();
        for (MouseListener listener : listeners) {
            btnRemove.removeMouseListener(listener);
        }
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        eventTable(table,jpnView,jtfSearch,btnRemove);
        remove(table,jpnView,jtfSearch,btnRemove);
        return table;
    }
}
