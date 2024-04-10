package Service;

import model.CustomerModel;
import model.EmployeeModel;

import java.util.List;

public interface ICustomerService {
    public int save(CustomerModel customerModel);
    public List<CustomerModel> selectAll();

    public int update(CustomerModel customerModel);
    public int delete(CustomerModel customerModel);
}
