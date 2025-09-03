package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumLineaReferencia implements IEnumValores{
    VWAP("lineaReferencia.vwap"),
    MA("lineaReferencia.ma"),
    EMA("lineaReferencia.ema");
    
    private final String etiqueta;
}