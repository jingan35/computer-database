package com.excilys.cdb.exception;


public class PageNumberLessThan1Exception extends Exception {

	public PageNumberLessThan1Exception(int localId){
		super("Pas de page inferieur à 1");
	}
}
