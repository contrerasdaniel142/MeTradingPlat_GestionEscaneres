package net.metradingplat.ms_escaner.application.input;

import net.metradingplat.ms_escaner.domain.models.EstadoEscaner;

public interface GestionarEstadoEscanerCUIntPort {
    public EstadoEscaner iniciarEscaner(Long id);
    public EstadoEscaner detenerEscaner(Long id);
    public EstadoEscaner archivarEscaner(Long id);
    public EstadoEscaner desarchivarEscaner(Long id);
}
