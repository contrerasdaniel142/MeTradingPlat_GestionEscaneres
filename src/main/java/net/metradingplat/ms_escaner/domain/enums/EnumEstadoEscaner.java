package net.metradingplat.ms_escaner.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumEstadoEscaner {
    ARCHIVADO,
    INICIADO,
    DETENIDO,
    DESARCHIVADO;
}