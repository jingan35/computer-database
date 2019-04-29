package com.excilys.cdb.exception;

public class NotACommandException extends Exception {
	
	public NotACommandException() {
		super("La commande demandé n'existe pas");
	}

}
