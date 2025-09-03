package net.metradingplat.ms_escaner.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoEjecucion;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoEjecucion {
    private String etiqueta;
    private EnumTipoEjecucion enumTipoEjecucion;
}
