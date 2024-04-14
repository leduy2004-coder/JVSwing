package controller.Employee.Movie;

import model.MovieModel;
import utility.SetTable;
import view.Employee.MoviePanel.MovieJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MovieController extends EventMovie{
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;
    private MovieModel movieModel;
    private JTable table = new JTable();

    public MovieController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
    }

    public void displayView() {
        table = loadTable(jpnView,jtfSearch,btnRemove);
    	btnAdd.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				MovieJFrame movieJFrame = new MovieJFrame(movieModel,jpnView,jtfSearch,btnRemove);
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

        loadTable(jpnView,jtfSearch,btnRemove);
    }
}
