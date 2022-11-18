package com.exemple.dizcontre.api.enuns;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public enum SexoEnum {

	MASCULINO("Masculino", "M"),
	FEMININO("Feminino", "F");
	
	
	private String value;
	private String label;
	
	private SexoEnum(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return this.label;
	}
	
	
}
