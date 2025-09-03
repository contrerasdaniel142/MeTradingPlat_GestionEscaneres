package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumDireccionRompimiento implements IEnumValores {
    ABOVE("direccionRompimiento.above"),
    BELOW("direccionRompimiento.below");

    private final String etiqueta;
}