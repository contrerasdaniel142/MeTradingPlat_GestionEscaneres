package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValorCondicionalDTORespuesta extends ValorDTORespuesta {
    private EnumCondicional enumCondicional;
    private Number valor1;
    private Number valor2;

    public ValorCondicionalDTORespuesta(EnumTipoValor enumTipoValor, String etiqueta, EnumCondicional enumCondicional, Number valor1, Number valor2){
        super(etiqueta, enumTipoValor);

    }
}
