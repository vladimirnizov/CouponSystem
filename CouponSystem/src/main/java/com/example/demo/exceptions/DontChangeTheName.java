package com.example.demo.exceptions;
/**
 * 
 * @author vladimirnizovtsev
 *
 */
public class DontChangeTheName extends RuntimeException {

	public DontChangeTheName(String msg) {
		super(msg);
	}
}