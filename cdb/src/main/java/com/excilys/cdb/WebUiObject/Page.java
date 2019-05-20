package com.excilys.cdb.WebUiObject;

import java.util.ArrayList;

public class Page {

	private String currentPage="1";
	private String nbComputersByPage="10";
	private String nbComputers="0";
	private String searched=null;
	private OrderByEnum orderBy = null;
	private boolean isOrdered = false;
	
	 ArrayList<Integer> availablePages = new ArrayList<Integer>();
	
	private static Page INSTANCE = new Page();
	
	private Page() {
		
	}
	
	public int getNbPages(){
		int resteDernierePage = Integer.parseInt(getNbComputers())%Integer.parseInt(getNbComputersByPage());
		int lastPage =(resteDernierePage==0)? (Integer.parseInt(getNbComputers())/Integer.parseInt(getNbComputersByPage())):((Integer.parseInt(getNbComputers())/Integer.parseInt(getNbComputersByPage()))+1);
		
		return lastPage;
	}

	public OrderByEnum getOrderBy() {
		return orderBy;
	}
	
	
	public static Page getInstance(){
		return INSTANCE;
	}
	
	public String getCurrentPage() {
		return currentPage;
	}
	
	public String getNbComputersByPage() {
		return nbComputersByPage;
	}
	
	public String getNbComputers() {
		return nbComputers;
	}
	
	public String getSearched() {
		return searched;
	}
	
	public ArrayList<Integer> getAvailablePages( ){
		return this.availablePages;
	}
	
	public boolean getIsOrdered() {
		return isOrdered;
	}
	
	public void setOrderBy(OrderByEnum orderBy) {
		this.orderBy=orderBy;
	}
	
	public void setCurrentPage(String currentPage) {
		this.currentPage=currentPage;
	}
	
	public void setNbComputersByPage(String nbComputersByPage) {
		this.nbComputersByPage=nbComputersByPage;
	}
	
	public void setNbComputers(String nbComputers) {
		this.nbComputers=nbComputers;
	}
	
	public void setSearched(String searched) {
		this.searched=searched;
	}
	public void setAvailablePages(ArrayList<Integer> availablePages){
		this.availablePages=availablePages;
	}
	
	public void setIsOrdered(boolean isOrdered) {
		this.isOrdered=isOrdered;
	}
}
