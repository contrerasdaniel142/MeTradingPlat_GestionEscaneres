package net.metradingplat.ms_escaner.application.output;

import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;
import net.metradingplat.ms_escaner.domain.models.Escaner;
import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;

public interface GestionarEstadoEscanerGatewayIntPort {
    public EnumEstadoEscaner obtenerEstadoDeEscanerActual(Long id);
    public EstadoEscaner cambiarEstadoDeEscaner(Escaner escaner, EnumEstadoEscaner nuevoEstadoEnum);
}