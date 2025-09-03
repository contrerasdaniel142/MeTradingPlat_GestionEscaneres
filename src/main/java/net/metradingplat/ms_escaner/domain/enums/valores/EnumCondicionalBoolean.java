package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumCondicionalBoolean implements IEnumValores {
    VERDADERO("condicion.verdadero"),
    FALSO("condicion.falso");

    private final String etiqueta;
}