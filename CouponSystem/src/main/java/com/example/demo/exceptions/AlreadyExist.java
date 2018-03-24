package com.example.demo.exceptions;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public class AlreadyExist extends RuntimeException {

	public AlreadyExist(String msg) {
		super(msg);
	}
}