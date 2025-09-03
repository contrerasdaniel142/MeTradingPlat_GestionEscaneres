package net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.ownExceptions;

import net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.exceptionStructure.CodigoError;

public class ReglaNegocioException extends BaseException {
    public ReglaNegocioException(String llaveMensaje, Object... args) {
        super(CodigoError.VIOLACION_REGLA_DE_NEGOCIO, llaveMensaje, args);
    }
}
