package net.metradingplat.ms_escaner.domain.states.escaner;

import net.metradingplat.ms_escaner.domain.states.GestorEstadoEscaner;

public interface IEstadoEscaner {
    ResultadoGestorEscaner iniciarEscaner(GestorEstadoEscaner gestorEstado);
    ResultadoGestorEscaner detenerEscaner(GestorEstadoEscaner gestorEstado);
    ResultadoGestorEscaner archivarEscaner(GestorEstadoEscaner gestorEstado);
    ResultadoGestorEscaner desarchivarEscaner(GestorEstadoEscaner gestorEstado);
}