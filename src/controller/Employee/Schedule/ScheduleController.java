package controller.Employee.Schedule;

import Service.impl.ScheduleService;
import model.MovieModel;
import model.ScheduleModel;
import model.ShiftModel;
import utility.SetTable;
import view.Employee.SchedulePanel.Frame.TimeFrame;
import view.Employee.SchedulePanel.SchedulePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ScheduleController {
    private SchedulePanel sche;

    private ScheduleService scheduleService;
    private ScheduleModel scheduleModel;
    private ShiftModel shiftModel;
    private MovieModel movieModel;
    private MouseListener[] mouseListeners;


    private final String[] COLUMNS = {"Mã SC", "Ngày chiếu"};
    String[] methodNames = {"getMaSC", "getNgayChieu"};
    SetTable<MovieModel> setTable = SetTable.getInstance();
    private JTable table = new JTable();


    int a, b, c;

    public ScheduleController(SchedulePanel sche) {
        this.sche = sche;
        scheduleService = new ScheduleService();
        scheduleModel = new ScheduleModel();
        shiftModel = new ShiftModel();
        movieModel = new MovieModel();
        b = 0;
    }

    public void setData() {
        sche.btnFilter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sche.jbxShift.removeAllItems();
                sche.jbxName.removeAllItems();
                sche.jbxRoom.removeAllItems();
                sche.panelFrmtable.removeAll();
                sche.panelFrmtable.validate();
                sche.panelFrmtable.repaint();
                if (sche.jdcBegin.getDate() != null && sche.jdcEnd.getDate() != null) {
                    if ((sche.jdcBegin.getDate()).compareTo(sche.jdcEnd.getDate()) <= 0) {
                        List<ScheduleModel> list = new ArrayList<>();
                        list = scheduleService.selectAllDate(covertDateToDateSql(sche.jdcBegin.getDate()), covertDateToDateSql(sche.jdcEnd.getDate()));
                        if (list.size() > 0) {
                            sche.jbxShift.removeAllItems();
                            sche.jbxShift.addItem(new ShiftModel());
                            sche.jbxName.removeAllItems();
                            sche.jbxName.addItem(new MovieModel());
                            sche.jbxRoom.removeAllItems();
                            sche.jbxRoom.addItem("");
                            for (ScheduleModel s : list) {
                                movieModel = scheduleService.selectByMPhim(s);
                                shiftModel = scheduleService.selectByMCa(s);
                                if (!containItem(sche.jbxName, movieModel.getTenPhim()))
                                    sche.jbxName.addItem(movieModel);
                                if (!containItem(sche.jbxShift, shiftModel.getTenCa()))
                                    sche.jbxShift.addItem(shiftModel);
                                if (!containItem(sche.jbxRoom, s.getMaPhong()))
                                    sche.jbxRoom.addItem(s.getMaPhong());
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy suất chiếu !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Phải chọn đầy đủ ngày bắt đầu và kết thúc !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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
        sche.btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sche.panelFrmtable.removeAll();
                sche.panelFrmtable.validate();
                sche.panelFrmtable.repaint();
                if (check()) {
                    List<ScheduleModel> listItem = new ArrayList<>();
                    listItem = getAll();
                    if (listItem.size() > 0) {
                        table = setTable.setDataToTable(sche.panelFrmtable, COLUMNS, listItem, sche.jtfSearch, methodNames);
                        remove(table);
                        mouseListeners = table.getMouseListeners();
                    } else
                        JOptionPane.showMessageDialog(null, "Không tìm thấy suất chiếu !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                } else
                    JOptionPane.showMessageDialog(null, "Phải chọn đầy đủ dữ liệu !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
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

    public boolean containItem(JComboBox<?> comboBox, String target) {
        int itemCount = comboBox.getItemCount();
        for (int i = 1; i < itemCount; i++) {
            Object item = comboBox.getItemAt(i);
            if (item != null) {
                if (item instanceof MovieModel) {
                    if (((MovieModel) item).getTenPhim().equals(target))
                        return true;
                } else if (item instanceof ShiftModel) {
                    if (((ShiftModel) item).getTenCa().equals(target))
                        return true;
                } else if (item instanceof String) {
                    if (((String) item).equals(target))
                        return true;
                }
            }
        }
        return false;
    }

    public void setEvent() {
        sche.btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TimeFrame t = new TimeFrame();
                t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                t.setVisible(true);
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

    private boolean check() {
        int a = sche.jbxRoom.getSelectedIndex();
        boolean isRoomSelected = a != -1 && !((String) sche.jbxRoom.getItemAt(a)).equals("");
        boolean isNameSelected = sche.jbxName.getSelectedItem() != null && Objects.requireNonNull(sche.jbxName.getSelectedItem()).toString() != null;
        boolean isShiftSelected = sche.jbxShift.getSelectedItem() != null && Objects.requireNonNull(sche.jbxShift.getSelectedItem()).toString() != null;
        return isRoomSelected && isNameSelected && isShiftSelected;
    }

    public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }

    private String convertDatetoString(Date d) {
        SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
        String date = null;
        return date = sp.format(d);
    }

    private void remove(JTable table) {
        ScheduleModel scheduleModel1 = new ScheduleModel();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    scheduleModel1.setMaSC(model.getValueAt(selectedRowIndex, 0).toString());

                }
            }
        });
        MouseListener[] listeners = sche.btnRemove.getMouseListeners();
        for (MouseListener listener : listeners) {
            sche.btnRemove.removeMouseListener(listener);
        }
        sche.btnRemove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (showDialog()) {
                    if (scheduleModel1.getMaSC() == null)
                        JOptionPane.showMessageDialog(null, "Kích chuột vào 1 dòng của table để xóa !!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    else {
                        scheduleService.delete(scheduleModel1);
                        loadTable();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sche.btnRemove.setBackground(new Color(172, 92, 92));
                sche.btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sche.btnRemove.setBackground(new Color(255, 0, 0));

            }
        });
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có muốn xóa không ?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private List<ScheduleModel> getAll() {
        List<ScheduleModel> listItem = new ArrayList<>();
        ScheduleModel s = new ScheduleModel();
        s.setMaPhim(((MovieModel) sche.jbxName.getSelectedItem()).getMaPhim());
        s.setMaCa(((ShiftModel) sche.jbxShift.getSelectedItem()).getMaCa());
        a = sche.jbxRoom.getSelectedIndex();
        s.setMaPhong((String) sche.jbxRoom.getItemAt(a));
        return listItem = scheduleService.selectAllToTal(covertDateToDateSql(sche.jdcBegin.getDate()), covertDateToDateSql(sche.jdcEnd.getDate()), s);
    }

    private void loadTable() {
        List<ScheduleModel> listItem = getAll();
        table = setTable.setDataToTable(sche.panelFrmtable, COLUMNS, listItem, sche.jtfSearch, methodNames);
        for (MouseListener listener : mouseListeners) {
            table.addMouseListener(listener);
        }
    }

}
