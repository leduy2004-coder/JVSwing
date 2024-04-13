package controller.Employee.Movie;

import Service.impl.MovieService;
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

public class MovieController  {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;
    private MovieModel movieModel;
    private MouseListener[] mouseListeners;
    private JTable table = new JTable();
    private final String[] COLUMNS = {"Mã phim", "Tên phim", "Loại phim", "Đạo diễn","Độ tuổi", "Ngày chiếu","Thời lượng", "Trạng thái"};
    String[] methodNames = {"getMaPhim", "getTenPhim", "getTenLoaiPhim","getDaoDien","getDoTuoi","getNgayKhoiChieu","getThoiLuong","isTinhTrang"};
    private MovieService movieService = null;

    SetTable<MovieModel> setTable = SetTable.getInstance();

    public MovieController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.movieService = new MovieService();
    }

    public void displayView() {
        List<MovieModel> listItem = movieService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
    	btnAdd.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MovieJFrame movieJFrame = new MovieJFrame(movieModel,jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
                movieJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                movieJFrame.setTitle("Thông tin phim");
                movieJFrame.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(89, 190, 89));
                btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(34, 139, 34));
			}
		});
    	
        eventTable(table);
        remove(table);
        mouseListeners = table.getMouseListeners();
    }
    private void eventTable(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    MovieModel movieModel1 = new MovieModel();
                    movieModel1.setMaPhim(model.getValueAt(selectedRowIndex, 0).toString());
                    MovieJFrame jframe = new MovieJFrame( movieService.selectById(movieModel1),jpnView,COLUMNS,jtfSearch,methodNames,mouseListeners);
                    jframe.setLocationRelativeTo(null);
                    jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    jframe.setResizable(false);
                    jframe.setTitle("Thông tin phim");
                    jframe.setVisible(true);

                }
            }

        });
    }
    private void remove(JTable table) {
        MovieModel movieModel = new MovieModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

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
                        movieService.delete(movieModel);
                        loadTable();
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
    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    private void loadTable(){
        List<MovieModel> listItem = movieService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }

}
