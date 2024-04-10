package utility;

import model.EmployeeModel;
import model.ManageModel;

import java.util.ArrayList;

import java.util.ArrayList;

public class SessionUtil {

    private ArrayList<ManageModel> listManage;
    private ArrayList<EmployeeModel> listEmpl;
    private static SessionUtil sessionUtil = null;

    private SessionUtil() {
        listManage = new ArrayList<>();
        listEmpl = new ArrayList<>();
    }

    public static synchronized SessionUtil getInstance() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    public void putValueManage(ManageModel manageModel) {
        listManage.add(manageModel);
    }
    public void putValueEmpl(EmployeeModel employeeModel) {
        listEmpl.add(employeeModel);
    }
    public ManageModel getValueManage() {
        if (!listManage.isEmpty()) {
            return listManage.get(0);
        }
        return null;
    }
    public EmployeeModel getValueEmpl() {
        if(!listEmpl.isEmpty()){
            return listEmpl.get(0);
        }
        return null;
    }

    public void removeValue() {
        if (!listManage.isEmpty()) {
            listManage.remove(0);
        }else if(!listEmpl.isEmpty()){
            listEmpl.remove(0);
        }
    }
}

