package bean;

import javax.swing.*;

public class BookBean {
    private JLabel lbNameChair;
    private JLabel lbChair;

    public BookBean(JLabel lbNameChair, JLabel lbChair) {
        this.lbNameChair = lbNameChair;
        this.lbChair = lbChair;
    }

    public JLabel getLbNameChair() {
        return lbNameChair;
    }

    public void setLbNameChair(JLabel lbNameChair) {
        this.lbNameChair = lbNameChair;
    }

    public JLabel getLbChair() {
        return lbChair;
    }

    public void setLbChair(JLabel lbChair) {
        this.lbChair = lbChair;
    }
}
