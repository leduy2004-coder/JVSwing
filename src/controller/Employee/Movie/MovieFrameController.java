package controller.Employee.Movie;

import Service.impl.MovieService;
import Service.impl.TypeMovieService;
import model.MovieModel;
import model.TypeMovieModel;
import utility.SetTable;
import view.Employee.MoviePanel.MovieJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MovieFrameController {
    private MovieJFrame movie;
    private MovieService movieService;
    private MovieModel movieModel;
    private String msg;
    private JPanel jpnView;
    private JTable table;
    private String[] COLUMNS;
    private JTextField jtfSearch;
    private String[] methodNames;
    private MouseListener[] mouseListeners;
    TypeMovieService typeMovieService = new TypeMovieService();
    SetTable<MovieModel> setTable = SetTable.getInstance();

    int t=0;

    public MovieFrameController( MovieJFrame movie,JPanel jpnView, String[] COLUMNS, JTextField jtfSearch, String[] methodNames,MouseListener[] mouseListeners) {
        this.movie = movie;
        this.jpnView = jpnView;
        this.COLUMNS = COLUMNS;
        this.jtfSearch = jtfSearch;
        this.methodNames = methodNames;
        this.mouseListeners = mouseListeners;
        movieService = new MovieService();
    }

    public void setView(MovieModel movieModel) {
        t ++;
        this.movieModel = movieModel;
        movie.jlbId.setText("Mã: "+movieModel.getMaPhim());
        movie.jtfName.setText(movieModel.getTenPhim());
        movie.jdcDate.setDate(movieModel.getNgayKhoiChieu());
        for (TypeMovieModel type : typeMovieService.selectAll()){
            movie.jbxType.addItem(type);
        }
        for (int i = 1; i < movie.jbxType.getItemCount(); i++) {
            TypeMovieModel item = (TypeMovieModel) movie.jbxType.getItemAt(i);
            if (item.toString().equalsIgnoreCase(movieModel.getTypeMovieModel().getTenLPhim()))
                movie.jbxType.setSelectedIndex(i);
        }
        movie.jtfDirector.setText(movieModel.getDaoDien());
        movie.jtfYear.setText(String.valueOf(movieModel.getDoTuoi()));
        movie.jtfDura.setText(String.valueOf(movieModel.getThoiLuong()));
        movie.jtaDes.setText(movieModel.getMoTa());
        movie.jtaVideo.setText(movieModel.getVideo());
        movie.jtfThumbnail.setText(movieModel.getHinhDaiDien());
        movie.jcbStatus.setSelected(movieModel.isTinhTrang());
        CreateOrUpdate();
    }
    public void CreateOrUpdate() {
        if(t==0)
            for (TypeMovieModel type : typeMovieService.selectAll()){
                movie.jbxType.addItem(type);
            }
        movie.btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                	if(!check()){
                        msg = "Vui lòng nhập đầy đủ dữ liệu !!";
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        if(movieModel == null) {
                            movieModel = new MovieModel();
                        }
                        movieModel.setVideo(movie.jtaVideo.getText().trim());
                        movieModel.setTypeMovieModel((TypeMovieModel)movie.jbxType.getSelectedItem());
                        msg = "Dữ liệu độ tuổi là kiểu số!!";
                        movieModel.setDoTuoi(Integer.parseInt(movie.jtfYear.getText().trim()));
                        msg = "Dữ liệu thời lượng là kiểu số!!";
                        movieModel.setThoiLuong(Integer.parseInt(movie.jtfDura.getText().trim()));
                        movieModel.setDaoDien(movie.jtfDirector.getText().trim());
                        movieModel.setTinhTrang(movie.jcbStatus.isSelected());
                        movieModel.setHinhDaiDien(movie.jtfThumbnail.getText().trim());
                        movieModel.setTenPhim(movie.jtfName.getText().trim());
                        movieModel.setMoTa(movie.jtaDes.getText().trim());
                        movieModel.setNgayKhoiChieu(covertDateToDateSql(movie.jdcDate.getDate()));

                        if(movieModel.getMaPhim() == null){
                            msg = "Bạn muốn thêm dữ liệu không ?";
                            if(showDialog(msg)){
                                movieService.save(movieModel);
                                movie.dispose();
                                loadTable();
                            }
                        }else {
                            msg = "Bạn muốn cập nhật dữ liệu không ?";
                            if(showDialog(msg)){
                                movieService.update(movieModel);
                                movie.dispose();
                                loadTable();
                            }
                        }
                    }
                }catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null,msg,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
            }


            @Override
            public void mouseEntered(MouseEvent e) {
                movie.btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                movie.btnSave.setBackground(new Color(6, 199, 73));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                movie.btnSave.setBackground(new Color(0, 153, 51));
            }
        });
        exit();
    }
    private boolean showDialog(String msg) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                msg, "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    private boolean check(){
        return movie.jtfName.getText() != null && !movie.jtfName.getText().equalsIgnoreCase("") &&
                movie.jtfYear.getText() != null && !movie.jtfYear.getText().equalsIgnoreCase("") &&
                movie.jdcDate.getDate() != null && !convertDatetoString(movie.jdcDate.getDate()).equalsIgnoreCase("") &&
                movie.jtfDura.getText() != null && !movie.jtfDura.getText().equalsIgnoreCase("") &&
                movie.jtaVideo.getText() != null && !movie.jtaVideo.getText().equalsIgnoreCase("") &&
                movie.jbxType.getSelectedItem() != null && Objects.requireNonNull(movie.jbxType.getSelectedItem()).toString() != null;
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

    private void exit() {
        movie.btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movie.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                movie.btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                movie.btnExit.setBackground(new Color(232, 54, 54));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                movie.btnExit.setBackground(new Color(255, 0, 0));
            }
        });
    }
    private String convertDatetoString (Date d){
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        return date = sp.format(d);
    }

    private void loadTable(){
        jpnView.removeAll();
        jpnView.validate();
        jpnView.repaint();
        List<MovieModel> listItem = movieService.selectAll();
        table = setTable.setDataToTable(jpnView,COLUMNS,listItem,jtfSearch,methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }


}
