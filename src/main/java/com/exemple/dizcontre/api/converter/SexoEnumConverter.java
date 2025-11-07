package com.exemple.dizcontre.api.converter;

import javax.persistence.AttributeConverter;

import com.exemple.dizcontre.api.enuns.SexoEnum;

public class SexoEnumConverter implements AttributeConverter<SexoEnum, String>{

	// Converte o Enum Java para o valor do DB
    @Override
    public String convertToDatabaseColumn(SexoEnum sexo) {
        if (sexo == null) {
            return null;
        }
        // Retorna o "M" ou "F" que você definiu no seu Enum
        return sexo.getLabel(); 
    }
    
 // Converte o valor do DB para o Enum Java
    @Override
    public SexoEnum convertToEntityAttribute(String label) {
        if (label == null || label.isEmpty()) {
            return null;
        }
        // Usa o método estático que criamos para buscar o Enum pelo 'label'
        return SexoEnum.fromLabel(label);
    }
}
