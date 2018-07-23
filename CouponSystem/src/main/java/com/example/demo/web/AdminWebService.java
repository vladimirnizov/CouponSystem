package com.example.demo.web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Company;
import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.enums.ClientType;
import com.example.demo.exceptions.AlreadyExist;
import com.example.demo.exceptions.DontChangeTheName;
import com.example.demo.exceptions.NotExist;
import com.example.demo.facades.AdminFacade;
import com.example.demo.facades.CustomerFacade;
import com.example.demo.main.CouponsSystem;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "Admin")
public class AdminWebService {
	
	//for fake login
	@Autowired
    public AdminFacade a;
	
	
	private CouponsSystem cs;
	
	/***
	 * Login
	 * @return admin Facade
	 */
	private AdminFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		AdminFacade af = (AdminFacade) session.getAttribute("facade");
		return af;
	}
	
	/***
	 * Fake Login
	 * @return fake admin
	 */
//	 private AdminFacade getFacade(HttpServletRequest request , HttpServletResponse response)
//	 {
//		 return a.login("admin", "1234", ClientType.ADMIN);
//		
//	 }
	
	//!!!------Company------!!!
		
		/***
		 * Create Company
		 */
		@RequestMapping(value = "/createCompany" , method = RequestMethod.POST)
		public ResponseEntity<Company> createCompany(@RequestBody Company company , HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			try {
				af.createCompany(company);
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(company);
			} catch (AlreadyExist e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
			}
		}
		
		/***
		 * Remove Company
		 */
		@RequestMapping(value = "/removeCompany/{id}" , method = RequestMethod.DELETE)
		public @ResponseBody ResponseEntity<String> removeCompany(@RequestBody @PathVariable("id") int id,
				HttpServletRequest request , HttpServletResponse response )
		{
			// Login
			
			AdminFacade af = getFacade(request, response);
			
			try{
			af.removeCompany(af.getCompany(id));
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Company was removed successfully");
			}catch (NotExist e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
			}
		}
		
		/***
		 * Update Company
		 */
		@RequestMapping(value = "/updateCompany" , method = RequestMethod.PUT)
		public @ResponseBody ResponseEntity<Object> updateCompany(@RequestBody Company company , HttpServletRequest request , HttpServletResponse response )
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			try{
			af.updateCompany(company);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("updated successfully");
			}catch(NotExist e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
			}catch(DontChangeTheName e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
			}
		}
		
		/***
		 * Get Company
		 */
		@RequestMapping(value = "/getbyid/{id}" , method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<Company> getById(@RequestBody @PathVariable("id") int id, HttpServletRequest request , HttpServletResponse response)
			{
			// Login
			AdminFacade af=getFacade(request, response);
			
			Company company = af.getCompany(id);
			
			if(company!=null)
				  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(company);
				else
				  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
			
			}
		
		/***
		 * Get Company by name
		 */
		@RequestMapping(value = "/getcompany/{name}" , method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<Company> getCompanyByName(@RequestBody @PathVariable("name") String name , HttpServletRequest request , HttpServletResponse response)
			{
			// Login
			AdminFacade af = getFacade(request, response);
		
			
				Company company = af.getCompanyByName(name);
				if(company!=null)
					  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(company);
					else
					  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
				
				}

		/***
		 * Get All Companies
		 */
		@RequestMapping(value = "/getallcompanies" , method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<List<Company>> getAllCompanies(HttpServletRequest request , HttpServletResponse response)
			{
			// Login
			AdminFacade af = getFacade(request, response);
			
			
				ArrayList<Company> companies = af.getAllCompanies();
			
				if(companies!=null)
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(companies);
			
				return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);
			
			}
		
		//!!!--------Customer--------!!!
		
		/***
		 * Create Customer
		 */
		@RequestMapping(value = "createCustomer" , method = RequestMethod.POST)
		public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer , HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			try {
				af.createCustomer(customer);
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
			} catch (AlreadyExist e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
			}
		}
		
		/***
		 * Remove Customer
		 * @param weCustomer
		 * @return
		 */
		@RequestMapping(value = "/removeCustomer/{id}" , method = RequestMethod.DELETE)
		public ResponseEntity<String> removeCoupon(@RequestBody @PathVariable("id") int id, HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			try{
			af.removeCustomer(af.getCustomer(id));
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Customer was removed successfully");
			}catch(NotExist e){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
			}
		}
		
		/***
		 * Update Customer
		 */
		@RequestMapping(value = "updateCustomer" , method = RequestMethod.PUT)
		public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer , HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
		
			
			try{
				af.updateCustomer(customer);
				return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("updated successfully");
				}catch(NotExist e){
				return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
				}catch(DontChangeTheName e) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());
				}
		}
		
		/***
		 * Get Customer
		 */
		@RequestMapping(value = "/getCustomer/{id}" , method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<Customer> getCustomer(@PathVariable("id") int id , HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			
			Customer customer = af.getCustomer(id);
			if(customer!=null)
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
			else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
			
			
		}
		
		/***
		 * Get Customer by name
		 */
		@RequestMapping(value = "/getCustomer/{name}" , method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<Customer> getCustomerByName(@PathVariable("name") String name , HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
			
			Customer customer = af.getCustomerByName(name);
			if(customer!=null)
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customer);
			else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
			
			
		}
		
		/***
		 * Get All Customers
		 */
		@RequestMapping(value = "/getallcustomers" , method = RequestMethod.GET)
		public @ResponseBody ResponseEntity<List<Customer>> getAllCustomers(HttpServletRequest request , HttpServletResponse response)
		{
			// Login
			AdminFacade af = getFacade(request, response);
			
//			List<Customer> customers=new ArrayList<Customer>();
//			customers.add(new Customer());
//			customers.add(new Customer());
			
			List<Customer> customers = af.getAllCustomers();
			if(customers!=null)
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(customers);
			else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);
				
			
		}
	 
	 
}
