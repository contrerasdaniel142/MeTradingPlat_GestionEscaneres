package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOPetition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumTipoEjecucion;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoEjecucionDTOPeticion {
    @NotNull(message = "tipoEjecucion.empty")
    private EnumTipoEjecucion enumTipoEjecucion;
}
