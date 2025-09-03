package net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.ownExceptions;

import net.metradingplat.ms_escaner.infrastructure.output.exceptionsController.exceptionStructure.CodigoError;

public class EntidadNoExisteException extends BaseException {
    public EntidadNoExisteException(String llaveMensaje, Object... args) {
        super(CodigoError.ENTIDAD_NO_ENCONTRADA, llaveMensaje, args);
    }
    
}
