package com.model;

public class Quote {

	int id;
	String quote;
	
	public Quote() {
		super();
	}

	public Quote(int id, String quote) {
		super();
		this.id = id;
		this.quote = quote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", quote=" + quote + "]";
	}
	
	

}
