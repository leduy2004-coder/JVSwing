package Service.impl;

import Dao.impl.CustomerDAO;
import Dao.impl.EmployeeDAO;
import Service.ICustomerService;
import Service.IEmployeeService;
import model.CustomerModel;
import model.EmployeeModel;

import java.util.List;

public class CustomerService implements ICustomerService {

    @Override
    public CustomerModel save(CustomerModel customerModel) {
        return CustomerDAO.getInstance().insertGetId(customerModel);
    }

    @Override
    public List<CustomerModel> selectAll() {
        return CustomerDAO.getInstance().selectAll();
    }

    @Override
    public int update(CustomerModel customerModel) {
        return CustomerDAO.getInstance().update(customerModel);
    }

    @Override
    public int delete(CustomerModel customerModel) {
       return CustomerDAO.getInstance().delete(customerModel);
    }
}
