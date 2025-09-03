package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoEjecucion;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoEjecucionDTORespuesta {
    private String etiqueta;
    private EnumTipoEjecucion enumTipoEjecucion;
}
