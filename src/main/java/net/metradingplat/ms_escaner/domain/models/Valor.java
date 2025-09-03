package net.metradingplat.ms_escaner.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valor {
    private String etiqueta;
    private EnumTipoValor enumTipoValor;
}
