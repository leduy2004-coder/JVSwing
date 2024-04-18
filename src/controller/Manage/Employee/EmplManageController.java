package controller.Manage.Employee;

import Service.impl.EmployeeService;
import model.EmployeeModel;
import view.Manage.EmplPanel.EmplManageJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmplManageController extends EventEmpl {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRemove;
    private JTextField jtfSearch;

    private EmployeeModel employeeModel;

//    obj[3] = employeeModel.isGioiTinh() ? "Nam" : "Nữ";


    public EmplManageController(JPanel jpnView, JButton btnAdd, JButton btnRemove, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.btnRemove = btnRemove;
        this.jtfSearch = jtfSearch;
        this.employeeService = new EmployeeService();
    }
    
    public void displayView() {
        loadTable(jpnView,jtfSearch,btnRemove);
    	btnAdd.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				EmplManageJFrame emplManageJFrame = new EmplManageJFrame(employeeModel,jpnView,jtfSearch,btnRemove);
				emplManageJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                emplManageJFrame.setTitle("Thông tin nhân viên");
				emplManageJFrame.setVisible(true);
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
