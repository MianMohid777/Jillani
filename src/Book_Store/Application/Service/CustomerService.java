package Book_Store.Application.Service;

import Book_Store.Model.Customer;
import Book_Store.Persistance.CustomerDao;

public class CustomerService {

    private CustomerDao customerRepo;

    public CustomerService() {
        customerRepo = new CustomerDao();
    }

    public Customer getCustomer(Long id)
    {
        return customerRepo.getCustomer(id);
    }


}
