package com.example.demo.facades;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.demo.enums.ClientType;

/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Service
@Scope("singleton")
public class FacadeFactory {

	@Autowired
	private AdminFacade adminFacade;
	
	@Autowired
	private CustomerFacade customerFacade;
	
	@Autowired
	private CompanyFacade companyFacade;
	
	/**
	 * 
	 * @param userName 
	 * @param password 
	 * @param type
	 * @return correct facade
	 */
	public CouponClientFacade login(String name, String pass, ClientType type)
	{
		if(name==null&&pass==null)
			return null;
		
		switch(type){
		case ADMIN:
			return adminFacade.login(name, pass, type);
		case COMPANY:
			return companyFacade.login(name, pass, type);
		case CUSTOMER:
			return customerFacade.login(name, pass, type);
		default:
			return null;
		}
	}

}