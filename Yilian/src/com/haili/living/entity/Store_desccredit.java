package com.haili.living.entity;

import java.io.Serializable;

public class Store_desccredit implements Serializable {
	public Store_desccredit() {
	}
	private String text;
	private int credit;
	private String percent;
	private String percent_class;
	private String percent_text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getPercent_class() {
		return percent_class;
	}
	public void setPercent_class(String percent_class) {
		this.percent_class = percent_class;
	}
	public String getPercent_text() {
		return percent_text;
	}
	public void setPercent_text(String percent_text) {
		this.percent_text = percent_text;
	}
	
}