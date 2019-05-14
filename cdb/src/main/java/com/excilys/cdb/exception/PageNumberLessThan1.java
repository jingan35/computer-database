package com.excilys.cdb.exception;


public class PageNumberLessThan1 extends Exception {

	public PageNumberLessThan1(int localId){
		super("Pas de page inferieur à 1");
	}
}
