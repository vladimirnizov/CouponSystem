package com.example.demo.web;

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

import com.example.demo.beans.Coupon;
import com.example.demo.beans.Customer;
import com.example.demo.enums.ClientType;
import com.example.demo.enums.CouponType;
import com.example.demo.exceptions.AlreadyPurchased;
import com.example.demo.exceptions.OutOfStock;
import com.example.demo.facades.CustomerFacade;
import com.example.demo.main.CouponsSystem;

@CrossOrigin(origins = "*")
@RequestMapping(value = "Customer")
@RestController
public class CustomerWebService {

	@Autowired
	private CouponsSystem cs;
	

	/***
	 * Login
	 * @return Customer Facade
	 */
	private CustomerFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		CustomerFacade cf = (CustomerFacade) session.getAttribute("facade");
		return cf;
	}
	
	/***
	 * Fake Login
	 * @param request
	 * @param response
	 * @return
	 */
//	private CustomerFacade getFacade(HttpServletRequest request, HttpServletResponse response)
//	{
//		return (CustomerFacade) cs.login("customer111", "123", ClientType.CUSTOMER);
//	}
//	
	@RequestMapping(value = "/getPurchasedCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getPurchasedCoupons(HttpServletRequest request , HttpServletResponse response)
	{
		//Login
		CustomerFacade cf = getFacade(request, response);
		
		
		List<Coupon> coupons = cf.getPurchasedCoupons();
		if(coupons!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	@RequestMapping(value = "/getAllCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getAllCoupons(HttpServletRequest request , HttpServletResponse response)
	{
		//Login
		CustomerFacade cf = getFacade(request, response);
		
		
		List<Coupon> coupons = cf.getAllCoupons();
		if(coupons!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	/***
	 * Get all purchased coupons by type
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getPurchasedCouponsByType/type/{type}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getPurchasedCouponsByType(@PathVariable("type") CouponType type , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
	
		

		List<Coupon> coupons = cf.getPurchaseCouponsByType(type);
		if(coupons!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);
	}
	
	/***
	 * Get all purchased coupons by price
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "/getPurchasedCouponsByPrice/price/{price}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getPurchasedCouponsByPrice(@PathVariable("price") double price , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
	
		

		List<Coupon> coupons = cf.getPurchaseCouponsByPrice(price);
		if(coupons!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);
	}
	
	/***
	 * Get coupon by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getPurchasedCouponById/id/{id}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Coupon> getPurchasedCouponById(@PathVariable("id") int id, HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
	
		

		Coupon coupon = cf.getCoupon(id);
		if(coupon!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);
	}
	
	/***
	 * Purchase coupon
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/purchaseCoupon" , method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Coupon> purchaseCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
		
		try {
			cf.purchaseCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		} catch (OutOfStock | AlreadyPurchased e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
		}
	}
	
	
	/***
	 * Get logged customer
	 * @return customer
	 */
	@RequestMapping(value = "/getLoggedCustomer" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Customer> getLoggedCustomer(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CustomerFacade cf = getFacade(request, response);
	
		

		Customer cust = cf.getCust();
		if(cust!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(cust);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);
	}
	
	
}
