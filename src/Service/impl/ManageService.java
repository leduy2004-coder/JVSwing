package Service.impl;

import Dao.impl.ManageDAO;
import Service.IManageService;
import model.ManageModel;

public class ManageService implements IManageService {
    private ManageDAO manageDAO = null;
    public ManageService() {
        manageDAO = new ManageDAO();
    }
    @Override
    public ManageModel selectByUserNameAndPassword(String userName, String password) {
        return manageDAO.selectByUserNameAndPassword(userName,password);
    }
}
