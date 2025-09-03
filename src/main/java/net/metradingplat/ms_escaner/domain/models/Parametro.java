package net.metradingplat.ms_escaner.domain.models;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.domain.enums.EnumParametro;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parametro {
    private EnumParametro enumParametro;
    private String etiqueta;
    private Valor objValorSeleccionado;
    private List<Valor> opciones = new ArrayList<Valor>();
}
