package com.excilys.cdb.persistance;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class Dao<T> {
	String url = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
	String utilisateur = "admincdb";
	String motDePasse = "qwerty1234";
	public abstract void update(int id, T maj);
	public abstract void insert(T model);
	public abstract void delete(int id);
	public abstract ArrayList<T> select(int nbRowByPage,int page);
	

}
