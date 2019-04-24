package com.excilys.cdb.exception;

public class NotIntExpectedException extends Exception {
	
	public NotIntExpectedException(){
		super("l'id est censé être un entier , ce n'est pas le cas ici");
	}

}
