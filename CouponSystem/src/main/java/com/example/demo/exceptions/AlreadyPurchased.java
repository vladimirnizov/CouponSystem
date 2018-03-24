package com.example.demo.exceptions;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public class AlreadyPurchased extends RuntimeException {

	public AlreadyPurchased(String msg) {
		super(msg);
	}
}