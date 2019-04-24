package com.excilys.cdb.exception;

public class AllAttributesNeededException extends Exception{

	public AllAttributesNeededException() {
		super("Vous devez fournir tous les attributs du nouvel ordinateur pour l'ajout et "
				+ "toutes les nouveau attribus et les valeurs de celle non modifiés pour la mise à jour");
	}
}
