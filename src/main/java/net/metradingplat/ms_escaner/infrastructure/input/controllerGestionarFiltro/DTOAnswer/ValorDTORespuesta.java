package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorDTORespuesta {
    private String etiqueta;
    private EnumTipoValor enumTipoValor;
}
