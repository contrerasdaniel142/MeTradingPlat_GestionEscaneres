package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.DTOAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumMercado;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MercadoDTORespuesta {
    private String etiqueta;
    private EnumMercado enumMercado;
}
