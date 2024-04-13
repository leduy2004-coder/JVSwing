package Service.impl;

import Dao.impl.EmployeeDAO;
import Service.IEmployeeService;
import model.EmployeeModel;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    @Override
    public EmployeeModel selectByUserNameAndPassword(String userName, String password) {
        return EmployeeDAO.getInstance().selectByUserNameAndPassword(userName,password);
    }

    @Override
    public int save(EmployeeModel employeeModel) {
        return EmployeeDAO.getInstance().insert(employeeModel);
    }

    @Override
    public List<EmployeeModel> selectAll() {
        return EmployeeDAO.getInstance().selectAll();
    }

    @Override
    public int update(EmployeeModel employeeModel) {
        return EmployeeDAO.getInstance().update(employeeModel);
    }

    @Override
    public int delete(EmployeeModel employeeModel) {
       return EmployeeDAO.getInstance().delete(employeeModel);
    }
}
