package net.metradingplat.ms_escaner.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.metradingplat.ms_escaner.domain.enums.EnumCategoriaFiltro;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaFiltro {
    private String etiqueta;
    private EnumCategoriaFiltro enumCategoriaFiltro;
}
