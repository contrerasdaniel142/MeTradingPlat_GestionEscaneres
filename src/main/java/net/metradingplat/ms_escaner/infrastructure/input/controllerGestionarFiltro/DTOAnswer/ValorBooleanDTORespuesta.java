package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;

@Getter
@Setter
@NoArgsConstructor
public class ValorBooleanDTORespuesta extends ValorDTORespuesta{
    private Boolean valor;

    public ValorBooleanDTORespuesta(EnumTipoValor enumTipoValor, String etiqueta, Boolean valor){
        super(etiqueta, enumTipoValor);
        this.valor = valor;
    }
}
