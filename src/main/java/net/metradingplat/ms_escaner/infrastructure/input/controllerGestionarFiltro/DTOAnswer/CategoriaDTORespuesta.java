package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTORespuesta {
    private EnumCategoriaFiltro enumCategoriaFiltro;
    private String etiqueta;
}
