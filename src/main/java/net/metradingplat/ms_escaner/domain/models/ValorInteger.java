package net.metradingplat.ms_escaner.domain.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoValor;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class ValorInteger extends Valor{
    private Integer valor;

    public ValorInteger(String etiqueta, EnumTipoValor enumTipoValor, Integer valor) {
        super(etiqueta, enumTipoValor);
        this.valor = valor;
    }

    public ValorInteger(){
        super();
    }
}
