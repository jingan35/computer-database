package com.excilys.cdb.exception;

public class PasLeBonFormatTimestamp extends Exception{
	
	public PasLeBonFormatTimestamp(){
		super("Le Timestamp ecrit dans un mauvais format : yyyy-mm-dd hh:mm:ss");
	}

}
