package net.metradingplat.ms_escaner.domain.strategies.validacion;

import java.util.Optional;

import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.enums.valores.EnumCondicional;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorCondicional;

public class ValidacionCondicional implements IValidacionFiltro {

    private float min;
    private float max;

    public ValidacionCondicional(Float min, Float max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Optional<ResultadoValidacion> validar(EnumParametro enumParametro, Valor valor) {
        if (!(valor instanceof ValorCondicional vc)) {
            return resultado("filtro.parametro.tipoInvalido", enumParametro);
        }

        Float v1 = toFloat(vc.getValor1());
        EnumCondicional cond = vc.getEnumCondicional();

        if (cond == EnumCondicional.ENTRE || cond == EnumCondicional.FUERA) {
            if (vc.getValor2() == null) {
                return resultado("filtro.parametro.valorInvalido", enumParametro);
            }

            Float v2 = toFloat(vc.getValor2());

            if ((cond == EnumCondicional.ENTRE && v1 >= v2) ||
                (cond == EnumCondicional.FUERA && v1 <= v2)) {
                return resultado("filtro.parametro.rangoInvalido", enumParametro);
            }

            if (v1 < min || v2 > max) {
                return resultado("filtro.parametro.fueraDeRango", enumParametro);
            }

        } else {
            if (v1 < min || v1 > max) {
                return resultado("filtro.parametro.fueraDeRango", enumParametro);
            }
        }

        return Optional.empty();
    }

    private static float toFloat(Number n) {
        return switch (n) {
            case Integer i -> i.floatValue();
            case Float f   -> f;
            default       -> n.floatValue();
        };
    }

    private Optional<ResultadoValidacion> resultado(String mensaje, EnumParametro parametro) {
        return Optional.of(new ResultadoValidacion(mensaje, parametro));
    }
}