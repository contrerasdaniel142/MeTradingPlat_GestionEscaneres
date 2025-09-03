package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTipoRol implements IEnumValores {
    RESISTENCIA("tipoRol.resistencia"),
    SOPORTE("tipoRol.soporte");

    private final String etiqueta;
}