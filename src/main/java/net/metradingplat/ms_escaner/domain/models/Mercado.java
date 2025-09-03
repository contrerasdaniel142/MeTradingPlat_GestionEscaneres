package net.metradingplat.ms_escaner.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.metradingplat.ms_escaner.domain.enums.EnumMercado;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mercado {
    private String etiqueta;
    private EnumMercado enumMercado;
}
