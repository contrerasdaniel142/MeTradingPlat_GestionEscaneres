package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumCondicionFirstCandle implements IEnumValores {
    ALCISTA("firstCandle.alcista"),
    BAJISTA("firstCandle.bajista");

    private final String etiqueta;
}
