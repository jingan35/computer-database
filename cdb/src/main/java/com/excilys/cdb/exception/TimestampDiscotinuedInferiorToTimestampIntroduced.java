package com.excilys.cdb.exception;

public class TimestampDiscotinuedInferiorToTimestampIntroduced extends Exception {
	public TimestampDiscotinuedInferiorToTimestampIntroduced(){
		super("La date d'introducton dans le marché est censé être supèrieur à celle de fin de production");
	}
}
