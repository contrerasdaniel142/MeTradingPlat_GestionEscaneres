package net.metradingplat.ms_escaner.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoEscaner {
    private EnumEstadoEscaner enumEstadoEscaner = EnumEstadoEscaner.DETENIDO;
    private LocalDate fechaRegistro;
}
