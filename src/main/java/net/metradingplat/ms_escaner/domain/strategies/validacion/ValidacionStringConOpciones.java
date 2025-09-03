package net.metradingplat.ms_escaner.domain.strategies.validacion;

import net.metradingplat.ms_escaner.domain.enums.EnumParametro;
import net.metradingplat.ms_escaner.domain.enums.valores.IEnumValores;
import net.metradingplat.ms_escaner.domain.models.Valor;
import net.metradingplat.ms_escaner.domain.models.ValorString;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ValidacionStringConOpciones<E extends Enum<E> & IEnumValores> implements IValidacionFiltro {

    private final List<E> opcionesPermitidas;

    public ValidacionStringConOpciones(List<E> opcionesPermitidas) {
        this.opcionesPermitidas = opcionesPermitidas;
    }

    @Override
    public Optional<ResultadoValidacion> validar(EnumParametro enumParametro, Valor valor) {
        if (valor == null || !(valor instanceof ValorString)) {
            return Optional.of(new ResultadoValidacion("validacion.error.formatoInvalido", enumParametro));
        }

        String valorString = ((ValorString) valor).getValor();

        boolean esValido = opcionesPermitidas.stream()
                .anyMatch(opcion -> opcion.name().equalsIgnoreCase(valorString));

        if (!esValido) {
            String opcionesValidas = opcionesPermitidas.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            return Optional.of(new ResultadoValidacion("validacion.error.valorNoPermitido: " + opcionesValidas, enumParametro));
        }

        return Optional.empty();
    }
}