package com.example.demo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.enums.ClientType;
import com.example.demo.facades.CouponClientFacade;
import com.example.demo.facades.FacadeFactory;
import com.example.demo.utilites.ConnectionPool;
import com.example.demo.utilites.DailyService;




/**
 * 
 * @author vladimirnizovtsev
 *
 */
@Component
@Scope("singleton")
public class CouponsSystem {

	@Autowired
	private FacadeFactory ff;
	
	@Autowired
	private DailyService daily;
	
    private Thread dailyThread;
	
	private boolean dailyTask = false;
	
	/**
	 * 
	 * @param userName 
	 * @param password 
	 * @param type
	 * @return correct facade
	 */
	public CouponClientFacade login(String name, String password, ClientType type) 
	{
		return ff.login(name, password, type);
	}

	/**
	 * shutdown of dailyTask
	 */
	public synchronized void stop() 
	{
		if(!dailyTask) return;
		dailyTask = false;
	}
	
	/**
	 * starting the dailyTask
	 */
	public synchronized void start() {
		if(dailyTask) return;
		dailyTask = true;
		
		dailyThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(dailyTask){
					
					daily.removeExpired();
					
					try {
						Thread.sleep(24*60*60*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		 
		 dailyThread.start();
		 

}
	/**
	 * shutdown of system
	 */
	public void shutdown() 
	{
		ConnectionPool.getInstance().closeConnetions();
		this.stop();
	}
	
}