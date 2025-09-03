package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumNivelCruce implements IEnumValores{
    OPEN("nivelCruce.open"),
    CLOSE("nivelCruce.close"),
    VWAP("nivelCruce.vwap"),
    EMA("nivelCruce.ema");
    
    private final String etiqueta;
}