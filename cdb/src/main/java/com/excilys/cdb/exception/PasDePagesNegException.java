package com.excilys.cdb.exception;

public class PasDePagesNegException extends Exception {
	public PasDePagesNegException() {
		super("Pas de pages Negatives ou d'index 0 . Premiere page : page 1");
		
	}
}
