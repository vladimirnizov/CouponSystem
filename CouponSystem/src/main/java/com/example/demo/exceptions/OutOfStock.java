package com.example.demo.exceptions;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public class OutOfStock extends RuntimeException {

	public OutOfStock(String msg) {
		super(msg);
	}
}