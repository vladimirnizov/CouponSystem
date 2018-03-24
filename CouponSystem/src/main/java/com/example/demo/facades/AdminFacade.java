package com.example.demo.facades;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.dao.CompanyDBDAO;
import com.example.demo.dao.CustomerDBDAO;
import com.example.demo.enums.ClientType;
import com.example.demo.exceptions.AlreadyExist;
import com.example.demo.exceptions.DontChangeTheName;
import com.example.demo.exceptions.NotExist;


/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component
public class AdminFacade implements CouponClientFacade{

	private static final String adminName = "admin";
	private static final String adminPass = "1234";

	@Autowired
	private CompanyDBDAO compDBDAO;
	
	@Autowired
	private CustomerDBDAO custDBDAO;
	
	
	public AdminFacade() {}
	
	/**
	 * 
	 * @param userName 
	 * @param password 
	 * @return CouponClientFacade
	 */
	@Override
	public AdminFacade login(String userName, String password, ClientType type) {
		if(!type.equals(ClientType.ADMIN))
		{
			System.out.println(type+ " is wrong type.");
			return null;
		}
		if(userName.equals(adminName) && password.equals(adminPass) ) 
			return this;
		return null;
	}

	/**
	 * 
	 * @param company to create company
	 * @throws AlredyExist exception
	 */
	public synchronized void createCompany(Company company) {
		if(this.getCompanyByName(company.getCompanyName()) != null)
			throw new AlreadyExist(company.getCompanyName() + " already exist!");
		else
			compDBDAO.createCompany(company);
	}
/**
 * 
 * @param company to remove company
 * @throws NotExist exception
 */
	public synchronized void removeCompany(Company company) {
		Company currentCompany = compDBDAO.getCompany(company.getId());
		if(currentCompany == null) 
			throw new NotExist(company.getCompanyName() + " does not exist!");
		else{
			compDBDAO.removeCompany(currentCompany);
		}
	}

	/**
	 * 
	 * @param company to update company
	 * @throws NotExist exception
	 */
	public synchronized void updateCompany(Company company) {
		Company currentCompany = compDBDAO.getCompany(company.getId());
		if(currentCompany == null) 
			throw new NotExist(company.getCompanyName() + " does not exist!");
		else if(!currentCompany.getCompanyName().equals(company.getCompanyName())) {
			throw new DontChangeTheName("You can't change the name of company");
		}else {
			compDBDAO.updateCompany(company);
		}
	}

	/**
	 * 
	 * @param id to get company by id
	 * @return Company
	 */
	public Company getCompany(int id) {
		return compDBDAO.getCompany(id);
	}
/**
 * 
 * @param companyName to get company by name
 * @return Company
 */
	public Company getCompanyByName(String companyName) {
		return compDBDAO.getCompanyByName(companyName);
	}
	
	/**
	 * 
	 * @return array of all companies
	 */
	public ArrayList<Company> getAllCompanies() {
		return compDBDAO.getAllCompanies();
	}

	/**
	 * 
	 * @param customer to create customer
	 * @throws AlreadyExist exception
	 */
	public synchronized void createCustomer(Customer customer) {
		if(custDBDAO.getCustomerByName(customer.getCustomerName())!=null)
			throw new AlreadyExist("This customer aledy exist");
		else
			custDBDAO.createCustomer(customer);
		
	}

	/**
	 * 
	 * @param customer to remove customer
	 * @throws NotExist exception
	 */
	public synchronized void removeCustomer(Customer customer) {
		Customer currentCustomer = custDBDAO.getCustomer(customer.getId());
		if(currentCustomer == null)
			throw new NotExist(customer.getCustomerName() + " does not exist!");
		else {
			custDBDAO.removeCustomer(currentCustomer);
		}
	}

	/**
	 * 
	 * @param customer to update customer
	 * @throws NotExist exception
	 * @throws DontChangeTheName exception
	 */
	public synchronized void updateCustomer(Customer customer) {
		Customer currentCustomer = custDBDAO.getCustomer(customer.getId());
		if(currentCustomer==null)
			throw new NotExist(customer.getCustomerName() + " does not exist!");
		else if(!currentCustomer.getCustomerName().equals(customer.getCustomerName())) {
			throw new DontChangeTheName("You can't change the name of customer");
		}
			custDBDAO.updateCustomer(customer);
	}

	/**
	 * 
	 * @param id to get customer by id
	 * @return Customer
	 */
	public Customer getCustomer(int id) {
		return custDBDAO.getCustomer(id);
	}

	/**
	 * 
	 * @param customerName to get customer by name
	 * @return Customer
	 */
	public Customer getCustomerByName(String customerName) {
		return custDBDAO.getCustomerByName(customerName);
	}
	/**
	 * 
	 * @return array of all customers
	 */
	public ArrayList<Customer> getAllCustomers() {
		return custDBDAO.getAllCustomers();
	}


}
