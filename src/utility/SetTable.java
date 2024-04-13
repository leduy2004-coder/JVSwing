package utility;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.List;

public class SetTable<T> {
    public static SetTable getInstance() {
        return new SetTable();
    }

    private TableRowSorter<TableModel> rowSorter = null;

    public DefaultTableModel createTableModel(List<?> dataList, String[] columnNames, String[] methodNames) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        model.setColumnIdentifiers(columnNames);
        for (Object data : dataList) {
            Object[] rowData = new Object[methodNames.length];
            for (int i = 0; i < methodNames.length; i++) {
                try {
                    Method method = data.getClass().getMethod(methodNames[i]);
                    Object value = method.invoke(data);
                    rowData[i] = value;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            model.addRow(rowData);
        }
        return model;
    }
    public JTable setDataToTable(JPanel panelTable, String[] COLUMNS ,List<?> listItem,JTextField jtfSearch, String[] methodNames) {

        DefaultTableModel model = createTableModel(listItem,COLUMNS,methodNames);
        JTable table = new JTable(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

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

        return table;
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
