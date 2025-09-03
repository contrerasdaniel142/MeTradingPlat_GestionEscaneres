package net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.ownExceptions;

import net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.exceptionStructure.CodigoError;

public class EntidadYaExisteException extends BaseException {
    public EntidadYaExisteException(String llaveMensaje, Object... args) {
        super(CodigoError.ENTIDAD_YA_EXISTE, llaveMensaje, args);
    }
}
