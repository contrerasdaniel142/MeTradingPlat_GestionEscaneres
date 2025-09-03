package net.metradingplat.ms_escaner.domain.enums.valores;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTipoValorMedida implements IEnumValores {
    PRECIO("tipoValorMedida.precio"),
    PORCENTAJE("tipoValorMedida.porcentaje");

    private final String etiqueta;
}