package net.metradingplat.ms_escaner.domain.strategies.validacion;

import java.util.Optional;

import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorFloat;

public class ValidacionFloat implements IValidacionFiltro{
    private final Float min;
    private final Float max;

    public ValidacionFloat (Float min, Float max){
        this.min = min;
        this.max = max;
    }

    @Override
    public Optional<ResultadoValidacion> validar(EnumParametro enumParametro, Valor valor) {
        if (valor == null) {
            return resultado("filtro.parametro.requerido", enumParametro);
        }

        if (!(valor instanceof ValorFloat valorFloat)) {
            return resultado("filtro.parametro.tipoInvalido", enumParametro);
        }

        Float contenido = valorFloat.getValor();

        if (contenido == null) {
            return resultado("filtro.parametro.valorInvalido", enumParametro);
        }

        if (contenido < min || contenido > max) {
            return resultado("filtro.parametro.rangoInvalido", enumParametro);
        }

        return Optional.empty();
    }

    private Optional<ResultadoValidacion> resultado(String mensaje, EnumParametro parametro) {
        return Optional.of(new ResultadoValidacion(mensaje, parametro));
    }
}
