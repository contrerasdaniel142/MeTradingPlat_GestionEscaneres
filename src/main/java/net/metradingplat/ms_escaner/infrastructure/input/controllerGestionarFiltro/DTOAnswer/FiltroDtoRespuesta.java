package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarFiltro.DTOAnswer;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FiltroDtoRespuesta {
    private EnumFiltro enumFiltro;
    private String etiquetaNombre;
    private String etiquetaDescripcion;
    private CategoriaDTORespuesta objCategoria;
    private List<ParametroDTORespuesta> parametros;
}
