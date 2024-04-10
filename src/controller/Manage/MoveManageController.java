package controller.Manage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import bean.ListBean;
import utility.SessionUtil;
import view.LoginView;
import view.Manage.CustomerPanel.CustomerManagePanel;
import view.Manage.EmplPanel.EmplManagePanel;
import view.Manage.HomeManagePanel;
import view.Manage.StatisticPanel.StatisticPanel;

public class MoveManageController {
    private JFrame frame;
    private JMenuItem exit;
    private JPanel root;
    private String kindSelect = "";

    private List<ListBean> listItem = null;
    public MoveManageController(JPanel jpnRoot, JFrame frame, JMenuItem exit) {
        this.root = jpnRoot;
        this.frame = frame;
        this.exit = exit;
    }
    public void setView(JPanel jPanelItem, JLabel jLabelItem){
        kindSelect = "Trang chủ";
        jPanelItem.setBackground(new Color(96, 100, 191));
        jLabelItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new HomeManagePanel());
        root.validate();
        root.repaint();
        exit();
    }
    public void setViewEmpl(JPanel jPanelItem, JLabel jLabelItem){
        kindSelect = "Nhân viên";
        jPanelItem.setBackground(new Color(96, 100, 191));
        jLabelItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new EmplManagePanel(frame));
        root.validate();
        root.repaint();
        exit();
    }
    public void setViewCustomer(JPanel jPanelItem, JLabel jLabelItem){
        kindSelect = "Khách hàng";
        jPanelItem.setBackground(new Color(96, 100, 191));
        jLabelItem.setBackground(new Color(96, 100, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new CustomerManagePanel(frame));
        root.validate();
        root.repaint();
        exit();
    }

    public void setEvent(List<ListBean> listItem) {
        this.listItem = listItem;
        for (ListBean item:listItem  ) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }
    class LabelEvent implements MouseListener {
        private JPanel node;
        private String kind;

        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Trang chủ":
                    node = new HomeManagePanel();
                    break;
                case "Nhân viên":
                    node = new EmplManagePanel(frame);
                    break;
                case "Khách hàng":
                    node = new CustomerManagePanel(frame);
                    break;
                case "Thống kê":
                    node = new StatisticPanel();
                    break;
                default:
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelect = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
        	jlbItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelect.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(44, 134, 93));
                jlbItem.setBackground(new Color(44, 134, 93));
            }
        }

        private void setChangeBackground(String kind){
            for (ListBean item: listItem  ) {
                if(item.getKind().equalsIgnoreCase(kind)){
                    item.getJpn().setBackground(new Color(96, 100, 191));
                    item.getJlb().setBackground(new Color(96, 100, 191));
                } else {
                    item.getJpn().setBackground(new Color(44, 134, 93));
                    item.getJlb().setBackground(new Color(44, 134, 93));
                }
            }
        }
    }

    public void exit(){
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SessionUtil.getInstance().removeValue();
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
            }
        });
    }
}
