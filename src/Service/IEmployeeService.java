package Service;

import model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

public interface IEmployeeService {
    public EmployeeModel selectByUserNameAndPassword(String userName, String password);
    public int save(EmployeeModel employeeModel);
    public List<EmployeeModel> selectAll();

    public int update(EmployeeModel employeeModel);
    public int delete(EmployeeModel employeeModel);
}
