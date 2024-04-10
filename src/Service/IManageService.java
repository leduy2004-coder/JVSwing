package Service;

import model.EmployeeModel;
import model.ManageModel;

public interface IManageService {
    public ManageModel selectByUserNameAndPassword(String userName, String password);
}
