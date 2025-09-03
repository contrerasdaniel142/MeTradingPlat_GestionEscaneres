package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumLineaCruce implements IEnumValores{
    VWAP("lineaCruce.vwap"),
    EMA("lineaCruce.ema");
    
    private final String etiqueta;
}