package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOPetition;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTOPeticion {
    @NotNull(message = "categoria.enumCategoriaFiltro.empty")
    private EnumCategoriaFiltro enumCategoriaFiltro;
}