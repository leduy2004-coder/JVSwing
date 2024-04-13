package utility;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;

public class SetTable<T> {
    public static SetTable getInstance() {
        return new SetTable();
    }
     ClassTableModel classTableModel = new ClassTableModel();

    private TableRowSorter<TableModel> rowSorter = null;
    public void setDataToTable(JPanel panelTable, String[] COLUMNS ,List<T> listItem,JTextField jtfSearch) {


        DefaultTableModel model = classTableModel.setTableMovieStatus(listItem, COLUMNS);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter); //sort

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter(jtfSearch);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter(jtfSearch);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

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
        panelTable.removeAll();
        panelTable.setLayout(new CardLayout());
        panelTable.add(scroll);
        panelTable.validate();
        panelTable.repaint();
    }

    private void applyFilter(JTextField jtfSearch) {
        String text = jtfSearch.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            RowFilter<Object, Object> rowFilter = RowFilter.regexFilter("(?i)" + text, 0);
            rowSorter.setRowFilter(rowFilter);
        }
    }
}
