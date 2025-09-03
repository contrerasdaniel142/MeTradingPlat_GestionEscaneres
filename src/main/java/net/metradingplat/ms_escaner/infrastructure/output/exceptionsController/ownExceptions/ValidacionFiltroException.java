package net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.ownExceptions;

import java.util.List;

import lombok.Getter;
import net.metradingplat.ms_escaner.domain.strategies.validacion.ResultadoValidacion;
import net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.exceptionStructure.CodigoError;

@Getter
public class ValidacionFiltroException extends BaseException {
    private final List<ResultadoValidacion> erroresValidacion;

    public ValidacionFiltroException(List<ResultadoValidacion> erroresValidacion) {
        super(CodigoError.VIOLACION_REGLA_DE_NEGOCIO, "");
        this.erroresValidacion = erroresValidacion;
    }
}