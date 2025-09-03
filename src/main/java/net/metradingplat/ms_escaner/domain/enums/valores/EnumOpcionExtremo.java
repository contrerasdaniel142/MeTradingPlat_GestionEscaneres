package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumOpcionExtremo implements IEnumValores {
    HIGH("opcionExtremo.high"),
    LOW("opcionExtremo.low");

    private final String etiqueta;
}