package com.excilys.cdb.exception;

public class RequeteSansResultatException extends Exception {
	
	public RequeteSansResultatException(){
		super("Requete ne donnant Aucun Resultat");
	}

}
