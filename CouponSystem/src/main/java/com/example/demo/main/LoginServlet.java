package com.example.demo.main;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Repo.CompanyRepo;
import com.example.demo.Repo.CustomerRepo;
import com.example.demo.enums.ClientType;
import com.example.demo.facades.CouponClientFacade;

@Controller
@CrossOrigin(origins = "*")
public class LoginServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private CouponsSystem cs;
	
	@Autowired
	private CompanyRepo compRep;
	
	@Autowired
	private CustomerRepo custRep;
	
	ClientType type = null;
	

@RequestMapping(value = "/loginservlet" , method = RequestMethod.POST)	
public @ResponseBody void doPost(HttpServletRequest request, HttpServletResponse response  ,
		                          @RequestParam String name , 
		                          @RequestParam String password)
{
	
	if(name.equals("admin")) {
		type=ClientType.ADMIN;

		
	}
	else {
		
		if(compRep.findCompByName(name)!=null) {
			type=ClientType.COMPANY;
		}
		else if(custRep.getCustomerByName(name)!=null) {
			type=ClientType.CUSTOMER;
		}
		
	}
	
	
	
	
	
	if(type==null){
		try {
			response.sendRedirect("/index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	else{
	try {
		
		
		CouponClientFacade cf = cs.login(name, password, type);
	
		
		if(cf == null){
			try {
				response.sendRedirect("/index.html");
			} catch (IOException e) {
			}
		}
		
		if(cf!=null) {
		request.getSession().setAttribute("facade", cf);
		
		
		switch(type) {
		case ADMIN:
			response.sendRedirect("/admin_page/index.html");
			break;
		case COMPANY:
			response.sendRedirect("/company_page/index.html");
			break;
		case CUSTOMER:
			response.sendRedirect("/customer_page/index.html");
			break;
		}
		}
		
	
		
		
	
	} catch (IOException e) {
	}
	
}
}
}
