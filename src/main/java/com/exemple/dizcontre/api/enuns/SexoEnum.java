package com.exemple.dizcontre.api.enuns;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public enum SexoEnum {

	MASCULINO("Masculino", "M"),
	FEMININO("Feminino", "F");
	
	
	private String valor;
	private String label;
	
	private SexoEnum(String name, String label) {
		this.valor = name;
		this.label = label;
	}
	
	public void setValue(String value) {
		this.valor = value;
	}
	
	public String getValue() {
		return valor;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return this.getLabel().toString();
	}
	
	// Método estático para converter a String do DB de volta para o Enum
		public static SexoEnum fromLabel(String label) {
			if (label == null || label.isEmpty()) {
	            return null;
	        }
			for (SexoEnum sexo : SexoEnum.values()) {
				if (sexo.label.equals(label)) {
					return sexo;
				}
			}
			throw new IllegalArgumentException("Código de Sexo inválido: " + label);
		}
	
}
