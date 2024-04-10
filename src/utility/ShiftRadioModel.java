package utility;

import model.ShiftModel;

import javax.swing.*;

public class ShiftRadioModel extends JRadioButton {
    private ShiftModel shiftModel;

    public ShiftRadioModel() {
    }

    public ShiftRadioModel(ShiftModel shiftModel) {
        super(shiftModel.getTenCa());
        this.shiftModel = shiftModel;
    }

    public ShiftModel getShiftModel() {
        return shiftModel;
    }

    public void setShiftModel(ShiftModel shiftModel) {
        this.shiftModel = shiftModel;
        setText(shiftModel.getTenCa());
    }
}
