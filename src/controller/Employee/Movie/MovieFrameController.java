package controller.Employee.Movie;

import Dao.impl.MovieDAO;
import Dao.impl.TypeMovieDAO;
import model.MovieModel;
import model.TypeMovieModel;
import view.Employee.MoviePanel.MovieJFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MovieFrameController extends EventMovie{
    private MovieJFrame movie;
    private MovieModel movieModel;
    private String msg;
    private JPanel jpnView;
    private JTable table;
    private JTextField jtfSearch;
    private JButton btnRemove;

    String s;
    int t=0;

    public MovieFrameController( MovieJFrame movie,JPanel jpnView, JTextField jtfSearch,JButton btnRemove) {
        this.movie = movie;
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.btnRemove = btnRemove;
    }

    public void setView(MovieModel movieModel) {
        t ++;
        this.movieModel = movieModel;
        movie.jlbId.setText("Mã: "+movieModel.getMaPhim());
        movie.jtfName.setText(movieModel.getTenPhim());
        movie.jdcDate.setDate(movieModel.getNgayKhoiChieu());
        for (TypeMovieModel type : TypeMovieDAO.getInstance().selectAll()){
            movie.jbxType.addItem(type);
        }
        for (int i = 1; i < movie.jbxType.getItemCount(); i++) {
            TypeMovieModel item = (TypeMovieModel) movie.jbxType.getItemAt(i);
            if (item.toString().equalsIgnoreCase(movieModel.getTypeMovieModel().getTenLPhim()))
                movie.jbxType.setSelectedIndex(i);
        }
        if(movieModel.getImg()!=null){
            ImageIcon imageIcon = new ImageIcon(movieModel.getImg());
            Image im = imageIcon.getImage();
            Image myImg = im.getScaledInstance(movie.lbImage.getWidth(), movie.lbImage.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon newImage = new ImageIcon(myImg);
            movie.lbImage.setIcon(newImage);
        }
        movie.jtfDirector.setText(movieModel.getDaoDien());
        movie.jtfYear.setText(String.valueOf(movieModel.getDoTuoi()));
        movie.jtfDura.setText(String.valueOf(movieModel.getThoiLuong()));
        movie.jtaDes.setText(movieModel.getMoTa());
        movie.jtaVideo.setText(movieModel.getVideo());
        movie.jcbStatus.setSelected(movieModel.isTinhTrang());
        CreateOrUpdate();
    }
    public void CreateOrUpdate() {
        if(t==0)
            for (TypeMovieModel type : TypeMovieDAO.getInstance().selectAll()){
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
                        movieModel.setS(s);
                        movieModel.setTenPhim(movie.jtfName.getText().trim());
                        movieModel.setMoTa(movie.jtaDes.getText().trim());
                        movieModel.setNgayKhoiChieu(covertDateToDateSql(movie.jdcDate.getDate()));

                        if(movieModel.getMaPhim() == null){
                            msg = "Bạn muốn thêm dữ liệu không ?";
                            if(showDialog(msg)){
                                MovieDAO.getInstance().insert(movieModel);
                                movie.dispose();
                                loadTable(jpnView,jtfSearch,btnRemove);
                            }
                        }else {
                            msg = "Bạn muốn cập nhật dữ liệu không ?";
                            if(showDialog(msg)){
                                MovieDAO.getInstance().update(movieModel);
                                movie.dispose();
                                loadTable(jpnView,jtfSearch,btnRemove);
                            }
                        }
                    }
                }catch (Exception ex) {
                    if(s == null)
                        msg = "Vui lòng thêm ảnh !!";
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

        movie.btnImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
                fileChooser.addChoosableFileFilter(filter);
                int result = fileChooser.showSaveDialog(null);
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    System.out.println(path);
                    movie.lbImage.setIcon(ResizeImage(path));
                    s = path;
                }
                else if(result == JFileChooser.CANCEL_OPTION){
                    System.out.println("No Data");
                }
            }
        });
        exit();
    }
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(movie.lbImage.getWidth(), movie.lbImage.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
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

}
