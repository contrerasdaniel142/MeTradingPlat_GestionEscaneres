package net.metradingplat.ms_escaner.domain.states;

import lombok.Getter;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;
import net.metradingplat.ms_escaner.domain.states.escaner.EstadoEscanerArchivado;
import net.metradingplat.ms_escaner.domain.states.escaner.EstadoEscanerDetenido;
import net.metradingplat.ms_escaner.domain.states.escaner.EstadoEscanerIniciado;
import net.metradingplat.ms_escaner.domain.states.escaner.IEstadoEscaner;
import net.metradingplat.ms_escaner.domain.states.escaner.ResultadoGestorEscaner;

@Getter
@Setter
public class GestorEstadoEscaner {
    private IEstadoEscaner estadoActual;

    public GestorEstadoEscaner(EnumEstadoEscaner estadoActualEnum) {
        this.estadoActual = this.establecerEstado(estadoActualEnum);
    }

    public IEstadoEscaner establecerEstado(EnumEstadoEscaner estadoEnum) {
        return switch (estadoEnum) {
            case ARCHIVADO -> new EstadoEscanerArchivado();
            case INICIADO -> new EstadoEscanerIniciado();
            case DETENIDO -> new EstadoEscanerDetenido();
            case DESARCHIVADO -> new EstadoEscanerDetenido();
        };
    }

    public ResultadoGestorEscaner iniciarEscaner() {
        return this.estadoActual.iniciarEscaner(this);
    }
    public ResultadoGestorEscaner detenerEscaner() {
        return this.estadoActual.detenerEscaner(this);
    }
    public ResultadoGestorEscaner archivarEscaner() {
        return this.estadoActual.archivarEscaner(this);
    }
    public ResultadoGestorEscaner desarchivarEscaner() {
        return this.estadoActual.desarchivarEscaner(this);
    }

    public ResultadoGestorEscaner cambiarEstado(EnumEstadoEscaner nuevoEstado) {
        return switch (nuevoEstado) {
            case INICIADO -> this.iniciarEscaner();
            case DETENIDO -> this.detenerEscaner();
            case ARCHIVADO -> this.archivarEscaner();
            case DESARCHIVADO -> this.desarchivarEscaner();
        };
    }
        
}
