package com.example.demo;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.Repo.CompanyRepo;
import com.example.demo.Repo.CouponRepo;
import com.example.demo.Repo.CustomerRepo;
import com.example.demo.beans.Company;
import com.example.demo.beans.Customer;
import com.example.demo.enums.ClientType;
import com.example.demo.facades.AdminFacade;
import com.example.demo.facades.CompanyFacade;
import com.example.demo.facades.CustomerFacade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponSystemApplicationTests1 {


	@Autowired
	public AdminFacade admin;
	
	@Autowired
	public CustomerFacade cust;
	
	@Autowired
	public CompanyFacade comp;
	
	@Autowired
	CompanyRepo cr;
	
	@Autowired
	CustomerRepo custRep;
	
	@Autowired
	CouponRepo coupRep;
	
	@Test
	public void contextLoads() {
		
	}
	
	// !!!!! PROPERTIES!!!!!!: spring.jpa.hibernate.ddl-auto=create   (Should be CREATE)
	
	//AdminFacade Functions:
	
	/**
	 * Login as admin
	 */
	@Test
	public void adminLogin()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		Assert.assertNotNull(af);
	}
	/**
	 * Login as admin with wrong password
	 */
	@Test
	public void adminLogin2()
	{
		AdminFacade af = admin.login("admin", "4321", ClientType.ADMIN);
		Assert.assertNull(af);
	}
	/**
	 * Creating company
	 */
	@Test
	public void createComp()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCompany(new Company("Comp1", "12345", "a@gmail.com"));
		Assert.assertNotNull(cr.findCompByName("Comp1"));
	}
	
	/**
	 * Creating company
	 */
	@Test
	public void createComp2()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCompany(new Company("Comp2", "12345", "a@gmail.com"));
		Assert.assertNotNull(cr.findCompByName("Comp2"));
	}
	/**
	 * Creating company
	 */
	@Test
	public void createComp3()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCompany(new Company("Comp3", "12345", "a@gmail.com"));
		Assert.assertNotNull(cr.findCompByName("Comp3"));
	}
	
	
	
	
	
	/**
	 * Creating customer
	 */
	@Test
	public void createCustomer()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCustomer(new Customer("Customer1", "4321"));
		Assert.assertNotNull(custRep.getCustomerByName("Customer1"));
	}
	/**
	 * Creating customer
	 */
	@Test
	public void createCustomer2()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCustomer(new Customer("Customer2", "4321"));
		Assert.assertNotNull(custRep.getCustomerByName("Customer2"));
	}
	/**
	 * Creating customer
	 */
	@Test
	public void createCustomer3()
	{
		AdminFacade af = admin.login("admin", "1234", ClientType.ADMIN);
		af.createCustomer(new Customer("Customer3", "4321"));
		Assert.assertNotNull(custRep.getCustomerByName("Customer3"));
	}
	
	
	
	//CompanyFacade functions:
	/**
	 * Login as Company
	 */
	@Test
	public void companyLogin()
	{
		CompanyFacade comf = comp.login("Comp1", "12345", ClientType.COMPANY);
		Assert.assertNotNull(comf);
	}
	
	/**
	 * Login as Company with wrong pass
	 */
	@Test
	public void companyLogin2()
	{
		CompanyFacade comf = comp.login("Comp1", "43621", ClientType.COMPANY);
		Assert.assertNull(comf);
	}
	

	
	
	
	//CustomerFacade functions:
	
	/**
	 * Login as Customer
	 */
	@Test
	public void customerLogin()
	{
		CustomerFacade cf = cust.login("Customer2", "4321", ClientType.CUSTOMER);
		Assert.assertNotNull(cf);
	}
	
	/**
	 * Login as Customer with wrong pass
	 */
	@Test
	public void customerLogin2()
	{
		CustomerFacade cf = cust.login("Customer2", "43621", ClientType.CUSTOMER);
		Assert.assertNull(cf);
	}
	
	
}


