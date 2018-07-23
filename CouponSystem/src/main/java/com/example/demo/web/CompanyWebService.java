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
import com.example.demo.enums.ClientType;
import com.example.demo.enums.CouponType;
import com.example.demo.exceptions.AlreadyExist;
import com.example.demo.exceptions.NotExist;
import com.example.demo.facades.CompanyFacade;
import com.example.demo.facades.CouponClientFacade;
import com.example.demo.facades.CustomerFacade;
import com.example.demo.main.CouponsSystem;

@CrossOrigin(origins = "*")
@RequestMapping(value = "Company")
@RestController
public class CompanyWebService {

	
	@Autowired
    	private CouponsSystem cs;
	
	/***
	 * Fake Login
	 */
	
	/***
	 * Login
	 * @return company Facade
	 */
	private CompanyFacade getFacade(HttpServletRequest request , HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		CompanyFacade cf = (CompanyFacade) session.getAttribute("facade");
		return cf;
	}
	
//	private CouponClientFacade getFacade(HttpServletRequest request , HttpServletResponse response)
//	{
//		return   cs.login("Comp1","12345", ClientType.COMPANY);
//	}
	
	@RequestMapping(value = "/createCoupon" , method = RequestMethod.POST)
	public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		try {
			
			cf.createCoupon(coupon);
			return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		} catch (AlreadyExist e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(null);
		}
	}
	
	/***
	 * Removing a coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/removeCoupon/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<String> removeCoupon(@RequestBody @PathVariable("id") int id , HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		try{
		cf.removeCoupon(cf.getCoupon(id));
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("Coupon was removed from the data base");
		}catch( NotExist e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(e.getMessage());	
		}
	}
	
	/***
	 * Updateing coupon for a company that logged in
	 * @param webCoupon
	 * @return
	 */
	@RequestMapping(value = "/updateCoupon" , method = RequestMethod.PUT)
	public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon coupon , HttpServletRequest request , HttpServletResponse response)
	{
		
		
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		try
		{
		cf.updateCoupon(coupon);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon);
		
		}catch(NotExist e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(null);
		}
	}
	
	/***
	 * Get coupon by id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getCoupon/{id}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Coupon> getCoupon(@PathVariable("id") int id ,HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		
		
		Coupon coupon = cf.getCoupon(id);
		if(coupon!=null)
		  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon); 
		else
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	/***
	 * Get coupon by title
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/getCoupon/{title}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Coupon> getCoupon(@PathVariable("title") String title ,HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		
		
		Coupon coupon = cf.getCouponByTitle(title);
		if(coupon!=null)
		  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupon); 
		else
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	/***
	 * Get all Coupons
	 * @return
	 */
	@RequestMapping(value = "getAllCoupons" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getAllCoupons(HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		
		List<Coupon> coupons = cf.getAllCoupons();
		if(coupons!=null)
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons);
		else
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	/***
	 * Get coupon by type
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getCoupon/{type}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getCouponByType(@PathVariable("type") CouponType type ,HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		
		
		List<Coupon> coupons = cf.getCouponsByType(type);
		if(coupons!=null)
		  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons); 
		else
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
	/***
	 * Get coupon by price
	 * @param price
	 * @return
	 */
	@RequestMapping(value = "/getCoupon/{price}" , method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Coupon>> getCouponByPrice(@PathVariable("price") double price ,HttpServletRequest request , HttpServletResponse response)
	{
		// Login
		CompanyFacade cf = (CompanyFacade) getFacade(request, response);
		
		
		
		List<Coupon> coupons = cf.getCouponsByPrice(price);
		if(coupons!=null)
		  return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(coupons); 
		else
		  return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.TEXT_PLAIN).body(null);	
		
	}
	
}
