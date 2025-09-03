package net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEscaner.DTOAnswer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.metradingplat.ms_escaner.infrastructure.input.controllerGestionarEntidadMercado.DTOAnswer.MercadoDTORespuesta;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EscanerDTORespuesta {
    private Long idEscaner;
    private String nombre;
    private String descripcion;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private LocalDate fechaCreacion;
    private List<MercadoDTORespuesta> mercados;
    private EstadoEscanerDTORespuesta objEstado;
    private TipoEjecucionDTORespuesta objTipoEjecucion;
}