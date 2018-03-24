package com.example.demo.exceptions;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public class NotExist extends RuntimeException {

	public NotExist(String msg) {
		super(msg);
	}
}