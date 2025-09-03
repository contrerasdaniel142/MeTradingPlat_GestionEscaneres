package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOPetition;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDtoPeticion {
    @NotNull(message = "filtro.enumFiltro.empty")
    private EnumFiltro enumFiltro;

    @NotNull(message = "filtro.parametros.empty")
    @Valid
    private List<ParametroDTOPeticion> parametros;
}
