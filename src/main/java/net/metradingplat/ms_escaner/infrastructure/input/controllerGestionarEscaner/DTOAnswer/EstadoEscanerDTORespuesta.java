package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumEstadoEscaner;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoEscanerDTORespuesta {
    private EnumEstadoEscaner enumEstadoEscaner;
    private LocalDate fechaRegistro;
}
