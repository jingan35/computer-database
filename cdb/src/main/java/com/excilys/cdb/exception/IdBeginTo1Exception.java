package com.excilys.cdb.exception;

public class IdBeginTo1Exception extends Exception{
	IdBeginTo1Exception(){
		super("les valeurs d'id allant de 1 à + mais pas de 0 ou moins");
	}
}
