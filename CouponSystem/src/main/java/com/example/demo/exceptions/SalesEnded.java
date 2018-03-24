package com.example.demo.exceptions;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public class SalesEnded extends RuntimeException {
	
	public SalesEnded(String msg) {
		super(msg);
	}

}