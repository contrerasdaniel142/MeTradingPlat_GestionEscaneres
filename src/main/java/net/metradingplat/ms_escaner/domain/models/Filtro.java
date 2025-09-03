package net.metradingplat.ms_escaner.domain.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.metradingplat.ms_escaner.domain.enums.EnumFiltro;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filtro {
    private EnumFiltro enumFiltro;
    private String etiquetaNombre;
    private String etiquetaDescripcion;
    private CategoriaFiltro objCategoriaFiltro;
    private List<Parametro> parametros;
}