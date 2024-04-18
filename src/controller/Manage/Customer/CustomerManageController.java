package controller.Manage.Customer;

import Service.impl.CustomerService;
import model.CustomerModel;
import view.Manage.CustomerPanel.CustomerManageJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerManageController extends EventCustomer{
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;

    private CustomerModel customerModel;
    private JTable table = new JTable();

    private CustomerService customerService;


    public CustomerManageController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.customerService = new CustomerService();
    }

    public void displayView() {
        loadTable(jpnView,jtfSearch,btnRemove);

        btnAdd.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CustomerManageJFrame customerManageJFrame = new CustomerManageJFrame(customerModel,jpnView,jtfSearch,btnRemove);
                customerManageJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                customerManageJFrame.setTitle("Thông tin khách hàng");
                customerManageJFrame.setVisible(true);
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

    }


}
