package com.excilys.cdb.exception;

public class AllAttributesNeededException extends Exception{

	public AllAttributesNeededException() {
		super("Vous devez fournir tous les attributs du nouvel ordinateur pour l'ajout et "
				+ "et de me pour la mise à jour, les valeurs non modifiés ayant les mêmes qu'avant");
	}
}
